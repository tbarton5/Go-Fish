import java.util.Scanner;
public class Card {

	public String suit;
	public String rank;
	
	public static final String[] SUIT = {"Spades", "Clubs", "Hearts", "Diamonds"};
	public static final String[] RANK = {"Ace", "2", "3", "4", "5", "6", "7", 
			"8", "9", "10", "Jack", "Queen", "King"};

	
	public Card(int num){
		if (num == -1) {
			suit = SUIT[(int)(Math.random() * 4)];
			rank = RANK[(int)(Math.random() * 13)];
		}
		else {
			suit = SUIT[num / 13];
			rank = RANK[num % 13];
		}
	}
	
	public void setSuit(int x){
		suit = SUIT[x];
	}
	
	public void setRank(int x){
		rank = RANK[x];
	}
	public String getRank(){
		return rank;
	}
	
	public String getSuit(){
		return suit;
	}
	
	public static void shuffleDeck(Card[] deck){
		int index;
		Card temp;
		
		temp = new Card(-1);
		index = 0;
		
		for(int i = 0; i < deck.length; i++){
			index = (int)(Math.random() * deck.length);
			
			temp = deck[i];
			deck[i] = deck[index];
			deck[index] = temp;
		}
	}
}