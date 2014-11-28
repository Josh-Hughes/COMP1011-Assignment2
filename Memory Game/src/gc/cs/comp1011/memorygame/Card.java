package gc.cs.comp1011.memorygame;

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
		setRolloverIcon(cardBackHighlight);
		setRolloverEnabled(true);
		
		//Make button invisible
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		
		//Set the card to appear
		setDefinedCard(cardIdentity);
	}
	
	public String getCardIdentity(){
		return cardIdentity;
	}
	
	public void hideCard(){
		setIcon(cardBack);
		setRolloverIcon(cardBackHighlight);
	}
	
	private void setCardIdentity(String cardIdentity){
		this.cardIdentity = cardIdentity;
	}
	
	private void setNewCard(){
		cardNumber = null;
		try{
			cardNumber = new ImageIcon(ImageIO.read(new File("resources/img/card_"+getCardIdentity()+".png")));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void setDefinedCard(String cardIdentity){
		setCardIdentity(cardIdentity);
		setNewCard();
	}
	
	public void showCard() {
		setIcon(cardNumber);
		setRolloverIcon(cardNumber);
	}
	
}
