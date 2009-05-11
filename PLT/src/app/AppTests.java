package app;

import org.junit.* ;

import app.Enemy;
import app.FuncSet;
import app.Location;
import app.Percentage;
import app.Resource;
import app.RobBool;
import app.RobNumber;
import app.Robot;
import app.RobotList;
import static org.junit.Assert.* ;

public class AppTests {

	FuncSet funcset = new FuncSet();
	
	@Test
	public void test_robotlist() {
		System.out.println("Testing RobotList...");
		RobotList<Location> ll = new RobotList<Location>(Location.class);

		ll.get(1f).x = 10f;
		ll.get(1f).y = 10f;
		ll.get(5f).x = 20f;
		ll.get(5f).y = 20f;

		Float f = new Float(1f);

		assertTrue(ll.get(1f).x == 10f);
		assertTrue(ll.get(f).x == 10f);
		assertTrue(ll.get(3f).x == 0f);
		assertTrue(ll.get(5f).x == 20f);
		assertTrue(ll.get(0f) == null);

	}

	@Test
	public void test_location_equality() {

		System.out.println("Testing Location.equals()...");

		Location l1 = new Location();
		Location l2 = new Location(0f, 0f);

		assertTrue(l1.equals(l2));
		assertTrue(l1!=l2);

		l2.x = 1f;

		assertTrue(!l1.equals(l2));

	}

	@Test
	public void test_rollover() {

		System.out.println("Testing Func.rollover()...");

		float f1;

		f1 = funcset.rollover(1f, 0f); assertTrue(f1 == 1f);
		f1 = funcset.rollover(0f, 4f); assertTrue(f1 == 0f);
		f1 = funcset.rollover(1f, 4f); assertTrue(f1 == 1f);
		f1 = funcset.rollover(2f, 4f); assertTrue(f1 == 2f);
		f1 = funcset.rollover(3f, 4f); assertTrue(f1 == 3f);
		f1 = funcset.rollover(4f, 4f); assertTrue(f1 == 4f);
		f1 = funcset.rollover(5f, 4f); assertTrue(f1 == 1f);
		f1 = funcset.rollover(8f, 4f); assertTrue(f1 == 4f);

	}


/*
	public boolean test_add_generic() {
		try {
			funcset.add(new Location(), new RobNumber(0f));
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	//@Test
	public void test_funcset_times() {
		
		System.out.println("test funcset.times...");
		
		System.out.println("    between Number...");
		
		RobNumber n1 = new RobNumber(2f);
		RobNumber n2 = new RobNumber(1.5f);

		assertTrue(funcset.times(n1,n2).equals(new RobNumber(3f)));
		assertTrue(!funcset.times(n1,n2).equals(new RobNumber(3.5f)));

		System.out.println("    between Percentage...");
		
		Percentage p1 = new Percentage(50f);
		Percentage p2 = new Percentage(60f);

		assertTrue(funcset.times(p1,p2).equals(new Percentage(30f)));
		assertTrue(!funcset.times(p1,p2).equals(new Percentage(110f)));
		assertTrue(!funcset.times(p1,p2).equals(new Percentage(100f)));
		
		System.out.println("    between Number and Percentage...");
		
		assertTrue(funcset.times(n1,p1).equals(new RobNumber(1f)));
		assertTrue(funcset.times(p1,n1).equals(new RobNumber(1f)));
		assertTrue(!funcset.times(p1,n2).equals(new Percentage(100f)));
		
		System.out.println("    between Location and Numbers...");
		
		Location l1 = new Location(1f,2f);
		Location l2 = new Location(2f,3f);

		assertTrue(funcset.times(n1,l2).equals(new Location(4f, 6f)));
		assertTrue(funcset.times(l2,n1).equals(new Location(4f, 6f)));
		assertTrue(!funcset.times(l2,n1).equals(new Location(1f, 6f)));
		
		System.out.println("    between Location and Percentages...");

		assertTrue(funcset.times(p1,l2).equals(new Location(1f, 1.5f)));
		assertTrue(funcset.times(l2,p1).equals(new Location(1f, 1.5f)));
		assertTrue(!funcset.times(l2,p1).equals(new Location(4f, 6f)));
		
		System.out.println("    between RobBool...");
		
		RobBool b1 = new RobBool(true);
		RobBool b2 = new RobBool(false);

		assertTrue(funcset.times(b1,b2).equals(new RobBool(false)));
		assertTrue(!funcset.times(b1,b2).equals(new RobBool(true)));
		
		System.out.println("    errors handling...");
		
		//assertTrue(test_times_generic());
	}
	*/
	
