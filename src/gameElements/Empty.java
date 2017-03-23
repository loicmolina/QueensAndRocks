package gameElements;

public class Empty implements Square{
	protected Player player;
		
	public Empty(Player p) {
		player=p;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public void setPlayer(Player p) {
		this.player=p;
	}
	
	public String toString(){
		return "[  ]";		
	}

}
