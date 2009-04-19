package app;

import java.util.*;

public class RobotCompiled extends Robot {

	public static void think() {

		targetLocation.x = 10f;
		targetLocation.y = 10f;
		move_to(targetLocation, new Percentage(100));

	}


}
