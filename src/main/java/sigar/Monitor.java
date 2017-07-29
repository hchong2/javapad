package sigar;

import java.io.File;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileSystemUtils;
import org.hyperic.sigar.ProcCpu;
import org.hyperic.sigar.Uptime;

public class Monitor {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Uptime u = new Uptime();
		System.out.p(u.getUptime());
		System.out.p(u.toString());
		
		ProcCpu cpu = new ProcCpu();
		System.out.p(cpu.getLastTime());
		System.out.p(cpu.toString());
		
		FileSystemUtils fsUtils = new FileSystemUtils();
		
		Process process = null;
		File outFile = null;
		File errFile = null;
		String command = "ls";
		try{
			ProcessBuilder pb = new ProcessBuilder(command);
			
			outFile = File.createTempFile("processOut", null);
			errFile = File.createTempFile("processErr", null);
			
			pb.redirectOutput(outFile);
			pb.redirectError(errFile);
			
			process = pb.start();
			
			OutputStream stdinOut = process.getOutputStream();
			
			String str = "";
			stdinOut.write(str.getBytes(StandardCharsets.UTF_8));
			
			stdinOut.close();
			
			System.out.println(str);
		}catch(Exception e){
			
		}
		
	}

}
