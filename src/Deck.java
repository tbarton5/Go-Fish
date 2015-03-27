
public class Deck {

	public static int counter = 0;
	
	public static void makeDeck(Card[] deck){
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 13; j++){
		
				deck[counter] = new Card();
				deck[counter].setSuit(i);
				deck[counter].setRank(j);
		
				counter++;
			}
		}
	}
}


