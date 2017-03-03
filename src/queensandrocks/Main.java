package queensandrocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

import gameElements.*;
import graphics.GameUI;

public class Main {
	 public static void main(String[] args) {/*
		 Scanner sc= new Scanner(System.in);
		 boolean jouer=true;
		 System.out.println("Choisir une taille de tableau (supérieur à 0) : ");	
		 int decisionX = sc.nextInt();
		 while(decisionX<=0){
			 System.out.println("Taille trop petite");
			 decisionX = sc.nextInt();
		 }
		 Board b = new Board(decisionX);
		 
		 while (jouer){			 
			 System.out.println("Plateau :\n"+b.toStringAccess());
			 System.out.println("Où placer la prochaine reine ?");
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
					 System.out.println("Vous avez gagné !");
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
		 
		 
		 
		 
		 
		 
		 
		 Board b = new Board(3);
		 
		 //b.placeQueen(1, 3);
		 //System.out.println(b.toStringAccess());
		 
		 Date d = new Date();
		 long debut = d.getTime();
		 System.out.println(b.solutionSteps(b));
		 
		 System.out.println(d.getTime()-debut);
		
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
	 }
}
