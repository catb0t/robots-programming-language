package app;

public class Number {

	private float value;
	
	public Number ()
	{
		value = 0;
	}
	
	public Number (float a)
	{
		value = a;
	}
	
	public Number (Number a)
	{
		value = a.getValue();
	}
	
	public float getValue()
	{
		return value;
	}
}
