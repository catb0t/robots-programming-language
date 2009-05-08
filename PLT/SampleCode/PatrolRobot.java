import app.*;
import java.util.*;

public class RobotCompiled extends Robot {

Float count = new Float(0f);

	public void think() {

		Location my_loc = this.getLocation();
		RobotList<Location> targetLocation = new RobotList<Location>(Location.class);
		targetLocation.get(1f).x = new Float(10f);
		targetLocation.get(1f).y = my_loc.y;
		targetLocation.get(2f).x = new Float(10f);
		targetLocation.get(2f).y = new Float(10f);
		targetLocation.get(3f).x = my_loc.x;
		targetLocation.get(3f).y = new Float(10f);
		targetLocation.get(4f).x = my_loc.x;
		targetLocation.get(4f).y = my_loc.y;
		
		if (!my_loc.equals(targetLocation.get(count))) {
			move_to(targetLocation.get(count), new Percentage(10));
		} else {
			count = funcset.rollover(( funcset.add(count, new Float(1f)) ), new Float(4f));
		}


	}


}
