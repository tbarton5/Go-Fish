import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class GoFish {
	public static DiscardPile pile;
	public static Hand player;
	public static Hand computer;
	public static Deck deck;
	
	public static JFrame window;
	public static JLabel currentCards[];
	public static JButton button[];
	public static void main(String[] args) {
		deck = new Deck(52);
		deck.shuffle();
		player = new Hand(7, deck);
		computer = new Hand(7, deck);
		
		pile = new DiscardPile();
		
		window = new JFrame();
		window.getContentPane().setPreferredSize(new Dimension(1120, 630));
		
		window.pack();
		
		window.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		window.setTitle("Go Fish");
		window.setLocationRelativeTo(null);
		
		JPanel panelTop = new JPanel();
		panelTop.setBackground(new Color(0, 0, 128));
		
		JPanel panelBottom = new JPanel();
		panelBottom.setBackground(new Color(0, 128, 0));
		panelBottom.setLayout((LayoutManager) new GridLayout(1,13));
		
		currentCards = new JLabel[52];
		button = new JButton[13];
		for (int i = 0; i < 13; i++) {
			JPanel col = new JPanel();
			col.setLayout((LayoutManager) new GridLayout(5, 1));
			button[i] = new JButton(Card.RANK[i]);
			button[i].setEnabled(false);
			button[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//System.out.print(e.getSource());
					Gameplay.playRound(((JButton) e.getSource()).getActionCommand());
				}
			});
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
		
		
		//Card.shuffleDeck(deck);
		
		Gameplay.enablePlayerCards(player);
		Gameplay.listAllCards(GoFish.player, GoFish.computer);
		
		/*while(!isGameOver){
			Gameplay.playRound(player, computer, deck, pile);
		}*/
	}
}