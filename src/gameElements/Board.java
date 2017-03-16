package gameElements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;


public class Board {
	protected Game game;
	protected int size;
	protected int numberOfPieces;
	protected Square[][] board;
	protected int rockPlayer0;
	protected int rockPlayer1;
	
	//ATTENTION
	//Ceci est un squelette incomplet contenant uniquement le profil de quelques méthodes, dans le but de compiler la classe GameUI sans erreurs
	//Il manque les getters et les setters ainsi que les classes externes telles que Square, Eval, Game, Player,...
	
	public Board(){
		game = new Game();
		size=8;
		numberOfPieces=0;
		board = new Square[size][size];
		
		rockPlayer0 = size;
		rockPlayer1 = size;
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
		rockPlayer0 = size;
		rockPlayer1 = size;
		for (int i=0;i<size;i++){
			for (int j=0;j<size;j++){
				board[j][i]=game.getEmpty();
			}
		}
	}
	
	public Board(Game g, int s, int nop, int rp0, int rp1 , Square[][] b){
		game = g;
		size=s;
		rockPlayer0 = rp0;
		rockPlayer1 = rp1;
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
		
		return new Board(game, size, numberOfPieces, rockPlayer0, rockPlayer1 ,newBoard);
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
	
	public boolean isSolution(){
		return this.numberOfQueens()==this.getSize();
	}
	
	public ArrayList<Board> getSuccessors(){
		ArrayList<Board> successors = new ArrayList<Board>();
		for (int j=0;j<size;j++){
			for (int i=0;i<size;i++){
				if (isAccessible(j,i)){
					Board b = clone();
					b.placeQueen(j, i);
					successors.add(b);
				}
			}
		}
		return successors;		
	}
	
	
	public ArrayList<Board> depthFirstSearch(Board b) {
		ArrayList<Board> sol = null ;
		if (b.isSolution()){
			sol = new ArrayList<Board>();
			sol.add(b);
			return sol;
		}
		for (Board board : b.getSuccessors()){
			
			sol = depthFirstSearch(board);	
			
			if (sol != null){
				sol.add(b);
				return sol;
			}
			
		}
			
		if (sol == null && (b.numberOfPieces==0 || b.equals(this))){
			throw new NoSuchElementException();
		}
		
		return sol;
		
	}
	
	
	public ArrayList<Board> depthFirstSearch() {
		return depthFirstSearch(new Board(size));
	}
	
	public String solutionSteps(Board b){
		StringBuilder sb = new StringBuilder();
		for(Board i : b.depthFirstSearch()){
			sb.append(i.toStringAccess());
			sb.append("\n\n");
		}
		return sb.toString();
	}
	
	public ArrayList<Board> getNewSuccessors(){
		ArrayList<Board> successors = new ArrayList<Board>();
		int nbReine = this.numberOfQueens();
		for (int i=0;i<size;i++){
			if (isAccessible(nbReine,i)){
				Board board = clone();
				
				board.placeQueen(nbReine,i);
				successors.add(board);
			}
		}
		return successors;	
	}
	
	public ArrayList<Board> depthFirstSearch2(Board b) {
		ArrayList<Board> sol = null ;
		if (b.isSolution()){
			sol = new ArrayList<Board>();
			sol.add(b);
			return sol;
		}
		for (Board board : b.getNewSuccessors()){
			
			sol = depthFirstSearch2(board);	
			
			if (sol != null){
				sol.add(b);
				return sol;
			}
			
		}
			
		if (sol == null && (b.numberOfPieces==0 || b.equals(this))){
			throw new NoSuchElementException();
		}
		
		return sol;
		
	}
	
	
	public ArrayList<Board> depthFirstSearch2() {
		return depthFirstSearch2(new Board(size));
	}
	
	
	public String solutionSteps2(Board b){
		StringBuilder sb = new StringBuilder();
		for(Board i : b.depthFirstSearch2()){
			sb.append(i.toStringAccess());
			sb.append("\n\n");
		}
		return sb.toString();
	}
	
	
	
	public int[] boardToArray(){
		int[] tab = new int[size];
		int pos;
		
		
		for (int j=0;j<size;j++){
			pos=-1;
			for (int i=0;i<size;i++){
				if (board[j][i] instanceof Queen){
					pos=i;
				}
			}
			tab[j]=pos;
		}
		return tab;
	}
	
	
	public Board arrayToBoard(int[] array){
		Board b = new Board(array.length);
		
		
		for (int i=0 ; i< array.length; i++){
			if (array[i]!=-1){
				b.placeQueen(array[i],i);
			}
		}
		
		return b;
	}
	
	
	public ArrayList<int[]> getArraySuccessors(int[] array){
		ArrayList<int[]> arr = new ArrayList<int[]>();
		Board b = this.arrayToBoard(array);
		
		for (int i = 0 ; i < array.length ; i++){
			if (array[i]==-1){
				for (int j = 0 ; j < array.length ; j++){
					if (b.isAccessible(j, i)){
						int[] succ = array.clone();
						succ[i]=j;
						arr.add(succ);
					}					
				}				
			}
		}
		
		return arr;
	}
	
	public boolean isSolutionArray(int[] array){
		boolean solution = true;
		for (int i = 0 ; i < array.length ; i++){
			if (array[i] == -1)
				solution = false;
		}
		return solution; 
	}
	
	public boolean isInitialArray(int[] array){
		boolean solution = true;
		for (int i = 0 ; i < array.length ; i++){
			if (array[i] != -1)
				solution = false;
		}
		return solution; 
	}
	
	
	public ArrayList<int[]> depthFirstSearchArray(int[] initialState){
		ArrayList<int[]> sol = null ;
		if (isSolutionArray(initialState)){
			sol = new ArrayList<int[]>();
			sol.add(initialState);
			return sol;
		}
		for (int[] a : this.getArraySuccessors(initialState)){
			
			sol = depthFirstSearchArray(a);	
			
			if (sol != null){
				sol.add(initialState);
				return sol;
			}
			
		}
			
		if (sol == null && (this.isInitialArray(initialState) || initialState.equals(this.boardToArray()))){
			throw new NoSuchElementException();
		}
		
		return sol;
		
	}
	
	
	public ArrayList<int[]> depthFirstSearchArray() {
		return depthFirstSearchArray(new Board(size).boardToArray());
	}
	
	
	public String solutionStepsArray(Board b){
		StringBuilder sb = new StringBuilder();
		for(int[] i : b.depthFirstSearchArray()){
			for (int j = 0 ; j < i.length ; j++){
				sb.append(i[j]+" ");
			}
			sb.append("\n\n");
		}
		return sb.toString();
	}
	
	
	//------------TP3----------------------
	public int getRockPlayer0(){
		return rockPlayer0;
	}
	
	public int getRockPlayer1(){
		return rockPlayer1;
	}
	
	public void setRockPlayer0(int rp){
		rockPlayer0=rp;
	}
	
	public void setRockPlayer1(int rp){
		rockPlayer1=rp;
	}
	
	public int getNumberOfRocksleft(Player player){
		if(player.getNumber() == 0){
			return rockPlayer0;
		}else{
			return rockPlayer1;
		}
	}
	
	public void useRock(Player player){
		if(player.getNumber() == 0){
			rockPlayer0--;
		}else{
			rockPlayer1--;
		}
	}
	
	public boolean isAccessible2(int i, int j, Player currentPlayer) {
		int nPlayer = currentPlayer.getNumber();
		boolean diagHaut = true;
		boolean diagBas = true;
		boolean ligne = true;
		
		for (int k = 0 ; k<i ; k++){		
			if (board[j][k].getPlayer().getNumber()!=nPlayer && board[j][k] instanceof Queen){
				ligne = false;
			}else{
				ligne = true;
			}
			int posDiagHaut = j-i-k;
			if (posDiagHaut>=0){
				if (board[posDiagHaut][k].getPlayer().getNumber()!=nPlayer && board[posDiagHaut][k] instanceof Queen){
					diagHaut = false;
				}else{
					diagHaut = true;
				}
			}
			int posDiagBas = j+i-k;
			if (posDiagBas<size){
				if (board[posDiagHaut][k].getPlayer().getNumber()!=nPlayer && board[posDiagHaut][k] instanceof Queen){
					diagBas = false;
				}else{
					diagBas = true;
				}
			}
		}
		if (ligne && diagBas && diagHaut == false){
			return false;
		}
		
		
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
