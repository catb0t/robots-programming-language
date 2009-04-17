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
