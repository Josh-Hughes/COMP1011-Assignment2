/**
 * @author Josh Hughes & Luis Acevedo
 * @version November 12th, 2014
 * @revision_history:
 * 2014-11-12:
 * Created the initial configuration for the screen
 * 2014-11-23:
 * Added all control variables to class
 */
package gc.cs.comp1011.memorygame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GameFrame extends JFrame {
	/* Variable definition */
	// Serialization identifier
	private static final long serialVersionUID = 1L;

	// Main buttons.
	private JButton playButton;
	private JButton exitButton;
	private JButton cheatButton;
	
	// Constants.
	private final int SCREEN_WIDTH = 310;
	private final int SCREEN_HEIGHT = 500;
	
	// Board specification
	private final int CARDS_PER_ROW = 4;
	private final int CARDS_PER_COLUMN = 4;
	
	// Label definition
	private JLabel highScoreText;
	private JLabel timerText;
	private JLabel scoreText;
	private JLabel highScore;
	private JLabel timer;
	private JLabel score;
	private JLabel userMessage;

	// Panel for the labels to go in.
	private JPanel labelPanel;

	// Panel for the cards to go in.
	private JPanel cardsPanel;
	
	// Panel for the buttons to go in.
	private JPanel buttonPanel;
	
	// Panel for the user message to go in.
	private JPanel userMessagePanel;
	
	// Panel for the label panel and user message panel to go in.
	private JPanel topContainer;
	
	// All the cards for the memory game.
	private Card[][] cards;
	// Deck for the assignment of cards
	private Deck deck;
	
	// Timer configuration
	private final double COUNTDOWN_DEFAULT = 60;
	private final int COUNTDOWN_INTERVAL = 100;
	private final double COUNTDOWN_DECREMENT = 0.1;

	// The countdown timer.
	private Timer countdownTimer;
	// The current countdown value.
	private double countdown;
	
	// A timer that will flip the 2 mismatched cards back after a delay.
	private Timer flipCardsBackTimer;
	// The delay before flipping the cards back will be 60 milliseconds.
	private final int FLIP_CARDS_BACK_DELAY = 600;
	
	// A timer that will hide 2 matched cards after a delay.
	private Timer hideMatchedCardsTimer;
	// The delay before hiding the cards will be 30 milliseconds.
	private final int HIDE_MATCHED_CARDS_DELAY = 600;
		
	// A timer that will hide the game cards after a delay
	private Timer flipGameBackTimer;
	//The delay before hiding the cards when clicking the cheat button
	private final int HIDE_CARDS_AFTER_CHEAT_DELAY = 1000;
	
	// The score board keeps track of the player's score.
	private Scoreboard scoreboard;

	// The cards selected by the player, null if not yet picked. 
	private Card selectedCard1 = null;
	private Card selectedCard2 = null;

	/* Methods */
	/**
	 * 
	 */
	public GameFrame() {
		// Call the super class JFrame constructor.
		super("Memory Game");
		
		// Frame configuration.
		setLayout(new BorderLayout());
		setUndecorated(true);
		setResizable(false);
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Create the countdown timer.
		countdownTimer = new Timer(COUNTDOWN_INTERVAL, new TimerTickListener());
		// Set the default countdown timer value.
		countdown = COUNTDOWN_DEFAULT;
		
		// Create the layout panels.
		topContainer = new JPanel(new BorderLayout());
		labelPanel = new JPanel(new GridLayout(2, 3));
		userMessagePanel = new JPanel(new BorderLayout());
		cardsPanel = new JPanel(new GridLayout(CARDS_PER_ROW, CARDS_PER_COLUMN));
		buttonPanel = new JPanel(new GridLayout(1, 3));
		
		// Add the top level panels to the window.
		add(topContainer, BorderLayout.NORTH);
		add(cardsPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		// Create the buttons.
		/*playButton = new JButton("Play Again");
		playButton.setEnabled(false);*/
		playButton = new JButton("Play");
		cheatButton = new JButton("Cheat");
		exitButton = new JButton("Exit");
		
		// Add buttons to button panel.
		buttonPanel.add(playButton);
		buttonPanel.add(cheatButton);
		buttonPanel.add(exitButton);

		// Create the labels.
		highScoreText = new JLabel("High Score", SwingConstants.CENTER);
		highScore = new JLabel("####", SwingConstants.CENTER);
		timerText = new JLabel("Time", SwingConstants.CENTER);
		timer = new JLabel(String.format("%.1f", 60.0f), SwingConstants.CENTER);
		scoreText = new JLabel("Score", SwingConstants.CENTER);
		score = new JLabel("", SwingConstants.CENTER);
		userMessage = new JLabel(GameMessages.FIRST_CARD.getMessage(), SwingConstants.CENTER);

		// Add the label panel and the user message panel to the top container.
		topContainer.add(labelPanel, BorderLayout.NORTH);
		topContainer.add(userMessagePanel, BorderLayout.CENTER);
				
		// Add labels to label panel.
		labelPanel.add(highScoreText);
		labelPanel.add(timerText);
		labelPanel.add(scoreText);
		labelPanel.add(highScore);
		labelPanel.add(timer);
		labelPanel.add(score);
		
		// Add message to message panel.
		userMessagePanel.add(userMessage);
		
		// Border for the game panel
		cardsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		// Create the card deck.
		deck = new Deck();
		// Create the array of cards.
		cards = new Card[CARDS_PER_ROW][CARDS_PER_COLUMN];
		
		for(int x=0; x<CARDS_PER_ROW; x++){
			for(int y=0; y<CARDS_PER_COLUMN;y++){
				cards[x][y] = new Card(deck.getRandomCard());
				cards[x][y].addActionListener(new CardClickListener());
			}
		}
		
		// Create the score board.
		scoreboard = new Scoreboard(score);
		
		// Add button event handling.
		exitButton.addActionListener(new ExitButtonListener());
		cheatButton.addActionListener(new CheatButtonListener());
		playButton.addActionListener(new PlayButtonListener());

		// Generate the board.
		NewGame();		
		
	}//GameFrame Constructor
	
	// Methods
	/**
	 * 
	 */
	private void NewGame(){
		//New board with cards
		ChangeCards();
		LayoutGame();
		ResetGame();
		
	}//NewGame
	
	/**
	 * 
	 */
	private void ChangeCards(){
		// Restart the deck for a new game
		deck.resetDeck();
		
		// Assign the new cards to the game
		for(int x=0; x<CARDS_PER_ROW; x++){
			for(int y=0; y<CARDS_PER_COLUMN;y+=2){
				cards[x][y].setDefinedCard(deck.getRandomCard());
				cards[x][y+1].setDefinedCard(cards[x][y].getCardIdentity());
			}
		}
	}//ChangeCards
	
	/**
	 * 
	 */
	private void LayoutGame(){
		//instance variables
		Card temporaryCard;
		int newXPosition = 0;
		int newYPosition = 0;
		
		//Generate the random positions
		for(int x=0; x<CARDS_PER_ROW; x++){
			for(int y=0; y<CARDS_PER_COLUMN; y++){
				newXPosition = (int) (Math.random() *  CARDS_PER_ROW);
				newYPosition = (int) (Math.random() *  CARDS_PER_COLUMN);
				
				temporaryCard = cards[x][y];
				cards[x][y] = cards[newXPosition][newYPosition];
				cards[newXPosition][newYPosition] = temporaryCard;
				
			}
		}
		
		// Clear the panel before adding the new cards
		cardsPanel.removeAll();
		
		//Add the random generated gameboard
		for(int x=0; x<CARDS_PER_ROW; x++){
			for(int y=0; y<CARDS_PER_COLUMN; y++){
				cardsPanel.add(cards[x][y]);
			}
		}
	}//LayoutGame
	
	/**
	 * 
	 */
	private void ResetGame() {
		countdownTimer.stop();
		
		//playButton.setEnabled(false);
		cheatButton.setEnabled(true);

		userMessage.setText(GameMessages.FIRST_CARD.getMessage());
		countdown = COUNTDOWN_DEFAULT;
		timer.setText(String.format("%.1f", countdown));

		scoreboard.resetScore();
		
		//Hide the cards
		for(int x=0; x<CARDS_PER_ROW; x++){
			for(int y=0; y<CARDS_PER_COLUMN;y++){
				cards[x][y].setVisible(true);
				cards[x][y].setEnabled(true);
				cards[x][y].hideCard();
				cards[x][y].setIsCardPaired(false);
			}
		}
	}//ResetGame
	
	/**
	 * 
	 */
	private void StartTimer(){
		// Start the countdown timer if it's not started yet.
		if (!countdownTimer.isRunning()) {
			countdownTimer.start();
			
			playButton.setText("Reset");
			playButton.setEnabled(true);
		}
	}//startTimer
	
	/**
	 * 
	 * @return
	 */
	private boolean CheckGameStatus(){
		boolean allCardsPaired = true;
		
		//Check if the cards are all paired
		for(int x = 0; x < CARDS_PER_ROW; x++){
			for(int y = 0; y < CARDS_PER_COLUMN; y++){
				if(!cards[x][y].getIsCardPaired()){
					allCardsPaired = false;
				}
			}
		}
		
		//System.out.println("The game is over?: "+allCardsPaired);
		return allCardsPaired;
	}//CheckGameStatus
	
	// Button Classes
	/**
	 * 
	 *
	 */
	class CardClickListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// Start timer when card clicked
			StartTimer();
			
			// Get the card that was clicked on.
			Card clickedCard = (Card)e.getSource();
			
			if (selectedCard1 == null) {
				// Reveal the card face.
				clickedCard.showCard();
				
				selectedCard1 = clickedCard;
				
				userMessage.setText(GameMessages.SECOND_CARD.getMessage());
			} else {
				if (!selectedCard1.equals(clickedCard)) {
					// Reveal the card face.
					clickedCard.showCard();

					selectedCard2 = clickedCard;
					
					if (selectedCard1.getCardIdentity().equals(selectedCard2.getCardIdentity())) {
						userMessage.setText(GameMessages.RIGHT_MATCH.getMessage());
						
						scoreboard.addScore(1);
						
						// This will stop the cards from being clicked again after being matched.
						// They will be re-enabled once the game is reset.
						selectedCard1.setEnabled(false);
						selectedCard2.setEnabled(false);
						
						hideMatchedCardsTimer = new Timer(HIDE_MATCHED_CARDS_DELAY, new HideMatchedCardsTimerTickListener(selectedCard1, selectedCard2));
						hideMatchedCardsTimer.start();
						
						//Change status of the card
						selectedCard1.setIsCardPaired(true);
						selectedCard2.setIsCardPaired(true);
		
						
					} else {
						userMessage.setText(GameMessages.WRONG_MATCH.getMessage());
						
						// This will stop the cards from being clicked again after being mismatched.
						// They will be re-enabled after the timer delay when they are flipped back again.
						selectedCard1.setEnabled(false);
						selectedCard2.setEnabled(false);
						
						flipCardsBackTimer = new Timer(FLIP_CARDS_BACK_DELAY, new FlipCardPairBackTimerTickListener(selectedCard1, selectedCard2));
						flipCardsBackTimer.start();
					}
					selectedCard1 = null;
					selectedCard2 = null;
				} else {
					selectedCard1.hideCard();
					selectedCard1 = null;
					userMessage.setText(GameMessages.FIRST_CARD.getMessage());
				}
			}
		}
		
	}//CardClickListener
	
	/**
	 * 
	 *
	 */
	class ExitButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}//ExitButtonListener
	
	/**
	 * 
	 *
	 */
	class CheatButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			StartTimer();
			
			//Disable the button since it was clicked
			cheatButton.setEnabled(false);
			
			//Show all cards to the player
			for(int x = 0; x < CARDS_PER_ROW; x++){
				for(int y = 0; y < CARDS_PER_COLUMN; y++){
					cards[x][y].showCard();
				}
			}
			
			flipGameBackTimer = new Timer(HIDE_CARDS_AFTER_CHEAT_DELAY, new FlipGameBackTimerTickListener(cards));
			flipGameBackTimer.start();
		}
	}//CheatButtonListener
	
	/**
	 * 
	 *
	 */
	class PlayButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!playButton.getText().equals("Reset")){
				StartTimer();
			}else{
				playButton.setText("Play");
				playButton.setEnabled(true);
				NewGame();
			}
			
		}
		
	}//PlayButtonListener
	
	// Timer Classes
	/**
	 * 
	 *
	 */
	class TimerTickListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			countdown -= COUNTDOWN_DECREMENT;
			timer.setText(String.format("%.1f", countdown));
		}
	}//TimerTickListener
	
	/**
	 * 
	 *
	 */
	class FlipCardPairBackTimerTickListener implements ActionListener {

		private Card card1, card2;
		
		public FlipCardPairBackTimerTickListener(Card card1, Card card2) {
			this.card1 = card1;
			this.card2 = card2;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Timer timer = (Timer)e.getSource();
			timer.stop();
			card1.hideCard();
			card2.hideCard();
			card1.setEnabled(true);
			card2.setEnabled(true);
		}
	}//FlipCardPairBackTimerTickListener
	
	/**
	 * 
	 *
	 */
	class FlipGameBackTimerTickListener implements ActionListener{
		private Card[][] card;
		
		public FlipGameBackTimerTickListener(Card[][] card){
			this.card = card;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Timer timer = (Timer)e.getSource();
			timer.stop();
			
			for(int x=0; x<CARDS_PER_ROW; x++){
				for(int y=0; y<CARDS_PER_COLUMN;y++){
					card[x][y].hideCard();
					card[x][y].setEnabled(true);
				}
			}
		}
	}//FlipGameBackTimerTickListener
	
	/**
	 * 
	 *
	 */
	class HideMatchedCardsTimerTickListener implements ActionListener {

		private Card card1, card2;
		
		public HideMatchedCardsTimerTickListener(Card card1, Card card2) {
			this.card1 = card1;
			this.card2 = card2;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Timer timer = (Timer)e.getSource();
			timer.stop();
			card1.setVisible(false);
			card2.setVisible(false);
			
			//Check if the game is finished
			if(CheckGameStatus()){
				ResetGame();
				StartTimer();
			}
		}
		
	}//HideMatchedCardsTimerTickListener
	
}// GameFrame
