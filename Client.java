import java.net.Socket;
import java.net.InetSocketAddress;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class Client{
	public static void main(String args[])throws Exception{
		boolean done = false;
		String serverIP = "localhost";
		int port = 7117;
		InetSocketAddress socketAddress = 
			new InetSocketAddress(serverIP,port);
		Socket socket = new Socket();
		socket.connect(socketAddress);
		InputStream is = socket.getInputStream();
		OutputStream os = socket.getOutputStream();
		JFrame mainFrame = new JFrame();
		mainFrame.setSize(800,600);
		mainFrame.setLayout(new BorderLayout());
		JPanel imagePanel = new JPanel(){
			@Override
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				try{
					g.drawImage(ImageIO.read(is),0,0,this);
				}catch(Exception e){}
			}
		};
		imagePanel.setSize(800,600);
		mainFrame.add(imagePanel);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		while(!done){
			//Thread.sleep(1000);
			imagePanel.repaint();
		}
		socket.close();
	}
}
