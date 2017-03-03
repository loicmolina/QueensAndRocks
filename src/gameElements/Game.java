package gameElements;

public class Game {
	protected Player player0;
	protected Queen queen0;
	protected Rock rock0;
	protected Player player1;
	protected Queen queen1;
	protected Rock rock1;
	protected Empty empty;
	
	public Game(){
		 player0 = new Player(0);
		 queen0 = new Queen(player0);
		 rock0 = new Rock(player0);
		 player1 = new Player(1);
		 queen1 = new Queen(player1);
		 rock1 = new Rock(player1);
		 empty = new Empty(player0);
	}

	public Player getPlayer0() {
		return player0;
	}

	public Queen getQueen0() {
		return queen0;
	}

	public Rock getRock0() {
		return rock0;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Queen getQueen1() {
		return queen1;
	}

	public Rock getRock1() {
		return rock1;
	}

	public Empty getEmpty() {
		return empty;
	}
	
	public void setColorMode(){
		if (player0.getColorMode()=="bw"){
			player0.setColorMode("og");
			player1.setColorMode("og");			
		}else{
			player0.setColorMode("bw");
			player1.setColorMode("bw");
		}
	}
	
	public Player otherPlayer(Player p){
		if (p.getNumber()==0){
			return player1;
		}else{
			return player0;
		}
	}
}
