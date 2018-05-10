 

import java.awt.MenuItem;

import javax.swing.JFrame;

public class Surround {
	
	public static void main (String[] args)
	{	
		JFrame frame = new JFrame ("Surround!");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		SurroundPanel panel = new SurroundPanel();
		frame.setJMenuBar(SurroundPanel.menuBar);
		frame.getContentPane().add(panel);
		frame.pack();
		//frame.setSize(800, 800);
		frame.setVisible(true);
	}
	
}
