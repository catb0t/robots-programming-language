package UnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

public class PercentageTest {

	@Test
	public void testAdd() {
		app.Percentage a = new app.Percentage(60);
		app.Percentage b = new app.Percentage(80);
		app.Percentage c = new app.Percentage(30);
		app.Percentage result = new app.Percentage(0);
		
		assertEquals("Result",100,app.Percentage.add(a, b).percent);
		assertEquals("Result",90,app.Percentage.add(a, c).percent);
	}

	@Test
	public void testTimes() {
		app.Percentage a = new app.Percentage(8);
		app.Percentage b = new app.Percentage(80);
		app.Percentage c = new app.Percentage(10);
		app.Percentage result = new app.Percentage(0);
		
		assertEquals("Result",6.4f,app.Percentage.times(a, b).percent);
		assertEquals("Result",0.8f,app.Percentage.times(a, c).percent);
	}

	@Test
	public void testcap (){
		app.Percentage a = new app.Percentage(120);
		app.Percentage b = new app.Percentage(-50);
		assertEquals("Result",100,a.percent);
		assertEquals("Result",0,b.percent);
	}
}
