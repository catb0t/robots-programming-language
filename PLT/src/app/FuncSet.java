package app;

public class FuncSet extends BasicFuncSet {
	
	public FuncSet () {
		
	}
	
	//add functions which do not return errors
	
	public Float add (Float a, Float b) {
		
		return new Float(a+b);
		
	}
	
	public Percentage add (Percentage a, Percentage b) {

		return Percentage.add(a,b);

	}
	
	public Location add (Location a, Location b) {
		
		return new Location(a.x+b.x, a.y+b.y);
		
	}
	
	public Boolean add (Boolean a, Boolean b) {

		return new Boolean(a||b);

	}
	
	//times functions which do not return errors
	
	public Float times (Float a, Float b) {
		return new Float(a*b);
	}
	
	public Float times (Float a, Percentage b)
	{
		return new Float(a*b.percent/100f);
	}
	
	public Float times (Percentage a, Float b)
	{
		return new Float(a.percent/100f*b);
	}
	
	public Location times (Float a, Location b)
	{
		return new Location(b.x*a, b.y*a);
	}
	
	public Location times (Location b, Float a)
	{
		return new Location(b.x*a, b.y*a);
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
	
	public Boolean times (Boolean a, Boolean b)
	{
		return new Boolean(a&&b);
	}
	
	//rollover functions which do not return errors
	
	public Float rollover (Float n1, Float n2)
	{
           int a = (int)Math.floor(n1);
           int b = (int)Math.floor(n2);
           int retVal;

           if (a == 0 || b == 0) {
              retVal = 1;
           } else if (a == b) {
              retVal = b;
           } else {
              retVal = a % b;
           }

           return new Float(retVal);

        }
		
}
