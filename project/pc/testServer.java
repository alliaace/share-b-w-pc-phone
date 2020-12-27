
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import org.w3c.dom.Document;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.nio.channels.ServerSocketChannel;
import java.io.*;
import java.util.*;
public class testServer extends JFrame {
	private JPanel contentPane;
	private JTextField input;
	 JButton btnsend,btnClose ;
		private JTextPane out;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Throwable {
			testServer frame = new testServer();
					frame.setVisible(true);
					ServerSocket  s;
					String strin="",strout="";
			
					
						Socket socket=new Socket(InetAddress.getByName("0.0.0.0") , 10);	
						frame.btnsend.addActionListener(frame.new send(socket));
						frame.btnClose.addActionListener(frame.new closecon(socket));
						frame.out.getStyledDocument().insertString(frame.out.getStyledDocument().getLength(), String.format("connected\n"), null);
						DataInputStream sin=new DataInputStream( socket.getInputStream());  
						DataOutputStream sout=new DataOutputStream(socket.getOutputStream());  
						BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
						byte[] b;
						while(!strin.equals("quit")){
							b=new byte[sin.readInt()];
							 sin.readFully(b,0,b.length);
							strin=new String(b);
							System.out.println(strin);
							frame.out.getStyledDocument().insertString(frame.out.getStyledDocument().getLength(), String.format("response : %s\n",strin), null);

						}
						System.out.println("end");
						sin.close();  
						socket.close();  
					
	}

	/*
	 * Create the frame.
	 */
	public testServer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel top = new JPanel();
		GridBagConstraints gbc_top = new GridBagConstraints();
		gbc_top.gridheight = 7;
		gbc_top.insets = new Insets(0, 0, 5, 0);
		gbc_top.fill = GridBagConstraints.BOTH;
		gbc_top.gridx = 0;
		gbc_top.gridy = 0;
		contentPane.add(top, gbc_top);
		top.setLayout(new BorderLayout(0, 0));
		
		out = new JTextPane();
		top.add(new JScrollPane(out), BorderLayout.CENTER);
		
		JPanel bottom = new JPanel();
		GridBagConstraints gbc_bottom = new GridBagConstraints();
		gbc_bottom.fill = GridBagConstraints.BOTH;
		gbc_bottom.gridx = 0;
		gbc_bottom.gridy = 7;
		contentPane.add(bottom, gbc_bottom);
		bottom.setLayout(new GridLayout(0, 2, 0, 0));
		
		input = new JTextField();
		bottom.add(input);
		input.setColumns(10);
		
		JPanel panel = new JPanel();
		bottom.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		btnsend= new JButton("send");
		panel.add(btnsend);
		 
		btnClose = new JButton("close");
		 panel.add(btnClose);
		 
		
	}
	
	 class closecon implements ActionListener{
		Socket  socket;
		closecon(Socket  socket ){
			this.socket=socket;
		}
		public void actionPerformed(ActionEvent a) {
			//System.out.println("good"+a);
			
			
			try {
				socket.close();
				out.getStyledDocument().insertString(out.getStyledDocument().getLength(), String.format("signed out\n"), null);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}
	 class send implements ActionListener{

		Socket  socket;
		send(Socket  socket ){
			this.socket=socket;
		}
		public void actionPerformed(ActionEvent a) {
			try {
				out.getStyledDocument().insertString(out.getStyledDocument().getLength(), String.format("you : %s\n",input.getText()), null);
				DataOutputStream sout=new DataOutputStream(socket.getOutputStream());  
				byte[] b = input.getText().getBytes();
				sout.writeInt(b.length);
				sout.write(b);
				sout.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	 
/*void socket(JButton snd,JButton cls){
	ServerSocket  socket;
	try{
		socket= new ServerSocket(11111);
		socket.accept();

		System.out.println("good");
		snd.addActionListener(new send(socket));
		cls.addActionListener(new closecon(socket));
	}catch(Throwable e){
		e.printStackTrace();
	}
}*/
}
