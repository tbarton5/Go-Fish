import java.util.*;
import java.awt.*;

import javax.swing.*;
public class GoFish {
	public static JFrame window;
	public static JLabel currentCards[];
	public static JButton button[];
	public static void main(String[] args) {
		DiscardPile pile;
		Hand player;
		Hand computer;
		boolean isGameOver;
		window = new JFrame();
		JPanel panelTop = new JPanel();
		JPanel panelBottom = new JPanel();
		
		pile = new DiscardPile();
		isGameOver = false;
		
		window.getContentPane().setPreferredSize(new Dimension(960, 600));
		
		window.pack();
		
		window.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		window.setTitle("Go Fish");
		window.setLocationRelativeTo(null);
		
		panelTop.setBackground(new Color(0, 0, 128));
		
		panelBottom.setBackground(new Color(0, 128, 0));
		panelBottom.setLayout((LayoutManager) new GridLayout(1,13));
		currentCards = new JLabel[52];
		button = new JButton[13];
		for (int i = 0; i < 13; i++) {
			JPanel col = new JPanel();
			col.setLayout((LayoutManager) new GridLayout(5, 1));
			button[i] = new JButton(Card.RANK[i]);
			button[i].setEnabled(false);
			col.add(button[i]);
			for (int j = 0; j < 52; j += 13) {
				currentCards[i + j] = new JLabel();
				col.add(currentCards[i + j]);
			}
			panelBottom.add(col);
		}
		
		window.setLayout((LayoutManager) new GridLayout(2, 1));
		window.add(panelTop);
		window.add(panelBottom);
		
		window.setVisible(true);
		
		Deck deck = new Deck(52);
		
		//Card.shuffleDeck(deck);
		deck.shuffle();
		
		player = new Hand(7, deck);
		Gameplay.enablePlayerCards(player);
		
		computer = new Hand(7, deck);
		
		while(!isGameOver){
			
			Gameplay.listAllCards(player, computer);
			Gameplay.playerTurn(player, computer, deck);
			Gameplay.computerTurn(player, computer, deck);
			Gameplay.checkForBooks(player, computer, pile);
			isGameOver = Gameplay.isGameOver(pile);
		}
		
		if (Gameplay.playerPoints > Gameplay.computerPoints)
			System.out.println("You won with a total of " + Gameplay.playerPoints + " points!");
		
		else if(Gameplay.playerPoints < Gameplay.computerPoints)
			System.out.println("The opponent won with a total of " + Gameplay.computerPoints + " points!");
		
		else
			System.out.println("It's a tie!");
		
		System.out.println("**game has ended**");
		System.exit(0);
	}
}