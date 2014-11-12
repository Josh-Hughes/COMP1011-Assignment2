package gc.cs.comp1011.memorygame;

import java.awt.*;

import javax.swing.*;

public class GameFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JButton playButton;
	private JButton exitButton;

	private JLabel highScoreText;
	private JLabel timerText;
	private JLabel scoreText;
	private JLabel highScore;
	private JLabel timer;
	private JLabel score;

	private JPanel labelPanel;
	private JPanel cardsPanel;
	private JPanel buttonPanel;

	public GameFrame() {
		super("Memory Game");
		setLayout(new BorderLayout());
		
		// Create the layout panels.
		labelPanel = new JPanel(new GridLayout(2, 3));
		cardsPanel = new JPanel(new GridLayout(4, 4));
		buttonPanel = new JPanel(new GridLayout(1, 3));
		
		// Add panels to window.
		add(labelPanel, BorderLayout.NORTH);
		add(cardsPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		// Create the buttons.
		playButton = new JButton("Play Game");
		exitButton = new JButton("Exit");
		buttonPanel.add(playButton);
		buttonPanel.add(exitButton);

		// Create the labels.
		highScoreText = new JLabel("High Score");
		timerText = new JLabel("Time");
		scoreText = new JLabel("Score");
		highScore = new JLabel("####");
		timer = new JLabel("##");
		score = new JLabel("####");
		labelPanel.add(highScoreText);
		labelPanel.add(timerText);
		labelPanel.add(scoreText);
		labelPanel.add(highScore);
		labelPanel.add(timer);
		labelPanel.add(score);
		
		//plainButton.setText("Test Button");
		//highScore.setText("Test");
		
		highScore.setSize(100, 20);

	}// of constructor
}
