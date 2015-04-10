
public class Hand {
public int numCards;
public Card card[];

public Hand(int numCards, Deck deck) {
	int x;
	x = 0;
	
	this.numCards = numCards;
	this.card = new Card[numCards];
	for (int i = 0; i < (numCards + x); i++) {
		if (deck.card[i].isInDeck){
			this.card[i - x] = new Card(deck.getCardInt(i), false);
			deck.card[i].isInDeck = false;
			System.out.println(this.card[i - x].getRank() + " " + this.card[i - x].getSuit());
		}
		else
			x++;
	}
}
}
