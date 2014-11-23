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
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class GameFrame extends JFrame{
	//variable definition
	private static final long serialVersionUID = 1L;

	private JButton playButton;
	private JButton exitButton;
	private JButton cheatButton;
	
	private final int SCREEN_WIDTH = 340;
	private final int SCREEN_HEIGHT = 400;
	
	private final int CARDS_PER_ROW = 4;
	private final int CARDS_PER_COLUMN = 4;
	private final int AMOUNT_OF_CARDS = CARDS_PER_COLUMN * CARDS_PER_ROW;

	private JLabel highScoreText;
	private JLabel timerText;
	private JLabel scoreText;
	private JLabel highScore;
	private JLabel timer;
	private JLabel score;

	private JPanel labelPanel;
	private JPanel cardsPanel;
	private JPanel buttonPanel;
	
	private JButton[] cards;

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
		cardsPanel = new JPanel(new GridLayout(CARDS_PER_ROW,CARDS_PER_COLUMN));
		buttonPanel = new JPanel(new GridLayout(1, 3));

		// Add panels to window.
		add(labelPanel, BorderLayout.NORTH);
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

		// Add labels to label panel.
		labelPanel.add(highScoreText);
		labelPanel.add(timerText);
		labelPanel.add(scoreText);
		labelPanel.add(highScore);
		labelPanel.add(timer);
		labelPanel.add(score);
		
		//Set back of cards
		Icon cardBack = null;
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
		
		for(int x=0; x<AMOUNT_OF_CARDS; x++){
			cards[x] = new JButton(cardBack);
			cards[x].setRolloverIcon(cardBackHighLight);
			cardsPanel.add(cards[x]);
		}

		//Add button handling
		/*exitButton.addActionListener(new ExitButtonListener());
		cheatButton.addActionListener(new CheatButtonListener());
		playButton.addActionListener(new PlayButtonListener());*/
		
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}//action performed
		});
		
		cheatButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
			}//action performed
		});
		
		playButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
			}//action performed
		});
		
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
	}//cheat button listener
	
	class PlayButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}//action performed
	}//play button listener*/
	
}//END
