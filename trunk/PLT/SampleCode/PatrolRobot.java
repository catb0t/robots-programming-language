import app.*;
import java.util.*;

public class RobotCompiled extends Robot {

Float count = new Float(0f);

	public void think() {

		Location my_loc = this.location;
		targetLocation.get(1).x = 10f;
		targetLocation.get(1).y = my_loc.y;
		targetLocation.get(2).x = 10f;
		targetLocation.get(2).y = 10f;
		targetLocation.get(3).x = my_loc.x;
		targetLocation.get(3).y = 10f;
		targetLocation.get(4).x = my_loc.x;
		targetLocation.get(4).y = my_loc.y;
		
		if (my_loc!=targetLocation.get(count)) {
			move_to(targetLocation.get(count), new Percentage(10));
		} else {
			count = Func.rollover(( Func.add(count, 1f) ), 4f);
		}


	}


}
