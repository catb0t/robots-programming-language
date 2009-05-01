package app;

public class RobBool extends RobotType {

	public boolean value;
	
	public RobBool ()
	{
		super();
		value = false;
	}
	
	public RobBool (boolean b)
	{
		super();
		value = b;
	}
	
	public RobBool (RobBool b)
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
	
	public void setValue (RobBool b)
	{
		value = b.getValue();
	}
	
	/*
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
	*/
	
	public boolean equals(Object o) {

		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		RobBool equalTest = (RobBool) o;

		return (this.value == equalTest.value);
	}
}
