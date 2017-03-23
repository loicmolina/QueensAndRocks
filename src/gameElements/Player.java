package gameElements;

public class Player {
	protected int number;
	protected String colorMode;
	
	public Player(int nb){
		
		number = nb;
		colorMode = "bw";
		
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getColorMode() {
		return colorMode;
	}

	public void setColorMode(String colorMode) {
		this.colorMode = colorMode;
	}
	
	public String toString(){
		String res;
		if (number==0){
			if (colorMode == "bw"){
				res="white";
			}else{
				res="green";
			}
		}else{
			if (colorMode == "bw"){
				res="black";
			}else{
				res="orange";
			}
		}		
		return res;
	}
	
	
}
