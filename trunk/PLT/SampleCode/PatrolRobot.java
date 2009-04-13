import app.Robot;

//Expected result from PatrolRobot.r
//count# = 0
//think
//   my_loc@ = location@ of self
//   x# of 1th of targetLocation@...=10
//   y# of 1th of targetLocation@...=y# of my_loc@
//   x# of 2th of targetLocation@...=10
//   y# of 2th of targetLocation@...=10
//   x# of 3th of targetLocation@...=x# of my_loc@
//   y# of 3th of targetLocation@...=10
//   x# of 4th of targetLocation@...=x# of my_loc@
//   y# of 4th of targetLocation@...=y# of my_loc@
//
//   if my_loc@ != count#th of targetLocation@...
//       move_to count#th of targetLocation@... 10%
//   else
//      count# = (count# + 1) rollover 4
//   end
//end

public class PatrolRobot {
	public int count=0;
	public PatrolRobot(){
		Robot robot = new Robot();
		public Vector2 my_loc=robot.getPositionVector2();	//Need new member functions of Robot class
		public Vector2 targetLocation[]={{10,0},{10,10},{0,10},{0,0}};
		while(count <= targetLocation.lenth()){
			robot.move_to(targetLocation[count], 100);
			count++;
		}
	}
}
