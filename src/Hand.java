import java.util.Scanner;
public class Hand {
public int numCards;
public Card card[];
public static int cardOnTop = 0;
public static String [] last3Guesses = {"", "", ""};

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
	
	public static void playerTurn(Hand human, Hand computer, Deck deck){
		Scanner console = new Scanner(System.in);
		String rank, tempGuess;
		int cardNum;
		boolean hasRank, hasCard;
		
		hasRank = false;
		hasCard = false;
		
		System.out.print("Please enter the rank you want: ");
		
		rank = console.next();
		
		for(int i = 0; i < human.card.length; i++){
			if(human.card[i] != null){	
				if(human.card[i].getRank().equals(rank)){
					hasRank = true;
					last3Guesses[2] = last3Guesses[1];
					last3Guesses[1] = last3Guesses[0];
					last3Guesses[0] = rank;
				}
			}
		}
		
		if(hasRank){
			for(int i = 0; i < computer.card.length; i++){
				if(computer.card[i] != null){
					if(computer.card[i].getRank().equals(rank)){
						System.out.println("You got a card!\n");
						cardNum = computer.card[i].getNum();
						human.card[Card.emptyCard(human.card)] = new Card(cardNum, false);
						computer.card[i] = null;
						hasCard = true;
					}
				}
			}
			
			if(!hasCard){
				if(cardOnTop < 52){
					System.out.println("Sorry, go fish!\n");
					cardNum = deck.card[cardOnTop].getNum();
					human.card[Card.emptyCard(human.card)] = new Card(cardNum, false);
					cardOnTop++;
				}
				else
					System.out.println("The deck is empty, you can't draw.");
			}
			
		}
		else
			System.out.println("You do not have any cards of rank " + rank);
		
	}
	
	public static void computerTurn(Hand human, Hand computer, Deck deck){
		
		String playerCard, computerCard;
		boolean hasPlayerCard, isValid;
		boolean firstGuess, secondGuess, thirdGuess, hasCard;
		int cardNum, cardsInHand, compNum;
		
		hasPlayerCard = false;
		hasCard = false;
		isValid = false;
		cardsInHand = 0;
		cardNum = 0;
		compNum = 0;
		playerCard = "";
		
		for(int i = 0; i < computer.card.length; i++){
			if(computer.card[i] != null){
				firstGuess = computer.card[i].getRank().equals(last3Guesses[0]);
			
				if(firstGuess)
					playerCard = last3Guesses[0];
				secondGuess = computer.card[i].getRank().equals(last3Guesses[1]);
			
				if(secondGuess)
					playerCard = last3Guesses[1];
				thirdGuess = computer.card[i].getRank().equals(last3Guesses[2]);
			
				if(thirdGuess)
					playerCard = last3Guesses[2];
			
				if(firstGuess || secondGuess || thirdGuess)
					hasPlayerCard = true;
			}
		}
		
		if(hasPlayerCard){
			System.out.println("The computer got card(s) from you!\n");
			for (int i = 0; human.card[i] != null; i++){
				if(human.card[i].getRank().equals(playerCard)){
					cardNum = human.card[i].getNum();
					computer.card[Card.emptyCard(computer.card)] = new Card(cardNum, false);
					human.card[i] = null;
				}
			}
		}
		else{
			for(int i = 0; i < computer.card.length; i++){
				if(computer.card[i] != null)
					cardsInHand++;
			}
			
			while(!isValid){
				compNum = (int)(Math.random() * cardsInHand);
				
				if(computer.card[compNum] != null)
					isValid = true;
			}
			
			computerCard = computer.card[compNum].getRank();
			
			for(int i = 0; i < human.card.length; i++){
				if(human.card[i] != null){
					if(human.card[i].getRank().equals(computerCard))
						hasCard = true;
				}
			}
			
			if(hasCard){
				System.out.println("The computer got card(s) from you!\n");
				for(int i = 0; i < human.card.length; i++){
					if(human.card[i] != null){
						if(human.card[i].getRank().equals(computerCard)){
							cardNum = human.card[i].getNum();
							computer.card[Card.emptyCard(computer.card)] = new Card(cardNum, false);
							human.card[i] = null;
						}
					}
				}
			}
			else{
				if(cardOnTop < 52){
					System.out.println("The computer drew from the pile.\n");
					cardNum = deck.card[cardOnTop].getNum();
					computer.card[Card.emptyCard(computer.card)] = new Card(cardNum, false);
					cardOnTop++;
				}
				else
					System.out.println("The deck is empty, the computer couldn't draw.");
			}
		}
	}
	
}
