package app;

import java.util.*;

public class RobotCompiled extends Robot {

	public static void think() {

		Resource closest_resource = new Resource();
		ping_radar();
		LinkList<Resource> resources = get_resources();
		closest_resource = find_closest_resource(resources);
		move_to(closest_resources.location, new Percentage(30));

	}


	public static Resource find_closest_resource(LinkList<Resource> resources) {

		float closest_distance = Float.MAX_VALUE;
		Resource closest_resource = new Resource();
		Location my_loc = this.location;
		
		for (Resource resource : resources) {

			Location resource_loc = resource.location;
			float distance = distance(resource_loc, my_loc);
			if(distance<=closest_distance){
			closest_distance = distance;
			closest_resource = resource;

			}


		}



	}
}
