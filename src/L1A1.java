import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.imageio.ImageIO;

public class L1A1 extends Area{
	public L1A1(){
		super();
		curArea = 2;
		filename="levels/l1a0.txt";
		WIDTH = 800;
		end = WIDTH;
		begin = 0;
		ZOMBIES = 0;
		HEALTH = 4;
		AMMO = 5;
		BATTERIES = 0;
		SANDWICHES = 1;
		SURVIVORS = 0;
		populate();
		rb = new Ribbon(WIDTH, HEIGHT, MOVE_SIZE);
		rb.setIm(build());
	}
	
	public BufferedImage build() {
		// TODO Auto-generated method stub
		  BufferedImage im = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_BYTE_INDEXED);
		  Graphics2D g = im.createGraphics();
		  BufferedImage img = genBufferedImage("sprites/bricks/room0.png");
		  g.drawImage(img,0,0,null);
		  doors.add(new Door(WIDTH/2,HEIGHT-50,0));
		  g.setColor(Color.black);
		  g.fillRect((int)doors.get(0).getDoorX(),(int)doors.get(0).getDoorY(),(int)doors.get(0).getDoor().getWidth(),(int)doors.get(0).getDoor().getHeight());

		  return im;
	}
	
	private BufferedImage genBufferedImage(String si){
		BufferedImage i;
		try {
			URL url = this.getClass().getClassLoader().getResource(si);
			
			if (url == null) {
				System.out.println("Can't find ref: "+si);
			}
			
			i = ImageIO.read(url);
			return i;
		} catch (IOException e) {
			System.out.println("Failed to load: "+si);
			return null;
		}
		
	}
	
	public boolean hasZombies(){
		return false;
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
			ammo[i].setX(rnd.nextInt(10)+400);
			ammo[i].setY(rnd.nextInt(50)+300);
			ammo[i].setValue(rnd.nextInt(100));
		}
		
		health = new Item[HEALTH];
		for (int i=0; i< HEALTH;i++){
			health[i] = new Item();
			health[i].setItemType(1);
			health[i].setX(rnd.nextInt(10)+400);
			health[i].setY(rnd.nextInt(50)+300);
		}
		
		sandwiches = new Item[SANDWICHES];
		for (int i=0; i< SANDWICHES;i++){
			sandwiches[i] = new Item();
			sandwiches[i].setItemType(2);
			sandwiches[i].setX(rnd.nextInt(10)+400);
			sandwiches[i].setY(rnd.nextInt(50)+300);
		}
		
		batteries = new Item[BATTERIES];
		
		survivor = new Survivor[SURVIVORS];
	
	}
	
}
