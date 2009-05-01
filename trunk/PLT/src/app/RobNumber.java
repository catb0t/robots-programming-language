package app;

public class RobNumber extends RobotType {

	public float value;
	
	public RobNumber ()
	{
		value = 0;
	}
	
	public RobNumber (float a)
	{
		value = a;
	}
	
	public RobNumber (RobNumber a)
	{
		value = a.getValue();
	}
	
	public RobNumber (Percentage a) {
		value = a.percent;
	}
	
	public float getValue()
	{
		return value;
	}
	
	public void setValue (float a)
	{
		value = a;
	}
	
	public void setValue (RobNumber a)
	{
		value = a.getValue();
	}
	
	public boolean equals(Object o) {

		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		RobNumber equalTest = (RobNumber) o;

		return (Math.abs(this.value - equalTest.value) < 0.000001);
	}
	
	public String toString() {
		return Float.toString(value);
	}
}
