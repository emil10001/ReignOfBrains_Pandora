import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class GameLoop extends Canvas implements KeyListener,MouseMotionListener {

	private static final long serialVersionUID = 1L;	
	protected boolean running;
	protected boolean paused;
	protected boolean fullscreen;
	protected Ming ming;
	protected Bullet [] bullet;
	protected BufferedImage bulletP;
	protected BufferedImage zombieP;
	protected BufferedImage healthP;
	protected BufferedImage sandwichP;
	protected BufferedImage batteryP;
	protected BufferedImage radioP;
	private BufferedImage load;
	private BufferedImage menu;
	@SuppressWarnings("unused")
	private BufferStrategy strategy;
	private static int HEIGHT = 475;
	private static int WIDTH = 800;
	private static int BULLETS = 15;
	protected GameLevel level;
    public static final int MOVE_SIZE = 500;
    private int ZOMBIES = 10;
    protected Zombie [] zombie;
	private Ribbon rb; 
	//private WeaponPanel weapon_panel;
//	protected Sound gunshot;
//	protected Sound reload;
	private boolean win;
	private Rectangle2D screen;
	protected InputHandler gp;
	protected Point target;
	//protected int absolutePosition;

	public GameLoop(InputHandler input){
		running = true;
		paused = false;
		win = false;
		fullscreen = false;
		level = new Level1();
		rb = level.getArea().rb;
		target = new Point();
		screen = new Rectangle2D.Double(0,0,WIDTH,HEIGHT);
		gp = input;
		setVisible(true);
		setIgnoreRepaint(true);
		setFont(new Font("Arail", Font.PLAIN, 30));
		setBackground(Color.DARK_GRAY);
		setForeground(Color.WHITE);
		addKeyListener(this);
		addMouseMotionListener(this);
		requestFocus();
		try {
			loadImages();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void init()
	{
		this.createBufferStrategy(2);
		strategy = getBufferStrategy();
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
	
	public void loadImages() throws IOException{
		menu = genBufferedImage("sprites/bg2.png");
		load = genBufferedImage("sprites/bg2.png");
		bulletP = genBufferedImage("sprites/panel/ammo.gif");
		zombieP = genBufferedImage("sprites/panel/zombiehead.png");
		healthP = genBufferedImage("sprites/panel/health.png");
		sandwichP = genBufferedImage("sprites/panel/sandwich.png");
		batteryP = genBufferedImage("sprites/panel/battery.png");
		radioP = genBufferedImage("sprites/panel/radio1.gif");
		
		zombie = new Zombie[this.ZOMBIES];
		SecureRandom rnd = null;
		try {
			rnd = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		for (int i=0; i< this.ZOMBIES;i++){
			zombie[i] = new Zombie();
			zombie[i].visible = false;
			zombie[i].setX(rnd.nextInt(800)+800);
			zombie[i].setY(rnd.nextInt(HEIGHT*3/2)+300);
		}
			
		ming = new Ming(rb);
		ming.setDirectionFacing(2);
		ming.gun=true;
		ming.setX(110);
		ming.setY(HEIGHT / 2);
		gp.setMingG(ming);
		
		bullet = new Bullet[BULLETS];
		for (int i=0; i< bullet.length;i++){
			bullet[i] = new Bullet();
			bullet[i].setX(0);
			bullet[i].setY(0);
			bullet[i].visible = false;
		}
	
//		gunshot = new Sound("sounds/reign-gunshot.wav");
//		reload = new Sound("sounds/reign-reload.wav");
	}
	
	public void stop(){
		running = false;
	}
	
	public boolean run() throws IOException{
		
		return gameLoop();
	}

		
	public void pauseScreen(){
		String welcome = "Paused";
		String welcome2 = "Hit Enter to continue";
		String welcome3 = "";
		welcome3 = "Press Q to quit";
		BufferStrategy bf = this.getBufferStrategy();
		Graphics g = null;
		
		try {
			g = bf.getDrawGraphics();
			g.drawImage(menu,0,0,null);
			g.drawString(welcome, (WIDTH/2)-50, (HEIGHT/2)-50);
			g.drawString(welcome2, (WIDTH/2)-50, (HEIGHT/2));
			g.drawString(welcome3, (WIDTH/2)-50, (HEIGHT/2)+30);
			
		} catch (Exception ex) {}
		if (g!=null){
			g.dispose();
		}
			
		if (bf!=null){
			bf.show();
		}
		Toolkit.getDefaultToolkit().sync();

	}
	
	public void loadScreen(){
		String welcome = "Loading...";
		BufferStrategy bf = this.getBufferStrategy();
		Graphics g = null;
		
		try {
			g = bf.getDrawGraphics();
			g.drawImage(load,0,0,null);
			g.drawString(welcome, (WIDTH/2)-50, (HEIGHT/2)-50);
			
		} catch (Exception ex) {}
		if (g!=null){
			g.dispose();
		}
			
		if (bf!=null){
			bf.show();
		}
		Toolkit.getDefaultToolkit().sync();

	}
	
	public boolean pause(){
		//System.out.println("pause func");
		rb.stayStill();
		if (paused == true){
			paused = false;
		}
		else {
			paused = true;
		}
		return paused;
		
	}
	
	public boolean gameLoop(){
    	this.requestFocusInWindow();
		long startTime = System.currentTimeMillis();
		long cumTime = startTime;
		paused = false;	
		//level.startMusic();
		while (!running){
			try {Thread.sleep(20);} catch (Exception ex){}
		}
		while (running){
			//gp.gamepadPressed();
			fireAt();
			rb.update();
			moveThings();
			long timePassed = System.currentTimeMillis() - cumTime;
			cumTime += timePassed;
			if (ming.isDead){
				//gameover
				running = false;
				//level.stopMusic();
				return false;
			}
			if (win){
				running = false;
				//level.stopMusic();
				return true;
			}
			while (paused || gp.isPaused()){
				//System.out.println("paused");
				//gp.gamepadPressed();
				pauseScreen();
				try {Thread.sleep(200);} catch (Exception ex){}
			}
			fireAt();
			update(timePassed);
			fireAt();
			detectCollide();
			//level.runningMusic();
			BufferStrategy bf = this.getBufferStrategy();
			Graphics g = null;
			try {
				g = bf.getDrawGraphics();
				draw(g);
			} catch (Exception ex){}
			if (g!=null){
				g.dispose();
			}
			if (bf!=null){
				bf.show();
			}	
			try{
				Thread.sleep(50);
			} catch (Exception ex){}
		}
		return false;
		
	}
	
	
	private void detectCollide() {
		// did you shoot a zombie?
		// did a zombie bite you?
		
		for (int i=0; i<zombie.length; i++){
			for (int j=0; j<bullet.length; j++){			
				if(bullet[j].detectCollide(zombie[i]) && !zombie[i].isHit && zombie[i].visible){
					zombie[i].isHit = true;
					level.updateKills();
				}
			}
			if (!ming.gun){
				if (ming.axing(zombie[i]) && !zombie[i].isHit && zombie[i].visible) {
					zombie[i].isHit = true;
					level.updateKills();
				}
			}
			if (zombie[i].visible){
				ming.detectAttack(zombie[i]);
			}
		}
		fireAt();
		// go through a door?
		int doorNum;
		for (int i=0; i<level.getArea().doors.size(); i++){
			if (ming.detectDoor(level.getArea().doors.get(i))){
				loadScreen();
				//System.out.println("Go in the door!");
				doorNum = level.inDoor(level.getArea().doors.get(i).num, rb.getAbsolutePosition());
				rb = level.getArea().rb;
				if (level.getPrevPosition() > 0 && i==0){
					while ((level.getArea().rb.getAbsolutePosition()) < level.getPrevPosition()){
						//System.out.println("move it!");
						level.getArea().rb.moveLeft();
						level.getArea().rb.update();
						this.moveThingsSlower();
					}
				}
				else if (i==0){
					while ((level.getArea().rb.getAbsolutePosition()) > level.getPrevPosition()){
						//System.out.println("move it!");
						level.getArea().rb.moveRight();
						level.getArea().rb.update();
						this.moveThingsSlower();
					}
				}
				int x = level.getArea().doors.get(doorNum).getDoorX();
				int y = level.getArea().doors.get(doorNum).getDoorY();
				ming.setX((float)x);
				ming.setY((float)y);
				break;
			}
		}
		fireAt();
		// did you pick up an item?
		for (int i=0; i<level.getArea().ammo.length; i++){
			if (ming.detectItems(level.getArea().ammo[i])){
				level.getArea().ammo[i].unload();

			}
		}
		for (int i=0; i<level.getArea().health.length; i++){
			if (ming.detectItems(level.getArea().health[i])){
				level.getArea().health[i].unload();

			}
		}
		for (int i=0; i<level.getArea().sandwiches.length; i++){
			if (ming.detectItems(level.getArea().sandwiches[i])){
				level.getArea().sandwiches[i].unload();

			}
		}
		for (int i=0; i<level.getArea().batteries.length; i++){
			if (ming.detectItems(level.getArea().batteries[i])){
				level.getArea().batteries[i].unload();

			}
		}
		
		// see if you've found a hiding survivor
		for (int i=0; i<level.getArea().survivor.length; i++){
			if (!level.getArea().survivor[i].isFound){
				level.getArea().survivor[i].isFound = ming.detectSurvivor(level.getArea().survivor[i]);
			}
		}
		
		if (level.getArea().helicopter != null){
			if (ming.detectItems(level.getArea().helicopter)){
				win = true;
			}
		}
 	}
	
	public void moveThingsSlower()
	{
		if (rb.isMovingR())
		{
			//this.absolutePosition += rb.getMoveSize()/2;
			// rb is moving right
			// move items and level.getArea().zombies left
			
			for (int i = 0; i < level.getArea().doors.size(); i++)
			{
				int currX = level.getArea().doors.get(i).getDoorX();
				level.getArea().doors.get(i).setDoorX(currX + (rb.getMoveSize()));
			}
			
			for (int i = 0; i < level.getArea().ammo.length; i++)
			{
				float currX = level.getArea().ammo[i].getX();
				level.getArea().ammo[i].setX(currX + (rb.getMoveSize()));
			}
			
			for (int i = 0; i < level.getArea().sandwiches.length; i++)
			{
				float currX = level.getArea().sandwiches[i].getX();
				level.getArea().sandwiches[i].setX(currX + (rb.getMoveSize()));
			}
			
			for (int i = 0; i < level.getArea().health.length; i++)
			{
				float currX = level.getArea().health[i].getX();
				level.getArea().health[i].setX(currX + (rb.getMoveSize()));
			}
			
			for (int i = 0; i < level.getArea().batteries.length; i++)
			{
				float currX = level.getArea().batteries[i].getX();
				level.getArea().batteries[i].setX(currX + (rb.getMoveSize()));
			}
			if (level.getArea().helicopter != null){
				float currX = level.getArea().helicopter.getX();
				level.getArea().helicopter.setX(currX + (rb.getMoveSize()));
			}
		}
		else if (rb.isMovingL())
		{
			//this.absolutePosition -= rb.getMoveSize()/2;
			// move items and level.getArea().zombies right
			
			for (int i = 0; i < level.getArea().doors.size(); i++)
			{
				int currX = level.getArea().doors.get(i).getDoorX();
				level.getArea().doors.get(i).setDoorX(currX - (rb.getMoveSize()));
			}
			
			for (int i = 0; i < level.getArea().ammo.length; i++)
			{
				float currX = level.getArea().ammo[i].getX();
				level.getArea().ammo[i].setX(currX - (rb.getMoveSize()));
			}

			for (int i = 0; i < level.getArea().sandwiches.length; i++)
			{
				float currX = level.getArea().sandwiches[i].getX();
				level.getArea().sandwiches[i].setX(currX - (rb.getMoveSize()));
			}
			
			for (int i = 0; i < level.getArea().health.length; i++)
			{
				float currX = level.getArea().health[i].getX();
				level.getArea().health[i].setX(currX - (rb.getMoveSize()));
			}
			
			for (int i = 0; i < level.getArea().batteries.length; i++)
			{
				float currX = level.getArea().batteries[i].getX();
				level.getArea().batteries[i].setX(currX - (rb.getMoveSize()));
			}			
			if (level.getArea().helicopter != null){
				float currX = level.getArea().helicopter.getX();
				level.getArea().helicopter.setX(currX - (rb.getMoveSize()));
			}
		}
	}
	
	public void moveThings()
	{
		if (rb.isMovingR())
		{
			//this.absolutePosition += 2*rb.getMoveSize()/2;
			// rb is moving right
			// move items and level.getArea().zombies left
			for (int i = 0; i < zombie.length; i++)
			{
				float currX = zombie[i].getX();
				zombie[i].setX(currX + (2*rb.getMoveSize()));
			}
			
			for (int i = 0; i < level.getArea().doors.size(); i++)
			{
				int currX = level.getArea().doors.get(i).getDoorX();
				level.getArea().doors.get(i).setDoorX(currX + (2*rb.getMoveSize()));
			}
			
			for (int i = 0; i < level.getArea().ammo.length; i++)
			{
				float currX = level.getArea().ammo[i].getX();
				level.getArea().ammo[i].setX(currX + (2*rb.getMoveSize()));
			}
			
			for (int i = 0; i < level.getArea().sandwiches.length; i++)
			{
				float currX = level.getArea().sandwiches[i].getX();
				level.getArea().sandwiches[i].setX(currX + (2*rb.getMoveSize()));
			}
			
			for (int i = 0; i < level.getArea().health.length; i++)
			{
				float currX = level.getArea().health[i].getX();
				level.getArea().health[i].setX(currX + (2*rb.getMoveSize()));
			}
			
			for (int i = 0; i < level.getArea().batteries.length; i++)
			{
				float currX = level.getArea().batteries[i].getX();
				level.getArea().batteries[i].setX(currX + (2*rb.getMoveSize()));
			}
			if (level.getArea().helicopter != null){
				float currX = level.getArea().helicopter.getX();
				level.getArea().helicopter.setX(currX + (2*rb.getMoveSize()));
			}
		}
		else if (rb.isMovingL())
		{
			//this.absolutePosition -= 2*rb.getMoveSize()/2;
			// move items and level.getArea().zombies right
			for (int i = 0; i < zombie.length; i++)
			{
				float currX = zombie[i].getX();
				zombie[i].setX(currX - (2*rb.getMoveSize()));
			}
			
			for (int i = 0; i < level.getArea().doors.size(); i++)
			{
				int currX = level.getArea().doors.get(i).getDoorX();
				level.getArea().doors.get(i).setDoorX(currX - (2*rb.getMoveSize()));
			}
			
			for (int i = 0; i < level.getArea().ammo.length; i++)
			{
				float currX = level.getArea().ammo[i].getX();
				level.getArea().ammo[i].setX(currX - (2*rb.getMoveSize()));
			}

			for (int i = 0; i < level.getArea().sandwiches.length; i++)
			{
				float currX = level.getArea().sandwiches[i].getX();
				level.getArea().sandwiches[i].setX(currX - (2*rb.getMoveSize()));
			}
			
			for (int i = 0; i < level.getArea().health.length; i++)
			{
				float currX = level.getArea().health[i].getX();
				level.getArea().health[i].setX(currX - (2*rb.getMoveSize()));
			}
			
			for (int i = 0; i < level.getArea().batteries.length; i++)
			{
				float currX = level.getArea().batteries[i].getX();
				level.getArea().batteries[i].setX(currX - (2*rb.getMoveSize()));
			}			
			if (level.getArea().helicopter != null){
				float currX = level.getArea().helicopter.getX();
				level.getArea().helicopter.setX(currX - (2*rb.getMoveSize()));
			}
		}
	}
	
	//check that nothing is trying to move where it shouldn't
	public void update(long timePassed){
		
		
		if ((ming.getY()+ming.getHeight()) >= HEIGHT){
			ming.setY(HEIGHT-ming.getHeight());
		}
		else if (ming.getY() <= 200){
			ming.setY(200);
		}
		if (ming.getX() <= 200 && rb.getAbsolutePosition() > level.getArea().getBegin()){
			ming.setX(200);
			if (ming.getVx()<0 && rb.getAbsolutePosition() > level.getArea().getBegin()){
				rb.moveRight();
				//System.out.println("move ribbon right, rb.absPos = " + rb.getAbsolutePosition());
			}
			else{
				rb.stayStill();
			}
		}
		else if (ming.getX() <= 10){
			ming.setX(10);
			rb.stayStill();
		}
		else if ((ming.getX()+ming.getWidth()) >= WIDTH-200 && (rb.getAbsolutePosition()+800) < level.getArea().getEnd()){
			ming.setX(WIDTH-ming.getWidth()-200);
			if (ming.getVx()>0 && (rb.getAbsolutePosition()+800) < level.getArea().getEnd()){
				rb.moveLeft();
				//System.out.println("move ribbon left, rb.absPos = " + rb.getAbsolutePosition());
			}
			else {
				rb.stayStill();
			}
			
		}
		else if ((ming.getX()+ming.getWidth()) >= WIDTH-20){
			ming.setX(WIDTH-ming.getWidth()-20);
			rb.stayStill();
		}
		else {
			rb.stayStill();
		}
		fireAt();
//		if (ming.getHealth()>45){
//			level.musicNormal();
//		}
//		else if (ming.getHealth()>25){
//			level.musicTense();
//		}
//		else {
//			level.musicMoreTense();
//		}
		if (level.getArea().helicopter != null){
			if (level.getArea().helicopter.getX() < WIDTH){
				if (!level.getArea().helicopter.visible){
					level.getArea().helicopter.loadImages();
				}
			}
		}
		
		fireAt();
		
		//bullet will not bounce around the screen
		for (int i=0;i<bullet.length;i++){
			if ((bullet[i].getY()+bullet[i].getHeight()) >= HEIGHT || bullet[i].getY() <= 0){
				bullet[i].firing = false;
			}
			if ((bullet[i].getX()+bullet[i].getWidth()) >= WIDTH || bullet[i].getX() <= 0){
				bullet[i].firing = false;
			}
			bullet[i].update(timePassed);
		}
		if (level.getArea().helicopter != null){
			if (level.getArea().helicopter.visible){
				level.getArea().helicopter.update(timePassed);
			}
		}		
		fireAt();
		ming.update(timePassed);
		for (int i=0;i<zombie.length;i++){
			if (screen.intersects(zombie[i].visBox) || screen.contains(zombie[i].visBox) || zombie[i].visible && level.getArea().hasZombies()){ 
				zombie[i].visible = true;
			}
			else if (screen.intersects(zombie[i].visBox) || screen.contains(zombie[i].visBox) || zombie[i].visible){ 
				zombie[i].visible = false;
				zombie[i].setX(zombie[i].getX()+WIDTH);
			}
			else {
				zombie[i].visible = false;
			}
			if (zombie[i].visible && !level.getArea().hasZombies()){ 
				zombie[i].visible = false;
			}
            if (zombie[i].getY() <= 130){
                zombie[i].setY(130);
            }
            //System.out.println("zombie: " + i + " " + zombie[i].visible);
			zombie[i].update(timePassed,ming);
		}
		level.updateHealth(ming.getHealth());
		
		for (int i=0; i<level.getArea().ammo.length; i++){
			level.getArea().ammo[i].update(timePassed);
		}
		for (int i=0; i<level.getArea().health.length; i++){
			level.getArea().health[i].update(timePassed);
		}
		for (int i=0; i<level.getArea().sandwiches.length; i++){
			level.getArea().sandwiches[i].update(timePassed);
		}
		for (int i=0; i<level.getArea().batteries.length; i++){
			level.getArea().batteries[i].update(timePassed);
		}
		
		level.updateItems(ming);
		
		rb.update();
			
	}
	
	//always override	
	public void draw(Graphics g){
		rb.display(g);
		if (level.getArea().helicopter != null){
			if (level.getArea().helicopter.visible){
				g.drawImage(level.getArea().helicopter.getImage(),(int)level.getArea().helicopter.getX(),(int)level.getArea().helicopter.getY(),null);
			}
		}
		
		for (int i=0; i< level.getArea().ammo.length; i++){
			if (level.getArea().ammo[i].visible){
				g.drawImage(level.getArea().ammo[i].getImage(),(int)level.getArea().ammo[i].getX(),(int)level.getArea().ammo[i].getY(),null);
			}
		}
		for (int i=0; i< level.getArea().health.length; i++){
			if (level.getArea().health[i].visible){
				g.drawImage(level.getArea().health[i].getImage(),(int)level.getArea().health[i].getX(),(int)level.getArea().health[i].getY(),null);
			}
		}
		for (int i=0; i< level.getArea().sandwiches.length; i++){
			if (level.getArea().sandwiches[i].visible){
				g.drawImage(level.getArea().sandwiches[i].getImage(),(int)level.getArea().sandwiches[i].getX(),(int)level.getArea().sandwiches[i].getY(),null);
			}
		}
		for (int i=0; i< level.getArea().batteries.length; i++){
			if (level.getArea().batteries[i].visible){
				g.drawImage(level.getArea().batteries[i].getImage(),(int)level.getArea().batteries[i].getX(),(int)level.getArea().batteries[i].getY(),null);
			}
		}
		for (int i=0; i< zombie.length; i++){
			g.drawImage(zombie[i].getImage(),(int)zombie[i].getX(),(int)zombie[i].getY(),null);
		}
		g.drawImage(ming.getImage(),(int)ming.getX(),(int)ming.getY(),null);
		
		for (int i=0;i<bullet.length;i++){
			if (bullet[i].firing){
				g.drawImage(bullet[i].getImage(),(int)bullet[i].getX(),(int)bullet[i].getY(),null);
			}
		}
		
		
		g.drawImage(healthP, 0,2,null);
		g.drawString(ming.getHealth() + "%", 22, 22);
		
		g.drawImage(healthP, 100,2,null);
		g.drawString(((Integer)ming.getHealthPack()).toString(), 122, 22);

		g.drawImage(batteryP, 200,2,null);
		g.drawString(((Integer)ming.getBattery()).toString(), 222, 22);
		
		g.drawImage(bulletP, 300, 2, null);
		g.drawString(((Integer)ming.getAmmo()).toString(), 322, 22);
		
		g.drawImage(sandwichP, 400, 2, null);
		g.drawString(((Integer)ming.getSandwiches()).toString(), 422, 22);
		
		g.drawImage(zombieP, 500, 2, null);
		g.drawString(((Integer)level.getKills()).toString(), 522, 22);
		
	}
	
	
	public void paintComponent (Graphics g)
	{
		rb.display(g);
	}

	public void fireAt(){
		if (gp.firing()){
			target.setLocation(gp.fireAt());
			ming.attack(target.x,target.y);
			if (ming.gun && ming.getAmmo()>0){
				ming.setAmmo(ming.getAmmo()-1);
//				if (!gunshot.running()){
//					//System.out.println("gunshot");
//					gunshot.start();
//				}
//				if (ming.getAmmo()%5 == 0){
//					if (!reload.running()){
//						//System.out.println("reload");
//						reload.start();
//					}
//				}
				
				for (int i=0; i<BULLETS; i++){
					if (!bullet[i].firing){
						bullet[i].fireAt(target.x,target.y, ming);
						//System.out.println("Firing bullet " + i);
						return;
					}
				}
			}
			else {
				ming.attack = true;
			}
			try { Thread.sleep(50);} catch (Exception ex){}
		}
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_UP){
			gp.up = true;
		}
		if (keyCode == KeyEvent.VK_DOWN){
			gp.down = true;
		}
		if (keyCode == KeyEvent.VK_LEFT){
			gp.left = true;
		}
		if (keyCode == KeyEvent.VK_RIGHT){
			gp.right = true;
		}
		if (keyCode == KeyEvent.VK_PAGE_DOWN){
			gp.action();
			ming.action = true;
		}
		if (keyCode == KeyEvent.VK_CONTROL){
			gp.firing = true;			
			gp.attack();
			fireAt();
		}
		if (keyCode == KeyEvent.VK_ALT){
			pause();
		}
		if (paused && (keyCode == KeyEvent.VK_Q)){
			System.exit(0);
		}
		if (keyCode == KeyEvent.VK_SHIFT){
			ming.run = true;
		}
		if (keyCode == KeyEvent.VK_END){
			ming.switchWeapon();			
		}
		e.consume();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_PAGE_DOWN){
			gp.action();
			ming.action = false;
		}
		if (keyCode == KeyEvent.VK_SHIFT){
			ming.run = false;
		}
		if (keyCode == KeyEvent.VK_UP){
			gp.up = false;
		}
		if (keyCode == KeyEvent.VK_DOWN){
			gp.down = false;
		}
		if (keyCode == KeyEvent.VK_LEFT){
			gp.left = false;
		}
		if (keyCode == KeyEvent.VK_RIGHT){
			gp.right = false;
		}
		if (keyCode == KeyEvent.VK_CONTROL){
			gp.firing = false;	
			gp.attack();
		}
		e.consume();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		e.consume();		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		if (!gp.paused){
			gp.setX(e.getX());
			gp.setY(e.getY());
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}
}
