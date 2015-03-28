
public class Deck {

	public static int counter = 0;
	public int numCards;
	public Card card[];
	
	public Deck(int numCards) {
		this.numCards = numCards;
		this.card = new Card[numCards];
		for (int i = 0; i < numCards; i++) {
			this.card[i] = new Card(i);
		}
	}
	
	public static void makeDeck(Card[] deck){
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 13; j++){
		
				deck[counter] = new Card(-1);
				deck[counter].setSuit(i);
				deck[counter].setRank(j);
		
				counter++;
			}
		}
	}
	public void shuffle() {
		int index;
		Card temp;
		
		temp = new Card(-1);
		index = 0;
		
		for(int i = 0; i < this.card.length; i++){
			index = (int)(Math.random() * this.card.length);
			
			temp = this.card[i];
			this.card[i] = this.card[index];
			this.card[index] = temp;
		}
	}
}


