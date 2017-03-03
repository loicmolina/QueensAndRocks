package gameElements;

import java.util.ArrayList;


public class Board {
	protected Game game;
	protected int size;
	protected int numberOfPieces;
	protected Square[][] board;
	
	//ATTENTION
	//Ceci est un squelette incomplet contenant uniquement le profil de quelques méthodes, dans le but de compiler la classe GameUI sans erreurs
	//Il manque les getters et les setters ainsi que les classes externes telles que Square, Eval, Game, Player,...
	
	public Board(){
		game = new Game();
		size=8;
		numberOfPieces=0;
		board = new Square[size][size];
		for (int i=0;i<size;i++){
			for (int j=0;j<size;j++){
				board[j][i]=game.getEmpty();
			}
		}
	}
	
	public Board(int s){
		game = new Game();
		size=s;
		numberOfPieces=0;
		board = new Square[size][size];
		for (int i=0;i<size;i++){
			for (int j=0;j<size;j++){
				board[j][i]=game.getEmpty();
			}
		}
	}
	
	public Board(Game g, int s, int nop, Square[][] b){
		game = g;
		size=s;
		numberOfPieces=nop;
		board = b;
	}
	
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNumberOfPieces() {
		return numberOfPieces;
	}

	public void setNumberOfPieces(int numberOfPieces) {
		this.numberOfPieces = numberOfPieces;
	}

	public Square[][] getBoard() {
		return board;
	}

	public void setBoard(Square[][] board) {
		this.board = board;
	}

	//---------------TP1------------------------
	public Square getPiece(int i, int j) {
		return board[i][j];
	}
	
	public void setPiece(int i, int j, Square piece) {	
		
			if(board[i][j].toString().equals("[  ]") && !piece.toString().equals("[  ]")){
				numberOfPieces ++;
			}else{
				if(!board[i][j].toString().equals("[  ]") && piece.toString().equals("[  ]")){
					numberOfPieces --;
				}
			}
			board[i][j]=piece;
		
	}
	
	public void removePiece(int i, int j){
		numberOfPieces --;
		board[i][j]=game.getEmpty();
	}
	
	public String toString(){
		StringBuilder res = new StringBuilder();
		for (int j=0;j<size;j++){
			for (int i=0;i<size;i++){
				res.append(board[j][i].toString());
			}
			res.append("\n");
		}
		return res.toString();
	}
	
	public Board clone(){
		Square[][] newBoard = new Square[size][size];
		for (int j=0;j<size;j++){
			for (int i=0;i<size;i++){
				newBoard[j][i]=board[j][i];
			}
		}		
		return new Board(game, size, numberOfPieces, newBoard);
	}

	public boolean isAccessible(int i, int j) {
		boolean accessible = true;
		for (int y=0;y<size;y++){
			for (int x=0;x<size;x++){
				if (board[x][y].toString().contains("Q")){ // Une autre reine a été trouvé
					if (x==i || y==j){  // alignement vertical
						accessible=false;
					}
					int dist = Math.abs(x-i);
					if (y-dist==j || y+dist==j){ //alignement horizontal
						accessible=false;
					}
				}
			}
		}
		return accessible;
	}

	public String toStringAccess(){
		StringBuilder res = new StringBuilder();
		for (int j=0;j<size;j++){
			for (int i=0;i<size;i++){
				if (!isAccessible(j,i) && board[j][i].toString().equals("[  ]")){
					res.append("[XX]");
				}else{
					res.append(board[j][i].toString());
				}
			}
			res.append("\n");
		}
		return res.toString();
	}
	
	public int numberOfAccessible() {
		int nb=0;
		for (int j=0;j<size;j++){
			for (int i=0;i<size;i++){
				if (isAccessible(j,i) && board[j][i].toString().equals("[  ]")){
					nb++;
				}
			}
		}
		return nb;
	}

	public int numberOfQueens() {
		int nb=0;
		for (int j=0;j<size;j++){
			for (int i=0;i<size;i++){
				if (board[j][i].toString().contains("Q")){
					nb++;
				}
			}
		}
		return nb;
	}
	
	public boolean placeQueen(int i, int j) {
		boolean res=false;
		if (isAccessible(i,j)){
			this.setPiece(i, j,game.queen0);
			res = true;
		}
		return res;
	}
	
	//----------TP2-----------------------
	public ArrayList<Board> depthFirstSearch(Board b) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//------------TP3----------------------
	public boolean isAccessible2(int i, int j, Player currentPlayer) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public boolean placeQueen2(int i, int j, Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean placeRock2(int i, int j, Player player) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public int getNumberOfRocksLeft(Player player){
		// TODO Auto-generated method stub
		return 0;  
	}
	
	public int getScore(Player player){
		// TODO Auto-generated method stub
		return 0;
	}



	//----------------------TP4&5--------------------------
	public boolean isFinal() {
		// TODO Auto-generated method stub
		return false;
	}

	public Board minimax(Board b, Player currentPlayer, int minimaxDepth, Eval evaluation) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	





	
	

}
