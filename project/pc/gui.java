import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.net.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.BadLocationException;

public class gui extends JFrame {
	JTextPane pane;
	public gui() {
		super ("Dispenser")	;
		pane=new JTextPane();
}
public static void main(String[] args){
	JPanel[] panel = {new JPanel(),new JPanel(),new JPanel()};
	Button[] button={new Button()};
	JLabel[] label = {new JLabel()};
	GridBagConstraints c= new GridBagConstraints();
	GridBagLayout lay = new GridBagLayout();
	c.fill= GridBagConstraints.VERTICAL;
	c.weightx=1.0;
	gui frame = new gui();
	frame.setLayout(lay);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	c.gridx=0;
	c.gridy=0;
	lay.setConstraints(panel[0], c);
	c.gridy=1;
	c.insets = new Insets(10, 10, 10, 10);
	c.weighty=1.0;
	lay.setConstraints(panel[1], c);
	frame.add(panel[0]);
	frame.add(panel[1]);
	panel[0].setLayout(new GridLayout(0, 2));
	label[0].setText("Status: disabled");
	label[0].setAlignmentX(LEFT_ALIGNMENT);
	label[0].setPreferredSize(new Dimension(300, 0));
	panel[0].add(label[0]);
	button[0].setLabel("Clear log");
	button[0].addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			frame.pane.setText("");
		}
	});
	panel[0].add(button[0]);
	frame.pane.setEditable(false);
	frame.pane.setBackground(Color.BLACK);
	frame.pane.setForeground(Color.WHITE);
	frame.pane.setPreferredSize(new Dimension(700, 270));
	panel[1].add(new JScrollPane(frame.pane));
	frame.pack();
	/*--------------------------------------------------------*/
	String str;
	byte[] img;
	 comunicator cmn= new comunicator();
	 commandDispenser cd= new commandDispenser();
	 ArrayList<String> cmdOut = new ArrayList<String> ();
	 while(true){
	 try {
			Socket s =cmn.connection(10);
			label[0].setText("Status: Conected");
			DataOutputStream out=cmn.getSout();
			while(!((str =new String(cmn.recieve(s))).equals("quit"))){
				frame.appendPane("command recieved: "+str);
				if(str.equals("img")){
					 img=message.sendImg();
					cmn.send(img , img.length,s);
				}else{
				cd.getCmd().setCmd(str);
				cd.getCmd().execute();
				cmdOut = cd.getCmd().output();
				
				for(String x:cmdOut){
				frame.appendPane(x);
				cmn.send(x.getBytes(),x.getBytes().length, s);
				}
				}
				cmn.send("execution Finished".getBytes(),"execution Finished".getBytes().length, s);
				frame.appendPane("Execution finished");
			}
			out.writeUTF("closed");
			cmn.close(s);
		} catch (Throwable e) {
			e.printStackTrace();
			label[0].setText("Status: disabled");
		}
	 }
}
static void initGui(){
	JPanel[] panel = {new JPanel(),new JPanel(),new JPanel()};
	Button[] button={new Button()};
	JLabel[] label = {new JLabel()};
	GridBagConstraints c= new GridBagConstraints();
	GridBagLayout lay = new GridBagLayout();
	c.fill= GridBagConstraints.VERTICAL;
	c.weightx=1.0;
	gui frame = new gui();
	frame.setLayout(lay);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	c.gridx=0;
	c.gridy=0;
	lay.setConstraints(panel[0], c);
	c.gridy=1;
	c.insets = new Insets(10, 10, 10, 10);
	c.weighty=1.0;
	lay.setConstraints(panel[1], c);
	frame.add(panel[0]);
	frame.add(panel[1]);
	panel[0].setLayout(new GridLayout(0, 2));
	label[0].setText("Status:");
	label[0].setAlignmentX(LEFT_ALIGNMENT);
	label[0].setPreferredSize(new Dimension(300, 0));
	panel[0].add(label[0]);
	button[0].setLabel("Clear log");
	panel[0].add(button[0]);
	frame.pane.setEditable(false);
	frame.pane.setBackground(Color.BLACK);
	frame.pane.setForeground(Color.WHITE);
	frame.pane.setPreferredSize(new Dimension(700, 270));
	  panel[1].add(frame.pane);
	frame.pack();
}
void appendPane(String str) throws Exception{
	pane.getStyledDocument().insertString(pane.getStyledDocument().getLength(), String.format("%s\n", str),null);
	pane.setCaretPosition(pane.getDocument().getLength());
}
}
