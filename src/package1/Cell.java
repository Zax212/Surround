 

public class Cell {

	public int playerNumber= 0;

	public Cell(int playerNumber) {
		this.playerNumber = playerNumber;
	}
	public Cell(){
		playerNumber = 0;
	}

	public int getPlayerNumber() {
		return playerNumber;

	}
	public void setPlayerNumber(int p) {
		playerNumber = p;
	}
}