	public boolean test_times_generic () {
		try {
			funcset.times(new Location(), new Location());
			return false;
		} catch (Exception e) {
			return true;
		}
	}
	
	@Test
	public void test_Float () {
		
		System.out.println("test Float object...");
		
		Float f1 = new Float(1f);
		Float f2 = 2f;
		Float f3 = new Float(3f);
		
		assertTrue(f1+f2==3f);
		assertTrue((f1+f2==(new Float(3f))));
		assertTrue(f3.equals(f1+f2));
		assertTrue(!((new Float(3f)).equals(4f)));
		//assertTrue(new Float((new Location()).x).equals(0f));
		assertTrue((new Location()).x.equals(0f));
		assertTrue((new Float(new Float(2f)))==(2f));
		assertTrue(1f<=(new Float(2f)));
		
	}
	
	@Test
	public void test_Boolean () {
		
		System.out.println("test Boolean object...");
		
		Boolean b1 = new Boolean(true);
		Boolean b2 = false;
		Boolean b3 = new Boolean(false);
		
		assertTrue(b1);
		assertTrue(b1||b2);
		assertTrue(!(b1&&b2));
		assertTrue(!(b1&&b3));
		assertTrue(!b3);
		
	}
	
	@Test
	public void test_funcset_times_with_Float_object() {
		
		System.out.println("test funcset.times with Float object...");
		
		System.out.println("    between Number...");
		
		Float n1 = new Float(2f);
		Float n2 = new Float(1.5f);

		assertTrue(funcset.times(n1,n2).equals(new Float(3f)));
		assertTrue(!funcset.times(n1,n2).equals(new Float(3.5f)));

		System.out.println("    between Percentage...");
		
		Percentage p1 = new Percentage(50f);
		Percentage p2 = new Percentage(60f);

		assertTrue(funcset.times(p1,p2).equals(new Percentage(30f)));
		assertTrue(!funcset.times(p1,p2).equals(new Percentage(110f)));
		assertTrue(!funcset.times(p1,p2).equals(new Percentage(100f)));
		
		System.out.println("    between Number and Percentage...");
		
		assertTrue(funcset.times(n1,p1).equals(new Float(1f)));
		assertTrue(funcset.times(p1,n1).equals(new Float(1f)));
		assertTrue(!funcset.times(p1,n2).equals(new Percentage(100f)));
		
		System.out.println("    between Location and Numbers...");
		
		Location l1 = new Location(1f,2f);
		Location l2 = new Location(2f,3f);

		assertTrue(funcset.times(n1,l2).equals(new Location(4f, 6f)));
		assertTrue(funcset.times(l2,n1).equals(new Location(4f, 6f)));
		assertTrue(!funcset.times(l2,n1).equals(new Location(1f, 6f)));
		
		System.out.println("    between Location and Percentages...");

		assertTrue(funcset.times(p1,l2).equals(new Location(1f, 1.5f)));
		assertTrue(funcset.times(l2,p1).equals(new Location(1f, 1.5f)));
		assertTrue(!funcset.times(l2,p1).equals(new Location(4f, 6f)));
		
		System.out.println("    between Boolean...");
		
		Boolean b1 = new Boolean(true);
		Boolean b2 = new Boolean(false);

		assertTrue(funcset.times(b1,b2).equals(new Boolean(false)));
		assertTrue(!funcset.times(b1,b2).equals(new Boolean(true)));
		
		System.out.println("    errors handling...");
		
		//assertTrue(test_times_generic());
	}
	
