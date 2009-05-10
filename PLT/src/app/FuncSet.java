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
	
	
	//subtract functions which do not return errors
	
	public Float subtract (Float a, Float b) {
		
		return new Float(a-b);
		
	}
	
	public Percentage subtract (Percentage a, Percentage b) {

		Percentage p = new Percentage(a.percent-b.percent);
		p.cap();
		return p;

	}
	
	public Location subtract (Location a, Location b) {
		
		return new Location(a.x-b.x, a.y-b.y);
		
	}

	
	//times functions which do not return errors
	
	public Float times (Float a, Float b) {
		return new Float(a*b);
	}
	
	public Float times (Float a, Percentage b)
	{
		return new Float(a*(b.percent/100f));
	}
	
	public Float times (Percentage a, Float b)
	{
		return new Float(b*(a.percent/100f));
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
	
	
	//divided functions which do not return errors
	
	public Float divided (Float a, Float b) {
		return new Float(a/b);
	}

	public Float divided (Float a, Percentage b)
	{
		return new Float(a/(b.percent/100f));
	}

	public Float divided (Percentage a, Float b)
	{
		return new Float((a.percent/100f)/b);
	}

	public Location divided (Float a, Location b)
	{
		return new Location(b.x/a, b.y/a);
	}

	public Location divided (Location b, Float a)
	{
		return new Location(b.x/a, b.y/a);
	}

	public Percentage divided (Percentage a, Percentage b) {
		if (b.percent==0) {
			Exception e = new Exception();
			try {
				throw e;
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			return new Percentage(0f);
		}
		else {
			Percentage p = new Percentage(a.percent/b.percent);
			p.cap();
			return p;
		}
	}

	public Location divided (Percentage a, Location b)
	{
		return new Location(b.x/(a.percent/100f), b.y/(a.percent/100f));
	}

	public Location divided (Location b, Percentage a)
	{
		return new Location(b.x/(a.percent/100f), b.y/(a.percent/100f));
	}


	//rollover functions which do not return errors

	public Float rollover (Float n1, Float n2)
	{
		int a = (int)Math.floor(n1);
		int b = (int)Math.floor(n2);
		int retVal;

		if (a == 0) {
			retVal = 0; 
		}
		else if (b == 0) {
			retVal = 1;
		}
		else if ((a % b) == 0) {
			retVal = b;
		}
		else {
			retVal = a % b;
		}

		return new Float(retVal);

	}
	
	
	//cosinus functions which do not return errors
	
	public Float cos (Float a) {
		return new Float(Math.cos(a));
	}
	
	public Float cos (Percentage a) {
		return new Float(Math.cos(a.percent/100f));
	}
	
	
	//sinus functions which do not return errors
	
	public Float sin (Float a) {
		return new Float(Math.sin(a));
	}
	
	public Float sin (Percentage a) {
		return new Float(Math.sin(a.percent/100f));
	}
	
	
	//tangent functions which do not return errors
	
	public Float tan (Float a) {
		return new Float(Math.tan(a));
	}
	
	public Float tan (Percentage a) {
		return new Float(Math.tan(a.percent/100f));
	}
	
	
	//minus functions which do not return errors
	
	public Float minus (Float a) {
		return new Float(-a);
	}
	
	public Percentage minus (Percentage a) {
		return new Percentage(0f);
	}
	
	public Location minus (Location a) {
		return new Location(-a.x, -a.y);
	}
	
	public Boolean minus (Boolean a) {
		return new Boolean(!a);
	}
	
	
	//pow functions which do not return errors
	
	public Float power (Float a, Float b) {
		return new Float(Math.pow(a,b));
	}
	
	public Float power (Percentage a, Float b) {
		return new Float(Math.pow(a.percent/100f, b));
	}
	
	public Float power (Float a, Percentage b) {
		return new Float(Math.pow(a, b.percent/100f));
	}
	
	public Float power (Percentage a, Percentage b) {
		return new Float(Math.pow(a.percent/100f, b.percent/100f));
	}
	
	
	//sqrt functions which do not return errors
	
	public Float sqrt (Float a) {
		return new Float(Math.sqrt(a));
	}
	
	public Percentage sqrt (Percentage a) {
		return new Percentage((float) Math.sqrt(a.percent/100f)*100f);
	}

}
