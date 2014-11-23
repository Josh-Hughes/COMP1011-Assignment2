package gc.cs.comp1011.memorygame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Cards extends JButton{
	//Object variables
	private Icon cardNumber;
	
	public Cards() {
		super();
		
		//Load images for the card button
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
		
		//Set the random card
		try{
			cardNumber = new ImageIcon(ImageIO.read(new File("resources/img/card_"+setRandomCard()+".png")));
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public String setRandomCard(){
		String card = "";
		
		card += (int) (Math.random() * 10 + 1);
		
		switch((int) (Math.random() * 4 + 1)){
		case 1:
			card += "c";
			break;
		case 2:
			card += "d";
			break;
		case 3:
			card += "h";
			break;
		default:
			card += "s";
		}
		
		return card;
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
