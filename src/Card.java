import java.util.Scanner;
public class Card {

	public String suit1;
	public String rank1;
	
	public String[] suit = {"Spades", "Clubs", "Hearts", "Diamonds"};
	public String[] rank = {"Ace", "2", "3", "4", "5", "6", "7", 
			"8", "9", "10", "Jack", "Queen", "King"};

	
	public Card(){
		suit1 = suit[(int)(Math.random() * 4)];
		rank1 = rank[(int)(Math.random() * 13)];
	}
	
	public void setSuit(int x){
		suit1 = suit[x];
	}
	
	public void setRank(int x){
		rank1 = rank[x];
	}
	public String getRank(){
		return rank1;
	}
	
	public String getSuit(){
		return suit1;
	}
	
	public static void shuffleDeck(Card[] deck){
		int index;
		Card temp;
		
		temp = new Card();
		index = 0;
		
		for(int i = 0; i < deck.length; i++){
			index = (int)(Math.random() * deck.length);
			
			temp = deck[i];
			deck[i] = deck[index];
			deck[index] = temp;
		}
	}
}