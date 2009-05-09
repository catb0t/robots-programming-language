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
	
	public int run () {

		System.out.println(System.getProperty("user.dir"));
		String dir = System.getProperty("user.dir");

                int exitVal = -99;
		
		File javaFile = new File("./RobotCompiled.java");
		File classFile = new File("./RobotCompiled.class");
		
		if (javaFile.exists())
		{
			javaFile.delete();
		}
		if (classFile.exists())
		{
			classFile.delete();
		}
		
		
		try
		{            
			Runtime rt = Runtime.getRuntime();
			String command = "java -jar ./robot_parser.jar ./tempcode.robot ./RobotCompiled.java";
			System.out.println(command);
                        Process proc = rt.exec(command);
			
                        BufferedReader br = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
			String line = null;
			while ( (line = br.readLine()) != null)
				Global.WriteLineToOutput(line);
			
                        exitVal = proc.waitFor();
			Global.WriteLineToOutput("Translation exit code: " + exitVal);

                        if (exitVal == 0) 
			   Global.WriteLineToOutput("SUCCESS in translating from ROBOT to Java");
                        else
                           Global.WriteLineToOutput("ERROR in translating from ROBOT to Java");


		} catch (Throwable t)
		{
			t.printStackTrace();
			Global.WriteLineToOutput(t.getMessage());
                        return 1;
		}
		
                if (exitVal != 0) return 1;

		try
		{            
			Runtime rt = Runtime.getRuntime();
			String command = "javac -cp .:../lib/jogl.jar:../lib/gluegen-rt.jar ./RobotCompiled.java";
			System.out.println(command);
			Process proc = rt.exec(command);
			
                        BufferedReader br = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
			String line = null;
			while ( (line = br.readLine()) != null)
				Global.WriteLineToOutput(line);
			
                        exitVal = proc.waitFor();
                        Global.WriteLineToOutput("Compilation exit code: " + exitVal);

			if (exitVal == 0)
                           Global.WriteLineToOutput("SUCCESS compiled generated Java code into a Java class");
                        else
                           Global.WriteLineToOutput("ERROR in compiling generated Java code into a Java class");


		} catch (Throwable t)
		{
			t.printStackTrace();
                        Global.WriteLineToOutput(t.getMessage());
                        return 2;
		}

                if (exitVal != 0) return 2;
        
		try {
			reloadClass(dir);
		} catch (IOException ex) {
			ex.printStackTrace();
			Global.WriteLineToOutput(ex.getMessage());
                        return 3;
		}

                return 0;
		
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
        	Global.WriteLineToOutput(e.getMessage());
                return;
        }
        
        try {
            // Create a new class loader with the directory
            Global.WriteLineToOutput("Loading new Java class into Runtime environment");
            
            ClassLoader cl = new URLClassLoader(urls);
        
            // Load in the class
            Class cls = cl.loadClass("RobotCompiled");
        
            // Create a new instance of the new class
            RobotInterface player = (RobotInterface)cls.newInstance();
            
            parent.setPlayer(player);
            
        } catch (Exception e) {
        	e.printStackTrace();
        	Global.WriteLineToOutput(e.getMessage());
                return;
        }
    }
}
