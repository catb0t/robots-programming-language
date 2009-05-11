import app.*;
import java.util.*;

public class RobotCompiled extends Robot {


	public void think() {

		Float a = new Float(100f);
		Float b = new Float(50f);
		Float c = funcset.add(a, b);
		Float d = funcset.subtract(a, b);
		Float e = funcset.times(a, b);
		Float f = funcset.divided(a, b);
		Percentage health_boost = new Float(70f);
		Percentage health = new Float(40f);
		health = funcset.add(health, health_boost);
		Float multiplier = new Float(2f);
		health = new Float(60f);
		Percentage healthNew = funcset.times(health, multiplier);
		Float healthNum = funcset.times(health, multiplier);
		
		if (a<b) {
		}

		
		if (a<=b) {
		}

		
		if (a>b) {
		}

		
		if (a>=b) {
		}

		
		if (a.equals(b)) {
		}

		
		if (!a.equals(b)) {
		}

		
		if (a<b) {
		}

		
		if (a<=b) {
		}

		
		if (a>b) {
		}

		
		if (a>=b) {
		}

		
		if (a.equals(b)) {
		}

		
		if (!a.equals(b)) {
		}

		Boolean x = true;
		Boolean y = false;
		Boolean z = x && y;
		z = x || y;
		z = !x;
		z = x && y;
		z = x || y;
		z = !x;

	}


}
