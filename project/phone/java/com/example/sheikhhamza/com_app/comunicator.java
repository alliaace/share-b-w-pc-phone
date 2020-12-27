package com.example.sheikhhamza.com_app;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class comunicator extends Service {
private final IBinder binder = new binder();
     public class binder extends Binder{
    comunicator getService(){
        return comunicator.this;
    }

}
@Override
public IBinder onBind(Intent i){
        return binder;
}
    public comunicator() {

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
    Socket connection(String add,int port) throws Exception{
        Socket socket=new Socket(InetAddress.getByName(add) , port);
        System.out.println("connected");
        return socket;
    }
    DataOutputStream send(byte[] msg,int len, Socket s) throws Exception{
        DataOutputStream sout=new DataOutputStream(s.getOutputStream());
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
        DataInputStream sin=new DataInputStream( s.getInputStream());
        int l=sin.readInt();
        //ArrayList<String>  strin=new ArrayList<String> ();
        byte [] b = new byte[l];
        sin.readFully(b,0,b.length);
        //strin.add(str);
        return b;
    }
    void close(Socket s,DataOutputStream stream) throws Exception{
        s.close();
        stream.close();
    }
}
