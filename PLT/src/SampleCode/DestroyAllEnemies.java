import app.*;
import java.util.*;

public class RobotCompiled extends Robot {


	public void think() {

		
		if (this.energy_level>=new Percentage(10)) {
			ping();
			RobotList<Enemy> enemies = get_enemies();
			
			if (!enemies.equals(new Enemy())) {
				Enemy closest_enemy = new Enemy();
				closest_enemy = find_closest_enemy(enemies);
				Resource closest_resource = new Resource();
				RobotList<Resource> resources = get_resources();
				
				if (this.energy_level<=new Percentage(30)) {
					move_to(closest_resource.location, new Percentage(80));
				} else {
					Float dist = distance(closest_enemy.location, this.getLocation());
					
					if (dist>=new Float(10f)) {
						shoot(closest_enemy);
					} else {
						move_to(closest_enemy.location, new Percentage(100));
					}

				}

			} else {
				say(" I'm bored!!               ");
			}

		}


	}


}
