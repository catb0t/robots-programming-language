package app;

import java.util.LinkedList;

public class Number extends RobotType {

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
	
	public void setValue (float a)
	{
		value = a;
	}
	
	public void setValue (Number a)
	{
		value = a.getValue();
	}
	
	
	
	/*
	public Number rollover (Number n)
	{	
		float a = this.getValue();
		float b = n.getValue();
		
		if (b==0) {
			return new Number();
		}
		else {
			return new Number(a-a/b);
		}
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
	
	public float rollover (LinkedList n)
	{
		Exception e = new Exception();
		try {
			throw e;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return 0f;
	}
	
	public float rollover (Resource n)
	{
		Exception e = new Exception();
		try {
			throw e;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return 0f;
	}
	
	public float rollover (Enemy n)
	{
		Exception e = new Exception();
		try {
			throw e;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return 0f;
	}
	
	public float rollover (Location n)
	{
		Exception e = new Exception();
		try {
			throw e;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return 0f;
	}
	*/
}
