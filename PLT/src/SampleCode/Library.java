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
		Location enemy_loc = enemy.location;
		Float distance = distance(enemy_loc, my_loc);
		RobotList<Enemy> enemies = get_enemies();
		RobotList<Resource> resources = get_resources();
		Float height = get_environment_height(enemy_loc);
		RobotList<Enemy> furthest_enemies = sort_incr(enemy, "health");
		RobotList<Resource> most_valuable_resources = sort_decr(resources, "ammostash");
		modify_list(cliff_locations, "add", loc_of_cliff);
		Percentage randomNumber = gen_random_num();
		Boolean should_attack = flipCoin(this.health);
		Float testnum = new Float(100f);
		Float result = funcset.sqrt(testnum);
		result = funcset.power(testnum, new Float(2f));
		result = funcset.sin(new Float(45f));
		result = funcset.cos(new Float(120f));
		result = funcset.tan(new Float(125f));
		result = funcset.rollover(testnum, new Float(10f));

	}


}
