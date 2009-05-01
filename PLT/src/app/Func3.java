package app;

public class Func3 extends Func2 {
	
	public Func3 () {
		
	}
	
	//add functions which do not return errors
	
	public RobNumber add (RobNumber a, RobNumber b) {
		
		return new RobNumber(a.value+b.value);
		
	}
	
	public static Percentage add (Percentage a, Percentage b) {

		return Percentage.add(a,b);

	}
	
	public static Location add (Location a, Location b) {
		
		return new Location(a.x+b.x, a.y+b.y);
		
	}
	
	public RobBool add (RobBool a, RobBool b) {

		return new RobBool(a.value||b.value);

	}
	
	//times functions which do not return errors
	
	public RobNumber times (RobNumber a, RobNumber b) {
		return new RobNumber(a.value*b.value);
	}
	
	public RobNumber times (RobNumber a, Percentage b)
	{
		return new RobNumber(a.value*b.percent/100f);
	}
	
	public RobNumber times (Percentage a, RobNumber b)
	{
		return new RobNumber(a.percent/100f*b.value);
	}
	
	public Location times (RobNumber a, Location b)
	{
		return new Location(b.x*a.value, b.y*a.value);
	}
	
	public Location times (Location b, RobNumber a)
	{
		return new Location(b.x*a.value, b.y*a.value);
	}
	
	public Percentage times (Percentage a, Percentage b) {
		return Percentage.times(a,b);
	}
	
	public Location times (Percentage a, Location b)
	{
		return new Location(b.x*a.percent/100f, b.y*a.percent/100f);
	}

	public Location times (Location b, Percentage a)
	{
		return new Location(b.x*a.percent/100f, b.y*a.percent/100f);
	}
	
	public RobBool times (RobBool a, RobBool b)
	{
		return new RobBool(a.value&&b.value);
	}
	
	//rollover functions which do not return errors
	
	public RobNumber rollover (RobNumber n1, RobNumber n2)
	{
		float a = n1.value;
		float b = n2.value;
		
		if (b == 0f)
		{
			return new RobNumber(a);
		}
		else
		{
			return new RobNumber(a-((int) (a/b))*b);
		}
		
	}
}
