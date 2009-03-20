package app;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.awt.GridLayout;
import java.awt.*;

import javax.media.opengl.GLCanvas;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JFrame;
import javax.swing.JList;

import com.sun.opengl.util.Animator;

public class Main extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 7633042051769682994L;
	
	protected GLCanvas canvas;
	protected Animator animator;
	Robot player;
	Level level;
	Scene scene;

	TextArea editArea;
	JButton start; 
	JMenuBar menu;
	
	
	public Main () {
		super();
	}

	
	public void init () {
		
		
		this.setName("ROBOT");
		
		this.setLayout(new BorderLayout());
		
		menu = new JMenuBar();
		
		JMenu file = new JMenu("File");
		file.add(new JMenuItem("New"));
		file.add(new JMenuItem("Open"));
		file.add(new JMenuItem("Save"));
		file.add(new JMenuItem("Save As"));
		//file.add(new JMenu("plop"));
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		file.add(exit);
		
		JMenu item = new JMenu("Items");
		item.add(new JMenuItem("if"));
		item.add(new JMenuItem("while"));
		item.add(new JMenuItem("for"));
		
		menu.add(file);
		menu.add(item);
		
		this.add(menu, BorderLayout.NORTH);
		
		//set the project viewer and the toolbox
		JList project_view = new JList();
		project_view.setMinimumSize(new Dimension(100,100));
		
		String[] data = {"if then else", "repeat", "instruction"};
		JList toolbox = new JList(data);
		toolbox.setMinimumSize(new Dimension(100,100));
		
		JSplitPane tools = new JSplitPane(JSplitPane.VERTICAL_SPLIT, project_view, toolbox);
		tools.setDividerLocation(150);
		tools.setBorder(null);
		tools.setOneTouchExpandable(true);
		
		
		
		//set the editor
		JPanel editor = new JPanel();
		editor.setLayout(new BoxLayout(editor, BoxLayout.Y_AXIS));
		editor.setMinimumSize(new Dimension(300,100));
		
		editArea = new TextArea("Add your text here",1,1, TextArea.SCROLLBARS_VERTICAL_ONLY);
		start = new JButton("Run!");
		
		editor.add(editArea);
		editor.add(start);
		
		
		JSplitPane tools_write = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tools, editor);
		tools_write.setDividerLocation(150);
		tools_write.setBorder(null);
		tools_write.setOneTouchExpandable(true);
		
		
		//set the 3d viewer
		GLCanvas view3D = new GLCanvas();
	    
	    //create level object
	    Level level = new Level("level1.lvl");
	    
	    Scene scene = new Scene();
	    //create the player object
	    Robot player = new Robot(scene.terrain);
	    
	    view3D.addGLEventListener(scene);
	   
	    final Animator animator = new Animator(view3D);
	   
	    animator.start();
	   
		JPanel control_view = new JPanel();
		control_view.setLayout(new GridLayout(1,2));
		control_view.add(tools_write);
		control_view.add(view3D);
		
		this.add(control_view, BorderLayout.CENTER);
		
		
		setSize(1000,600);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		
		
		while(true)
	    {
	    	//update the players decision
	    	player.think();
	    	scene.player = player;
	    }
	    
		
	}
	
	public void actionPerformed (ActionEvent e) {
		System.exit(0);
	}
	
	public static void main (String[] args) {
		
		Main display = new Main();
		display.init();
		
	}
}
