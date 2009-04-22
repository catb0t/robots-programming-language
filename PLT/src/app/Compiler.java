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
			//String[] command = {"parser", inputFile, "MyRobot.java"};
			//String[] command = {"java", "-jar", "JointEM.jar"};
			//String[] command = {"/Applications/Preview.app/Contents/MacOS/Preview", "/Users/aurelien/Pictures/chou.jpg"};
			//Runtime.getRuntime().exec(command);
			
			//String[] command = {"java", "-jar", "JointEM.jar"};
			//String[] command javac -cp "/Users/aurelien/workspace/PLT/src/:/Users/aurelien/workspace/PLT/lib/jogl.jar:/Users/aurelien/workspace/PLT/lib/gluegen-rt.jar" /Users/aurelien/workspace/PLT/RobotCompiled.java
			
			System.out.println(System.getProperty("user.dir"));
			String dir = System.getProperty("user.dir");
			
			//String[] command = {"java", "-jar", dir+"/robot_parser.jar", dir+"/"+inputFile, dir+"/RobotCompiled.java"};
			String command = "java -jar "+dir+"/robot_parser.jar "+dir+"/"+inputFile+" "+dir+"/RobotCompiled.java";
			System.out.println(command);
			Process p = Runtime.getRuntime().exec(command);
			
			//p.getOutputStream();
			
			
			int exitCode = p.waitFor();
			
			
			//reloadClass("/Users/aurelien/workspace/PLT");
			
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
            
            //player.think();
            
            parent.setPlayer(player);
            
        } catch (Exception e) {
        	//System.out.println(e.getMessage());
        	e.printStackTrace();
        }
    }
}
