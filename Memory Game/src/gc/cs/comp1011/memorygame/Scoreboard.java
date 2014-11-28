package gc.cs.comp1011.memorygame;

import javax.swing.JLabel;

public final class Scoreboard {
	
	// Instance variables
	private int score;
	
	// A reference to the label which displays the score
	// so that this class can update it automatically.
	private JLabel scoreDisplay;
	
	// Constructors
	public Scoreboard(JLabel scoreDisplay) {
		this.scoreDisplay = scoreDisplay;
		resetScore();
	}
	
	public Scoreboard(int score) {
		this.score = score;
	}
	
	private void updateLabel() {
		scoreDisplay.setText(String.format("%d", score));
	}
	
	// Methods
	public void resetScore() {
		score = 0;
		updateLabel();
	}
	
	public void addScore(int score) {
		this.score += score;
		updateLabel();
	}
	
	public void removeScore(int score) {
		this.score -= score;
		updateLabel();
	}
	
	public void setScore(int score) {
		this.score = score;
		updateLabel();
	}
	
	public int getScore() {
		return score;
	}
	
	@Override
	public String toString() {
		return String.format("%d", score);
	}
}
