package gc.cs.comp1011.memorygame;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class GameBoard {
	//Variables
	private Deck deck;
	private Card[][] cards;
	private JPanel cardLayout;
	private JPanel userMessages;
	
	
	public GameBoard(int rows, int columns){
		 deck = new Deck();
		 cards = new Card[rows][columns];
		 for(int x=0; x<rows; x++){
				for(int y=0; y<columns;y++){
					cards[x][y] = new Card();
					cards[x][y].addActionListener(new ActionListener());
					//cardsPanel.add(cards[x]);
					//System.out.println(cards[x].getCardIdentity());
				}
			}
		 
		 userMessages = new JPanel(new FlowLayout());
	}

}
