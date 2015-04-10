import java.util.Scanner;
public class Card {

	public int num;
	public String suit;
	public String rank;
	public boolean isInDeck;
	public static final String[] SUIT = {"Spades", "Clubs", "Hearts", "Diamonds"};
	public static final String[] RANK = {"Ace", "2", "3", "4", "5", "6", "7", 
			"8", "9", "10", "Jack", "Queen", "King"};

	
	public Card(int num, boolean isInDeck){
		if (num == -1)
			num = (int) (Math.random() * 52);
		
		suit = SUIT[num / 13];
		rank = RANK[num % 13];
		this.num = num;
		this.isInDeck = isInDeck;
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
	
	public void setNum(int num){
		this.num = num;
	}
	
	public int getNum(){
		return this.num;
	}
	
	public static void shuffleDeck(Card[] deck){
		int index;
		Card temp;
		
		temp = new Card(-1, true);
		index = 0;
		
		for(int i = 0; i < deck.length; i++){
			index = (int)(Math.random() * deck.length);
			
			temp = deck[i];
			deck[i] = deck[index];
			deck[index] = temp;
		}
	}
}