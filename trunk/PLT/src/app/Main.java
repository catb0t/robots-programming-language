package app;

import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

import javax.media.opengl.GLCanvas;
import javax.swing.*;

import com.sun.opengl.util.Animator;


public class Main extends JFrame implements ActionListener, TextListener, KeyListener {
	
	private static final long serialVersionUID = 7633042051769682994L;
	
	//protected GLCanvas canvas;
	GLCanvas view3D;
	protected Animator animator;
	Robot player;
	Level level;
	Scene scene;
	
	Animation anime;
	
	public Terrain terrain = null;
	
	TextArea editArea;
	//JEditorPane editArea;
	TextArea javaArea;
	TextArea outputArea;
	JButton start;
	JMenuBar menu;
    JTabbedPane tabbedPane;
	
    JPanel control_view;
    
	String filename;
	boolean fileChanged = false;
	
	Compiler compiler;
	
	boolean animation = false;
	
	int enemies_numb=0;
	
	public Main () {
		super();
	}
	
	
	public void setPlayer (RobotInterface r)
	{
		player = (Robot) r;
		player.init(terrain);
		anime.player[0] = player;
	}

	
	public void init (int enemies_number) {
		
		enemies_numb=enemies_number;

                Global.initialize();
		
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
		
		//JSplitPane tools = new JSplitPane(JSplitPane.VERTICAL_SPLIT, project_view, toolbox);
		//tools.setDividerLocation(150);
		//tools.setBorder(null);
		//tools.setOneTouchExpandable(true);
		
		
		
		//set the editor
		JPanel editor = new JPanel();
		editor.setLayout(new BoxLayout(editor, BoxLayout.Y_AXIS));
		editor.setMinimumSize(new Dimension(300,100));
		
		tabbedPane = new JTabbedPane();
		
		editArea = new TextArea("think\n|\n+---end",20,20, TextArea.SCROLLBARS_VERTICAL_ONLY);
		//editArea = new JEditorPane("text/html" , "<font color=\"font color\">think</font>\n|\n+---done");
		editArea.addTextListener(this);
		//editArea.addKeyListener(this);

                //editArea.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
                //editArea.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
                //editArea.enableInputMethods(true);
               editArea.setFont(new Font("Monaco", Font.BOLD, 12));
               editArea.setBackground(Color.DARK_GRAY);
               editArea.setForeground(Color.green);
               //editArea.setCaretColor(Color.white);

               editArea.addKeyListener(new KeyAdapter()
                 {
                     public void keyPressed(KeyEvent e)
                     {
                         if (e.getKeyCode() == KeyEvent.VK_LEFT)
                         {
                             e.consume();

                             int i,j;

                             if (e.isAltDown()) {
                                 String t = editArea.getText();
                                 i = t.lastIndexOf(" ",editArea.getCaretPosition());
                                 if (i == -1) {
                                    i = t.lastIndexOf("\n",editArea.getCaretPosition());
                                    if (i == -1) i = 0;
                                 }
                                 if (i == editArea.getCaretPosition()) i--;

                                 System.out.println(i + " " + editArea.getCaretPosition()); 
                             } else {
                                 i = editArea.getCaretPosition()-1;
                             }

                             if (i <= editArea.getText().length()) {

                                try {
                                   editArea.setCaretPosition(i);
                                } catch (Exception ex) {
                                   // Do nothing
                                }

                             }
                         }
          
                         if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                         {
                             e.consume();

                             int i;

                             if (e.isAltDown()) {
                                 String t = editArea.getText();
                                 i = t.indexOf(" ",editArea.getCaretPosition());
                                 if (i == -1) {
                                    i = t.indexOf("\n",editArea.getCaretPosition());
                                    if (i == -1) i = editArea.getText().length();
                                 }
                                 if (i == editArea.getCaretPosition()) i++;
                                 //System.out.println(i + " " + editArea.getCaretPosition()); 
                             } else {
                                 i = editArea.getCaretPosition()+1;
                             }

                             if (i <= editArea.getText().length()) {

                                try {
                                   editArea.setCaretPosition(i);
                                } catch (Exception ex) {
                                   // Do nothing
                                }

                             }

                         }

                         if (e.getKeyCode() == KeyEvent.VK_UP)
                         {
                             e.consume();
                             String t = editArea.getText();
                             int i = t.lastIndexOf("\n",editArea.getCaretPosition());
                             if (i == editArea.getCaretPosition()) i--;
                             
                             if (i <= editArea.getText().length()) {

                                try {
                                   editArea.setCaretPosition(i);
                                } catch (Exception ex) {
                                   // Do nothing
                                }

                             }
                         }

                         if (e.getKeyCode() == KeyEvent.VK_DOWN)
                         {
                             e.consume();
                             String t = editArea.getText();
                             int i = t.indexOf("\n",editArea.getCaretPosition());
                             if (i == editArea.getCaretPosition()) i++;
                             
                             if (i <= editArea.getText().length()) {

                                try {
                                   editArea.setCaretPosition(i);
                                } catch (Exception ex) {
                                   // Do nothing
                                }

                             }
                         }

                     }
                 });
                 
		
		tabbedPane.addTab("robot source", editArea);
		
		javaArea = new TextArea("",20,20, TextArea.SCROLLBARS_VERTICAL_ONLY);
		javaArea.setEditable(false);
		
                javaArea.setFont(new Font("Monaco", Font.BOLD, 12));
                javaArea.setBackground(Color.DARK_GRAY);
                javaArea.setForeground(Color.green);


		tabbedPane.addTab("java source", javaArea);
		
		//outputArea = new TextArea("",20,20, TextArea.SCROLLBARS_VERTICAL_ONLY);
		Global.outputArea.setEditable(false);
		
		tabbedPane.addTab("output", Global.outputArea);
		
		//editor.add(editArea);
		editor.add(tabbedPane);
		
		JPanel runparse = new JPanel();
		runparse.setLayout(new BoxLayout(runparse, BoxLayout.X_AXIS));
		
		start = new JButton("Run");
		start.setActionCommand("Run");		//032009 by joseph
		start.addActionListener(this);		//032009 by joseph
		
		JButton parse = new JButton("Parse");
		parse.setActionCommand("Parse");	//032009 by joseph
		parse.addActionListener(this);		//032009 by joseph
		
		
		JButton indent = new JButton("Indent");
		indent.setActionCommand("indent");
		indent.addActionListener(this);
		
		JButton reset = new JButton("Reset");
		reset.setActionCommand("reset");
		reset.addActionListener(this);
		//reset.setEnabled(false);
		
		
		runparse.add(start);
		runparse.add(parse);
		runparse.add(indent);
		runparse.add(reset);
		
		
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
		//GLCanvas
		view3D = new GLCanvas();
	    
	    //create level object
	    Level level = new Level("level1.lvl");
	    
	    scene = new Scene();

	    terrain = new Terrain();
	    //create the player object
	    player = new Robot(terrain);
	    
	    view3D.addGLEventListener(scene);
	   
	    final Animator animator = new Animator(view3D);
	   
	    animator.start();
	   
		control_view = new JPanel();
		control_view.setLayout(new GridLayout(1,2));
		//control_view.add(tools_write);
		control_view.add(editor);
		control_view.add(view3D);
		
		this.add(control_view, BorderLayout.CENTER);
		
		
		setSize(1000,600);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);

		
		
