package app;

import app.RobotType;

public class Bool extends RobotType {

	private boolean value;
	
	public Bool ()
	{
		super();
		value = false;
	}
	
	public Bool (boolean b)
	{
		super();
		value = b;
	}
	
	public Bool (Bool b)
	{
		super();
		value = b.getValue();
	}
	
	public boolean getValue()
	{
		return value;
	}
	
	public void setValue (boolean b)
	{
		value = b;
	}
	
	public void setValue (Bool b)
	{
		value = b.getValue();
	}
	
	public float rollover (Boolean n)
	{
		Exception e = new Exception();
		try {
			throw e;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return 0f;
	}
}
