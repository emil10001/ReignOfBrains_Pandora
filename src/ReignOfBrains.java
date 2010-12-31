/* Main.java
 * Control panels and canvas setup for Reign of Brains
 * Sarah Keefe 4/7/10, Sarah.Keefe@tufts.edu
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.*;


public class ReignOfBrains extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameLoop theGame;
	  private StartScreen startScreen;
	  private GameOver gameOver;
	  protected InputHandler gp;
		
    public static void main (String [] args) throws IOException {
	new ReignOfBrains ();
    }
    
    public ReignOfBrains () throws IOException {

    	// Basic window setup
    	setLocation(0,0);
    	setBackground(Color.BLACK);
		setSize (800, 480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setUndecorated(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		// Set up main content pane
		Container content = getContentPane();
	
		
		// Set up overlay panes
		JLayeredPane lp = new JLayeredPane();
		lp.setLayout(new BorderLayout());	
	
		// Add panels for the main game
        JPanel gamePanel = new JPanel();
        
        // Transparent 16 x 16 pixel cursor image.
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

        // Create a new blank cursor.
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");

        // Set the blank cursor to the JFrame.
        getContentPane().setCursor(blankCursor);
        
		gp = new InputHandler();
		gp.start();
        startScreen = new StartScreen();
        startScreen.setBounds(0, 0, 800, 480);
        setVisible(true);
        content.add(startScreen);
        startScreen.init();
        startScreen.run();
        theGame = new GameLoop(gp); 
        content.remove(startScreen);
		//gp.gamepadSetup();
                
    	// send labels to GameLoop - update on item/kill/task change
    	// run game level: send lvl1 level object to GameLoop as well?
    	// continuously test if level is over, if so end GameLoop and begin next game level
    	// if all levels completed - game over, win, calc score
    	theGame.setBounds(0, 0, 800, 480);
    	// Add overlay panels to top and bottom 
    	gamePanel.add(theGame);
    	//lp.add(gamePanel, BorderLayout.CENTER);
    	content.add(lp);
    	lp.add (gamePanel);
    	// Show the Main window
    	// Initialize & START THE GAME!
    	setVisible (true);
    	theGame.init();
    	if (theGame.run()){
        	content.remove(theGame);
        	lp.remove(gamePanel);
        	content.remove(lp);
        	gamePanel.remove(theGame);
        	gameOver = new GameOver(true);
    	}
    	else {
        	content.remove(theGame);
        	lp.remove(gamePanel);
        	content.remove(lp);
        	gamePanel.remove(theGame);
        	gameOver = new GameOver(false);
    	}
    	gameOver.setBounds(0, 0, 800, 480);
    	setVisible(true);
    	content.add(gameOver);
       	gameOver.init();
    	gameOver.run();
    	while (gameOver.isRunning()){
    		try { Thread.sleep(50);} catch (Exception ex){}
    	}
    	content.remove(gameOver);
    	System.exit(0);

    }

}
