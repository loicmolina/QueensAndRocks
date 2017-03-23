package gameElements;

public class Eval0 implements Eval{

	@Override
	public float getEval(Player player, Board b) {
		if (player.getNumber() == 0){
			return b.getScore(player) - b.getScore(b.getGame().getPlayer1());
		}else{
			return b.getScore(player) - b.getScore(b.getGame().getPlayer0());			
		}
	}

}
