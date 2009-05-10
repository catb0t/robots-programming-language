package app;

public class Enemy {

	public String name;
	public Location location;
	public Float health;
	
	public Enemy() {
		name ="";
		location = new Location();
		health = new Float(0f);
	}
	
	public Enemy(String n, Location l, Float h) {
		name = n;
		location = l;
		health = h;
	}
	
	public String toString()
	{
		String sol = "enemy "+name+" @"+location.toString()+", health="+Float.toString(health);
		return sol;
	}
	
	public float getHealth() {
		return health;
	}
	
	public Enemy copy() {
		return new Enemy(name, location.copy(), new Float(health.floatValue()));
	}
	
	public Location getLocation() {
		return location;
	}
	
	public Location getlocation() {
		return location;
	}
	
	
	public boolean equals(Object o) {

		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		Enemy equalTest = (Enemy) o;

		return (this.name.equals(equalTest.name)
				&&(this.location.equals(equalTest.location))
				&&(this.health==equalTest.health));
	}
}
