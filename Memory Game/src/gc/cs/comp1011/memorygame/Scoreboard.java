package gc.cs.comp1011.memorygame;
/**
 * @filename: Scoreboard.java
 * @author Josh Hughes & Luis Acevedo
 * @version November 28th, 2014
 * @description Sets the value of the scoreboard and the value of the highscore. Controls the JLabels in the fram
 */
import javax.swing.JLabel;

public final class Scoreboard {
	
	// Instance variables
	private int score;
	
	// A reference to the label which displays the score
	// so that this class can update it automatically.
	private JLabel scoreDisplay;
	
	// Constructors
	/**
	 * Starts the object of type Scoreboard
	 * @param score Display the JLabel to be modified
	 */
	public Scoreboard(JLabel scoreDisplay) {
		this.scoreDisplay = scoreDisplay;
		resetScore();
	}
	
	/**
	 * Sets the value of the current score
	 * @param score of the game
	 */
	public Scoreboard(int score) {
		this.score = score;
	}
	
	/**
	 * Updates the JLabel object
	 */
	private void updateLabel() {
		scoreDisplay.setText(String.format("%d", score));
	}
	
	// Methods
	/**
	 * Resets the value of the scoreboard to zero
	 */
	public void resetScore() {
		score = 0;
		updateLabel();
	}
	
	/**
	 * Adds the score to the current value
	 * @param score to be added to the current score
	 */
	public void addScore(int score) {
		this.score += score;
		updateLabel();
	}
	
	/**
	 * Decreases the score by the value given
	 * @param score the value to be decrease from the current score
	 */
	public void removeScore(int score) {
		this.score -= score;
		updateLabel();
	}
	
	/**
	 * Sets a specific value given
	 * @param score to be set in the JLabel
	 */
	public void setScore(int score) {
		this.score = score;
		updateLabel();
	}
	
	/**
	 * Returns a numerical value of the score
	 * @return the value of the score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Returns the value of the score as a String
	 * @return a string with the value of the score
	 */
	@Override
	public String toString() {
		return String.format("%d", score);
	}
}
