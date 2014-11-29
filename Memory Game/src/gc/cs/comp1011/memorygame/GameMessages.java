package gc.cs.comp1011.memorygame;
/**
 * @filename: GameMessages.java
 * @author Josh Hughes & Luis Acevedo
 * @version November 28th, 2014
 * @description Enum type to hold the messages from the system
 */
public enum GameMessages {
	// Enumerations
	FIRST_CARD("Pick a card, any card."),
	SECOND_CARD("Now pick another card."),
	RIGHT_MATCH("Right! Pick again..."),
	WRONG_MATCH("Wrong! Pick again..."),
	WIN_GAME("You won! Your final score is %d!"),
	LOSE_GAME("Times up! Your final score is %d");
	
	// Enum instance variables
	private final String message;
	
	// Enum methods
	/**
	 * Sets the message to the enum value required
	 * @param message required by user
	 */
	GameMessages(String message) {
		this.message = message;
	}
	
	/**
	 * Returns the current status of the enum type
	 * @return the value of the message required by the user
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Returns a string with a message
	 * @return the message required 
	 */
	@Override
	public String toString() {
		return getMessage();
	}
}
