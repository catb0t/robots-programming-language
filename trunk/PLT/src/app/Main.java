package app;

import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

import javax.media.opengl.GLCanvas;
import javax.swing.*;

import com.sun.opengl.util.Animator;

public class Main extends JFrame implements ActionListener, TextListener{
	
	private static final long serialVersionUID = 7633042051769682994L;
	
	protected GLCanvas canvas;
	protected Animator animator;
	Robot player;
	Level level;
	Scene scene;
	
	Animation anime;
	
	public Terrain terrain = null;
	
	TextArea editArea;
	TextArea javaArea;
	JButton start;
	JButton parse;
	JMenuBar menu;
	
	String filename;
	boolean fileChanged = false;
	
	Compiler compiler;
	
	boolean animation = false;
	
	
	public Main () {
		super();
	}
	
	
	public void setPlayer (RobotInterface r)
	{
		r.think();
		player = (Robot) r;
		player.init(terrain);
		player.think();
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
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		editArea = new TextArea("Add your text here",1,1, TextArea.SCROLLBARS_VERTICAL_ONLY);
		editArea.addTextListener(this);
		
		tabbedPane.addTab("robot source", editArea);
		
		
		javaArea = new TextArea("",1,1, TextArea.SCROLLBARS_VERTICAL_ONLY);
		javaArea.setEditable(false);
		
		tabbedPane.addTab("java source", javaArea);
		
		
		//editor.add(editArea);
		editor.add(tabbedPane);
		
		JPanel runparse = new JPanel();
		runparse.setLayout(new BoxLayout(runparse, BoxLayout.X_AXIS));
		
		start = new JButton("Run");
		start.setActionCommand("Run");		//032009 by joseph
		start.addActionListener(this);		//032009 by joseph
		
		parse = new JButton("Parse");
		parse.setActionCommand("Parse");	//032009 by joseph
		parse.addActionListener(this);		//032009 by joseph
		
		runparse.add(start);
		runparse.add(parse);
		
		editor.add(runparse);
		
		/*
		JSplitPane tools_write = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tools, editor);
		tools_write.setDividerLocation(150);
		tools_write.setBorder(null);
		tools_write.setOneTouchExpandable(true);
		*/
		
		//set the compiler
		compiler = new Compiler(this);
		
		//set the 3d viewer
		GLCanvas view3D = new GLCanvas();
	    
	    //create level object
	    Level level = new Level("level1.lvl");
	    
	    scene = new Scene();
	    terrain = new Terrain();
	    //create the player object
	    player = new Robot(terrain);
	    
	    view3D.addGLEventListener(scene);
	   
	    final Animator animator = new Animator(view3D);
	   
	    animator.start();
	   
		JPanel control_view = new JPanel();
		control_view.setLayout(new GridLayout(1,2));
		//control_view.add(tools_write);
		control_view.add(editor);
		control_view.add(view3D);
		
		this.add(control_view, BorderLayout.CENTER);
		
		
		setSize(1000,600);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);

		
		anime = new Animation(player, scene);
		start.doClick();
	  
	    
		
	}
	
	
	public void animate () {

		if (anime.animator==null) {
			anime.animator = new Thread(anime);
		}
		
		anime.animator.start();
		
	}
	
	public void stopAnimate () {

		anime.animator= null;
		
	}
	
		
	
	public void actionPerformed (ActionEvent event) {
		
		if ("Parse".equals(event.getActionCommand())) {
			
			stopAnimate();
			
			System.out.println("Start Compiler");
			
			try {
				
				File file = new File("tempcode.robot");
				PrintWriter out = new PrintWriter(new FileWriter(file));
				out.print(editArea.getText());
				out.close();
				
				compiler.setFileName("tempcode.robot");
				compiler.run();
				
				file.delete();
				
				displayJava();
				
				//load class
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if ("Run".equals(event.getActionCommand())) {
			
			animation = !animation;
			
			if (animation) {
				start.setText("Stop");
				start.validate();
				animate();
			} else {
				start.setText("Run");
				start.validate();
				stopAnimate();
			}
		}
		else if ("new".equals(event.getActionCommand())) {
			System.out.println("new File");
			
			if (fileChanged) {
				Object[] options = {"Yes", "No", "Cancel"};
				int choice = JOptionPane.showOptionDialog(this,
														  "Do you want to save you current file ?",
														  "Save changes",
														  JOptionPane.YES_NO_CANCEL_OPTION,
														  JOptionPane.QUESTION_MESSAGE,
														  null,
														  options,
														  options[2]);
				
				if (choice==0) {
					this.actionPerformed(new ActionEvent(this, 0, "save"));
				}
				else if (choice==1) {
					editArea.setText("");
					filename = null; 
				}
				else if (choice==2) {
					return;
				}
			}
			else {
				editArea.setText("");
				filename = null;
			}
			fileChanged = false;
			editArea.removeTextListener(this);
			editArea.addTextListener(this);
		}
		else if ("save".equals(event.getActionCommand())) {
			System.out.println("save file");
			
			if (filename==null) {
				//go to saveas
				this.actionPerformed(new ActionEvent(this, 0, "saveas"));
			}
			else {
				try {
					
					File file = new File(filename);
					PrintWriter out = new PrintWriter(new FileWriter(file));
					out.print(editArea.getText());
					out.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			fileChanged = false;
			editArea.addTextListener(this);
		}
		else if ("saveas".equals(event.getActionCommand())) {
			System.out.println("save file as");
			
			FileDialog filedialog = new FileDialog(this, "save file", FileDialog.SAVE);
			filedialog.setVisible(true);
			
			String name = filedialog.getFile();
			if (name ==  null) {
				return;
			}

			filename = filedialog.getDirectory()+name+".robot";
			
			this.actionPerformed(new ActionEvent(this, 0, "save"));
		}
		else if ("open".equals(event.getActionCommand())) {
			System.out.println("open file");
			
			if (fileChanged) {
				Object[] options = {"Yes", "No", "Cancel"};
				int choice = JOptionPane.showOptionDialog(this,
														  "Do you want to save you current file ?",
														  "Save changes",
														  JOptionPane.YES_NO_CANCEL_OPTION,
														  JOptionPane.QUESTION_MESSAGE,
														  null,
														  options,
														  options[2]);
				
				if (choice==0) {
					this.actionPerformed(new ActionEvent(this, 0, "save"));
				}
				else if (choice==2) {
					return;
				}
			}
				
			FileDialog filedialog = new FileDialog(this, "open file", FileDialog.LOAD);
			filedialog.setVisible(true);

			String name = filedialog.getFile();
			if (name ==  null) {
				return;
			}

			filename = filedialog.getDirectory()+name;

			try {
				BufferedReader in = new BufferedReader(new FileReader(filename));
				String str;
				String sol = "";
				while ((str = in.readLine()) != null) {
					sol+=str+"\n";
				}
				in.close();

				editArea.setText(sol);

			} catch (IOException e) {
				e.printStackTrace();
			}

			editArea.addTextListener(this);
			fileChanged = false;
		}
		else if ("exit".equals(event.getActionCommand())) {
			
			if (fileChanged)
			{
				Object[] options = {"Yes", "No", "Cancel"};
				int choice = JOptionPane.showOptionDialog(this,
														  "Do you want to save you current file ?",
														  "Save changes",
														  JOptionPane.YES_NO_CANCEL_OPTION,
														  JOptionPane.QUESTION_MESSAGE,
														  null,
														  options,
														  options[2]);
				
				if (choice==0) {
					this.actionPerformed(new ActionEvent(this, 0, "save"));
				}
				else if (choice==2) {
					return;
				}
			}
			System.exit(0);
		}
	}
	
	public void textValueChanged (TextEvent event) {
		fileChanged = true;
		editArea.removeTextListener(this);
	}
	
	public static void main (String[] args) {
		
		Main display = new Main();
		display.init();
		
	}
	
	public void displayJava () {
		try {
			BufferedReader in = new BufferedReader(new FileReader("RobotCompiled.java"));
			String str;
			String sol = "";
			while ((str = in.readLine()) != null) {
				sol+=str+"\n";
			}
			in.close();

			javaArea.setText(sol);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
