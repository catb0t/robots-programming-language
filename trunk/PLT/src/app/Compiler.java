package app;

public class Compiler {
	
	private String inputFile;
	private String outputFile;
	
	public Compiler() {
		inputFile = null;
		outputFile = null;
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
			//String[] command = {"parser", inputFile, outputFile};
			String[] command = {"/Applications/Preview.app/Contents/MacOS/Preview", "/Users/aurelien/Pictures/chou.jpg"};
			Runtime.getRuntime().exec(command);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
