import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;


public class StartScreen extends Canvas implements KeyListener{

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
	private Sound opening;
	private boolean screen0 = true;
	private boolean screen1 = true;
	private boolean screen2 = true;
	private boolean screen3 = true;
	
	public StartScreen(){
		running = true;
		WIDTH = 800;
		HEIGHT = 480;
		setVisible(true);
		setIgnoreRepaint(true);
		setFont(new Font("Arail", Font.PLAIN, 30));
		setBackground(Color.DARK_GRAY);
		setForeground(Color.WHITE);
		requestFocus();
		addKeyListener(this);
		opening = new Sound("sounds/RoB-OPEN.wav");
	}
	
	public void init(){
		this.createBufferStrategy(2);
		strategy = getBufferStrategy();
	}
	
	public void run(){
		opening.start();
		screen0();
		screen1();
		screen2();
		screen3();
		screen4();
		opening.stop();

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
	
	private void screen0(){
    	this.requestFocusInWindow();

		BufferStrategy bf = this.getBufferStrategy();
		
		String welcome = "Reign of Brains";
		String welcome2;
		welcome2 = "Hit Start to continue";
		Graphics g = null;
		menu = null;
		//System.out.println("startScreen()");
		
		menu = genBufferedImage("sprites/zombie.png");
		//System.out.println("Image Loaded");
		try {
			g = bf.getDrawGraphics();
			if (menu!=null){
				//System.out.println("drawing menu");
				g.drawImage(menu, 0, 0, null);
			}
			//System.out.println("Drawing stuff");
			g.drawString(welcome, (WIDTH/2)-175, 40);
			g.drawString(welcome2, (WIDTH/2)-175, 90);
		} catch (Exception ex) {}
		if (g!=null){
			g.dispose();
		}
		if (bf!=null){
			bf.show();
		}
		Toolkit.getDefaultToolkit().sync();
		while (screen0){
			try {Thread.sleep(500);} catch (Exception e){}
		}
	}
	
	private void screen1(){
    	this.requestFocusInWindow();

		BufferStrategy bf = this.getBufferStrategy();
		
		String welcome = "Reign of Brains";
		String welcome2;
		String welcome3;
		String welcome4;
		String welcome5;
		String welcome6;
		String welcome7;
		String welcome8;
		String welcome9;		
		welcome2 = "Hit Start to continue";
		welcome3 = "Directions:";
		welcome4 = "D-PAD to move";
		welcome5 = "B to switch weapons";
		welcome6 = "X for action (pick up items/enter doors)";
		welcome7 = "Right Analog Stick and Trigger to attack/fire";
		welcome8 = "Start to pause";
		welcome9 = "Q to quit from pause screen";
		Graphics g = null;
		menu = null;
		BufferedImage ming = genBufferedImage("sprites/ming/FrontGun1.png");
		//System.out.println("startScreen()");
		
		menu = genBufferedImage("sprites/bg2.png");
		//System.out.println("Image Loaded");
		try {
			g = bf.getDrawGraphics();
			if (menu!=null){
				//System.out.println("drawing menu");
				g.drawImage(menu, 0, 0, null);
				g.drawImage(ming, 650, HEIGHT/2, null);
			}
			//System.out.println("Drawing stuff");
			g.drawString(welcome, (WIDTH/2)-175, (HEIGHT/2)-150);
			g.drawString(welcome2, 5, (HEIGHT/2)-60);
			g.drawString(welcome3, 5, (HEIGHT/2)-30);
			g.drawString(welcome4, 5, (HEIGHT/2));
			g.drawString(welcome5, 5, (HEIGHT/2)+30);
			g.drawString(welcome6, 5, (HEIGHT/2)+60);
			g.drawString(welcome7, 5, (HEIGHT/2)+90);
			g.drawString(welcome8, 5, (HEIGHT/2)+120);
			g.drawString(welcome9, 5, (HEIGHT/2)+150);
		} catch (Exception ex) {}
		if (g!=null){
			g.dispose();
		}
		if (bf!=null){
			bf.show();
		}
		Toolkit.getDefaultToolkit().sync();
		while (screen1){
			try {Thread.sleep(500);} catch (Exception e){}
		}
	}
	
	private void screen2(){
		
    	this.requestFocusInWindow();

		BufferStrategy bf = this.getBufferStrategy();
		
		String welcome = "Reign of Brains";
		String welcome2 = "There is a dangerous virus ravaging the world";
		String welcome3 = "It turns people into monsters";
		String welcome4 = "Your name is Ming, and you fly helicopters for a living";
		String welcome5 = "Well, you used to";
		String welcome6 = "You have survived so far by securing the Safe House";
		String welcome7 = "Your mission is to get to the Hospital";
		String welcome8 = "There will be a helicopter there for you to fly back";
		Graphics g = null;
		menu = null;
		//System.out.println("startScreen()");
		
		menu = genBufferedImage("sprites/bg2.png");
		//System.out.println("Image Loaded");
		try {
			g = bf.getDrawGraphics();
			if (menu!=null){
				//System.out.println("drawing menu");
				g.drawImage(menu, 0, 0, null);
			}
			//System.out.println("Drawing stuff");
			g.drawString(welcome, (WIDTH/2)-175, (HEIGHT/2)-150);
			g.drawString(welcome2, 5, (HEIGHT/2)-60);
			g.drawString(welcome3, 5, (HEIGHT/2)-30);
			g.drawString(welcome4, 5, (HEIGHT/2));
			g.drawString(welcome5, 5, (HEIGHT/2)+30);
			g.drawString(welcome6, 5, (HEIGHT/2)+60);
			g.drawString(welcome7, 5, (HEIGHT/2)+90);
			g.drawString(welcome8, 5, (HEIGHT/2)+120);
		} catch (Exception ex) {}
		if (g!=null){
			g.dispose();
		}
		if (bf!=null){
			bf.show();
		}
		Toolkit.getDefaultToolkit().sync();
		while (screen2){
			try {Thread.sleep(500);} catch (Exception e){}
		}
	}
	
private void screen3(){
		
    	this.requestFocusInWindow();

		BufferStrategy bf = this.getBufferStrategy();
		
		String welcome = "Reign of Brains";
		String welcome2 = "Objective:";
		String welcome3 = "Make your way to the Hospital";
		String welcome4 = "There is a Helicopter on the roof there";
		String welcome5 = "Bring back the Helicopter";
		Graphics g = null;
		menu = null;
		//System.out.println("startScreen()");
		
		menu = genBufferedImage("sprites/bg2.png");
		//System.out.println("Image Loaded");
		try {
			g = bf.getDrawGraphics();
			if (menu!=null){
				//System.out.println("drawing menu");
				g.drawImage(menu, 0, 0, null);
			}
			//System.out.println("Drawing stuff");
			g.drawString(welcome, (WIDTH/2)-175, (HEIGHT/2)-150);
			g.drawString(welcome2, 5, (HEIGHT/2)-60);
			g.drawString(welcome3, 5, (HEIGHT/2)-30);
			g.drawString(welcome4, 5, (HEIGHT/2));
			g.drawString(welcome5, 5, (HEIGHT/2)+30);
		} catch (Exception ex) {}
		if (g!=null){
			g.dispose();
		}
		if (bf!=null){
			bf.show();
		}
		Toolkit.getDefaultToolkit().sync();
		while (screen3){
			try {Thread.sleep(500);} catch (Exception e){}
		}
	}
	
	private void screen4(){
		this.requestFocusInWindow();
	
		BufferStrategy bf = this.getBufferStrategy();
		
		String welcome = "Reign of Brains";
		String welcome2 = "Loading ...";
		Graphics g = null;
		menu = null;
		//System.out.println("startScreen()");
		
		menu = genBufferedImage("sprites/zombie.png");
		//System.out.println("Image Loaded");
		try {
			g = bf.getDrawGraphics();
			if (menu!=null){
				//System.out.println("drawing menu");
				g.drawImage(menu, 0, 0, null);
			}
			//System.out.println("Drawing stuff");
			g.drawString(welcome, (WIDTH/2)-175, 40);
			g.drawString(welcome2, (WIDTH/2)-175, 90);
		} catch (Exception ex) {}
		if (g!=null){
			g.dispose();
		}
		if (bf!=null){
			bf.show();
		}
		Toolkit.getDefaultToolkit().sync();
		try { Thread.sleep(1000);} catch (Exception ex){}
		
	}

	
	public boolean isRunning(){
		return running;
	}
	
	public void stop() {
		running = false;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_ALT){
			//stop();
			if (screen0){
				screen0 = false;
			}
			else if (screen1){
				screen1 = false;
			}
			else if (screen2){
				screen2 = false;
			}
			else {
				screen3 = false;
			}
		}
		if (keyCode == KeyEvent.VK_Q){
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
