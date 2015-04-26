
public class Hand {
	public int numCards;
	public Card card[];
	public static int cardOnTop = 0;

	public Hand(int numCards, Deck deck) {
		int x;
		x = 0;

		this.numCards = numCards;
		this.card = new Card[40];
		for (int i = 0; i < (7 + x); i++) {
			if (deck.card[i].isInDeck) {
				this.card[i - x] = new Card(deck.getCardInt(i), false);
				deck.card[i].isInDeck = false;
				// System.out.println(this.card[i - x].getRank() + " " +
				// this.card[i - x].getSuit());
				cardOnTop++;
			} else
				x++;
		}
	}
}
