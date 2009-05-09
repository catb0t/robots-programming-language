package app;

import java.util.Random;
import java.util.*;
import java.awt.TextArea;

public class Animation implements Runnable {

	Thread animator;

	Robot[] player = null;
	Scene scene;

	int numberOfPlayers = 1;

	int numberOfPowerups = 30;
	public Powerup[] resources = null;//new Powerup[numberOfPowerups];

	//ArrayList<Laser> lasers = null;
	LinkedList<Laser> lasers;
	
	public Animation (Robot[] p, int numPlayers, Scene s) {
		player = p;
		numberOfPlayers = numPlayers;
		scene = s;
		scene.player = new Robot[numPlayers];
		scene.numberOfRobots = numPlayers;

		//lasers = new ArrayList<Laser>();
		lasers = new LinkedList<Laser>();
		
		resources = new Powerup[numberOfPowerups];
		scene.numberOfPowerups = numberOfPowerups;
		scene.resources = new Powerup[numberOfPowerups];

		Random generator = new Random();
		
		Global.resource_list.clear();
		
		for(int i = 0; i < numberOfPowerups; i++)
		{
			//find location
			float x = generator.nextFloat()*(player[0].terrain.maxx - player[0].terrain.minx) + player[0].terrain.minx;
			float z = generator.nextFloat()*(player[0].terrain.maxz - player[0].terrain.minz) + player[0].terrain.minz;
			Vector3 pos = new Vector3(x, z, 0);
			float y = player[0].terrain.terrainIntersection(pos);
			pos = new Vector3(x, y, z);
			resources[i] = new Powerup(pos, generator.nextInt(4));
			
			Global.resource_list.add(resources[i].projectOnResourceClass());
		}

		Global.enemy_list.clear();
		for (int i = 1; i < numberOfPlayers; i++)
		{
			Global.enemy_list.add(player[i].projectOnEnemyClass());
		}

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
					updateGlobal();
					for(int i = 0; i < numberOfPlayers; i++)
					{
						if(player[i].health > 0)
							player[i].think();
					}
					time = System.currentTimeMillis();
				}

				if(System.currentTimeMillis() > timeUpdater)
				{
					t++;
					timeUpdater = System.currentTimeMillis();
				}
				
				//if the player has chosen to fire, go ahead and spawn a laser and set the players desire to fire to false
				for(int i = 0; i < numberOfPlayers; i++)
				{
					if(player[i].shoot == true)
					{
						player[i].shoot = false;
						//I should copy this data over so updates to robot during projectile flight wont effect projectile
						Vector3 pos = new Vector3(player[i].position.x, player[i].position.y, player[i].position.z);
						Vector3 dir = new Vector3(player[i].shootDirection.x, player[i].shootDirection.y, player[i].shootDirection.z);
						dir.normalize();
						float headStart = 0.3f; //we want to give our projectile a head start before collision detection or it will hit us
						pos = new Vector3(pos.x + dir.x*headStart, pos.y + dir.y*headStart, pos.z + dir.z*headStart);
						Laser newLaser = new Laser(pos, dir, (float)t/100.0f);
						lasers.add(newLaser);
					}
				} //end for all players

				//end think if


				//***************************************************
				//update players
				for(int i = 0; i < numberOfPlayers; i++)
				{
					if(numberOfPlayers > 1)
						player[i].update( (float)t/100.0f, player[1].position );
					else
						player[i].update( (float)t/100.0f, null );
					scene.player[i] = player[i];
					//System.out.println("position.x " + player.position.x);
					scene.player[i].setPosition(player[i].position.x, player[i].position.y, player[i].position.z);
					//System.out.println("avatar.position.x " + scene.player.position.x);
					scene.player[i].goal = player[i].goal;
				}

				//:::::::::::::::::::::::::::::::::::::::::::::::::::::
				//update the projectiles
				for(int i = 0; i < lasers.size(); i++)
				{
					//Laser tempLaser = ((Laser)(lasers.listIterator(i)));
					Laser tempLaser = lasers.get(i);
					tempLaser.update((float)t/100.0f);
					//check if laser has hit the terrain, if it has then kill it
					float terrainY = player[0].terrain.terrainIntersection(new Vector3(tempLaser.location.x, tempLaser.location.z, 0));
					if(tempLaser.location.y < terrainY) //kill it
					{
						lasers.remove(i);
					}
					else
					{
						for(int j = 0; j < numberOfPlayers; j++)
						{
							float dist = (float)Math.sqrt(Math.pow(player[j].position.x - tempLaser.location.x, 2) + Math.pow(player[j].position.z - tempLaser.location.z, 2));
							if(dist < 0.2f) //we hit a robot!, add damage and remove the projectile	
							{
								player[j].health -= tempLaser.strength/player[j].armor;
								//kill it
								lasers.remove(i);
							}

						} //j
					} //else
				} //i

				//copy the lasers to scene for rendering
				scene.lasers = lasers;

				//----------------------------------------------------
				//now that the players are updated, figure out if anyone got a resource or was hit
				for(int i = 0; i < numberOfPowerups; i++)
				{		
					scene.resources[i] = resources[i];
					//System.out.println(scene.resources[i].location.x);
					for(int j = 0; j < numberOfPlayers; j++)
					{

						float dist = (float)Math.sqrt(Math.pow(player[j].position.x - resources[i].location.x, 2) + Math.pow(player[j].position.z - resources[i].location.z, 2));
						if(dist < 1.0) //deactiveate the resource and give it to the robot
						{
							if(resources[i].type == 0) //we've got an energy
							{
								player[j].energy = player[j].maxEnergy;
							}
							if(resources[i].type == 1) //we've got speed 
							{
								player[j].max_speed *= 1.1;
								player[j].maxIncline *= 1.1;
							}
							if(resources[i].type == 2) //we've got ammo
							{
								player[j].ammo += 5;
							}
							if(resources[i].type == 3) //we've got health and armor
							{
								player[j].health += 20;
								player[j].armor *= 1.1;
							}
							resources[i].active = false;
						}

					}
				}
			}

		} catch (Exception ex) {
			animator = null;
			Global.outputArea.setText("Error in the main program".concat("\n\n").concat(Global.outputArea.getText()));
			ex.printStackTrace();
		}
	}


	public void updateGlobal ()
	{
		Global.resource_list.clear();
		
		for(int i = 0; i < numberOfPowerups; i++)
		{
			Global.resource_list.add(resources[i].projectOnResourceClass());
		}

		Global.enemy_list.clear();
		for (int i = 1; i < numberOfPlayers; i++)
		{
			Global.enemy_list.add(player[i].projectOnEnemyClass());
		}
	}

}