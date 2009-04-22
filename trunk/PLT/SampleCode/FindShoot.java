package app;

import java.util.*;

public class RobotCompiled extends Robot {

	public static void think() {

		Enemy closest_enemy = new Enemy();
		ping_radar();
		LinkList<Enemy> enemies = get_enemies();
		closest_enemy = find_closest_enemy(enemies);
		shoot(closest_enemy);

	}


	public static Enemy find_closest_enemy(LinkList<Enemy> enemies) {

		float closest_distance = Float.MAX_VALUE;
		Enemy closest_enemy = new Enemy();
		Location my_loc = this.location;
		
		for (Enemy enemy : enemies) {

			Location enemy_loc = enemy.location;
			float distance = distance(enemy_loc, my_loc);
			if(distance<=closest_distance){
			closest_distance = distance;
			closest_enemy = enemy;

			}


		}



	}
}
