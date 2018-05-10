/**********************************************************************
 * SurroundGame does all the thinking for the grid based game Surround.
 * 
 * @author Zach Hopman
 * @version Winter 2015
 */
 

public class SurroundGame {

	/** 2 dimensional array of the class cell */
	private Cell[][] board;
	/**enum game status to determine if a player has won  */
	private GameStatus status;
	/**a object of the cell class to determine whose turn it is  */
	public Cell turn;
	/**the number of rows for the grid  */
	private int rows = 20;
	/**the number of columns for the grid  */
	private int cols = 20;
	/**the number of players  */
	private int numPlayers = 5;
	/**an array holding all of the players   */
	private int[] Players;
	/** who starts the game  */
	private int starter = 0;

	/*****************************************************************
  	Constructor that sets all attributes to default
	 *****************************************************************/
	public SurroundGame() {
		status = GameStatus.IN_PROGRESS;
		board = new Cell[rows][cols];
		turn = new Cell();	
		Players = new int[numPlayers];
		reset();
	}
	/*****************************************************************
	  method used to get the 2 dimensional array of cells
	  @return the board
	 *****************************************************************/
	public Cell[] [] getBoard() {
		return board;
	}

	/*****************************************************************
	  used to set all attributes of game to default

	 *****************************************************************/
	public void reset() {

		for (int row = 0; row < rows; row++)     
			for (int col = 0; col < cols; col++){
				board[row][col] = new Cell();
				board[row][col].setPlayerNumber(0);
			}
		for (int i = 0; i <= numPlayers-1;i++)
			Players[i]=i+1;
		turn.setPlayerNumber(Players[0]);
	}
	/*****************************************************************
	  checks if the cell in the grid is occupied and if not replaces the
	  empty cell with turn
	  @param row is the row to check
	  @param col is the column to check
	  @return true if cell is empty
	 *****************************************************************/

	public boolean select(int row, int col) {

		if (board[row][col].getPlayerNumber() == 0 ){


			board[row][col].setPlayerNumber(turn.getPlayerNumber());

			if(board[row][col].getPlayerNumber() == numPlayers)
				turn.setPlayerNumber(Players[0]);
			else
				turn.setPlayerNumber(turn.getPlayerNumber()+1);
			return true;
		}
		else

			return false;

	}

	/*****************************************************************
	  used to get number of rows
	  @return the number of rows
	 *****************************************************************/
	public int getRows() {
		return rows;
	}

	/*****************************************************************
	 used to set number of rows
	 @prows number of desired rows 
	 *****************************************************************/
	public void setRows(String prows) {

		//checks if NaN
		if(isInteger(prows)){

			int Isize = Integer.parseInt(prows);

			if(Isize < 20 && Isize > 3)
				this.rows = Isize;
			else {
				this.rows = 10;
			}

		}
		else
			this.rows = 10;

	}


	/*****************************************************************
	  used to get number of columns
	  @return number of columns
	 *****************************************************************/
	public int getCols() {
		return cols;
	}

	/*****************************************************************
	 used to set number of columns
	 @param pcols desired number of columns 
	 *****************************************************************/
	public void setCols(String pcols) {

		//checks if NaN
		if(isInteger(pcols)){
			int Isize = Integer.parseInt(pcols);

			if(Isize < 20 && Isize > 3)
				this.cols = Isize;
			else {
				this.cols = 10;
			}
		}
		else
			this.cols = 10;
	}
	/*****************************************************************
	 used to get the number of players in the game
	 @return the number of players 
	 *****************************************************************/
	public int getNumPlayers() {
		return numPlayers;
	}
	/*****************************************************************
	  used to set the number of players 
	  @param numPlayers the desired number of players
	 *****************************************************************/
	public void setNumPlayers(String numPlayers) {

		//checks if NaN
		if(isInteger(numPlayers)){

			int num = Integer.parseInt(numPlayers);

			if(num < 6 && num > 2)
				this.numPlayers = num;
			else {
				this.numPlayers = 2;
			}

		}
		else
			this.numPlayers = 2;
	}
	/*****************************************************************
	  used to check if a input is a number
	  @param str the string input to check if number
	  @return true if number. false if NaN
	 *****************************************************************/
	public static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
	/*****************************************************************
	used to the starting player
	@param the player who will start  
	 *****************************************************************/
	public void setStarter(String starter) {

		//checks if NaN
		if(isInteger(starter)){

			int num = Integer.parseInt(starter);

			if(num < getNumPlayers() && num > 0)
				this.starter = num;
			else {
				this.starter = 1;
			}

		}
		else
			this.starter = 1;
	}
	public int getStarter(){
		return starter;
	}

