package app;

//import RobotReloadableClass;

import java.io.File;
import java.io.IOException;
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
		try {
			
			System.out.println(System.getProperty("user.dir"));
			String dir = System.getProperty("user.dir");
			
			/*
			String[] command = {"java", "-jar", dir+"/robot_parser.jar", dir+"/"+inputFile, dir+"/RobotCompiled.java"};
			Process p1 = Runtime.getRuntime().exec(command);
			
			System.out.println("Java file created");
			
			//p1.getOutputStream();
			
			
			int exitCode1 = p1.waitFor();
			*/
			
			String[] command2 = {"javac", "-cp", dir+"/bin/:"+dir+"/lib/jogl.jar:"+dir+"/lib/gluegen-rt.jar", dir+"/RobotCompiled.java"};
			
			Process p2 = Runtime.getRuntime().exec(command2);
			int exitCode2 = p2.waitFor();
		
			
			System.out.println("Java class created");
			
			reloadClass(dir);
			
		} catch (Exception e) {
			e.printStackTrace();
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
