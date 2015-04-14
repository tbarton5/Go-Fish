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
	
	java.awt.EventQueue.invokeLater(new Runnable() {
		public void run() {
			new GoFish().setVisible(true);
		}
	});
	/*Card[] deck = new Card[52];

	Deck.makeDeck(deck);*/
	Deck deck = new Deck(52);
	
	//Card.shuffleDeck(deck);
	deck.shuffle();
	
	System.out.println("Human player cards:");
	player = new Hand(7, deck);
	
	System.out.println("Computer player cards:");
	computer = new Hand(7, deck);
	
	while(!isGameOver){
		
		Hand.playerTurn(player, computer, deck);
		
		Card.listAllCards(player,  computer);
	
		Hand.computerTurn(player, computer, deck);
		
		Card.listAllCards(player, computer);
		
		DiscardPile.checkForBooks(player, computer, pile);
		
		isGameOver = DiscardPile.isGameOver(pile);
	}
}

}