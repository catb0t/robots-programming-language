package app;

public class Enemy {

	public String name;
	public Location location;
	public Float energy;
	
	public Enemy() {
		name ="";
		location = new Location();
		energy = new Float(0f);
	}
	
	public Enemy(String n, Location l, Float e) {
		name = n;
		location = l;
		energy = e;
	}
	
	public String toString()
	{
		String sol = "enemy "+name+" at ("+Float.toString(location.getX())+","+Float.toString(location.getY())+","+"), energy="+Float.toString(energy);
		return sol;
	}
	
	public float getEnergy() {
		return energy;
	}
	
	public Enemy copy() {
		return new Enemy(name, location.copy(), new Float(energy.floatValue()));
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
