package plt;


import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLCanvas;

import com.sun.opengl.util.Animator;

public class Main extends Frame {
	
	private static final long serialVersionUID = 7633042051769682994L;
	
	protected GLCanvas canvas;
	protected Animator animator;

	
	public static void main(String[] args) {
		Frame frame = new Frame("Robots");
	    GLCanvas canvas = new GLCanvas();
	    
	    //create level object
	    Level level = new Level("level1.lvl");
	    
	    Scene scene = new Scene();
	    //create the player object
	    Robot player = new Robot(scene.terrain);
	    
	    canvas.addGLEventListener(scene);//new Scene());
	    frame.setSize(1300, 900);
	    frame.add(canvas);
	//    frame.setSize(800, 600);
	    final Animator animator = new Animator(canvas);
	    frame.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	          new Thread(new Runnable() {
	              public void run() {
	                animator.stop();
	                System.exit(0);
	              }
	            }).start();
	        }
	      });
	    frame.setVisible(true);
	    animator.start();
	    
	    //main game loop
	    while(true)
	    {
	    	//update the players decision
	    	player.think();
	    	scene.player = player;
	    	
	    	
	    	
	    }
	    
	}
}
