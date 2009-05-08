package app;

import java.awt.TextArea;
import java.util.LinkedList;

public class Global {
	
	static TextArea outputArea = new TextArea("",20,20, TextArea.SCROLLBARS_VERTICAL_ONLY);
	static LinkedList<Enemy> enemy_list = new LinkedList<Enemy>();
	static LinkedList<Resource> resource_list = new LinkedList<Resource>();

}
