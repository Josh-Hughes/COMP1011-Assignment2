package gc.cs.comp1011.memorygame;
/**
 * @filename: Deck.java
 * @author Josh Hughes & Luis Acevedo
 * @version November 28th, 2014
 * @description This method holds the deck of cards.
 */
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	//Variables
	public ArrayList<String> deck = new ArrayList<String>();
	private final String[] DECK_FACE = {"1c","2c","3c","4c","5c","6c","7c","8c","9c","10c",
										"1d","2d","3d","4d","5d","6d","7d","8d","9d","10d",
										"1h","2h","3h","4h","5h","6h","7h","8h","9h","10h",
										"1s","2s","3s","4s","5s","6s","7s","8s","9s","10s"};
	/**
	 * Constructor of the deck class. Calls the initialize of the deck
	 */
	public Deck(){
		resetDeck();
	}
	
	/**
	 * This method sets the value of the array list to the value givens
	 * @param deck value of the card
	 */
	private void setDeck(String deck){
		this.deck.add(deck);
	}
	
	/**
	 * Clears the array list and sets all the cards once again in the deck
	 */
	public void resetDeck(){
		//Clean the array list
		deck.clear();
		
		//Fill the array list
		for(String deck: DECK_FACE){
			setDeck(deck);
		}
		
		//Random the deck 
		Collections.shuffle(deck);
	}
	
	/**
	 * Returns a random card to the user and takes it out of the deck
	 * @return the value of the card to be used
	 */
	public String getRandomCard(){
		//instance variables
		String cardBack = "";
		
		//Get the card for the user to use
		cardBack = deck.get(1);
		
		//Remove the card from the deck to avoid repetition
		deck.remove(1);
		
		//Return the value
		return cardBack;
	}

}
