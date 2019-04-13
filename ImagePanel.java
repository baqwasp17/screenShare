import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel{
	private BufferedImage image;
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		if(image!=null)
			g.drawImage(image, 0,0,null);
	}
	
	public BufferedImage getImage(){
		return image;
	}
	
	public void setImage(BufferedImage image){
		this.image = image;
	}
}
