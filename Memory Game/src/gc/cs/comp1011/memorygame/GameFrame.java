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
import javax.swing.Timer;

public class GameFrame extends JFrame {
	//variable definition
	private static final long serialVersionUID = 1L;

	private JButton playButton;
	private JButton exitButton;
	private JButton cheatButton;
	
	private final int SCREEN_WIDTH = 310;
	private final int SCREEN_HEIGHT = 500;
	
	private final int CARDS_PER_ROW = 4;
	private final int CARDS_PER_COLUMN = 4;
	private final int AMOUNT_OF_CARDS = CARDS_PER_COLUMN * CARDS_PER_ROW;
	
	private static final double COUNTDOWN_DEFAULT = 60;
	private static final int COUNTDOWN_INTERVAL = 100;
	private static final double COUNTDOWN_DECREMENT = 0.1;
	private static double countdown = COUNTDOWN_DEFAULT;

	//private String gameboard[][] = new String[CARDS_PER_ROW][CARDS_PER_COLUMN];

	/*private final String[] DECK = {"1c","2c","3c","4c","5c","6c","7c","8c","9c","10c",
									"1d","2d","3d","4d","5d","6d","7d","8d","9d","10d",
									"1h","2h","3h","4h","5h","6h","7h","8h","9h","10h",
									"1s","2s","3s","4s","5s","6s","7s","8s","9s","10s"};*/

	private JLabel highScoreText;
	private JLabel timerText;
	private JLabel scoreText;
	private JLabel highScore;
	private JLabel timer;
	private JLabel score;
	private JLabel userMessage;

	private JPanel labelPanel;
	private JPanel cardsPanel;
	private JPanel buttonPanel;
	private JPanel userMessagePanel;
	private JPanel topContainer;
	
	//private JButton[] cards;
	private Card[][] cards;

	private Scoreboard scoreboard;
	
	private Timer countdownTimer;
	