		Robot[] players = new Robot[enemies_numb+1];
		players[0] = player;
		
		for (int i=0; i<enemies_numb; i++)
		{
			Vector3 initP = new Vector3((float) Math.random()* 30f, 0f, (float) Math.random()* 30f);
			players[i+1] = new RobotEnemy(terrain, initP);
		}
		
		anime = new Animation(players, players.length, scene);
	//	start.doClick();
	  
	    
		
	}
	
	
	
	
	public void reset () {
		System.out.println("Reseting the simulation");
		
		control_view.remove(1);
		
		view3D = new GLCanvas();
	    
	    //create level object
	    Level level = new Level("level1.lvl");
	    
	    scene = new Scene();

	    terrain = new Terrain();
	    //create the player object
	    player = new Robot(terrain);
	    
	    view3D.addGLEventListener(scene);
	   
	    final Animator animator = new Animator(view3D);
	   
	    animator.start();
	    
	    Robot[] players = new Robot[enemies_numb+1];
		players[0] = player;
		
		for (int i=0; i<enemies_numb; i++)
		{
			Vector3 initP = new Vector3((float) Math.random()* 30f, 0f, (float) Math.random()* 30f);
			players[i+1] = new RobotEnemy(terrain, initP);
		}
		
		anime = new Animation(players, players.length, scene);
		
		control_view.add(view3D);
		
		control_view.validate();
	}
	
	
	public void animate () {

		if (anime.animator==null) {
			anime.animator = new Thread(anime);
		}
		
		Global.outputArea.setText("");
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
				
				javaArea.setText("");
                                Global.outputArea.setText("");
				
				compiler.setFileName("tempcode.robot");
				int retCode = compiler.run();
				
				if (file.exists()) file.delete();
				
				displayJava();
				
                                if (retCode == 0)
                                   tabbedPane.setSelectedIndex(1);
                                else
                                   tabbedPane.setSelectedIndex(2);

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
		else if ("reset".equals(event.getActionCommand())) {
			
			reset();
		}
		else if ("new".equals(event.getActionCommand())) {
			
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
					System.out.println("new File");
					editArea.setText("think\n|\n+---end");
					filename = null; 
				}
				else if (choice==2) {
					return;
				}
			}
			else {
				editArea.setText("think\n|\n+---end");
				filename = null;
			}
			fileChanged = false;
			//editArea.removeTextListener(this);
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
		else if ("indent".equals(event.getActionCommand())) {
			indent();
		}
	}
	
	public void textValueChanged (TextEvent event) {
		fileChanged = true;
		editArea.removeTextListener(this);
	}
	
	public void displayJava () {
		try {

                   File file = new File("RobotCompiled.java");

                   if (!file.exists()) return;

			BufferedReader in = new BufferedReader(new FileReader(file));
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
	
	public void indent() {
		System.out.println("proceed indentation");
		
		String[] program = editArea.getText().split("\n");
		
		String indentedProgram = "";
		
		LinkedList<String> firstL = new LinkedList<String>();
		String first = "";
		
		for (int i=0; i<program.length; i++) {
			
			//System.out.println(program[i]);
			
			String[] elements = program[i].split(" ");
			
			int start = 0;
			
			while ((start<elements.length) && isWhiteSpace(elements[start])) {
				start++;
			}
			
			String line = "";
			
			if (start<elements.length) {
				
				for (int j=start; j<elements.length-1; j++) {
					if (!elements[j].equals("")) {
						line = line.concat(elements[j].concat(" "));
					}
				}
				line = line.concat(elements[elements.length-1]);
				
				
				if (elements[start].equals("means")||elements[start].equals("done")||elements[start].equals("end")||elements[start].equals("gives")) {
					firstL.removeLast();
					first = createFirst(firstL);
					
					line = first.concat("+---").concat(line);
				}
				else if (elements[start].equals("+---means")||elements[start].equals("+---done")||elements[start].equals("+---end")||elements[start].equals("+---gives")) {
					firstL.removeLast();
					first = createFirst(firstL);
					
					line = first.concat(line);
				}
				else if (elements[start].equals("think")||elements[start].equals("instruction")) {
					line = first.concat(line);
					firstL.addLast("|   ");
					first = createFirst(firstL);
				}
				else if (elements[start].equals("while")||elements[start].equals("for")||elements[start].equals("foreach")||elements[start].equals("if")||elements[start].equals("repeat")) {
					line = first.concat(line);
					firstL.addLast("|   ");
					first = createFirst(firstL);
				}
				else if (elements[start].equals("+---else")) {
					firstL.removeLast();
					first = createFirst(firstL);
					
					line = first.concat(line);
					
					firstL.addLast("|   ");
					first = createFirst(firstL);
				}
				else if (elements[start].equals("else")) {
					firstL.removeLast();
					first = createFirst(firstL);
					
					line = first.concat("+---").concat(line);
					
					firstL.addLast("|   ");
					first = createFirst(firstL);
				}
				else {
					line = first.concat(line);
				}
			}
			else {
				line = first.concat(line);
			}
			
			line = line.concat("\n");
			
			indentedProgram = indentedProgram.concat(line);
		}
		
		editArea.setText(indentedProgram);
	}
	
	public boolean isWhiteSpace (String s) {
		return s.equals("")||s.equals("|")||s.equals("+---")||s.equals(" ");
	}
	
	public String createFirst (LinkedList<String> l) {
		String sol = "";
		
		for (String u:l) {
			sol = sol.concat(u);
		}
		
		return sol;
	}
	
	public void keyPressed (KeyEvent e) {
		
	}
	
	public void keyReleased (KeyEvent e) {
		
	}

	public void keyTyped (KeyEvent e) {
		
		if (e.getKeyChar()==KeyEvent.VK_ENTER) {
			int line = getCaretPosition();
			//int pos = editArea.getCaretPosition();
			indent();
			//editArea.setCaretPosition(pos+2);
			setCaretPosition(line);
		}
		if (e.getKeyChar()==KeyEvent.VK_RIGHT) {
			System.out.println("right");
		}
		
	}
	
	public int getCaretPosition() {
		int pos = editArea.getCaretPosition();
		
		String[] program = editArea.getText().split("\n");
		
		int line = 0;
		int count = 0;
		for (int i=0; i<program.length; i++) {
			count = count + program[i].length();
			
			if (pos > count) {
				line++;
				count++;
			}
			else {
				System.out.println(line);
				break;
			}
		}
		return line;
	}
	
	public void setCaretPosition (int line) {
		String[] program = editArea.getText().split("\n");
		
		int count = program[line].length();
		for (int i=0; i<line; i++) {
			count = count + program[i].length() + 1 ;
		}
		
		editArea.setCaretPosition(count);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Main

	public static void main (String[] args) {

		/*
		RobotList<Boolean> la = new RobotList<Boolean>(Boolean.class);
		la.set(1f, new Boolean(true));
		la.set(4f, new Boolean(true));
		
		RobotList<Float> l = new RobotList<Float>(Float.class);
		l.set(1f, new Float(1f));
		l.set(4f, new Float(5f));
		*/
		
		Main display = new Main();
		display.init(1);
	}
}
