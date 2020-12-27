
import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class testingclasses {

	public testingclasses() {
		// TODO Auto-generated constructor stub
	}
public static void main(String[] args) throws IOException{
	/*commandDispenser d= new commandDispenser();
	d.getCmd().setCmd("cmd.exe. /c start dir");
	
	try {
		Process p = Runtime.getRuntime().exec("cmd.exe /c start");
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));		
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
        out.write("dir");
		out.flush();
		System.out.println(stdInput.read());
		d.getCmd().execute();
		System.out.println(d.getCmd().output());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	*/
	/*BufferedImage img;
	try {
		 img = ImageIO.read(new File("src.jpg"));
	} catch (IOException e) {
		e.printStackTrace();
	}
	String a="hello";
	System.out.println(a);
	byte[] b=a.getBytes();
	System.out.println(b);
	System.out.println(Arrays.toString(b));
	System.out.println(new String(b));
	*/
	/*ProcessBuilder pb = new ProcessBuilder("cmd.exe","/c");
	Process proc = pb.start();
	PrintWriter out = new PrintWriter(new OutputStreamWriter(proc.getOutputStream()));
	BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));

	
	// feed in the program
	out.println("ping google.com");
	out.flush();
	
	String resultLine = in.readLine();

	while (resultLine != null) {

	  System.out.println(resultLine);
	  resultLine = in.readLine();
	}

	proc.destroy();*/
    new Thread(){
    	public void run(){
    		while(true){
    			System.out.println("thread 1");
    		}
    	}
    }.start();

    new Thread(){
    	public void run(){
    		while(true){
    			System.out.println("thread 2");
    		}
    	}
    }.start();
}
}
