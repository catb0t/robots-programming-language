package app;

import java.awt.*;
import java.util.LinkedList;

public class Global {
        static String nl = System.getProperty("line.separator");	
	static TextArea outputArea = new TextArea("",20,20, TextArea.SCROLLBARS_BOTH);
	static LinkedList<Enemy> enemy_list = new LinkedList<Enemy>();
	static LinkedList<Resource> resource_list = new LinkedList<Resource>();

        
        static void initialize() {
        
           outputArea.setFont(new Font("Monaco", Font.BOLD, 12));
           outputArea.setBackground(Color.DARK_GRAY);
           outputArea.setForeground(Color.green);
        }

        static void WriteLineToOutput(String s) {

           outputArea.append(s + nl);

        }

}
