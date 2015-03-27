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
	java.awt.EventQueue.invokeLater(new Runnable() {
		public void run() {
			new GoFish().setVisible(true);
		}
	});
	Card[] deck = new Card[52];

	Deck.makeDeck(deck);
	
	for(int i = 0; i < 52; i++)
		System.out.println("Card " + (i + 1) + " is the " + deck[i].getRank() + " of " + deck[i].getSuit());
		
	
	Card.shuffleDeck(deck);
	
	for(int i = 0; i < 52; i++)
		System.out.println("Now, card " + (i + 1) + " is the " + deck[i].getRank() + " of " + deck[i].getSuit());
}

}