//package ProofOfConcept;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.lang.Thread;

public class classloaderutil {

	public static void main(String[] args) throws InterruptedException, IOException {
		while(true) {
			System.out.println("Sleeping now");
			Thread.sleep(10000L);
			System.out.println("Woken up now. Reloading class ...");
			
			//addFile("/home/bruce/workspace/ROBOT/src/ProofOfConcept/test/RobotReloadableClass.java");
			reloadClass("/home/bruce/workspace/ROBOT/src/ProofOfConcept");
		}
	}

    public static void reloadClass(String str) throws IOException {

    	URL[] urls = null;
        try {
            // Convert the file object to a URL
            File dir = new File(/*System.getProperty("user.dir")*/str + File.separator + "test" + File.separator);
            URL url = dir.toURL();
            urls = new URL[]{url};
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
        
        try {
            // Create a new class loader with the directory
        	System.out.println("loading class");
            ClassLoader cl = new URLClassLoader(urls);
        
            // Load in the class
            Class cls = cl.loadClass("RobotReloadableClassImpl");
        
            // Create a new instance of the new class
            RobotReloadableClass myObj = (RobotReloadableClass)cls.newInstance();
            myObj.think();

        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
    }
}
