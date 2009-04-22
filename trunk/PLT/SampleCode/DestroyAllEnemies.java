package app;

import java.util.*;

public class RobotCompiled extends Robot {

	public static void think() {

		if(this.energy_level>=new Percentage(10)){
		ping_radar();
		LinkList<Enemy> enemies = get_enemies();
		if(enemies!=new Enemy()){
		Enemy closest_enemy = new Enemy();
		closest_enemy = find_closest_enemy(enemies);
		Resource closest_resource = new Resource();
		LinkList<Resource> resources = get_resources();
		if(this.energy_level<=new Percentage(30)){
		move_to(closest_resource.location, new Percentage(80));
		else {
		float dist = distance(closest_enemy.location, this.location);
		if(dist>=10f){
		shoot(closest_enemy);
		else {
		move_to(closest_enemy.location, new Percentage(100));

		}
;

		}
;
		else {
		System.out.println("I'm bored!!               ");

		}
;

		}
;

	}


}
