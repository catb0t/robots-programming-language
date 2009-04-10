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
		// fill the compiler
	}
}
