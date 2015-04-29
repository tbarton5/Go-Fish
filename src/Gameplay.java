import javax.swing.JOptionPane;


public class Gameplay {

	public static String[] last3Guesses = { "", "", "" };
	public static int playerPoints = 0, computerPoints = 0;
	
	public static void playRound(String rank) {
		playerTurn(GoFish.player, GoFish.computer, GoFish.deck, rank);
		computerTurn(GoFish.player, GoFish.computer, GoFish.deck);
		checkForBooks(GoFish.player, GoFish.computer, GoFish.pile);
		isGameOver(GoFish.pile);
		listAllCards(GoFish.player, GoFish.computer);
	}
	
	public static void playerTurn(Hand human, Hand computer, Deck deck, String rank) {
		//Scanner console = new Scanner(System.in);
		//String rank, tempGuess;
		int cardNum, numOfCards;
		boolean hasRank, hasCard;

		numOfCards = 0;
		hasRank = false;
		hasCard = false;
		//rank = "";

		for (int i = 0; i < human.card.length; i++) {
			if (human.card[i] != null)
				numOfCards++;
		}

		while (!hasRank) {

			/*if (numOfCards != 0) {
				System.out.print("\nPlease enter the rank you want: ");
				rank = console.next();
			}*/

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

			if (hasRank || numOfCards == 0) {
				for (int i = 0; i < computer.card.length; i++) {
					if (computer.card[i] != null) {
						if (computer.card[i].getRank().equals(rank)) {
							System.out.println("You got a card!\n");
							cardNum = computer.card[i].getNum();
							human.card[Card.emptyCard(human.card)] = new Card(
									cardNum, false);
							computer.card[i] = null;
							hasCard = true;
							GoFish.button[cardNum % 13].setEnabled(true);
							//GoFish.currentCards[cardNum].setText(human.card[i].getSuit());
							GoFish.currentCards[cardNum].setVisible(true);
						}
					}
				}

				if (!hasCard) {
					if (Hand.cardOnTop < 52) {
						cardNum = deck.card[Hand.cardOnTop].getNum();
						human.card[Card.emptyCard(human.card)] = new Card(
								cardNum, false);
						System.out.println("Go Fish! You received the "
								+ deck.card[Hand.cardOnTop].getRank() + " of "
								+ deck.card[Hand.cardOnTop].getSuit() + ".");
						Hand.cardOnTop++;
						GoFish.button[cardNum % 13].setEnabled(true);
						//GoFish.currentCards[cardNum].setText(deck.card[Hand.cardOnTop].getSuit());
						GoFish.currentCards[cardNum].setVisible(true);
					} else{
						System.out.println("The deck is empty, you can't draw.");
						hasRank = true;
					}
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
				secondGuess = computer.card[i].getRank()
						.equals(last3Guesses[1]);

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
			System.out.println("The computer got card(s) of rank " + playerCard
					+ " from you!\n");
			for (int i = 0; human.card[i] != null; i++) {
				if (human.card[i].getRank().equals(playerCard)) {
					cardNum = human.card[i].getNum();
					GoFish.button[cardNum % 13].setEnabled(false);
					//GoFish.currentCards[cardNum].setText("");
					GoFish.currentCards[cardNum].setVisible(false);
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

			if (cardsInHand != 0)
				computerCard = computer.card[compNum].getRank();

			for (int i = 0; i < human.card.length; i++) {
				if (human.card[i] != null) {
					if (human.card[i].getRank().equals(computerCard))
						hasCard = true;
				}
			}

			if (hasCard) {
				System.out.println("The computer got card(s) of rank "
						+ computerCard + " from you!!\n");
				for (int i = 0; i < human.card.length; i++) {
					if (human.card[i] != null) {
						if (human.card[i].getRank().equals(computerCard)) {
							cardNum = human.card[i].getNum();
							GoFish.button[cardNum % 13].setEnabled(false);
							//GoFish.currentCards[cardNum].setText("");
							GoFish.currentCards[cardNum].setVisible(false);
							computer.card[Card.emptyCard(computer.card)] = new Card(
									cardNum, false);
							human.card[i] = null;
						}
					}
				}
			} else {
				if (Hand.cardOnTop < 52) {
					System.out.println("The computer drew from the pile.\n");
					cardNum = deck.card[Hand.cardOnTop].getNum();
					computer.card[Card.emptyCard(computer.card)] = new Card(
							cardNum, false);
					Hand.cardOnTop++;
				} else
					System.out
							.println("The deck is empty, the computer couldn't draw.");
			}
		}
	}

	public static void isGameOver(DiscardPile Pile) {
		if (computerPoints + playerPoints == 13) {
			if (playerPoints > computerPoints)
				JOptionPane.showMessageDialog(null, "You win!");
			else if (playerPoints < computerPoints)
				JOptionPane.showMessageDialog(null, "You lose!");
			else
				JOptionPane.showMessageDialog(null, "It's a tie!");
			System.out.println("The game has ended. You may now close the window.");
		}
	}

	public static void checkForBooks(Hand human, Hand computer, DiscardPile Pile) {

		for (int i = 0; i < Card.RANK.length; i++) {
			int playerCounter, computerCounter, cardNum;
			playerCounter = 0;
			computerCounter = 0;
			cardNum = 0;

			for (int j = 0; j < human.card.length; j++) {
				if (human.card[j] != null) {
					if (human.card[j].getRank().equals(Card.RANK[i]))
						playerCounter++;
				}
			}

			if (playerCounter == 4) {
				playerPoints++;
				System.out.println("You earned a point! Your score is now "
						+ playerPoints);

				for (int j = 0; j < human.card.length; j++) {
					if (human.card[j] != null) {
						if (human.card[j].getRank().equals(Card.RANK[i])) {
							cardNum = human.card[j].getNum();
							Pile.pile[Card.emptyCard(Pile.pile)] = new Card(
									cardNum, false);
							human.card[j] = null;
							GoFish.button[cardNum % 13].setEnabled(false);
							GoFish.button[cardNum % 13].setText("\u2713");
							GoFish.currentCards[cardNum].setVisible(true);
						}
					}
				}
			}

			for (int j = 0; j < computer.card.length; j++) {
				if (computer.card[j] != null) {
					if (computer.card[j].getRank().equals(Card.RANK[i]))
						computerCounter++;
				}
			}

			if (computerCounter == 4) {
				computerPoints++;
				System.out
						.println("The computer earned a point! Their score is now "
								+ computerPoints);

				for (int j = 0; j < computer.card.length; j++) {
					if (computer.card[j] != null) {
						if (computer.card[j].getRank().equals(Card.RANK[i])) {
							cardNum = computer.card[j].getNum();
							Pile.pile[Card.emptyCard(Pile.pile)] = new Card(
									cardNum, false);
							computer.card[j] = null;
							GoFish.button[cardNum % 13].setText("X");
						}
					}
				}
			}
		}
	}

	public static void listAllCards(Hand player, Hand computer) {
		System.out.println("Your cards:\n");

		for (int i = 0; i < player.card.length; i++) {
			if (player.card[i] != null) {
				System.out.println(player.card[i].getRank() + " "
						+ player.card[i].getSuit());
				//GoFish.currentCards[player.card[i].getNum()].setText(player.card[i].getSuit());
				GoFish.currentCards[player.card[i].getNum()].setVisible(true);
			}
		}
		/*
		 * System.out.println("New computer player cards:");
		 * 
		 * for(int i = 0; i < computer.card.length; i++){ if(computer.card[i] !=
		 * null) System.out.println(computer.card[i].getRank() + " " +
		 * computer.card[i].getSuit()); }
		 */
	}
	public static void enablePlayerCards(Hand player) {
		for (int i = 0; i < player.card.length; i++)
			if (player.card[i] != null)
				GoFish.button[player.card[i].getNum() % 13].setEnabled(true);
	}
}
