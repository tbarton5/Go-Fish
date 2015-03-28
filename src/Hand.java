
public class Hand {
public int numCards;
public Card card[];

public Hand(int numCards) {
	this.numCards = numCards;
	this.card = new Card[numCards];
	for (int i = 0; i < numCards; i++) {
		this.card[i] = new Card(i);
	}
}
}
