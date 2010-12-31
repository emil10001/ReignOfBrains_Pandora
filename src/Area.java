import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;


public abstract class Area {
	protected String filename;
	protected int WIDTH;
	protected int HEIGHT = 480;
	protected int BATTERIES;
	protected int ZOMBIES;
	protected int HEALTH;
	protected int AMMO;
	protected int SANDWICHES;
	protected int SURVIVORS;
	protected ArrayList<Door> doors;
	public Item [] ammo;
	public Item [] health;
	public Item [] sandwiches;
	public Item [] batteries;
	public Survivor [] survivor;
	public Helicopter helicopter;
	public Ribbon rb;
	public static final int MOVE_SIZE = 5;
	protected int begin;
	protected int end;
	protected int curArea = 0;

	
	public Area(){
		doors = new ArrayList<Door>();
	}
	
	protected class Door{
		Rectangle2D door;
		int doorX;
		int doorY;
		int num;
		double w = 50;
		double h = 70;
		
		public Door(int x, int y, int num){
			doorX = x;
			doorY = y;
			this.num = num;
			door = new Rectangle2D.Double(doorX, doorY, w, h);
		}
		
		public Rectangle2D getDoor() {
			return door;
		}
		public void setDoor(Rectangle2D door) {
			this.door = door;
		}
		public int getDoorX() {
			return doorX;
		}
		public void setDoorX(int doorX) {
			this.doorX = doorX;
			door.setRect(this.doorX, doorY, w, h);
		}
		public int getDoorY() {
			return doorY;
		}
		public void setDoorY(int doorY) {
			this.doorY = doorY;
			door.setRect(doorX, this.doorY, w, h);
		}
		
	}
	
	public boolean hasZombies(){
		return false;
	}
	
	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public BufferedImage build() {
		return null;
	}
	
	public void populate(){

		SecureRandom rnd = null;
		try {
			rnd = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ammo = new Item[AMMO];
		for (int i=0; i< AMMO;i++){
			ammo[i] = new Item();
			ammo[i].setItemType(0);
			ammo[i].setX(rnd.nextInt(800)+300);
			ammo[i].setY(rnd.nextInt(HEIGHT-300)+300);
			ammo[i].setValue(rnd.nextInt(100));
		}
		
		health = new Item[HEALTH];
		for (int i=0; i< HEALTH;i++){
			health[i] = new Item();
			health[i].setItemType(1);
			health[i].setX(rnd.nextInt(800)+300);
			health[i].setY(rnd.nextInt(HEIGHT-300)+300);
		}
		
		sandwiches = new Item[SANDWICHES];
		for (int i=0; i< SANDWICHES;i++){
			sandwiches[i] = new Item();
			sandwiches[i].setItemType(2);
			sandwiches[i].setX(rnd.nextInt(800)+300);
			sandwiches[i].setY(rnd.nextInt(HEIGHT-300)+300);
		}
		
		batteries = new Item[BATTERIES];
		for (int i=0; i< BATTERIES;i++){
			batteries[i] = new Item();
			batteries[i].setItemType(2);
			batteries[i].setX(rnd.nextInt(800)+300);
			batteries[i].setY(rnd.nextInt(HEIGHT-300)+300);
			batteries[i].setValue(rnd.nextInt(100));
		}
		
		survivor = new Survivor[SURVIVORS];
		for (int i=0; i< SURVIVORS;i++){
			survivor[i] = new Survivor();
			survivor[i].setX(rnd.nextInt(800)+300);
			survivor[i].setY(rnd.nextInt(HEIGHT-300)+300);
		}		

	}
	
	public int getCurArea() {
		return curArea;
	}

	public void clean(){

	
		for (int i=0; i< AMMO;i++){
			ammo[i] = null;
		}
		ammo = null;
		
		for (int i=0; i< HEALTH;i++){
			health[i] = null;
		}
		health = null;
		
		for (int i=0; i< SANDWICHES;i++){
			sandwiches[i] = null;
		}
		sandwiches = null;
		
		for (int i=0; i< BATTERIES;i++){
			batteries[i] = null;
		}
		batteries = null;
		
		for (int i=0; i< SURVIVORS;i++){
			survivor[i] = null;
		}		
		survivor = null;
		
		rb = null;
		
		helicopter = null;
	}
}
