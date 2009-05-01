package app;

public class Animation implements Runnable {

	Thread animator;
	
	Robot player;
	Scene scene;
	
	
	public Animation (Robot p, Scene s) {
		player = p;
		scene = s;
		
		animator = null;
	}
	
	public void run() {
		int t = 0;
		long time = System.currentTimeMillis();
		long timeUpdater = System.currentTimeMillis();

		try {
			while (Thread.currentThread() == animator)
			{
				if( System.currentTimeMillis() - time > 500) //updates every second
				{
					player.think();
					time = System.currentTimeMillis();
				}
				if(System.currentTimeMillis() > timeUpdater)
				{
					t++;
					timeUpdater = System.currentTimeMillis();
				}

				player.update( (float)t/100.0f );
				scene.player = player;
				//System.out.println("position.x " + player.position.x);
				scene.player.setPosition(player.position.x, player.position.y, player.position.z);
				//System.out.println("avatar.position.x " + scene.player.position.x);
				scene.player.goal = player.goal;

			}
		} catch (Exception ex) {
			animator = null;
			ex.printStackTrace();
		}

	}
	
}
