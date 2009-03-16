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
			
			addFile("/home/bruce/workspace/ROBOT/test/Robot.java");
		}
	}

    /**
     * Add file to CLASSPATH
     * @param s File name
     * @throws IOException  IOException
     */
    public static void addFile(String s) throws IOException {
        File f = new File(s);
        addFile(f);
    }

    /**
     * Add file to CLASSPATH
     * @param f  File object
     * @throws IOException IOException
     */
    public static void addFile(File f) throws IOException {
        reloadClass(f.toURL());
    }

    public static void reloadClass(URL u) throws IOException {

    	URL[] urls = null;
        try {
            // Convert the file object to a URL
            File dir = new File(System.getProperty("user.dir") + File.separator + "test" + File.separator);
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
