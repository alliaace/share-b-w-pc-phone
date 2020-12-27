import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class commandDispenser {
	private sysCmd cmd; private String inputmsg;
	private Runtime rt;
	public commandDispenser() {
		cmd = new sysCmd();
		rt=Runtime.getRuntime();
	}
	public commandDispenser(String inputmsg) {
		this();
		this.inputmsg=inputmsg;
	}
public String getInputmsg() {
		return inputmsg;
	}
	public void setInputmsg(String inputmsg) {
		this.inputmsg = inputmsg;
	}
public sysCmd getCmd() {
		return cmd;
	}
	public void setCmd(sysCmd cmd) {
		this.cmd = cmd;
	}

public class sysCmd{
	private String cmd;private Process  process;
	sysCmd(String cmd){
		this.cmd=cmd;
	}
	sysCmd(){
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public void execute() throws Exception{
		if(cmd!=null){
		  process = rt.exec("cmd.exe /c "+cmd);
		}else{
			throw new Exception("no cmd to execute");
		}
	}
		public ArrayList<String> output() throws IOException{
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader errInput = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			ArrayList<  String> s = new  ArrayList<  String>() ;
			 String str=null;
			 while ((str =stdInput.readLine()) != null) {
				 s.add(str);
			   }
			 while ((str =errInput.readLine()) != null) {
				 s.add(str);
			   }
			return s;
		}
}
}
