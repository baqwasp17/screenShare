import java.net.Socket;
import java.net.InetSocketAddress;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.Date;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class Client{
	public static void main(String args[])throws Exception{
		boolean done = false;
		String serverIP = "localhost";
		int port = 7117;
		Socket socket;
		JFrame mainFrame = new JFrame();
		mainFrame.setSize(800,600);
		mainFrame.setLayout(new BorderLayout());
		ImagePanel imagePanel = new ImagePanel();
		imagePanel.setSize(800,600);
		mainFrame.add(imagePanel);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try{
			while(!done){
				socket = new Socket(serverIP,port);
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				BufferedImage image = ImageIO.read(ois);
				imagePanel.setImage(image);
				imagePanel.repaint();
				socket.close();
			}
		}catch(IOException e){}
	}
}
