package app;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

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
	
	String filename;
	boolean fileChanged = false;
	
	
	public Main () {
		super();
	}

	
	public void init () {
		
		this.setName("ROBOT");
		
		this.setLayout(new BorderLayout());
		
		menu = new JMenuBar();
		
		JMenu file = new JMenu("File");
		
		JMenuItem newFile = new JMenuItem("New");
		newFile.setActionCommand("new");
		newFile.addActionListener(this);
		file.add(newFile);
		
		
		JMenuItem open = new JMenuItem("Open");
		open.setActionCommand("open");
		open.addActionListener(this);
		file.add(open);
		
		JMenuItem save = new JMenuItem("Save");
		save.setActionCommand("save");
		save.addActionListener(this);
		file.add(save);
		
		JMenuItem saveAs = new JMenuItem("Save As");
		saveAs.setActionCommand("saveas");
		saveAs.addActionListener(this);
		file.add(saveAs);
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.setActionCommand("exit");
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
		start.setActionCommand("Run!");		//032009 by joseph
		start.addActionListener(this);		//032009 by joseph
		
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
		//System.exit(0);		//032009 by joseph
		if ("Run!".equals(e.getActionCommand())) {	//032009 by joseph
			System.out.println("Start Compliler");
			try {
				//Save the contents of TextArea to a text file.
				System.out.println(editArea.getText());
				
				File file=new File("robotcode.txt");
				PrintWriter out = new PrintWriter(new FileWriter(file));
				out.print(editArea.getText());
				out.close();

				//Run compiler with robotcode.txt
				Runtime.getRuntime().exec("..\\Compiler\\make"); // "C:\\Windows\\Explorer.exe");
				
				//Run Application
				//Runtime.getRuntime().exec(".\\app\\java main");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if ("new".equals(e.getActionCommand())) {
			System.out.println("new File");
			
			if (fileChanged) {
				
			}
			else {
				editArea.setText("");
				filename = null;
			}
		}
		else if ("save".equals(e.getActionCommand())) {
			System.out.println("save file");
			
			if (filename==null) {
				//go to saveas
				this.actionPerformed(new ActionEvent(this, 0, "saveas"));
			}
		}
		else if ("saveas".equals(e.getActionCommand())) {
			System.out.println("save file as");
			
			//filename = ;
		}
		else if ("open".equals(e.getActionCommand())) {
			System.out.println("open file");
			
			if (fileChanged) {
				
			}
			else {
				
			}
		}
		else if ("exit".equals(e.getActionCommand())) {
			System.exit(0);
		}
	}
	
	public static void main (String[] args) {
		
		Main display = new Main();
		display.init();
		
	}
}
