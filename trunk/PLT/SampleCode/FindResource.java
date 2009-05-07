import app.*;
import java.util.*;

public class RobotCompiled extends Robot {


	public void think() {

		Resource closest_resource = new Resource();
		ping_radar();
		RobotList<Resource> resources = get_resources();
		closest_resource = find_closest_resource(resources);
		move_to(Resource closest_resources = new Resource();
		closest_resources.location, new Percentage(30));

	}


	public Resource find_closest_resource(RobotList<Resource> resources) {

		Float closest_distance = Float.MAX_VALUE;
		Resource closest_resource = new Resource();
		Location my_loc = this.location;
		
		for (Resource resource : resources) {

			Location resource_loc = Resource resource = new Resource();
			resource.location;
			Float distance = distance(resource_loc, my_loc);
			
			if (distance<=closest_distance) {
				closest_distance = distance;
				closest_resource = resource;
			}


		}

		return closest_resource;
	}
}
