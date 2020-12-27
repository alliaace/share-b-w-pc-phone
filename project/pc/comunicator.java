
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class comunicator {
	commandDispenser cmdCatch;
	DataOutputStream sout;
	DataInputStream sin ;
	public comunicator() {
		cmdCatch = new commandDispenser();
	}
/*public static void main(String[] args){
	 comunicator c= new comunicator();
	 String str;
	try {
		Socket s =c.connection(10);
		DataOutputStream out=c.send("hello", s);
		while(!((str =c.recieve(s)).equals("quit"))){
		System.out.println(str);
		}
		out.writeUTF("close");
		System.out.println("closed");
		c.close(s,out);
	} catch (Throwable e) {
		e.printStackTrace();
	}
}*/
Socket connection(int port) throws Throwable{
	ServerSocket  s= new ServerSocket(port);
	Socket socket = s.accept();
	sout=new DataOutputStream(socket.getOutputStream());  
	 sin=new DataInputStream( socket.getInputStream());  
	System.out.println("connected");
	return socket;
}
DataOutputStream send(byte[] msg,int len, Socket s) throws Exception{
		sout.writeInt(len);
		sout.write(msg);
		sout.flush();
	return sout;
}
DataOutputStream send(ArrayList<String> msg, Socket s) throws Exception{
	DataOutputStream stream=null;
	byte[] b;
	for(String x:msg){
		b=x.getBytes();
		stream=send(b,b.length,s);
	}
	return stream;
}
byte[] recieve(Socket s) throws Exception{
	int l=sin.readInt();
	//ArrayList<String>  strin=new ArrayList<String> ();
	byte [] b = new byte[l];
	sin.readFully(b,0,b.length);
		//strin.add(str);
	return b;
}
void close(Socket s) throws IOException{
	s.close();
	sout.close();
}
public DataOutputStream getSout() {
	return sout;
}

}
