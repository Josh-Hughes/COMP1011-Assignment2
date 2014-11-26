package gc.cs.comp1011.memorygame;

public class Scoreboard {
	
	// Instance variables
	private int score;
	
	// Constructors
	public Scoreboard() {
		resetScore();
	}
	
	public Scoreboard(int score) {
		this.score = score;
	}
	
	// Methods
	public void resetScore() {
		score = 0;
	}
	
	public void addScore(int score) {
		this.score += score;
	}
	
	public void removeScore(int score) {
		this.score -= score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	@Override
	public String toString() {
		return String.format("%d", score);
	}
}
