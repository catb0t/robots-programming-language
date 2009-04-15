package app;

public class Compiler {
	
	private String inputFile;
	
	public Compiler() {
		inputFile = null;
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
			String[] command = {"java", "-jar", "JointEM.jar"};
			//String[] command = {"/Applications/Preview.app/Contents/MacOS/Preview", "/Users/aurelien/Pictures/chou.jpg"};
			Runtime.getRuntime().exec(command);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
