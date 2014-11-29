package gc.cs.comp1011.memorygame;
/**
 * @author Josh Hughes & Luis Acevedo
 * @version November 28th, 2014
 * @description This class is the card class, it will have the face of the card. Extends from a JButton
 */
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Card extends JButton {
	private static final long serialVersionUID = 529663813111176555L;
	//Object variables
	private Icon cardNumber;
	private Icon cardBack;
	private Icon cardBackHighlight;
	private String cardIdentity;
	private boolean isCardPaired;
	
	/**
	 * Constructor of the card class
	 * @param card Identity of the Card to be set 
	 */
	public Card(String cardIdentity) {
		super();

		//Load images for the card button
		try{
			cardBack = new ImageIcon(ImageIO.read(new File("resources/img/cardback.png")));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		try{
			cardBackHighlight = new ImageIcon(ImageIO.read(new File("resources/img/cardback2.png")));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		//Set the configuration for the appearance
		setIcon(cardBack);
		setDisabledIcon(cardBack);
		setRolloverIcon(cardBackHighlight);
		setRolloverEnabled(true);
		
		//Make button invisible
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		
		//Set the card to appear
		setDefinedCard(cardIdentity);
		
		//Set the card to not paired
		setIsCardPaired(false);
	}
	
	/**
	 * This returns the identity of the card as a string
	 * @return the identity of the card
	 */
	public String getCardIdentity(){
		return cardIdentity;
	}
	
	/**
	 * Hides the card
	 */
	public void hideCard(){
		setIcon(cardBack);
		setDisabledIcon(cardBack);
		setRolloverIcon(cardBackHighlight);
	}
	
	/**
	 * Sets the identity of the card
	 * @param card Identity of the card to be set
	 */
	private void setCardIdentity(String cardIdentity){
		this.cardIdentity = cardIdentity;
	}
	
	/**
	 * This method allows to set a new card face to the card
	 */
	private void setNewCard(){
		cardNumber = null;
		try{
			cardNumber = new ImageIcon(ImageIO.read(new File("resources/img/card_"+getCardIdentity()+".png")));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Allows to set the identifier of the card and bring the new one
	 * @param card Identity of the card
	 */
	public void setDefinedCard(String cardIdentity){
		setCardIdentity(cardIdentity);
		setNewCard();
	}
	
	/**
	 * Shows the card
	 */
	public void showCard() {
		setIcon(cardNumber);
		setDisabledIcon(cardNumber);
		setRolloverIcon(cardNumber);
	}
	
	/**
	 * Flag to keep a track if the card has been paired
	 * @param isCardPaired as the signal to show if paired
	 */
	public void setIsCardPaired(boolean isCardPaired){
		this.isCardPaired = isCardPaired;
	}
	
	/**
	 * Returns the value of isCardPaired
	 * @return the value of isCardPaired
	 */
	public boolean getIsCardPaired(){
		return isCardPaired;
	}
	
}
