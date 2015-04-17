
public class DiscardPile {
	public Card[] pile = new Card[52];
	
	public DiscardPile(){
		for (int i = 0; i < pile.length; i++)
			pile[i] = null;
	}

}
