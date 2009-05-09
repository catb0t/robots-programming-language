import app.*;
import java.util.*;

public class RobotCompiled extends Robot {


	public void think() {

		say(" Oh, oh. My health is now " + this.health);
		Location targetLocation = new Location();
		targetLocation.x = new Float(10f);
		targetLocation.y = new Float(10f);
		move_to(targetLocation, new Percentage(100));
		Enemy closest_enemy = new Enemy();
		shoot(closest_enemy);
		ping();
		Location my_loc = this.getLocation();
		Location enemy_loc = Enemy enemy = new Enemy();
		enemy.location;
		Float distance = distance(enemy_loc, my_loc);
		RobotList<Enemy> enemies = get_enemies();
		RobotList<Resource> resources = get_resources();
		Float height = get_environment_height(enemy_loc);
		RobotList<Enemy> furthest_enemies = sort(enemy_info);
		RobotList<Resource> most_valuable_resources = sort(resource_info>ammo_stash);
		modify_list(cliff_locations, add(loc_of_cliff));
		Percent randomNumber = gen_random_num();
		Boolean should_attack = flipCoin(this.health);
		Float testnum = new Float(100f);
		Float result = sqrt(testnum);
		result = power(testnum, new Float(2f));
		result = sin(new Float(45f));
		result = cos(new Float(120f));
		result = tan(new Float(125f));
		result = funcset.rollover(testnum, new Float(10f));

	}


}
