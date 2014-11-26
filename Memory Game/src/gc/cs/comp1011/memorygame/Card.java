package gc.cs.comp1011.memorygame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Card extends JButton{
	private static final long serialVersionUID = 529663813111176555L;
	//Object variables
	private Icon cardNumber;
	private Icon cardBack;
	private String cardIdentity;
	
	public Card() {
		super();
		//This is a change
		
		//Load images for the card button
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
		
		//Set the configuration for the appearance
		setIcon(cardBack);
		setRolloverIcon(cardBackHighLight);
		setRolloverEnabled(true);
		
		//Make button invisible
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		
		//Set the listener class for the button
		this.addActionListener(new cardClickedListener());
		
		//Set the identity of the random card
		setRandomCard();
	}
	
	public String getCardIdentity(){
		return cardIdentity;
	}
	
	public void setRandomCard(){
		cardIdentity = "";
		cardNumber = null;
		
		cardIdentity += (int) (Math.random() * 10 + 1);
		
		switch((int) (Math.random() * 4 + 1)){
		case 1:
			cardIdentity += "c";
			break;
		case 2:
			cardIdentity += "d";
			break;
		case 3:
			cardIdentity += "h";
			break;
		default:
			cardIdentity += "s";
		}
		
		try{
			cardNumber = new ImageIcon(ImageIO.read(new File("resources/img/card_"+getCardIdentity()+".png")));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void hideCards(){
		setRolloverEnabled(true);
		setIcon(cardBack);
	}
	
	class cardClickedListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//System.out.println(cardNumber);
			setIcon(cardNumber);
			setRolloverEnabled(false);
		}
		
	}
}
