import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class Display extends JApplet implements ActionListener {

	TextArea textArea;
	JButton start; 
	JMenuBar menu;
	
	public void init () {
		
		this.setName("ROBOT");
		
		this.setLayout(new BorderLayout());
		
		menu = new JMenuBar();
		
		JMenu file = new JMenu("File");
		file.add(new JMenuItem("New"));
		file.add(new JMenuItem("Open"));
		file.add(new JMenuItem("Save"));
		file.add(new JMenuItem("Save As"));
		
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
		
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(1,2));
		
		JPanel edit = new JPanel();
		edit.setLayout(new BoxLayout(edit, BoxLayout.Y_AXIS));
		
		textArea = new TextArea("Add your text here", 5, 40);
		start = new JButton("Run!");
		
		edit.add(textArea);
		edit.add(start);
		
		center.add(edit);
		
		JPanel show = new JPanel();
		center.add(show);
		
		this.add(center, BorderLayout.CENTER);
		
		resize(600,600);
	}
	
	public void actionPerformed (ActionEvent e) {
		System.exit(0);
	}
		
}
