import java.net.ServerSocket;
import java.net.Socket;
import java.awt.Robot;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class Server{
	public static void main(String args[])throws Exception{
		int port = 7117;
		boolean done = false;
		Rectangle sharedScreen = 
			new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		Robot robot = new Robot();
		ServerSocket serverSocket = new ServerSocket(port);
		while(!done){
			Socket socket = serverSocket.accept();
			BufferedImage screenShot = robot.createScreenCapture(sharedScreen);
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			ImageIO.write(screenShot, "JPG", oos);
			socket.close();
		}
	}

}
