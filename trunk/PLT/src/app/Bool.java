package app;

public class Bool {

	private boolean value;
	
	public Bool ()
	{
		value = false;
	}
	
	public Bool (boolean b)
	{
		value = b;
	}
	
	public Bool (Bool b)
	{
		value = b.getValue();
	}
	
	public boolean getValue()
	{
		return value;
	}
}
