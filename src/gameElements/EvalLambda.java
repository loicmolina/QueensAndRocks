package gameElements;

public class EvalLambda implements Eval{
	private float lambda = 0.5f;
	
	
	@Override
	public float getEval(Player player, Board b) {
		Eval eval0 = new Eval0();
		Eval eval1 = new Eval1();
		
		return lambda*eval0.getEval(player, b) + (1-lambda)*eval1.getEval(player, b);
	}

	public float getLambda() {
		return lambda;
	}

	public void setLambda(float lambda) {
		this.lambda = lambda;
	}
	
	

}