	public GameFrame() {
		//Call to the super class JFRAME
		super("Memory Game");
		
		//Frame configuration
		setLayout(new BorderLayout());
		setUndecorated(true);
		setResizable(false);
		setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
		getRootPane().setWindowDecorationStyle( JRootPane.QUESTION_DIALOG );
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Create the score board.
		scoreboard = new Scoreboard();
		
		// Create the countdown timer.
		countdownTimer = new Timer(COUNTDOWN_INTERVAL, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				countdown -= COUNTDOWN_DECREMENT;
				//System.out.printf("\nTick %.1f", countdown);
				timer.setText(String.format("%.1f", countdown));
			}
		});

		// Create the layout panels.
		labelPanel = new JPanel(new GridLayout(2, 3));
		userMessagePanel = new JPanel(new FlowLayout());
		cardsPanel = new JPanel(new GridLayout(CARDS_PER_ROW,CARDS_PER_COLUMN));
		buttonPanel = new JPanel(new GridLayout(1, 3));
		topContainer = new JPanel(new GridLayout(2,1));

		// Add panels to window.
		//add(labelPanel, BorderLayout.NORTH);
		add(topContainer, BorderLayout.NORTH);
		add(cardsPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		// Create the buttons.
		playButton = new JButton("Play Game");
		playButton.setEnabled(false);
		exitButton = new JButton("Exit");
		cheatButton = new JButton("Cheat");
		
		// Add buttons to button panel.
		buttonPanel.add(playButton);
		buttonPanel.add(cheatButton);
		buttonPanel.add(exitButton);

		// Create the labels.
		highScoreText = new JLabel("High Score", SwingConstants.CENTER);
		highScore = new JLabel("####", SwingConstants.CENTER);
		timerText = new JLabel("Time", SwingConstants.CENTER);
		timer = new JLabel(String.format("%.1f", countdown), SwingConstants.CENTER);
		scoreText = new JLabel("Score", SwingConstants.CENTER);
		score = new JLabel(scoreboard.toString(), SwingConstants.CENTER);
		userMessage = new JLabel(GameMessages.FIRST_CARD.getMessage(), SwingConstants.LEFT);

		// Add labels to label panel.
		labelPanel.add(highScoreText);
		labelPanel.add(timerText);
		labelPanel.add(scoreText);
		labelPanel.add(highScore);
		labelPanel.add(timer);
		labelPanel.add(score);
		
		// Add message to message panel
		userMessagePanel.add(userMessage);
		
		//Add the labels and the user message to the top container
		topContainer.add(labelPanel, BorderLayout.NORTH);
		topContainer.add(userMessagePanel, BorderLayout.CENTER);
		
		//Border for the game panel
		cardsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		/*cards = new Cards();
		cardsPanel.add(cards);*/
		
		//Set back of cards
		/*Icon cardBack = null;
		Icon cardBackHighLight = null;
		try{
			cardBack = new ImageIcon(ImageIO.read(new File("resources/img/cardback.png")));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		try{
			cardBackHighLight = new ImageIcon(ImageIO.read(new File("resources/img/cardback2.png")));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		//Setting the array of cards
		cards = new JButton[AMOUNT_OF_CARDS];
		cardsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));*/
		
		//cards = new Card[AMOUNT_OF_CARDS];
		cards = new Card[CARDS_PER_ROW][CARDS_PER_COLUMN];
		
		for(int x=0; x<CARDS_PER_ROW; x++){
			for(int y=0; y<CARDS_PER_COLUMN;y++){
				cards[x][y] = new Card();
				cards[x][y].addActionListener(new CardClickListener());
				//cardsPanel.add(cards[x]);
				//System.out.println(cards[x].getCardIdentity());
			}
		}
		
		//Generate the board
		newGame();

		//Add button handling
		exitButton.addActionListener(new ExitButtonListener());
		cheatButton.addActionListener(new CheatButtonListener());
		playButton.addActionListener(new PlayButtonListener());
		
	} //constructor
	
	private void layoutGame(){
		//instance variables
		boolean settingCard = true;
		Card temporaryCard;
		int previousXPosition = 0;
		int previousYPosition = 0;
		int newXPosition = 0;
		int newYPosition = 0;
		
		//Generate the random positions
		for(int x=0; x<CARDS_PER_ROW; x++){
			for(int y=0; y<CARDS_PER_COLUMN; y++){
				previousXPosition = (int) (Math.random() *  CARDS_PER_ROW);
				previousYPosition = (int) (Math.random() *  CARDS_PER_COLUMN);
				newXPosition = (int) (Math.random() *  CARDS_PER_ROW);
				newYPosition = (int) (Math.random() *  CARDS_PER_COLUMN);
				
				temporaryCard = cards[previousXPosition][previousYPosition];
				cards[previousXPosition][previousYPosition] = cards[newXPosition][newYPosition];
				cards[newXPosition][newYPosition] = temporaryCard;
				
			}
		}
		
		//Add the random generated gameboard
		for(int x=0; x<CARDS_PER_ROW; x++){
			for(int y=0; y<CARDS_PER_COLUMN; y++){
				cardsPanel.add(cards[x][y]);
			}
		}
	}
	
	private void changeCards(){
		for(int x=0; x<CARDS_PER_ROW; x++){
			for(int y=0; y<CARDS_PER_COLUMN;y+=2){
				cards[x][y].setRandomCard();
				cards[x][y+1].setDefinedCard(cards[x][y].getCardIdentity());
			}
		}
	}
	
	public void newGame(){
		changeCards();
		layoutGame();
	}
	
	class CardClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			userMessage.setText(GameMessages.SECOND_CARD.getMessage());
			if (!countdownTimer.isRunning()) {
				countdownTimer.start();
			}
			Card clickedCard = (Card)e.getSource();
			clickedCard.showCard();
		}//action performed
	};
	
	class ExitButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//userMessage.setText(GameMessages.SECOND_CARD.getMessage());
			System.exit(0);
		}
		
	}
	
	class CheatButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			for(int x=0; x<CARDS_PER_ROW; x++){
				for(int y=0; y<CARDS_PER_COLUMN;y++){
					newGame();
					cards[x][y].hideCards();
				}
			}
		}
		
	}
	
	class PlayButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//Set the array of cards with random cards
			
		}

	}
	
}
