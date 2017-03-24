package queensandrocks;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

import gameElements.*;
import graphics.GameUI;

@SuppressWarnings("unused")
public class Main {
	 public static void main(String[] args) {
		 /*   -----TP1-----
		 Scanner sc= new Scanner(System.in);
		 boolean jouer=true;
		 System.out.println("Choisir une taille de tableau (sup�rieur � 0) : ");	
		 int decisionX = sc.nextInt();
		 while(decisionX<=0){
			 System.out.println("Taille trop petite");
			 decisionX = sc.nextInt();
		 }
		 Board b = new Board(decisionX);
		 
		 while (jouer){			 
			 System.out.println("Plateau :\n"+b.toStringAccess());
			 System.out.println("O� placer la prochaine reine ?");
			 System.out.println("Position X : ");
			 decisionX = sc.nextInt();
			 System.out.println("Position Y : ");
			 int decisionY = sc.nextInt();
			 
			 boolean correct = (decisionX>=0 && decisionX<b.getSize()) && ((decisionY>=0 && decisionY<b.getSize()));
			 
			 if (correct){
				 boolean succ = b.placeQueen(decisionY, decisionX);
				 if (!succ){
					 System.out.println("Impossible de la placer ici !\n");
				 }
				 if (b.numberOfQueens()==b.getSize()){
					 System.out.println("Plateau :\n"+b.toStringAccess());
					 System.out.println("Vous avez gagn� !");
					 jouer=false;
				 }
				 if (b.numberOfAccessible()==0){
					 System.out.println("Plateau :\n"+b.toStringAccess());
					 System.err.println("Vous avez perdu !");
					 jouer=false;
				 }
			 }else{
					System.out.println("La reine est hors plateau\n");
			 }
		 }*/
		 
		 
		 /*-----TP2-----
		 
		 Board b = new Board(5);
		 
		 long debut = System.currentTimeMillis();
		 
		 System.out.println(b.solutionStepsArray(b));
		 
		 long fin = (System.currentTimeMillis() - debut);
		 System.out.println( fin + "ms");
		
		*/
		 
		 /*Board b = new Board();
		 b.placeQueen2(2, 3, b.getGame().getPlayer0());

		 System.out.println(b.numberOfRocks2(b.getGame().getPlayer1()));
		 b.placeRock2(2, 3, b.getGame().getPlayer1());
		 
		 System.out.println(b.toStringAccess2(b.getGame().getPlayer1()));
		 System.out.println(b.numberOfQueens2(b.getGame().getPlayer0()));
		 System.out.println(b.numberOfRocks2(b.getGame().getPlayer1()));
		 */
		
		 
		 
		 /*   -----TP3 | jeu à 2----- 
		 Scanner sc= new Scanner(System.in);
		 boolean jouer=true;
		 boolean joueur0 = true;
		 boolean finJ0, finJ1;
		 
		 System.out.println("Choisir une taille de tableau (superieur a 0) : ");	
		 int decisionX = sc.nextInt();
		 while(decisionX<=0){
			 System.out.println("Taille trop petite");
			 decisionX = sc.nextInt();
		 }
		 Board b = new Board(decisionX);
		 

		 Player p0 = b.getGame().getPlayer0();
		 Player p1 = b.getGame().getPlayer1();
		 
		 while (jouer){		
			 if (joueur0){				 
				 System.out.println("--- TOUR DU JOUEUR 0 :");
				 System.out.println("Plateau :\n"+b.toStringAccess2(p0));
			 }else{
				 System.out.println("--- TOUR DU JOUEUR 1 :");
				 System.out.println("Plateau :\n"+b.toStringAccess2(p1));
			 }
			 System.out.println("SCORE - Joueur 0 : "+b.getScore(p0)+" | Joueur 1 : "+b.getScore(p1)+"\n");
			 
			 String decisionP = "z";
			 while (!decisionP.equals("q") && !decisionP.equals("r")){
				 System.out.println("Placer une reine ou un rocher ? (q/r)");
				 decisionP = sc.next();
			 }			 
			 
			 if (decisionP.equals("q")){				 
				 System.out.println("Ou placer la prochaine reine ?");				 
			 }else{				 
				 System.out.println("Ou placer le prochain rocher ?");
			 }
			 System.out.print("Position X : ");
			 decisionX = sc.nextInt();
			 System.out.print("Position Y : ");
			 int decisionY = sc.nextInt();	
			 System.out.println("");
			 
			 boolean correct = (decisionX>=0 && decisionX<b.getSize()) && ((decisionY>=0 && decisionY<b.getSize()));
			 boolean succ;
			 if (correct){
				 if (joueur0){
					 if (decisionP.equals("q")){
						 succ = b.placeQueen2(decisionX, decisionY, p0);
					 }else{
						 succ = b.placeRock2(decisionX, decisionY, p0);
					 }
				 }else{
					 if (decisionP.equals("q")){
						 succ = b.placeQueen2(decisionX, decisionY, p1);
					 }else{
						 succ = b.placeRock2(decisionX, decisionY, p1);
					 }
				 }

				 finJ0 = b.numberOfAccessible2(p0)==0 && b.numberOfRocks2(p0)==0;
				 finJ1 = b.numberOfAccessible2(p1)==0 && b.numberOfRocks2(p1)==0;
				 if (finJ0 && finJ1){
					 System.out.println("\nPlateau final :\n"+b.toStringAccess());
					 System.out.println("Fin du jeu !");
					 System.out.println("Scores finaux : \n Joueur0 : "+ b.getScore(p0) + "\n Joueur1 : "+ b.getScore(p1));
					 jouer=false;
				 }else{

					 if (joueur0 && b.getNumberOfRocksLeft(p0)==0){
						 System.out.print("Plus de rochers disponibles pour le joueur 0. " );
					 }
					 if (!joueur0 && b.getNumberOfRocksLeft(p1)==0){
						 System.out.print("Plus de rochers disponibles pour le joueur 1. " );
					 }
					 
					 if (b.numberOfAccessible2(p0)==0){
						 joueur0 = false;
					 }else{
						 if (b.numberOfAccessible2(p1)==0){
							 joueur0 = true;
						 }else{
							 joueur0 = !joueur0;
						 }
					 }
				 }
				 
				 if (!succ){
					 System.out.println("Impossible de placer ici !\n");
				 }
			 }else{
					System.out.println("La pièce est hors plateau\n");
			 }
			 System.out.println("");
		 }
		 */
		 
		 
		 /* --- TP3 | Jeu à 2 avec implémentation de la règle 7 
		 
		 Scanner sc= new Scanner(System.in);
		 boolean jouer=true;
		 boolean joueur0 = true;
		 boolean tour1 = true;
		 boolean finJ0, finJ1;
		 
		 System.out.println("Choisir une taille de tableau (superieur a 0) : ");	
		 int decisionX = sc.nextInt();
		 while(decisionX<=0){
			 System.out.println("Taille trop petite");
			 decisionX = sc.nextInt();
		 }
		 Board b = new Board(decisionX);
		 

		 Player p0 = b.getGame().getPlayer0();
		 Player p1 = b.getGame().getPlayer1();
		 
		 while (jouer){		
			 if (joueur0){				 
				 System.out.println("--- TOUR DU JOUEUR 0 :");
				 System.out.println("Plateau :\n"+b.toStringAccess2(p0));
			 }else{
				 System.out.println("--- TOUR DU JOUEUR 1 :");
				 System.out.println("Plateau :\n"+b.toStringAccess2(p1));
			 }
			 System.out.println("SCORE - Joueur 0 : "+b.getScore(p0)+" | Joueur 1 : "+b.getScore(p1)+"\n");
			 
			 String decisionP = "z";
			 if (!tour1){
			 while (!decisionP.equals("q") && !decisionP.equals("r")){
				 System.out.println("Placer une reine ou un rocher ? (q/r)");
				 decisionP = sc.next();
			 }	
			 
			 }else{
				 decisionP = "r";
				 tour1 = false;
			 }
			 
			 if (decisionP.equals("q")){				 
				 System.out.println("Ou placer la prochaine reine ?");				 
			 }else{				 
				 System.out.println("Ou placer le prochain rocher ?");
			 }
			 System.out.print("Position X : ");
			 decisionX = sc.nextInt();
			 System.out.print("Position Y : ");
			 int decisionY = sc.nextInt();	
			 System.out.println("");
			 
			 boolean correct = (decisionX>=0 && decisionX<b.getSize()) && ((decisionY>=0 && decisionY<b.getSize()));
			 boolean succ;
			 if (correct){
				 if (joueur0){
					 if (decisionP.equals("q")){
						 succ = b.placeQueen2(decisionX, decisionY, p0);
					 }else{
						 succ = b.placeRock2(decisionX, decisionY, p0);
					 }
				 }else{
					 if (decisionP.equals("q")){
						 succ = b.placeQueen2(decisionX, decisionY, p1);
					 }else{
						 succ = b.placeRock2(decisionX, decisionY, p1);
					 }
				 }
				 finJ0 = b.numberOfAccessible2(p0)==0 && b.numberOfRocks2(p0)==0;
				 finJ1 = b.numberOfAccessible2(p1)==0 && b.numberOfRocks2(p1)==0;
				 if (finJ0 && finJ1){
					 System.out.println("\nPlateau final :\n"+b.toStringAccess());
					 System.out.println("Fin du jeu !");
					 System.out.println("Scores finaux : \n Joueur0 : "+ b.getScore(p0) + "\n Joueur1 : "+ b.getScore(p1));
					 jouer=false;
				 }else{
					 
					 if (joueur0 && b.getNumberOfRocksLeft(p0)==0){
						 System.out.print("Plus de rochers disponibles pour le joueur 0. " );
					 }
					 if (!joueur0 && b.getNumberOfRocksLeft(p1)==0){
						 System.out.print("Plus de rochers disponibles pour le joueur 1. " );
					 }
					 
					 if (b.numberOfAccessible2(p0)==0 && b.getNumberOfRocksLeft(p0)==0){
						 joueur0 = false;
					 }else{
						 if (b.numberOfAccessible2(p1)==0 && b.getNumberOfRocksLeft(p1)==0){
							 joueur0 = true;
						 }else{
							 joueur0 = !joueur0;
						 }
					 }
				 }
				 
				 if (!succ){
					 System.out.println("Impossible de placer ici !\n");
				 }
			 }else{
					System.out.println("La pièce est hors plateau\n");
			 }
			 System.out.println("");
		 }
		 */
		 
		 /* ----- TP4 ------- Humain contre Machine */
		 
		 Scanner sc= new Scanner(System.in);
		 boolean jouer=true;
		 boolean joueur0 = true;
		 boolean isOk = true;
		 boolean tour1 = true;
		 int choixJeu = 0, decisionX, decisionY;
		 Eval eval = new Eval0();


		 while(choixJeu != 1 && choixJeu != 2){
			 System.out.println("Jouer contre l'ordinateur (1) ou laisser l'ordinateur jouer contre lui-meme (2) ?");	
			 choixJeu = sc.nextInt();
		 }

		 System.out.println("Choisir une taille de tableau (superieur a 0) : ");	
		 decisionX = sc.nextInt();
		 while(decisionX<=0){
			 System.out.println("Taille trop petite");
			 decisionX = sc.nextInt();
		 }
		 Board b = new Board(decisionX);


		 Player p0 = b.getGame().getPlayer0();
		 Player p1 = b.getGame().getPlayer1();

		 long debut = System.currentTimeMillis();
		 
		 if(choixJeu == 1){
			 while (jouer){		
				 if (joueur0){				 
					 System.out.println("--- TOUR DU JOUEUR 0 :");
					 System.out.println("Plateau :\n"+b.toStringAccess2(p0));
				 }
				 System.out.println("SCORE - Joueur 0 : "+b.getScore(p0)+" | Joueur 1 : "+b.getScore(p1)+"\n");

				 String decisionP = "z";
				 if (!tour1 && joueur0){
					 while (!decisionP.equals("q") && !decisionP.equals("r")){
						 System.out.println("Placer une reine ou un rocher ? (q/r)");
						 decisionP = sc.next();
					 }	

				 }else{ //tour1 || joueur1
					 decisionP = "r";
					 tour1 = false;
				 }

				 if (joueur0){
					 if (decisionP.equals("q")){				 
						 System.out.println("Ou placer la prochaine reine ?");				 
					 }else{				 
						 System.out.println("Ou placer le prochain rocher ?");
					 }
					 System.out.print("Position X : ");
					 decisionX = sc.nextInt();
					 System.out.print("Position Y : ");
					 decisionY = sc.nextInt();	
					 System.out.println("");

					 boolean correct = (decisionX>=0 && decisionX<b.getSize()) && ((decisionY>=0 && decisionY<b.getSize()));
					 if (correct){
						 if (decisionP.equals("q")){
							 if(!b.placeQueen2(decisionX, decisionY, p0)){
								 System.out.println("Impossible de placer une reine ici. " );
								 isOk=false;
							 }
						 }else{
							 if (!b.placeRock2(decisionX, decisionY, p0)){
								 if (b.numberOfRocks2(p0)==0){
									 System.out.println("Plus de rochers disponibles pour le joueur 0. " );
									 isOk=false;
								 }else{
									 System.out.println("Impossible de placer un rocher ici. ");
									 isOk=false;
								 }
							 }
						 }

					 }else{
						 System.out.println("La pièce est hors plateau\n");
					 }
				 }else{
					 b = b.minimax(b, p1, 2, eval);
				 }

				 if (b.isFinal()){
					 System.out.println("\nPlateau final :\n"+b.toStringAccess());
					 System.out.println("Fin du jeu !");
					 System.out.println("Scores finaux : \n Joueur0 : "+ b.getScore(p0) + "\n Joueur1 : "+ b.getScore(p1));
					 jouer=false;
				 }else{
					 if (isOk){
						 joueur0 = !joueur0;
					 }
					 isOk=true;
				 }
			 }
			 System.out.println("");
			 sc.close();
			 
			 
		 }else{

			 sc.close();
			 while (jouer){	
				 if(joueur0){
					 b = b.minimax(b, p0, 2, eval);
					 //System.out.println("--- TOUR DU JOUEUR 0 :");
					 //System.out.println("Plateau :\n"+b.toStringAccess2(p0));
				 }else{
					 b = b.minimax(b, p1, 2, eval);
					 //System.out.println("--- TOUR DU JOUEUR 1 :");
					 //System.out.println("Plateau :\n"+b.toStringAccess2(p1));
				 }
				 //System.out.println("SCORE - Joueur 0 : "+b.getScore(p0)+" | Joueur 1 : "+b.getScore(p1)+"\n");

				 if (b.isFinal()){
					 //System.out.println("\nPlateau final :\n"+b.toStringAccess());
					 //System.out.println("Fin du jeu !");
					 System.out.println("Scores finaux : \n Joueur0 : "+ b.getScore(p0) + "\n Joueur1 : "+ b.getScore(p1));
					 jouer=false;
				 }else{
					 //try {
					 //	 System.out.println("-- Entree pour continuer --");
					 //	 System.in.read();
					 //} catch (IOException e) {
					 //	e.printStackTrace();
					 //}
					 joueur0 = !joueur0;
				 }
			 }
			 //System.out.println("");
		 }
		 
		 long fin = (System.currentTimeMillis() - debut);
		 System.out.println("Temps total : " + fin + "ms");

		 //jouer(5,2);		 
	 }
	 
	 public static void jouer(int taille, int profondeur){
		 boolean jouer=true;
		 boolean joueur0 = true;
		 float score;
		 Eval eval = new Eval0();

		 Board b = new Board(taille);

		 Player p0 = b.getGame().getPlayer0();
		 Player p1 = b.getGame().getPlayer1();

		 while (jouer){	
			 if(joueur0){
				 b = b.minimax(b, p0, profondeur, eval);
			 }else{
				 b = b.minimax(b, p1, profondeur, eval);
			 }

			 if (b.isFinal()){
				 jouer=false;
				 score = eval.getEval(p0, b);
				 if (score > 0){
					 System.out.println("Joueur 0 gagne");
				 }else{
					 if (score < 0){
						 System.out.println("Joueur 1 gagne");
					 }else{
						 System.out.println("Match nul");
					 }
				 }
			 }else{
				 joueur0 = !joueur0;
			 }
		 }
	 }


}
