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

//Libraries
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class GameFrame extends JFrame{
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
	private Card[] cards;

	public GameFrame() {
		//Call to the super class JFRAME
		super("Memory Game");
		
		//Frame configuration
		setLayout(new BorderLayout());
		setUndecorated(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
		getRootPane().setWindowDecorationStyle( JRootPane.QUESTION_DIALOG );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		exitButton = new JButton("Exit");
		cheatButton = new JButton("Cheat");
		
		// Add buttons to button panel.
		buttonPanel.add(playButton);
		buttonPanel.add(cheatButton);
		buttonPanel.add(exitButton);

		// Create the labels.
		highScoreText = new JLabel("High Score", SwingConstants.CENTER);
		timerText = new JLabel("Time", SwingConstants.CENTER);
		scoreText = new JLabel("Score", SwingConstants.CENTER);
		highScore = new JLabel("####", SwingConstants.CENTER);
		timer = new JLabel("##", SwingConstants.CENTER);
		score = new JLabel("####", SwingConstants.CENTER);
		userMessage = new JLabel("####################", SwingConstants.LEFT);

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
		
		cards = new Card[AMOUNT_OF_CARDS];
		
		for(int x=0; x<AMOUNT_OF_CARDS; x++){
			cards[x] = new Card();
			cardsPanel.add(cards[x]);
			
			//System.out.println(cards[x].getCardIdentity());
		}

		//Add button handling
		/*exitButton.addActionListener(new ExitButtonListener());
		cheatButton.addActionListener(new CheatButtonListener());*/
		playButton.addActionListener(new PlayButtonListener());
		
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//System.exit(0);
				for(int x=0; x<AMOUNT_OF_CARDS; x++){
					cards[x].setRandomCard();
				}
			}//action performed
		});
		
		cheatButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				for(int x=0; x<AMOUNT_OF_CARDS; x++){
					cards[x].hideCards();
					
					//System.out.println(cards[x].getCardIdentity());
				}
			}//action performed
		});
		
		/*playButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
			}//action performed
		});*/
		
	}//constructor
	
	/*class ExitButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}//action performed
	}//exit button listener
	
	class CheatButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}//action performed
	}//cheat button listener*/
	
	class PlayButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//Set the array of cards with random cards
			
			
		}//action performed
	}//play button listener
	
}//END
