package app;

public class Enemy extends RobotType {

	public String name;
	public Location location;
	public float energy;
	
	public Enemy() {
		super();
		name ="";
		location = new Location();
		energy = 0;
	}
	
	public String toString()
	{
		String sol = "enemy name: "+name+" at ("+Float.toString(location.getX())+","+Float.toString(location.getY())+","+"), energy="+Float.toString(energy);
		return sol;
	}
	
	public float getEnergy() {
		return energy;
	}
	
	
	public boolean equals(Object o) {

		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		Enemy equalTest = (Enemy) o;

		return (this.name.equals(equalTest.name)
				&&(this.location.equals(equalTest.location))
				&&(this.energy==equalTest.energy));
	}
}
