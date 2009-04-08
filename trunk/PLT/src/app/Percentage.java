package app;

public class Percentage {

	float percent;
	
	public Percentage ()
	{
		percent = 0;
	}
	
	public Percentage (float u)
	{
		percent = u;
		cap();
	}
	
	public void cap ()
	{
		if (percent>1)
		{
			percent = 1;
		}
		else if (percent<0)
		{
			percent = 0;
		}
	}
	
	public static Percentage add (Percentage p1, Percentage p2)
	{
		Percentage sol = new Percentage(p1.percent+p2.percent); 
		//sol.cap();
		return sol;
	}
	
	public static Percentage times (Percentage p1, Percentage p2)
	{
		Percentage sol = new Percentage(p1.percent*p2.percent); 
		//sol.cap();
		return sol;
	}
	
	public String toString()
	{
		return Float.toString(percent);
	}
}
