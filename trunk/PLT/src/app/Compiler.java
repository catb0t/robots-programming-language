package app;

//import RobotReloadableClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;

public class Compiler {
	
	private String inputFile;
	private Main parent;
	
	public Compiler() {
		inputFile = null;
		parent = null;
	}
	
	public Compiler(Main p) {
		inputFile = null;
		parent = p;
	}
	
	public Compiler (String s)
	{
		inputFile = s;
	}
	
	public void setFileName (String s) {
		inputFile = s;
	}
	
	public void run () {
		System.out.println(System.getProperty("user.dir"));
		String dir = System.getProperty("user.dir");
		
		try
		{            
			Runtime rt = Runtime.getRuntime();
			String command = "java -jar "+dir+"/robot_parser.jar "+dir+"/tempcode.robot "+dir+"/RobotCompiled.java"+" &> /dev/null";
			Process proc = rt.exec(command);
			InputStream stderr = proc.getErrorStream();
			InputStreamReader isr = new InputStreamReader(stderr);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			System.out.println("<ERROR>");
			while ( (line = br.readLine()) != null)
				System.out.println(line);
			System.out.println("</ERROR>");
			int exitVal = proc.waitFor();
			System.out.println("Process exitValue: " + exitVal);

			System.out.println("Java file created");

		} catch (Throwable t)
		{
			t.printStackTrace();
		}
		
		try
		{            
			Runtime rt = Runtime.getRuntime();
			String command = "javac -cp "+dir+"/src/:"+dir+"/lib/jogl.jar:"+dir+"/lib/gluegen-rt.jar "+dir+"/RobotCompiled.java";
			Process proc = rt.exec(command);
			InputStream stderr = proc.getErrorStream();
			InputStreamReader isr = new InputStreamReader(stderr);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			System.out.println("<ERROR>");
			while ( (line = br.readLine()) != null)
				System.out.println(line);
			System.out.println("</ERROR>");
			int exitVal = proc.waitFor();
			System.out.println("Process exitValue: " + exitVal);

			System.out.println("Java class created");

		} catch (Throwable t)
		{
			t.printStackTrace();
		}
        
		try {
			reloadClass(dir);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
	
	
	public void reloadClass(String str) throws IOException {

    	URL[] urls = null;
        try {
            // Convert the file object to a URL
            //File dir = new File(/*System.getProperty("user.dir")*/str + File.separator + "test" + File.separator);
            File dir = new File(/*System.getProperty("user.dir")*/str + File.separator);
            URL url = dir.toURI().toURL();
            urls = new URL[]{url};
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
        
        try {
            // Create a new class loader with the directory
        	System.out.println("loading class");
            ClassLoader cl = new URLClassLoader(urls);
        
            // Load in the class
            Class cls = cl.loadClass("RobotCompiled");
        
            // Create a new instance of the new class
            RobotInterface player = (RobotInterface)cls.newInstance();
            
            parent.setPlayer(player);
            
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
}
