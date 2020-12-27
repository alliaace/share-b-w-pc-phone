import java.awt.AWTException;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class img  extends message {
private BufferedImage img;
	public img() {
		// TODO Auto-generated constructor stub
	}
	public img(BufferedImage img) {
		this.img=img;
	}
	public img(String path) throws Exception{
	img= 	ImageIO.read(new File(path));
	}
	public BufferedImage getImg() {
		return img;
	}
	public void setImg(BufferedImage img) {
		this.img = img;
	}
static BufferedImage takess() throws Exception{
	return new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
}
/*public static void main(String[] args){
	JFrame frame = new JFrame();
	frame.getContentPane().setLayout(new FlowLayout());
	try {
		frame.getContentPane().add(new JLabel(new ImageIcon(takess())));
	} catch (HeadlessException e) {
		e.printStackTrace();
	} catch (AWTException e) {
		e.printStackTrace();
	}
	frame.pack();
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}*/
}
