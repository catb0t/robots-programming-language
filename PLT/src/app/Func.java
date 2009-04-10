package app;

import java.util.LinkedList;

//static functions
public class Func {

	///////////// ADD FUNCTION /////////////////////
	
	static float add (float a, float b) {
		return a+b;
	}
	
	static float add (float a, LinkedList<Float> b) {
		Exception e = new Exception();
		try {
			throw e;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return 0f;
	}
	
	static float add (LinkedList<Float> a, float b) {
		Exception e = new Exception();
		try {
			throw e;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return 0f;
	}
	
	static Percentage add (Percentage a, Percentage b) {
		return Percentage.add(a,b);
	}
	
	////////////// MULTIPLICATION FUNCTION /////////////////
	
	static float times (float a, float b) {
		return a*b;
	}
	
	static Percentage times (Percentage a, Percentage b) {
		return Percentage.times(a,b);
	}
	
	static float times (float a, Percentage b) {
		return a*b.percent;
	}
	
	static float times (Percentage a, float b) {
		return b*a.percent;
	}
}
