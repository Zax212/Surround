/**********************************************************************
 * Surroundpanel does all the GUI for the grid based game Surround.
 * 
 * @author Zach Hopman
 * @version Winter 2015
 */
 

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public class SurroundPanel extends JPanel{

	/**2d array of buttons to display grid */
	private JButton[][] board;
	/**2d array of objects of the class cell */
	private Cell[][] iBoard;
	/**object representing the whole game */
	private SurroundGame game;
	/**menu item to create a new game */
	private JMenuItem newGameItem;
	/**menu item to quit game */
	private JMenuItem quitItem;
	/**button to reset the grid */
	private JButton reset;
	/**label to print the game status */
	private JLabel status;
	/**label to print the number of wins */
	private JLabel wins;
	/** number of wins for players 1-5 */
	private int w1 = 0, w2 = 0, w3 = 0, w4 = 0, w5 = 0;
	/**locks the grid once a player has won */
	private boolean flag = true;
	/**the menubar for the window */
	public static JMenuBar menuBar;
	/**the menu for the window */
	private JMenu menu;
	/** the icons for plaayers */
	private ImageIcon Icon1,Icon2,Icon3,Icon4,Icon5;
	/** empty Icon */
	private ImageIcon emptyIcon;

	/*****************************************************************
	constructor creates new panel
	 *****************************************************************/
	public SurroundPanel() {

		newPanel();


	}
	/*****************************************************************
	displays the grid
	 *****************************************************************/
	private void displayBoard() {

		iBoard = game.getBoard ();

		for (int r = 0; r < game.getRows(); r++) 
			for (int c = 0; c < game.getCols(); c++) {

				board[r][c].setIcon(emptyIcon);
				if(game.Safe(r, c)==1)
					board[r][c].setBackground(Color.GREEN);
				if(game.Safe(r, c) == -1)
					board[r][c].setBackground(Color.WHITE);

				int a = iBoard[r][c].getPlayerNumber();
				if(a == 1)
					board[r][c].setIcon(Icon1);
				if(a == 2)
					board[r][c].setIcon(Icon2);
				if(a == 3)
					board[r][c].setIcon(Icon3);
				if(a == 4)
					board[r][c].setIcon(Icon4);
				if(a == 5)
					board[r][c].setIcon(Icon5);



				//				board[r][c].setText("0");
				//
				//				int a = iBoard[r][c].getPlayerNumber();
				//				String i = Integer.toString(a);
				//
				//				board[r][c].setText(i);
			}

	}
	/*****************************************************************
	creates new panel and game instance
	 *****************************************************************/
	private void newPanel(){
		String Ssize = JOptionPane.showInputDialog (null, "Enter in the size of the board: "); 

		game = new SurroundGame();
		game.setRows(Ssize);
		game.setCols(Ssize);

		String players = JOptionPane.showInputDialog (null, "Enter the number of players: ");

		game.setNumPlayers(players);

		String start = JOptionPane.showInputDialog (null, "What player will start?: ");

		game.setStarter(start);
		game.turn.setPlayerNumber(game.getStarter());

		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		menu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(menu);

		quitItem = new JMenuItem("Quit");
		quitItem.setMnemonic(KeyEvent.VK_B);

		newGameItem = new JMenuItem("New Game");
		newGameItem.setMnemonic(KeyEvent.VK_B);

		menu.add(quitItem);
		menu.add(newGameItem);




		JPanel bottom = new JPanel();
		JPanel center = new JPanel();

		ButtonListener listener = new ButtonListener();

		quitItem.addActionListener(listener);
		newGameItem.addActionListener(listener);

		reset = new JButton("Reset");
		reset.addActionListener(listener);

		status = new JLabel("In Progress");

		wins = new JLabel("Player 1 wins: "+w1+"  Player 2 wins: "+w2+"  Player 3 wins: "+w3+"  Player 4 wins: "+w4+"  Player 5 wins: "+w5);

		center.setLayout(new GridLayout(game.getRows(),game.getCols(),3,2));
		Dimension temp = new Dimension(30,30);
		board = new JButton[game.getRows()][game.getCols()];

		for (int row = 0; row < game.getRows(); row++) 
			for (int col = 0; col < game.getCols(); col++) {

				Border thickBorder = new LineBorder(Color.blue, 2);

				board[row][col] = new JButton ("0");
				board[row][col].setPreferredSize(temp);
				board[row][col].setBorder(thickBorder);
				board[row][col].setBackground(Color.WHITE);

				board[row][col].addActionListener(listener);
				center.add(board[row][col]);

			}
		Icon1 = new ImageIcon ("src/package1/crown.png");
		Icon2 = new ImageIcon ("src/package1/club.png");
		Icon3 = new ImageIcon ("src/package1/diamond.png");
		Icon4 = new ImageIcon ("src/package1/spade.png");
		Icon5 = new ImageIcon ("src/package1/heart.png");
		emptyIcon = new ImageIcon ("src/package1/empty.png");



		bottom.setLayout (new GridLayout(3,2));

		bottom.add(reset);
		bottom.add(status);
		bottom.add(wins);

		displayBoard();

		add (center, BorderLayout.CENTER);
		add (bottom, BorderLayout.SOUTH);
	}
	/*****************************************************************
	handles the buttons when "pushed"
	 *****************************************************************/
	private class ButtonListener implements ActionListener {


		public void actionPerformed(ActionEvent e) {

			if (reset == e.getSource()){
				game.reset();
				status.setText("In Progress");
				flag = true;
				
				for (int row = 0; row < game.getRows(); row++) 
					for (int col = 0; col < game.getCols(); col++)
						board[row][col].setBackground(Color.WHITE);
				displayBoard();
				
			}
			if(quitItem == e.getSource())
				System.exit(0);


			if(newGameItem == e.getSource()){

				removeAll();

				newPanel();
				flag = true;
				w1 = 0;
				w2 = 0;
				w3 = 0;
				w4 = 0;
				w5 = 0;


				displayBoard();


				revalidate();
				repaint();
			}


			if(flag){

				for (int r = 0; r < game.getRows(); r++) 
					for (int c = 0; c < game.getCols(); c++) 
						if (board[r][c] == e.getSource()){
							game.select (r,c);
							
						}



				displayBoard();


				if(game.isWinner()>0){
					status.setText("Player "+ game.isWinner()+" Wins!");
					flag = false;
					if(game.isWinner() == 1)
						w1++;
					if(game.isWinner() == 2)
						w2++;
					if(game.isWinner() == 3)
						w3++;
					if(game.isWinner() == 4)
						w4++;
					if(game.isWinner() == 5)
						w5++;
					


					wins.setText("Player 1 wins: "+w1+"  Player 2 wins: "+w2
							+"  Player 3 wins: "+w3+"  Player 4 wins: "+w4
							+"  Player 5 wins: "+w5);

					displayBoard();

				}
			}
		}

	}



}



