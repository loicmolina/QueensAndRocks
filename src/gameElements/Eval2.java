package gameElements;

import java.util.Random;

public class Eval2 implements Eval {

	@Override
	public float getEval(Player player, Board b) {
		int n=0,x,y,rq;
		boolean correct = false;
		Player joueur = player;
		Player adverse;
		
		Board courant;
		Random rand = new Random();
		
		if (b.getGame().getPlayer0().equals(player)){
			adverse = b.getGame().getPlayer1();
		}else{
			adverse = b.getGame().getPlayer0();
		}
		
		for(int i=0;i<10;i++){ //Test sur i<10 car 100 trop long
			courant = b.clone();
			while (!courant.isFinal()){
				rq=rand.nextInt(2);
				if (courant.numberOfAccessible2(joueur)>0 && (rq == 0 || courant.getNumberOfRocksLeft(joueur)==0)){
					correct=false;
					while (!correct){
						x = rand.nextInt(courant.getSize());
						y = rand.nextInt(courant.getSize());
						correct = courant.placeQueen2(x, y, joueur);
					}
				}else{  //nbrAccessible<=0 || (rq==1 && nbRocksLeft>0)
					correct=false;
					while (!correct){
						x = rand.nextInt(courant.getSize());
						y = rand.nextInt(courant.getSize());
						correct = courant.placeRock2(x, y, joueur);
					}
				}
				if (courant.getGame().getPlayer0().equals(joueur)){
					joueur = courant.getGame().getPlayer1();
				}else{
					joueur = courant.getGame().getPlayer0();
				}
			}
			if (courant.getScore(player) > courant.getScore(adverse)){
				n++;
			}
		}
		
		return 2*n/100-1;
	}

}
