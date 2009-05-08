import app.*;
import java.util.*;

public class RobotCompiled extends Robot {


	public void think() {

		Enemy closest_enemy = new Enemy();
		ping();
		RobotList<Enemy> enemies = get_enemies();
		closest_enemy = find_closest_enemy(enemies);
		shoot(closest_enemy);

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

		return closest_enemy;
	}
}
