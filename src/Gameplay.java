import java.util.Scanner;
public class Gameplay {
	
	public static String[] last3Guesses = { "", "", "" };
	public static int playerPoints = 0, computerPoints = 0;

	public static void playerTurn(Hand human, Hand computer, Deck deck) {
		Scanner console = new Scanner(System.in);
		String rank, tempGuess;
		int cardNum, cardPosition;
		boolean hasRank, hasCard;

		hasRank = false;
		hasCard = false;

		while (!hasRank) {

			System.out.print("\nPlease enter the rank you want: ");

			rank = console.next();

			for (int i = 0; i < human.card.length; i++) {
				if (human.card[i] != null) {
					if (human.card[i].getRank().equals(rank)) {
						hasRank = true;
						last3Guesses[2] = last3Guesses[1];
						last3Guesses[1] = last3Guesses[0];
						last3Guesses[0] = rank;
					}
				}
			}

			if (hasRank) {
				for (int i = 0; i < computer.card.length; i++) {
					if (computer.card[i] != null) {
						if (computer.card[i].getRank().equals(rank)) {
							System.out.println("You got a card!\n");
							cardNum = computer.card[i].getNum();
							human.card[Card.emptyCard(human.card)] = new Card(
									cardNum, false);
							computer.card[i] = null;
							hasCard = true;
						}
					}
				}

				if (!hasCard) {
					if (Hand.cardOnTop < 52) {
						cardNum = deck.card[Hand.cardOnTop].getNum();
						cardPosition = Card.emptyCard(human.card);
						human.card[cardPosition] = new Card(cardNum, false);
						System.out.println("Sorry, go fish! You received a(n) " + human.card[cardPosition].getCard() + "\n");
						Hand.cardOnTop++;
					} else
						System.out.println("The deck is empty, you can't draw.");
				}

			} else
				System.out.println("You do not have any cards of rank " + rank);
		}
	}

	public static void computerTurn(Hand human, Hand computer, Deck deck) {

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
		computerCard = "";

		for (int i = 0; i < computer.card.length; i++) {
			if (computer.card[i] != null) {
				firstGuess = computer.card[i].getRank().equals(last3Guesses[0]);

				if (firstGuess)
					playerCard = last3Guesses[0];
				secondGuess = computer.card[i].getRank().equals(last3Guesses[1]);

				if (secondGuess)
					playerCard = last3Guesses[1];
				thirdGuess = computer.card[i].getRank().equals(last3Guesses[2]);

				if (thirdGuess)
					playerCard = last3Guesses[2];

				if (firstGuess || secondGuess || thirdGuess)
					hasPlayerCard = true;
			}
		}

		if (hasPlayerCard) {
			System.out.println("The computer got card(s) of rank " + playerCard + " from you!\n");
			for (int i = 0; human.card[i] != null; i++) {
				if (human.card[i].getRank().equals(playerCard)) {
					cardNum = human.card[i].getNum();
					computer.card[Card.emptyCard(computer.card)] = new Card(
							cardNum, false);
					human.card[i] = null;
				}
			}
		} else {
			for (int i = 0; i < computer.card.length; i++) {
				if (computer.card[i] != null)
					cardsInHand++;
			}

			while (!isValid && cardsInHand != 0) {
				compNum = (int) (Math.random() * cardsInHand);

				if (computer.card[compNum] != null)
					isValid = true;
			}
			
			if(cardsInHand != 0)
				computerCard = computer.card[compNum].getRank();

			for (int i = 0; i < human.card.length; i++) {
				if (human.card[i] != null) {
					if (human.card[i].getRank().equals(computerCard))
						hasCard = true;
				}
			}

			if (hasCard) {
				System.out.println("The computer got card(s) of rank " + computerCard + " from you!\n");
				for (int i = 0; i < human.card.length; i++) {
					if (human.card[i] != null) {
						if (human.card[i].getRank().equals(computerCard)) {
							cardNum = human.card[i].getNum();
							computer.card[Card.emptyCard(computer.card)] = new Card(cardNum, false);
							human.card[i] = null;
						}
					}
				}
			} else {
				if (Hand.cardOnTop < 52) {
					System.out.println("The computer asked for a(n) " + computerCard);
					System.out.println("The computer drew from the pile.\n");
					cardNum = deck.card[Hand.cardOnTop].getNum();
					computer.card[Card.emptyCard(computer.card)] = new Card(
							cardNum, false);
					Hand.cardOnTop++;
				} else
					System.out.println("The deck is empty, the computer couldn't draw.");
			}
		}
	}

	public static boolean isGameOver(DiscardPile Pile){
		int totalPointsGiven;
		boolean GameOver;
		
		GameOver = false;
		totalPointsGiven = computerPoints + playerPoints;
		
		if(totalPointsGiven == 13)
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
				playerPoints++;
				System.out.println("You earned a point! Your score is now " + playerPoints);
				
				for(int j = 0; j < human.card.length; j++){
					if(human.card[j] != null){
						if(human.card[j].getRank().equals(Card.RANK[i])){
							cardNum = human.card[j].getNum();
							Pile.pile[Card.emptyCard(Pile.pile)] = new Card(cardNum, false);
							human.card[j] = null;
						}
					}
				}
			}
			
			for(int j = 0; j < computer.card.length; j++){
				if(computer.card[j] != null){
					if(computer.card[j].getRank().equals(Card.RANK[i]))
						computerCounter++;
				}
			}
			
			if(computerCounter == 4){
				computerPoints++;
				System.out.println("The computer earned a point! Their score is now " + computerPoints);
				
				for(int j = 0; j < computer.card.length; j++){
					if(computer.card[j] != null){
						if(computer.card[j].getRank().equals(Card.RANK[i])){
							cardNum = computer.card[j].getNum();
							Pile.pile[Card.emptyCard(Pile.pile)] = new Card(cardNum, false);
							computer.card[j] = null;
						}
					}
				}
			}
		}
	}
	
	public static void listAllCards(Hand player, Hand computer){
		System.out.println("Your cards:\n");
		
		for(int i = 0; i < player.card.length; i++){
			if(player.card[i] != null)
				System.out.println(player.card[i].getRank() + " " + player.card[i].getSuit());
		}
		/*
		System.out.println("New computer player cards:");
		
		for(int i = 0; i < computer.card.length; i++){
			if(computer.card[i] != null)
				System.out.println(computer.card[i].getRank() + " " + computer.card[i].getSuit());
		} */
	}
}


