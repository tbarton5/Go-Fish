import java.util.*;
import java.awt.*;

import javax.swing.*;
public class GoFish extends javax.swing.JFrame {
	
private GoFish() {
	getContentPane().setPreferredSize(new Dimension(960,600));
	getContentPane().setBackground(new Color(0,128,0));
	
	pack();
	
	setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	setTitle("Go Fish");
	setLocationRelativeTo(null);
}

public static void main(String[] args) {
	DiscardPile pile;
	Hand player;
	Hand computer;
	boolean isGameOver;
	
	pile = new DiscardPile();
	isGameOver = false;
	/*
	java.awt.EventQueue.invokeLater(new Runnable() {
		public void run() {
			new GoFish().setVisible(true);
		}
	}); */
	/*Card[] deck = new Card[52];

	Deck.makeDeck(deck);*/
	Deck deck = new Deck(52);
	
	//Card.shuffleDeck(deck);
	deck.shuffle();
	
	player = new Hand(7, deck);
	
	computer = new Hand(7, deck);
	
	while(!isGameOver){
		
		Gameplay.listAllCards(player, computer);
		
		Gameplay.playerTurn(player, computer, deck);
	
		Gameplay.computerTurn(player, computer, deck);
		
		Gameplay.checkForBooks(player, computer, pile);
		
		isGameOver = Gameplay.isGameOver(pile);
		
		System.out.println(Hand.cardOnTop);
	}
	
	if (Gameplay.playerPoints > Gameplay.computerPoints)
		System.out.println("You won with a total of " + Gameplay.playerPoints + " points!");
	
	else if(Gameplay.playerPoints < Gameplay.computerPoints)
		System.out.println("The opponent won with a total of " + Gameplay.computerPoints + " points!");
	
	else
		System.out.println("It's a tie!");
	
	System.out.println("**game has ended**");
}

}