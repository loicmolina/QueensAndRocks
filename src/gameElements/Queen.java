package gameElements;

public class Queen implements Square{
	protected Player player;
	
	
	
	public Queen(Player p) {
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
		String res;
		if (player.getNumber()==0){
			if (player.getColorMode() == "bw"){
				res="[WQ]";
			}else{
				res="[GQ]";
			}
		}else{
			if (player.getColorMode() == "bw"){
				res="[BQ]";
			}else{
				res="[OQ]";
			}
		}		
		return res;
	}

}
