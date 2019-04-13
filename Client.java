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
		boolean done = false;//I'm never done.
		/*
		* Change the below line of code to match the IP 
		* of the machine on which your server is running.
		
		* NOTE: if the client and server are running on the same
		*  machine then you need not change the line bellow
		*/
		String serverIP = "localhost";
		// The port number can be any positive number from 1025 to 65535
		int port = 7117;
		/*
		* InetSocketAddress is nothing but a socket 
		* i.e. IP + port commonly writen as 'IP:port'
		* example 192.168.0.1:8080 
		* above is a socket on machine with IP 192.168.0.1
		* and port number 8080
		*/
		InetSocketAddress socketAddress = 
			new InetSocketAddress(serverIP,port);
		/*
		* Below line is creating a socket which is not
		* yet associated to any port and IP
		*/
		Socket socket = new Socket();
		/*
		* Lines(47-49)
		* Associating the Socket to previosly created Socket address
		* and then getting the input and output streams from the socket
		*/
		socket.connect(socketAddress);
		InputStream is = socket.getInputStream();
		OutputStream os = socket.getOutputStream();
		/*
		* Lines(56-59)
		* Creating a JFrame to display the screenshot sent by server
		* setting size and layout of the frame
		* creating the panel that will contain the image being displayed
		*/
		JFrame mainFrame = new JFrame();
		mainFrame.setSize(800,600);
		mainFrame.setLayout(new BorderLayout());
		BufferedImage sc = ImageIO.read(is);
		JPanel imagePanel = new JPanel(){
			/*
			* Overriding the patinComponent of imagePanel JPanel
			* in an annonymous(don't mind the spelling) inner class
			* to draw screenshot from socket stream 'is'
			* rest is obvious(don't mind the spelling)
			*/
			@Override
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				try{
					g.drawImage(sc,0,0,this);
				}catch(Exception e){}
			}
		};
		imagePanel.setSize(800,600);
		mainFrame.add(imagePanel);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		socket.close();
	}
}
