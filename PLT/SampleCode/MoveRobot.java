//Expected result from MoveRobot.r

//think
//	x# of targetLocation@=10
//	y# of targetLocation@=10
//	move_to targetLocation@ 100%      
//end

public class MoveRobot {
	public MoveRobot{
		Robot robot=new Robot();
		robot.move_to(new Vector2(10,10),100);	//Need new member fuction of Robot class
	}
}
