package gc.cs.comp1011.memorygame;

public enum GameMessages {
	// Enumerations
	FIRST_CARD("Pick a card, any card."),
	SECOND_CARD("Now pick another card."),
	RIGHT_MATCH("Right! Pick again..."),
	WRONG_MATCH("Wrong! Pick again...");
	
	// Enum instance variables
	private final String message;
	
	// Enum methods
	GameMessages(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		return getMessage();
	}
}
