package gameElements;

public class Eval1 implements Eval{

	public float getEval(Player player, Board b) {
		float score = 0;
		Player adverse;
		if (player.getNumber()==0){
			adverse = b.getGame().getPlayer1();
		}else{
			adverse = b.getGame().getPlayer0();			
		}
		
		score += b.numberOfAccessible2(player);		
		score -= b.numberOfAccessible2(adverse);
		score += b.squaresAroundQueens(player);
		score -= b.squaresAroundQueens(adverse);		
		
		return score;
	}

}
