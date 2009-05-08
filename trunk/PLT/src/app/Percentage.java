package app;

public class Percentage {

	public Float percent;
	
	public Percentage ()
	{
		percent = new Float(0f);
	}
	
	public Percentage (float u)
	{
		percent = u;
		cap();
	}
	
	public void cap ()
	{
		if (percent>100f)
		{
			percent = 100f;
		}
		else if (percent<0f)
		{
			percent = 0f;
		}
	}
	
	public static Percentage add (Percentage p1, Percentage p2)
	{
		Percentage sol = new Percentage(p1.percent+p2.percent); 
		sol.cap();
		return sol;
	}
	
	public static Percentage times (Percentage p1, Percentage p2)
	{
		Percentage sol = new Percentage(p1.percent*p2.percent/100f); 
		//sol.cap();
		return sol;
	}
	
	public String toString()
	{
		return Float.toString(percent);
	}
	
	public boolean equals(Object o) {

		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		Percentage equalTest = (Percentage) o;

		return (Math.abs(this.percent - equalTest.percent) < 0.000001);
	}
	
	
}
