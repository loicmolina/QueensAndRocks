package gameElements;

import java.util.ArrayList;
import java.util.NoSuchElementException;


public class Board {
	protected final static int queenValue = 10;
	protected final static int rockValue = 2;
	protected Game game;
	protected int size;
	protected int numberOfPieces;
	protected Square[][] board;
	protected int rockPlayer0;
	protected int rockPlayer1;
	
	//ATTENTION
	//Ceci est un squelette incomplet contenant uniquement le profil de quelques m�thodes, dans le but de compiler la classe GameUI sans erreurs
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
				board[i][j]=game.getEmpty();
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
				board[i][j]=game.getEmpty();
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
				res.append(board[i][j].toString());
			}
			res.append("\n");
		}
		return res.toString();
	}
	
	public Board clone(){
		Square[][] newBoard = new Square[size][size];
		for (int j=0;j<size;j++){
			for (int i=0;i<size;i++){
				newBoard[i][j]=board[i][j];
			}
		}		
		
		return new Board(game, size, numberOfPieces, rockPlayer0, rockPlayer1 ,newBoard);
	}

	public boolean isAccessible(int i, int j) {
		boolean accessible = true;
		for (int y=0;y<size;y++){
			for (int x=0;x<size;x++){
				if (board[x][y].toString().contains("Q")){ // Une autre reine a �t� trouv�
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
				if (!isAccessible(i,j) && board[i][j].toString().equals("[  ]")){
					res.append("[XX]");
				}else{
					res.append(board[i][j].toString());
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
				if (isAccessible(i,j) && board[i][j].toString().equals("[  ]")){
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
				if (board[i][j].toString().contains("Q")){
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
				if (isAccessible(i,j)){
					Board b = clone();
					b.placeQueen(i, j);
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
			if (isAccessible(i,nbReine)){
				Board board = clone();
				
				board.placeQueen(i,nbReine);
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
				if (board[i][j] instanceof Queen){
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
				b.placeQueen(i,array[i]);
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
					if (b.isAccessible(i, j)){
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
	
	public int getNumberOfRocksLeft(Player player){
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
		boolean colonne = true;
		
		int posDiagBas, posDiagHaut;
		
		if (board[i][j] instanceof Queen || board[i][j] instanceof Rock){
			return false;
		}
		
		for (int k = 0 ; k<=i ; k++){	
			if (board[k][j].getPlayer().getNumber()!=nPlayer && board[k][j] instanceof Queen){
				ligne = false;
			}else{
				if (board[k][j] instanceof Rock){
					ligne = true;
				}
			}
			
			if (i == k){
				for (int m = 0 ; m < j; m ++){
					if (board[k][m].getPlayer().getNumber()!=nPlayer && board[k][m] instanceof Queen){
						colonne = false;
					}else{
						if (board[k][m] instanceof Rock){
							colonne = true;
						}
					}
				}
			}
			
			posDiagHaut = j-(i-k);
			if (posDiagHaut > -1){
				if (board[k][posDiagHaut].getPlayer().getNumber() != nPlayer && board[k][posDiagHaut] instanceof Queen){
					diagHaut = false;
				}else{
					if (board[k][posDiagHaut] instanceof Rock){
						diagHaut = true;
					}
				}
			}	
			
			posDiagBas = j + (i - k);
			if (posDiagBas < size){
				if (board[k][posDiagBas].getPlayer().getNumber() != nPlayer && board[k][posDiagBas] instanceof Queen){
					diagBas = false ;
				}else{
					if (board[k][posDiagBas] instanceof Rock){
						diagBas = true;
					}
				}
			}			
			
		}
		
		if ((ligne && diagBas && diagHaut && colonne) == false){
			return false;
		}
		
		
		ligne = true;
		diagHaut = true;
		diagBas = true;
		colonne = true;
		
		for(int l = size-1; l >= i; l--){
			if (board[l][j].getPlayer().getNumber()!=nPlayer && board[l][j] instanceof Queen){
				ligne = false;
			}else{
				if (board[l][j] instanceof Rock){
					ligne = true;
				}
			}
			
			if (i == l){
				for (int m = size-1 ; m > j; m--){
					if (board[l][m].getPlayer().getNumber()!=nPlayer && board[l][m] instanceof Queen){
						colonne = false;
					}else{
						if (board[l][m] instanceof Rock){
							colonne = true;
						}
					}
				}
			}
			
			posDiagHaut = j - (l - i);
			if (posDiagHaut >= 0){
				if (board[l][posDiagHaut].getPlayer().getNumber() != nPlayer && board[l][posDiagHaut] instanceof Queen){
					diagHaut = false;
				}else{
					if (board[l][posDiagHaut] instanceof Rock){
						diagHaut = true;
					}
				}
			}
			
			posDiagBas = j + (l - i);
			if (posDiagBas < size){
				if (board[l][posDiagBas].getPlayer().getNumber() != nPlayer && board[l][posDiagBas] instanceof Queen){
					diagBas = false;
				}else{
					if (board[l][posDiagBas] instanceof Rock){
						diagBas = true;
					}
				}
			}	
		}
		
		return diagHaut && diagBas && ligne && colonne;
	}
	
	public int numberOfAccessible2(Player player){
		int res = 0;
		for (int j=0;j<size;j++){
			for (int i=0;i<size;i++){
				if (isAccessible2(i, j, player) && board[i][j].toString().equals("[  ]")){
					res++;
				}
			}
		}
		return res;
	}
	
	public String toStringAccess2(Player player){
		StringBuilder res = new StringBuilder();
		for (int j=0;j<size;j++){
			for (int i=0;i<size;i++){
				if (!isAccessible2(i, j, player) && board[i][j].toString().equals("[  ]")){
					res.append("[XX]");
				}else{
					res.append(board[i][j].toString());
				}
			}
			res.append("\n");
		}
		return res.toString();
	}
	
	public int numberOfQueens2(Player player){
		int res = 0;
		
		for (int j = 0; j < size; j++){
			for (int i = 0; i < size; i++){
				if (board[i][j].toString().contains("Q") && board[i][j].getPlayer().getNumber() == player.getNumber()){
					res++;
				}
			}
		}
		
		return res;
	}
	
	public int numberOfRocks2(Player player){
		int res = 0;
		
		for (int j = 0; j < size; j++){
			for (int i = 0; i < size; i++){
				if (board[i][j].toString().contains("R") && board[i][j].getPlayer().getNumber() == player.getNumber()){
					res++;
				}
			}
		}
		
		return res;
	}
	
	
	public boolean placeQueen2(int i, int j, Player player) {
		boolean res = false;
		
		if (isAccessible2(i,j, player)){
			if(player.getNumber() == 0){
				this.setPiece(i, j, game.getQueen0());
			}else{
				this.setPiece(i, j, game.getQueen1());
			}
			res = true;
		}
		
		return res;
	}

	public boolean placeRock2(int i, int j, Player player) {
		boolean res = false;
		
		if(getNumberOfRocksLeft(player) > 0){
			if (!board[i][j].toString().contains("Q") && !board[i][j].toString().contains("R")){
				if(player.getNumber() == 0){
					this.setPiece(i, j, game.getRock0());
				}else{
					this.setPiece(i, j, game.getRock1());
				}
				useRock(player);
				res = true;
			}
		}

		return res;
	}
	
	public int getScore(Player player){
		return numberOfQueens2(player)*queenValue + numberOfRocks2(player)*rockValue;
	}



	//----------------------TP4&5--------------------------
	public boolean isFinal() {
		Player p0 = this.getGame().getPlayer0();
		Player p1 = this.getGame().getPlayer1();
		return ((this.getNumberOfRocksLeft(p0) == 0  && this.numberOfAccessible2(p0)==0) || (this.getNumberOfRocksLeft(p1) == 0  && this.numberOfAccessible2(p1)==0)) ;
	}
	
	public ArrayList<Board> getSuccessors2(Player player){
		ArrayList<Board> successors = new ArrayList<Board>();
		for (int j=0;j<size;j++){
			for (int i=0;i<size;i++){
				if (this.getNumberOfRocksLeft(player)>0 && !(board[i][j] instanceof Queen) && !(board[i][j] instanceof Rock)){
					Board bR = clone();
					bR.placeRock2(i, j, player);
					successors.add(bR);
				}
				if (isAccessible2(i,j, player)){
					Board bQ = clone();
					bQ.placeQueen2(i, j, player);
					successors.add(bQ);					
				}
			}
		}
		return successors;	
		
	}
	
	public float evaluation(Board b, Player player, int c, Eval e, Player playing){
		ArrayList<Board> S;
		float score_max, score_min, eval;
		eval = e.getEval(player, b);
		
		if (b.isFinal()){
			if (eval > 0){ //Partie gagnée par la machine
				return Float.POSITIVE_INFINITY;
			}else{
				if (eval < 0){ //Partie perdue par la machine
					return Float.NEGATIVE_INFINITY;
				}else{ // e.getEval(player, b) == 0   Match nul
					return 0;
				}
			}
		}
		
		if (c == 0){
			return eval;
		}
		
		S = b.getSuccessors2(playing);
		
		Player adverse;
		if (playing.equals(b.getGame().getPlayer0())){
			adverse = b.getGame().getPlayer1();
		}else{
			adverse = b.getGame().getPlayer0();
		}
		
		if (playing.equals(player)){
			score_max = Float.NEGATIVE_INFINITY;
			
			for (Board bo : S){
				score_max = Math.max(score_max, evaluation(bo, player, c-1, e, adverse));
			}
			return score_max;			
		}else{
			score_min = Float.POSITIVE_INFINITY;
			
			for (Board bo : S){
				score_min = Math.min(score_min, evaluation(bo, player, c-1, e, adverse));
			}
			return score_min;
		}
	}

	public Board minimax(Board b, Player currentPlayer, int minimaxDepth, Eval evaluation) {
		ArrayList<Board> S = b.getSuccessors2(currentPlayer);
		float score_max, score_min, score;
		
		if (b.isFinal()){
			return b;
		}
		
		score_min = Float.POSITIVE_INFINITY;
		score_max = Float.NEGATIVE_INFINITY;
		
		Board sortie = new Board(b.size);		
		
		for (Board bo : S){
			score = evaluation (bo, currentPlayer, minimaxDepth, evaluation, currentPlayer);
			if (b.numberOfPieces == 0 && score <= score_min){
				sortie = bo;
				score_min = score;
			}else{
				if (b.numberOfPieces !=0 && score >= score_max){
					sortie = bo;
					score_max = score;
				}
			}
		}
		
		return sortie;
	}

}