	@Test
	public void test_funcset_add_with_Float_object() {
		
		System.out.println("test funcset.add with Float Object...");
		
		System.out.println("    Number...");
		
		Float n1 = new Float(2f);
		Float n2 = new Float(1.5f);

		assertTrue(funcset.add(n1,n2).equals(new Float(3.5f)));
		assertTrue(!funcset.add(n1,n2).equals(new Float(3f)));

		System.out.println("    Percentage...");
		
		Percentage p1 = new Percentage(50f);
		Percentage p2 = new Percentage(60f);

		assertTrue(funcset.add(p1,p2).equals(new Percentage(100f)));
		assertTrue(funcset.add(p1,p2).equals(new Percentage(110f)));
		assertTrue(!funcset.add(p1,p2).equals(new Percentage(90f)));
		
		System.out.println("    Location...");
		
		Location l1 = new Location(1f,2f);
		Location l2 = new Location(2f,3f);

		assertTrue(!funcset.add(l1,l2).equals(new Location()));
		assertTrue(funcset.add(l1,l2).equals(new Location(3f,5f)));
		
		System.out.println("    Boolean...");
		
		Boolean b1 = new Boolean(true);
		Boolean b2 = new Boolean(false);

		assertTrue(funcset.add(b1,b2).equals(new Boolean(true)));
		assertTrue(!funcset.add(b1,b2).equals(new Boolean(false)));
		
		System.out.println("    errors handling...");
		
		//assertTrue(test_add_generic());
	}
	
	@Test
	public void test_sort_float() {
		//Possible listTypes: Float, Enemy, Resource, Percentage, Location
		Robot robot = new Robot();
		RobotList<Float> list = new RobotList<Float>(Float.class);
		RobotList<Float> result = new RobotList<Float>(Float.class);
		RobotList<Float> expectedResult = new RobotList<Float>(Float.class);
		list.add(new Float(10f));
		list.add(new Float(2f));
		list.add(new Float(20f));
		list.add(new Float(40f));
		list.add(new Float(15f));
		expectedResult.add(new Float(2f));
		expectedResult.add(new Float(10f));
		expectedResult.add(new Float(15f));
		expectedResult.add(new Float(20f));
		expectedResult.add(new Float(40f));
		
		//test sort_incr
		result=robot.sort_incr(list,"Float");
		for(int i=0;i<list.size();i++){
			assertEquals("Result",result.get(i),expectedResult.get(i));
		}
		
		//Test sort_decr
		result=robot.sort_decr(list,"Float");
		expectedResult.clear();
		expectedResult.add(new Float(40f));
		expectedResult.add(new Float(20f));
		expectedResult.add(new Float(15f));
		expectedResult.add(new Float(10f));
		expectedResult.add(new Float(2f));
		
		for(int i=0;i<list.size();i++){
			assertEquals("Result",result.get(i),expectedResult.get(i));
		}
	}
	
