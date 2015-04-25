
public class Deck {

	public int numCards;
	public Card card[];
	
	public Deck(int numCards) {
		this.numCards = numCards;
		this.card = new Card[numCards];
		for (int i = 0; i < numCards; i++) {
			this.card[i] = new Card(i, true);
			//System.out.println("Card " + (i + 1) + " is the " + this.card[i].getRank() + " of " + this.card[i].getSuit());
		}
	}
	
	
	public void shuffle() {
		int index;
		Card temp;
		
		temp = new Card(-1, true);
		
		for(int i = 0; i < this.card.length; i++){
			index = (int)(Math.random() * this.card.length);
			
			temp = this.card[i];
			this.card[i] = this.card[index];
			this.card[index] = temp;
		}
		/*
		for (int i = 0; i < this.card.length; i++) {
			System.out.println("After shuffling, card " + (i + 1) + " is the " + this.card[i].getRank() + " of " + this.card[i].getSuit());
		} */
	}
	
	public void setCard(int element, int num){
		this.card[element].setNum(num);
	}
	
	public int getCardInt(int num){
		Card card;
		card = this.card[num];
		return card.getNum();
	}
	
	public Card getCard(int num){
		Card card;
		card = this.card[num];
		return card;
	}
	
	public static int getCard(String rank, String suit, Deck deck){
		int result;
		result = 0;
		
		for (int i = 0; i < 52; i++){
			if(deck.card[i].getRank().equals(rank) && deck.card[i].getSuit().equals(suit))
				result = deck.card[i].getNum();
		}
		
		return result;
	}
}


