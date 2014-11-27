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
	// Serialization identifier
	private static final long serialVersionUID = 1L;

	// Main buttons.
	private JButton playButton;
	private JButton exitButton;
	private JButton cheatButton;
	
	// Constants.
	private final int SCREEN_WIDTH = 310;
	private final int SCREEN_HEIGHT = 500;
	
	//private final int CARDS_PER_ROW = 4;
	//private final int CARDS_PER_COLUMN = 4;
	//private final int AMOUNT_OF_CARDS = CARDS_PER_COLUMN * CARDS_PER_ROW;
	//private final int CARD_PANEL_ROWS = CARDS_PER_ROW + 1;
	
	//private String[] cardList = new String[AMOUNT_OF_CARDS/2]; 
	
	private final double COUNTDOWN_DEFAULT = 60;
	private final int COUNTDOWN_INTERVAL = 100;
	private final double COUNTDOWN_DECREMENT = 0.1;

	//private String gameboard[][] = new String[CARDS_PER_ROW][CARDS_PER_COLUMN];

	/*private final String[] DECK = {"1c","2c","3c","4c","5c","6c","7c","8c","9c","10c",
									"1d","2d","3d","4d","5d","6d","7d","8d","9d","10d",
									"1h","2h","3h","4h","5h","6h","7h","8h","9h","10h",
									"1s","2s","3s","4s","5s","6s","7s","8s","9s","10s"};*/

	// All the labels...
	private JLabel highScoreText;
	private JLabel timerText;
	private JLabel scoreText;
	private JLabel highScore;
	private JLabel timer;
	private JLabel score;
	//private JLabel userMessage;

	// Panel for the labels to go in.
	private JPanel labelPanel;

	// Panel for the cards to go in.
	private JPanel gameBoardPanel;
	// Panel for the buttons to go in.
	private JPanel buttonPanel;
	// Panel for the user message to go in.
	private JPanel userMessagePanel;
	// Panel for the label panel and user message panel to go in.
	private JPanel topContainer;
	
	// All the cards for the memory game.
	private Card[][] cards;

	// The score board keeps track of the player's score.
	private Scoreboard scoreboard;
	
	// The countdown timer.
	private Timer countdownTimer;
	// The current countdown value.
	private double countdown;

	// The cards selected by the player, null if not yet picked. 
	private Card selectedCard1 = null;
	private Card selectedCard2 = null;
	
	public GameFrame() {
		// Call the super class JFrame constructor
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
		
		// Set the default countdown timer value.
		countdown = COUNTDOWN_DEFAULT;

		// Create the layout panels.
		labelPanel = new JPanel(new GridLayout(2, 3));
		//userMessagePanel = new JPanel(new FlowLayout());
		//cardsPanel = new JPanel(new GridLayout(CARDS_PER_ROW,CARDS_PER_COLUMN));
		gameBoardPanel = new JPanel(new FlowLayout());
		buttonPanel = new JPanel(new GridLayout(1, 3));
		//topContainer = new JPanel(new GridLayout(2,1));

		// Add panels to window.
		//add(labelPanel, BorderLayout.NORTH);
		//add(topContainer, BorderLayout.NORTH);
		add(labelPanel, BorderLayout.NORTH);
		add(gameBoardPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		// Create the buttons.
		playButton = new JButton("Play Game");
		//playButton.setEnabled(false); <- Luis did this
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
		//userMessage = new JLabel(GameMessages.FIRST_CARD.getMessage(), SwingConstants.LEFT);

		// Add labels to label panel.
		labelPanel.add(highScoreText);
		labelPanel.add(timerText);
		labelPanel.add(scoreText);
		labelPanel.add(highScore);
		labelPanel.add(timer);
		labelPanel.add(score);
		
		// Add message to message panel
		//userMessagePanel.add(userMessage);
		
		// Add the labels and the user message to the top container
		//topContainer.add(labelPanel, BorderLayout.NORTH);
		//topContainer.add(userMessagePanel, BorderLayout.CENTER);
		
		// Border for the game panel
		gameBoardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
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
		/*cards = new Card[CARDS_PER_ROW][CARDS_PER_COLUMN];
		
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
		 */
		//Add button handling
		exitButton.addActionListener(new ExitButtonListener());
		cheatButton.addActionListener(new CheatButtonListener());
		playButton.addActionListener(new PlayButtonListener());
		
	} //constructor
	
	/*private void layoutGame(){
		//instance variables
		Card temporaryCard;
		int newXPosition = 0;
		int newYPosition = 0;
		
		//Generate the random positions
		for(int x=0; x<CARDS_PER_ROW; x++){
			for(int y=0; y<CARDS_PER_COLUMN; y++){
				newXPosition = (int) (Math.random() *  CARDS_PER_ROW);
				newYPosition = (int) (Math.random() *  CARDS_PER_COLUMN);
				
				//System.out.printf("Moved card from [%d][%d] to [%d][%d]\n",x,y,newXPosition,newYPosition);
				
				temporaryCard = cards[x][y];
				cards[x][y] = cards[newXPosition][newYPosition];
				cards[newXPosition][newYPosition] = temporaryCard;
				
			}
		}
		
		gameBoardPanel.removeAll();
		
		//Add the random generated gameboard
		for(int x=0; x<CARDS_PER_ROW; x++){
			for(int y=0; y<CARDS_PER_COLUMN; y++){
				gameBoardPanel.add(cards[x][y]);
			}
		}
	}
	
	private void changeCards(){
		//instance variables
		boolean repeatedCardFound = false;
		int positionOfRepeatedCard = -1;
		int cardListCounter = 0;
		int checkRepetitionCounter = 0;
		
		for(int x=0; x<CARDS_PER_ROW; x++){
			for(int y=0; y<CARDS_PER_COLUMN;y+=2){
				cards[x][y].setRandomCard();
				cards[x][y+1].setDefinedCard(cards[x][y].getCardIdentity());
				
				cardList[cardListCounter] =  cards[x][y].getCardIdentity();
				System.out.printf("Card[%s] = %s\n",cardListCounter,cardList[cardListCounter]);
				cardListCounter++;
			}
		}
			
		do{
			checkRepetitionCounter = cardListCounter - 1;
			System.out.println("Comparing:");
			repeatedCardFound = false;
			
			while(checkRepetitionCounter >= 0){
				for(int x=0; x<checkRepetitionCounter; x++){
					System.out.printf("[%s] == [%s]\n",cardList[checkRepetitionCounter],cardList[x]);
					if(cardList[checkRepetitionCounter].equals(cardList[x])){
						repeatedCardFound = true;
						positionOfRepeatedCard = x;
					}
				}
				
				checkRepetitionCounter--;
			}
		}while(repeatedCardFound);
		
		System.out.println("No more repeated cards");
	}
	
	public void newGame(){
		changeCards();
		layoutGame();
	}
	
	class CardClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Start the countdown timer if it's not started yet.
			if (!countdownTimer.isRunning()) {
				countdownTimer.start();
			}
			
			// Get the card that was clicked on.
			Card clickedCard = (Card)e.getSource();
			
			if (selectedCard1 == null) {
				selectedCard1 = clickedCard;
				userMessage.setText(GameMessages.SECOND_CARD.getMessage());
			} else {
				if (!selectedCard1.equals(clickedCard)) {
					selectedCard2 = clickedCard;
					if (selectedCard1.getCardIdentity().equals(selectedCard2.getCardIdentity())) {
						userMessage.setText(GameMessages.RIGHT_MATCH.getMessage() + " [" + selectedCard1.getCardIdentity() + " == " + selectedCard2.getCardIdentity() + "]");
						selectedCard1.setVisible(false);
						selectedCard2.setVisible(false);
					} else {
						userMessage.setText(GameMessages.WRONG_MATCH.getMessage() + " [" + selectedCard1.getCardIdentity() + " != " + selectedCard2.getCardIdentity() + "]");
					}
					selectedCard1 = null;
					selectedCard2 = null;
				}
			}
						
			// Reveal the card face.
			clickedCard.showCard();

		}//action performed
	};*/
	
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
			//Hide the cards
			/*for(int x=0; x<CARDS_PER_ROW; x++){
				for(int y=0; y<CARDS_PER_COLUMN;y++){
					//newGame();
					cards[x][y].setVisible(true);
					cards[x][y].hideCards();
				}
			}*/
		}
	}
	
	class PlayButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	}
}
