package gameElements;

public class Rock implements Square{
	protected Player player;
	
	public Rock(Player p) {
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
				res="[WR]";
			}else{
				res="[GR]";
			}
		}else{
			if (player.getColorMode() == "bw"){
				res="[BR]";
			}else{
				res="[OR]";
			}
		}		
		return res;
	}

}
