import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class message {
	private String msg, tag,body;
	public message() {
	
	}
	public message(String msg) {
		this();
		this.msg=msg;
	}
	void breakmsg(String msg){
	String[] cut= msg.split("-",2);
	tag=cut[0]; body=cut[1];
	}
	Object analyze(){
		breakmsg(msg);
		return null;
	}
public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	static byte[] sendImg () throws Exception {
		
			BufferedImage ss= img.takess();
			ByteArrayOutputStream os=new ByteArrayOutputStream();
			ImageIO.write(ss, "jpg", os);
			byte[] imgarr=os.toByteArray();
			return imgarr;
		
	}
public static void main(String[] args){
	try {
		BufferedImage ss= img.takess();
		ByteArrayOutputStream os=new ByteArrayOutputStream();
		ImageIO.write(ss, "jpg", os);
		byte[] imgarr=os.toByteArray();
		
		System.out.println(imgarr);
		//new comunicator().send(imgarr, imgarr.length, s);
	} catch (Exception e) {
		e.printStackTrace();
	} 
}
}

