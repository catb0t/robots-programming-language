package app;

import org.junit.* ;
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


	//@Test
	public void test_funcset_add() {
		
		System.out.println("test funcset.add...");
		
		System.out.println("    Number...");
		
		RobNumber n1 = new RobNumber(2f);
		RobNumber n2 = new RobNumber(1.5f);

		assertTrue(funcset.add(n1,n2).equals(new RobNumber(3.5f)));
		assertTrue(!funcset.add(n1,n2).equals(new RobNumber(3f)));

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
		
		System.out.println("    RobBool...");
		
		RobBool b1 = new RobBool(true);
		RobBool b2 = new RobBool(false);

		assertTrue(funcset.add(b1,b2).equals(new RobBool(true)));
		assertTrue(!funcset.add(b1,b2).equals(new RobBool(false)));
		
		System.out.println("    errors handling...");
		
		//assertTrue(test_add_generic());
	}
	
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
}
