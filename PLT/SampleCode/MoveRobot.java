import app.*;
import java.util.*;

public class RobotCompiled extends Robot {


	public void think() {

		Location targetLocation = new Location();
		targetLocation.x = new Float(10f);
		targetLocation.y = new Float(10f);
		move_to(targetLocation, new Percentage(100));

	}


}
