import app.*;
import java.util.*;

public class RobotCompiled extends Robot {


	public void think() {

		Enemy closest_enemy = new Enemy();
		RobotList<Enemy> enemies = get_enemies();
		closest_enemy = find_closest_enemy(enemies);

	}


	public Enemy find_closest_enemy(RobotList<Enemy> enemies) {

		Float closest_distance = Float.MAX_VALUE;
		Enemy closest_enemy = new Enemy();
		Location my_loc = this.getLocation();
		
		for (Enemy enemy : enemies) {

			Location enemy_loc = enemy.location;
			Float distance = distance(enemy_loc, my_loc);
			
			if (distance<=closest_distance) {
				closest_distance = distance;
				closest_enemy = enemy;
			}


		}

		Float a = new Float(1f);
		
		for(int __temp = 0; __temp < (new Float(5f)).intValue(); __temp++) {

			a = funcset.add(a, new Float(1f));

		}

		Float iteration = new Float(10f);
		
		while(a<=iteration()) {

			a = funcset.add(a, new Float(1f));

		}

		return closest_enemy;
	}
}