	@Test
	public void test_sort_Enemy() {
		//Possible listTypes: Float, Enemy, Resource, Percentage, Location
		Robot robot = new Robot();
		
		RobotList<Enemy> list = new RobotList<Enemy>(Enemy.class);
		RobotList<Enemy> result = new RobotList<Enemy>(Enemy.class);
		RobotList<Enemy> expectedResult = new RobotList<Enemy>(Enemy.class);
		Enemy e1=new Enemy("1",new Location(50,50),100f);
		Enemy e2=new Enemy("2",new Location(20,20),10f);
		Enemy e3=new Enemy("3",new Location(10,10),80f);
		Enemy e4=new Enemy("4",new Location(160,160),50f);
		Enemy e5=new Enemy("5",new Location(100,100),90f);
		
		list.add(e1);
		list.add(e2);
		list.add(e3);
		list.add(e4);
		list.add(e5);
		
		//test sort_incr by location
		expectedResult.add(e3);
		expectedResult.add(e2);
		expectedResult.add(e1);
		expectedResult.add(e5);
		expectedResult.add(e4);
		result=robot.sort_incr(list,"location");
		//System.out.println(list.size());
		for(int i=0;i<list.size();i++){
			//System.out.println(result.get(i).location.toString());
			//System.out.println(expectedResult.get(i).location.toString());
			assertTrue(result.get(i).location.equals(expectedResult.get(i).location));
		}
		
		//test sort_decr by location
		expectedResult.clear();
		expectedResult.add(e4);
		expectedResult.add(e5);
		expectedResult.add(e1);
		expectedResult.add(e2);
		expectedResult.add(e3);
		
		result=robot.sort_decr(list,"location");
		//System.out.println(list.size());
		for(int i=0;i<list.size();i++){
			//System.out.println(result.get(i).location.toString());
			//System.out.println(expectedResult.get(i).location.toString());
			assertTrue(result.get(i).location.equals(expectedResult.get(i).location));
		}
		
		
		//test sort_incr by energy
		expectedResult.clear();
		expectedResult.add(e2);
		expectedResult.add(e4);
		expectedResult.add(e3);
		expectedResult.add(e5);
		expectedResult.add(e1);
		result=robot.sort_incr(list,"health");
		//System.out.println(list.size());
		for(int i=0;i<list.size();i++){
			//System.out.println(result.get(i).health.toString());
			//System.out.println(expectedResult.get(i).health.toString());
			//assertTrue(result.get(i).location.equals(expectedResult.get(i).location));
			assertEquals("Result",result.get(i).health, expectedResult.get(i).health);
		}
		
		//test sort_decr by energy
		expectedResult.clear();
		expectedResult.add(e1);
		expectedResult.add(e5);
		expectedResult.add(e3);
		expectedResult.add(e4);
		expectedResult.add(e2);
		
		result=robot.sort_decr(list,"health");
		//System.out.println(list.size());
		for(int i=0;i<list.size();i++){
			//System.out.println(result.get(i).location.toString());
			//System.out.println(expectedResult.get(i).location.toString());
			//assertTrue(result.get(i).location.equals(expectedResult.get(i).location));
			assertEquals("Result",result.get(i).health, expectedResult.get(i).health);
		}
	}
	
	
	@Test
	public void test_sort_Resource() {
		//Possible listTypes: Float, Enemy, Resource, Percentage, Location
		//field : location,energy, ammostash
		//Resource (String n, Location l, Float e, Float a)
		Robot robot = new Robot();
		
		RobotList<Resource> list = new RobotList<Resource>(Resource.class);
		RobotList<Resource> result = new RobotList<Resource>(Resource.class);
		RobotList<Resource> expectedResult = new RobotList<Resource>(Resource.class);
		Resource e1=new Resource("1",new Location(50,50),100f, 60f, 0f, 0f);
		Resource e2=new Resource("2",new Location(20,20),10f,30f, 0f, 0f);
		Resource e3=new Resource("3",new Location(10,10),80f,200f, 0f, 0f);
		Resource e4=new Resource("4",new Location(160,160),50f,100f, 0f, 0f);
		Resource e5=new Resource("5",new Location(100,100),90f,150f, 0f, 0f);
		
		list.add(e1);
		list.add(e2);
		list.add(e3);
		list.add(e4);
		list.add(e5);
		
		//test sort_incr by location
		expectedResult.add(e3);
		expectedResult.add(e2);
		expectedResult.add(e1);
		expectedResult.add(e5);
		expectedResult.add(e4);
		result=robot.sort_incr(list,"location");
		//System.out.println(list.size());
		for(int i=0;i<list.size();i++){
			//System.out.println(result.get(i).location.toString());
			//System.out.println(expectedResult.get(i).location.toString());
			assertTrue(result.get(i).location.equals(expectedResult.get(i).location));
		}
		
		//test sort_decr by location
		expectedResult.clear();
		expectedResult.add(e4);
		expectedResult.add(e5);
		expectedResult.add(e1);
		expectedResult.add(e2);
		expectedResult.add(e3);
		
		result=robot.sort_decr(list,"location");
		//System.out.println(list.size());
		for(int i=0;i<list.size();i++){
			//System.out.println(result.get(i).location.toString());
			//System.out.println(expectedResult.get(i).location.toString());
			assertTrue(result.get(i).location.equals(expectedResult.get(i).location));
		}
		
		
		//test sort_incr by energy
		expectedResult.clear();
		expectedResult.add(e2);
		expectedResult.add(e4);
		expectedResult.add(e3);
		expectedResult.add(e5);
		expectedResult.add(e1);
		result=robot.sort_incr(list,"energy");
		//System.out.println(list.size());
		for(int i=0;i<list.size();i++){
			//System.out.println(result.get(i).location.toString());
			//System.out.println(expectedResult.get(i).location.toString());
			assertTrue(result.get(i).location.equals(expectedResult.get(i).location));
		}
		
		//test sort_decr by energy
		expectedResult.clear();
		expectedResult.add(e1);
		expectedResult.add(e5);
		expectedResult.add(e3);
		expectedResult.add(e4);
		expectedResult.add(e2);
		
		result=robot.sort_decr(list,"energy");
		//System.out.println(list.size());
		for(int i=0;i<list.size();i++){
			//System.out.println(result.get(i).location.toString());
			//System.out.println(expectedResult.get(i).location.toString());
			assertTrue(result.get(i).location.equals(expectedResult.get(i).location));
		}
		
		//test sort_incr by ammostash
		expectedResult.clear();
		expectedResult.add(e2);
		expectedResult.add(e1);
		expectedResult.add(e4);
		expectedResult.add(e5);
		expectedResult.add(e3);
		result=robot.sort_incr(list,"ammostash");
		//System.out.println(list.size());
		for(int i=0;i<list.size();i++){
			//System.out.println(result.get(i).location.toString());
			//System.out.println(expectedResult.get(i).location.toString());
			assertTrue(result.get(i).location.equals(expectedResult.get(i).location));
		}
		
		//test sort_decr by ammostash
		expectedResult.clear();
		expectedResult.add(e3);
		expectedResult.add(e5);
		expectedResult.add(e4);
		expectedResult.add(e1);
		expectedResult.add(e2);
		
		result=robot.sort_decr(list,"ammostash");
		//System.out.println(list.size());
		for(int i=0;i<list.size();i++){
			//System.out.println(result.get(i).location.toString());
			//System.out.println(expectedResult.get(i).location.toString());
			assertTrue(result.get(i).location.equals(expectedResult.get(i).location));
		}
	}
	