	/*****************************************************************
	  checks a player has surrounded another player
	  @return -1 if no winner else the number of the winner
	 *****************************************************************/
	public int isWinner() {

		for (int row = 0; row < rows; row++){     
			for (int col = 0; col < cols; col++) {

				//checks if blank cell
				if(board[row][col].getPlayerNumber()== 0){

				}


				//top left corner
				else if (row == 0 && col == 0) 	{ 

					if ((board[row][col+1].getPlayerNumber() !=
							board[row][col].getPlayerNumber() && 
							board[row][col+1].getPlayerNumber() != 0) && 
							(board[row+1][col].getPlayerNumber() != 
							board[row][col].getPlayerNumber()&& 
							board[row+1][col].getPlayerNumber() != 0) &&
							(board[rows-1][col].getPlayerNumber() != 
							board[row][col].getPlayerNumber()&&
							board[rows-1][col].getPlayerNumber() != 0)&&
							(board[row][cols-1].getPlayerNumber() != 
							board[row][col].getPlayerNumber()&& 
							board[row][cols-1].getPlayerNumber() != 0))
						return board[rows-1][col].getPlayerNumber();

				}
				//top right corner
				else  if (row == 0 && col == cols-1) {	  	
					if ((board[row][col-1].getPlayerNumber() != 
							board[row][col].getPlayerNumber() && 
							board[row][col-1].getPlayerNumber() != 0) && 
							(board[row+1][col].getPlayerNumber() != 
							board[row][col].getPlayerNumber()&& 
							board[row+1][col].getPlayerNumber() != 0) &&
							(board[rows-1][cols-1].getPlayerNumber() != 
							board[row][col].getPlayerNumber()&& 
							board[rows-1][cols-1].getPlayerNumber()!=0)&&
							(board[0][0].getPlayerNumber() != 
							board[row][col].getPlayerNumber()&& 
							board[0][0].getPlayerNumber() != 0))
						return board[row+1][col].getPlayerNumber();

				}
				//bottom left corner
				else if (row == rows-1 && col == 0) {	  	
					if ((board[row][col+1].getPlayerNumber() != 
							board[row][col].getPlayerNumber() &&
							board[row][col+1].getPlayerNumber() != 0) && 
							(board[row-1][col].getPlayerNumber() != 
							board[row][col].getPlayerNumber()&& 
							board[row-1][col].getPlayerNumber() != 0)&&
							(board[0][0].getPlayerNumber() != 
							board[row][col].getPlayerNumber()&& 
							board[0][0].getPlayerNumber() != 0)&&
							(board[rows-1][cols-1].getPlayerNumber() !=
							board[row][col].getPlayerNumber()&& 
							board[rows-1][cols-1].getPlayerNumber()!=0))
						return board[row-1][col].getPlayerNumber();

				}
				//bottom right corner
				else  if (row == rows-1 && col == cols-1) {	  	
					if ((board[row][col-1].getPlayerNumber() != 
							board[row][col].getPlayerNumber() && 
							board[row][col-1].getPlayerNumber() != 0)  && 
							(board[row-1][col].getPlayerNumber() != 
							board[row][col].getPlayerNumber()&&
							board[row-1][col].getPlayerNumber() != 0) &&
							(board[0][cols-1].getPlayerNumber() != 
							board[row][col].getPlayerNumber()&& 
							board[0][cols-1].getPlayerNumber() != 0)&&
							(board[rows-1][0].getPlayerNumber() != 
							board[row][col].getPlayerNumber()&& 
							board[rows-1][0].getPlayerNumber() != 0))
						return board[row-1][col].getPlayerNumber();

				}
				//top
				else if (row == 0){
					if( (board[row][col+1].getPlayerNumber() != 
							board[row][col].getPlayerNumber() && 
							board[row][col+1].getPlayerNumber() != 0) && 
							(board[row+1][col].getPlayerNumber() != 
							board[row][col].getPlayerNumber()&& 
							board[row+1][col].getPlayerNumber() != 0) && 
							(board[row][col-1].getPlayerNumber() != 
							board[row][col].getPlayerNumber()&& 
							board[row][col-1].getPlayerNumber() != 0) &&
							(board[rows-1][col].getPlayerNumber() != 
							board[row][col].getPlayerNumber()&& 
							board[rows-1][col].getPlayerNumber() != 0))
						return board[row][col-1].getPlayerNumber();

				}
				//bottom
				else if (row == rows-1){
					if( (board[row][col+1].getPlayerNumber() != 
							board[row][col].getPlayerNumber() && 
							board[row][col+1].getPlayerNumber() != 0) && 
							(board[row-1][col].getPlayerNumber() != 
							board[row][col].getPlayerNumber()&& 
							board[row-1][col].getPlayerNumber() != 0) && 
							(board[row][col-1].getPlayerNumber() != 
							board[row][col].getPlayerNumber()&& 
							board[row][col-1].getPlayerNumber() != 0)&&
							(board[0][col].getPlayerNumber() != 
							board[row][col].getPlayerNumber()&& 
							board[0][col].getPlayerNumber() != 0))
						return board[row][col-1].getPlayerNumber();	

				}
				//left
				else if (col == 0){
					if( (board[row+1][col].getPlayerNumber() != 
							board[row][col].getPlayerNumber() && 
							board[row+1][col].getPlayerNumber() != 0) && 
							(board[row][col+1].getPlayerNumber() != 
							board[row][col].getPlayerNumber()&& 
							board[row][col+1].getPlayerNumber() != 0) && 
							(board[row-1][col].getPlayerNumber() != 
							board[row][col].getPlayerNumber()&& 
							board[row-1][col].getPlayerNumber() != 0)&&
							(board[row][cols-1].getPlayerNumber() != 
							board[row][col].getPlayerNumber()&& 
							board[row][cols-1].getPlayerNumber() != 0))
						return board[row-1][col].getPlayerNumber();

				}
				//right
				else if (col == cols-1){
					if( (board[row+1][col].getPlayerNumber() != 
							board[row][col].getPlayerNumber() && 
							board[row+1][col].getPlayerNumber() != 0  && 
							(board[row-1][col].getPlayerNumber() != 
							board[row][col].getPlayerNumber()&& 
							board[row-1][col].getPlayerNumber() != 0) && 
							(board[row][col-1].getPlayerNumber() != 
							board[row][col].getPlayerNumber()&& 
							board[row][col-1].getPlayerNumber() != 0)&&
							(board[row][0].getPlayerNumber() != 
							board[row][col].getPlayerNumber()&& 
							board[row][0].getPlayerNumber() != 0)))
						return board[row][col-1].getPlayerNumber();

				}
				//middle
				else{
					if( (board[row+1][col].getPlayerNumber() != 
							board[row][col].getPlayerNumber() && 
							board[row+1][col].getPlayerNumber() != 0) && 
							(board[row-1][col].getPlayerNumber() != 
							board[row][col].getPlayerNumber()&& 
							board[row-1][col].getPlayerNumber() != 0) && 
							(board[row][col-1].getPlayerNumber() != 
							board[row][col].getPlayerNumber()&& 
							board[row][col-1].getPlayerNumber() != 0) &&
							(board[row][col+1].getPlayerNumber() != 
							board[row][col].getPlayerNumber()&& 
							board[row][col+1].getPlayerNumber() != 0))
						return board[row][col-1].getPlayerNumber();


				}



			}

		}



		return -1;
	}
	/*****************************************************************
	  checks if a cell is surrounded by any pieces
	  @param r row to check
	  @param c column to check
	  @return 1 if safe and -1 if not
	 *****************************************************************/
	public int Safe(int r, int c){

		//top left corner
		if (c == 0 && r == 0){
			if((board[r+1][c].getPlayerNumber() == 0) &&
					(board[rows-1][c].getPlayerNumber() == 0) &&
					(board[r][cols-1].getPlayerNumber() == 0) &&
					(board[r][c+1].getPlayerNumber() == 0))
				return 1;

		}
		//top right corner
		else  if (r == 0 && c == cols-1) {
			if((board[r][c-1].getPlayerNumber() == 0) &&
					(board[r+1][c].getPlayerNumber() == 0) &&
					(board[rows-1][cols-1].getPlayerNumber() == 0) &&
					(board[0][0].getPlayerNumber() == 0))
				return 1;

		}
		//bottom left corner
		else if (r == rows-1 && c == 0) {
			if((board[r][c+1].getPlayerNumber() == 0) &&
					(board[r-1][c].getPlayerNumber() == 0) &&
					(board[rows-1][cols-1].getPlayerNumber() == 0) &&
					(board[0][0].getPlayerNumber() == 0))
				return 1;

		}
		//bottom right corner
		else  if (r == rows-1 && c == cols-1) {
			if((board[r-1][c].getPlayerNumber() == 0) &&
					(board[r][c-1].getPlayerNumber() == 0) &&
					(board[0][cols-1].getPlayerNumber() == 0) &&
					(board[rows-1][0].getPlayerNumber() == 0))
				return 1;

		}
		//top
		else if (r == 0){
			if((board[r+1][c].getPlayerNumber() == 0) &&
					(board[rows-1][c].getPlayerNumber() == 0) &&
					(board[r][c+1].getPlayerNumber() == 0) &&
					(board[r][c-1].getPlayerNumber() == 0))
				return 1;

		}
		//bottom
		else if (r == rows-1){
			if((board[0][c].getPlayerNumber() == 0) &&
					(board[r-1][c].getPlayerNumber() == 0) &&
					(board[r][c+1].getPlayerNumber() == 0) &&
					(board[r][c-1].getPlayerNumber() == 0))
				return 1;

		}
		//left
		else if (c == 0){
			if((board[r+1][c].getPlayerNumber() == 0) &&
					(board[r-1][c].getPlayerNumber() == 0) &&
					(board[r][c+1].getPlayerNumber() == 0) &&
					(board[r][cols-1].getPlayerNumber() == 0))
				return 1;

		}
		//right
		else if (c == cols-1){
			if((board[r+1][c].getPlayerNumber() == 0) &&
					(board[r-1][c].getPlayerNumber() == 0) &&
					(board[r][0].getPlayerNumber() == 0) &&
					(board[r][c-1].getPlayerNumber() == 0))
				return 1;

		}
		else{
			if((board[r+1][c].getPlayerNumber() == 0) &&
					(board[r-1][c].getPlayerNumber() == 0) &&
					(board[r][c+1].getPlayerNumber() == 0) &&
					(board[r][c-1].getPlayerNumber() == 0))
				return 1;
		}


		return -1;
	}
}




