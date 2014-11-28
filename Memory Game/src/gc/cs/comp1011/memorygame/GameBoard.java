package gc.cs.comp1011.memorygame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class GameBoard {
	
	// A reference to the game frame.
	GameFrame gameFrame;
	
	private final double COUNTDOWN_DEFAULT = 60;
	private final int COUNTDOWN_INTERVAL = 100;
	private final double COUNTDOWN_DECREMENT = 0.1;

	// The countdown timer.
	private Timer countdownTimer;
	// The current countdown value.
	private double countdown;
	
	// The score board keeps track of the player's score.
	private Scoreboard scoreboard;

	// The cards selected by the player, null if not yet picked. 
	private Card selectedCard1 = null;
	private Card selectedCard2 = null;
	
	public GameBoard(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		
		// Create the score board.
		scoreboard = new Scoreboard();

		// Create the countdown timer.
		countdownTimer = new Timer(COUNTDOWN_INTERVAL, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				countdown -= COUNTDOWN_DECREMENT;
				//System.out.printf("\nTick %.1f", countdown);
				gameFrame.setTimerText(String.format("%.1f", countdown));
			}
		});
		
		// Set the default countdown timer value.
		countdown = COUNTDOWN_DEFAULT;
		
	}

	public void newGame(){
		gameFrame.changeCards();
		gameFrame.layoutGame();
	}
	
	public void cardClicked(ActionEvent e) {
		// Start the countdown timer if it's not started yet.
		if (!countdownTimer.isRunning()) {
			countdownTimer.start();
		}
		
		// Get the card that was clicked on.
		Card clickedCard = (Card)e.getSource();
		
		if (selectedCard1 == null) {
			selectedCard1 = clickedCard;
			gameFrame.setUserMessageText(GameMessages.SECOND_CARD.getMessage());
		} else {
			if (!selectedCard1.equals(clickedCard)) {
				selectedCard2 = clickedCard;
				if (selectedCard1.getCardIdentity().equals(selectedCard2.getCardIdentity())) {
					gameFrame.setUserMessageText(GameMessages.RIGHT_MATCH.getMessage() + " [" + selectedCard1.getCardIdentity() + " == " + selectedCard2.getCardIdentity() + "]");
					selectedCard1.setVisible(false);
					selectedCard2.setVisible(false);
				} else {
					gameFrame.setUserMessageText(GameMessages.WRONG_MATCH.getMessage() + " [" + selectedCard1.getCardIdentity() + " != " + selectedCard2.getCardIdentity() + "]");
				}
				selectedCard1 = null;
				selectedCard2 = null;
			}
		}
					
		// Reveal the card face.
		clickedCard.showCard();
		
	}//action performed
}
