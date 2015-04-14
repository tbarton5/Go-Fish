
public class DiscardPile {
	public Card[] pile = new Card[52];
	public static int playerPoints = 0, computerPoints = 0;
	
	public DiscardPile(){
		for (int i = 0; i < pile.length; i++)
			pile[i] = null;
	}

	public static boolean isGameOver(DiscardPile Pile){
		int numCardsInPile;
		boolean GameOver;
		
		numCardsInPile = 0;
		GameOver = false;
		
		for(int i = 0; i < Pile.pile.length; i++){
			if(Pile.pile[i] != null)
				numCardsInPile++;
		}
		
		if(numCardsInPile == Pile.pile.length)
			GameOver = true;
		
		return GameOver;
	}
	
	public static void checkForBooks(Hand human, Hand computer, DiscardPile Pile){
		
		for(int i = 0; i < Card.RANK.length; i++){
			int playerCounter, computerCounter, cardNum;
			playerCounter = 0;
			computerCounter = 0;
			cardNum = 0;
			
			for(int j = 0; j < human.card.length; j++){
				if(human.card[j] != null){
					if(human.card[j].getRank().equals(Card.RANK[i]))
						playerCounter++;
				}
			}
			
			if(playerCounter == 4){
				System.out.println("You earned a point!");
				
				for(int j = 0; j < human.card.length; j++){
					if(human.card[j] != null){
						if(human.card[j].getRank().equals(Card.RANK[i])){
							cardNum = human.card[j].getNum();
							Pile.pile[Card.emptyCard(Pile.pile)] = new Card(cardNum, false);
							human.card[j] = null;
						}
					}
				}
				playerPoints++;
			}
			
			for(int j = 0; j < computer.card.length; j++){
				if(computer.card[j] != null){
					if(computer.card[j].getRank().equals(Card.RANK[i]))
						computerCounter++;
				}
			}
			
			if(computerCounter == 4){
				System.out.println("The computer earned a point!");
				
				for(int j = 0; j < computer.card.length; j++){
					if(computer.card[j] != null){
						if(computer.card[j].getRank().equals(Card.RANK[i])){
							cardNum = computer.card[j].getNum();
							Pile.pile[Card.emptyCard(Pile.pile)] = new Card(cardNum, false);
							computer.card[j] = null;
						}
					}
				}
				computerPoints++;
			}
		}
	}
}
