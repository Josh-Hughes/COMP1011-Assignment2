package gc.cs.comp1011.memorygame;

import javax.swing.JFrame;
import javax.swing.JRootPane;

/**
 * @author Josh Hughes & 
 * @version November 12th, 2014
 * 
 */
public class MemoryGame {

	public static void main(String[] args) {
		GameFrame gameFrame = new GameFrame();
		
		gameFrame.setUndecorated(true);
		gameFrame.getRootPane().setWindowDecorationStyle( JRootPane.QUESTION_DIALOG );

		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setSize(340, 400);
		gameFrame.setVisible(true);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setResizable(false);
	}

}
