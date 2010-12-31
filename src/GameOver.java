import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;


public class GameOver extends Canvas implements KeyListener{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	public boolean running; 
	BufferedImage menu;
	@SuppressWarnings("unused")
	private BufferStrategy strategy;
	int WIDTH;
	int HEIGHT;
	protected Sound death;
	private boolean win;
	
	public GameOver(boolean win){
		running = true;
		WIDTH = 800;
		HEIGHT = 480;
		this.win = win;
		setVisible(true);
		setIgnoreRepaint(true);
		setFont(new Font("Arail", Font.PLAIN, 30));
		setBackground(Color.DARK_GRAY);
		setForeground(Color.WHITE);
		requestFocus();
		addKeyListener(this);
		death = new Sound("sounds/reign-mingdeath.wav");
	}
	
	public void init(){
		this.createBufferStrategy(2);
		strategy = getBufferStrategy();
	}
	
	public void run(){
		if (this.win){
			hasWon();
			
		}
		else{
			hasDied();
		}
	}
	
	private BufferedImage genBufferedImage(String si){
		BufferedImage i;
		try {
			// The ClassLoader.getResource() ensures we get the sprite
			// from the appropriate place, this helps with deploying the game
			// with things like webstart. You could equally do a file look
			// up here.
			URL url = this.getClass().getClassLoader().getResource(si);
			
			if (url == null) {
				System.out.println("Can't find ref: "+si);
			}
			
			// use ImageIO to read the image in
			i = ImageIO.read(url);
			//System.out.println("add scene totalTime = " + totalTime);
			return i;
		} catch (IOException e) {
			System.out.println("Failed to load: "+si);
			return null;
		}
		
	}
	
	private void hasWon(){
    	this.requestFocusInWindow();

		BufferStrategy bf = this.getBufferStrategy();
		String welcome = "Reign of Brains";
		String welcome2 = "You won!!!";
		String welcome3; 
		welcome3 = "Hit Q to exit";
		Graphics g = null;
		menu = null;
		//System.out.println("GameOver()");
		
		menu = genBufferedImage("sprites/bg2.png");
		//System.out.println("Image Loaded");
		try {
			g = bf.getDrawGraphics();
			if (menu!=null){
				//System.out.println("drawing menu");
				g.drawImage(menu, 0, 0, null);
			}
			//System.out.println("Drawing stuff");
			g.drawString(welcome, (WIDTH/2)-150, (HEIGHT/2)-50);
			g.drawString(welcome2, (WIDTH/2)-150, (HEIGHT/2));
			g.drawString(welcome3, (WIDTH/2)-150, (HEIGHT/2)+30);
		} catch (Exception ex) {}
		if (g!=null){
			g.dispose();
		}
		if (bf!=null){
			bf.show();
		}
		Toolkit.getDefaultToolkit().sync();
		while (running){
			try {Thread.sleep(500);} catch (Exception e){}
		}
	}
	
	private void hasDied(){
    	this.requestFocusInWindow();

		BufferStrategy bf = this.getBufferStrategy();
		death.start();
		String welcome = "Reign of Brains";
		String welcome2 = "You died";
		String welcome3; 
		welcome3 = "Hit Q to exit";
		Graphics g = null;
		menu = null;
		//System.out.println("GameOver()");
		
		menu = genBufferedImage("sprites/bg2.png");
		//System.out.println("Image Loaded");
		try {
			g = bf.getDrawGraphics();
			if (menu!=null){
				//System.out.println("drawing menu");
				g.drawImage(menu, 0, 0, null);
			}
			//System.out.println("Drawing stuff");
			g.drawString(welcome, (WIDTH/2)-150, (HEIGHT/2)-50);
			g.drawString(welcome2, (WIDTH/2)-150, (HEIGHT/2));
			g.drawString(welcome3, (WIDTH/2)-150, (HEIGHT/2)+30);
		} catch (Exception ex) {}
		if (g!=null){
			g.dispose();
		}
		if (bf!=null){
			bf.show();
		}
		Toolkit.getDefaultToolkit().sync();
		while (running){
			try {Thread.sleep(500);} catch (Exception e){}
		}
	}
	
	public boolean isRunning(){
		return running;
	}
	
	public void stop() {
		// TODO Auto-generated method stub
		running = false;
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_Q || keyCode == KeyEvent.VK_ALT){
			death.stop();
			running = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
