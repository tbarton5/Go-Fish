import java.util.Scanner;
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
			if (deck.card[i].isInDeck){
				this.card[i - x] = new Card(deck.getCardInt(i), false);
				deck.card[i].isInDeck = false;
				System.out.println(this.card[i - x].getRank() + " " + this.card[i - x].getSuit());
				cardOnTop++;
			}
			else
				x++;
		}
	}
	
	public static void askForCards(Hand human, Hand computer, Deck deck){
		Scanner console = new Scanner(System.in);
		String rank;
		int cardNum;
		boolean hasRank, hasCard;
		
		hasRank = false;
		hasCard = false;
		
		System.out.print("Please enter the rank you want: ");
		
		rank = console.next();
		
		for(int i = 0; human.card[i] != null; i++){
			if(human.card[i].getRank().equals(rank))
				hasRank = true;
		}
		
		if(hasRank){
			for(int i = 0; computer.card[i] != null; i++){
				if(computer.card[i].getRank().equals(rank)){
					System.out.println("You got a card!");
					cardNum = computer.card[i].getNum();
					human.card[Card.emptyCard(human.card)] = new Card(cardNum, false);
					computer.card[i] = null;
					hasCard = true;
				}
			}
			
			if(!hasCard){
				System.out.println("Sorry, go fish!");
				
				cardNum = deck.card[cardOnTop].getNum();
				human.card[Card.emptyCard(human.card)] = new Card(cardNum, false);
				cardOnTop++;
			}
			
		}
		else
			System.out.println("You do not have any cards of rank " + rank);
	}
	
}
