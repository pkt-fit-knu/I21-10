package project;

import java.util.ArrayList;

public class GroupWindow extends Window {
	public GroupWindow(PixelPoint corn, Document doc, Window ancestor) {
		super(corn, doc, ancestor);
		// TODO Auto-generated constructor stub
	}

	ArrayList<Window> elements;
}

/*
 * Document->   group)(?) 
 *             /   		\
 (combo of 2)Group1    Question2(8/9 b)
 15/25 + references to children
 *       /    |
 *Question1.1    Question1.2
 * (10/20 b)		(5/5 b)
 *         	
 * 
 */