	@Test
	public void test_sort_Percentage() {
		//Possible listTypes: Float, Enemy, Resource, Percentage, Location
		Robot robot = new Robot();
		RobotList<Percentage> list = new RobotList<Percentage>(Percentage.class);
		RobotList<Percentage> result = new RobotList<Percentage>(Percentage.class);
		RobotList<Percentage> expectedResult = new RobotList<Percentage>(Percentage.class);
		list.add(new Percentage(10f));
		list.add(new Percentage(2f));
		list.add(new Percentage(20f));
		list.add(new Percentage(40f));
		list.add(new Percentage(15f));
		expectedResult.add(new Percentage(2f));
		expectedResult.add(new Percentage(10f));
		expectedResult.add(new Percentage(15f));
		expectedResult.add(new Percentage(20f));
		expectedResult.add(new Percentage(40f));
		
		//test sort_incr
		result=robot.sort_incr(list,"Percentage");
		for(int i=0;i<list.size();i++){
			assertEquals("Result",result.get(i),expectedResult.get(i));
		}
		
		//Test sort_decr
		result=robot.sort_decr(list,"Percentage");
		expectedResult.clear();
		expectedResult.add(new Percentage(40f));
		expectedResult.add(new Percentage(20f));
		expectedResult.add(new Percentage(15f));
		expectedResult.add(new Percentage(10f));
		expectedResult.add(new Percentage(2f));
		
		for(int i=0;i<list.size();i++){
			assertEquals("Result",result.get(i),expectedResult.get(i));
		}
	}
	
	@Test
	public void test_sort_Location(){
		//Possible listTypes: Float, Enemy, Resource, Percentage, Location
		Robot robot = new Robot();
		
		RobotList<Location> list = new RobotList<Location>(Location.class);
		RobotList<Location> result = new RobotList<Location>(Location.class);
		RobotList<Location> expectedResult = new RobotList<Location>(Location.class);
		Location e1=new Location(50,50);
		Location e2=new Location(20,20);
		Location e3=new Location(10,10);
		Location e4=new Location(160,160);
		Location e5=new Location(100,100);
		
		list.add(e1);
		list.add(e2);
		list.add(e3);
		list.add(e4);
		list.add(e5);
		
		//test sort_incr by location
		expectedResult.add(e3);
		expectedResult.add(e2);
		expectedResult.add(e1);
		expectedResult.add(e5);
		expectedResult.add(e4);
		result=robot.sort_incr(list,"location");
		//System.out.println(list.size());
		for(int i=0;i<list.size();i++){
			//System.out.println(result.get(i).location.toString());
			//System.out.println(expectedResult.get(i).location.toString());
			assertTrue(result.get(i).equals(expectedResult.get(i)));
		}
		
		//test sort_decr by location
		expectedResult.clear();
		expectedResult.add(e4);
		expectedResult.add(e5);
		expectedResult.add(e1);
		expectedResult.add(e2);
		expectedResult.add(e3);
		
		result=robot.sort_decr(list,"location");
		//System.out.println(list.size());
		for(int i=0;i<list.size();i++){
			//System.out.println(result.get(i).location.toString());
			//System.out.println(expectedResult.get(i).location.toString());
			assertTrue(result.get(i).equals(expectedResult.get(i)));
		}
	}
}
