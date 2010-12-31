import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.imageio.ImageIO;

public class L1A3 extends Area{
//Inside Hospital, floor 1

	public L1A3(){
		super();
		curArea = 4;
		filename="levels/l1a3.txt";
		WIDTH = 800;
		end = WIDTH;
		begin = 0;
		ZOMBIES = 10;
		HEALTH = 0;
		AMMO = 0;
		BATTERIES = 0;
		SANDWICHES = 0;
		SURVIVORS = 0;
		populate();
		rb = new Ribbon(WIDTH, HEIGHT, MOVE_SIZE);
		rb.setIm(build());

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
	
	
	public BufferedImage build() {
		int j=0;
			// TODO Auto-generated method stub
		  BufferedImage im = new BufferedImage(5600,550,BufferedImage.TYPE_BYTE_INDEXED);
		  Graphics2D g = im.createGraphics();
		  
			  try {
				URL url = this.getClass().getClassLoader().getResource(filename);;
				BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			    String strLine;
			    //Read File Line By Line
			    while ((strLine = br.readLine()) != null)   {
			      // Print the content on the console
			    	for (int i=0; i< strLine.length(); i++){
			    		BufferedImage img = null;
			    		switch(strLine.charAt(i)){
			    		case 65:
			    			img = genBufferedImage("sprites/bricks/sky1.png");
			    			break;
			    		case 70:
			    			img = genBufferedImage("sprites/bricks/roof1.png");
			    			break;
			    		case 74:
			    			img = genBufferedImage("sprites/bricks/roof1.png");
			    			break;
			    		case 79:	    			
			    			img = genBufferedImage("sprites/bricks/sky2.png");
			    			break;
			    		case 80:
			    			img = genBufferedImage("sprites/bricks/sky3.png");
			    			break;
			    		case 81:
			    			img = genBufferedImage("sprites/bricks/sky4.png");
			    			break;
			    		case 82:
			    			img = genBufferedImage("sprites/bricks/sky5.png");
			    			break;
			       		case 97:
			    			img = genBufferedImage("sprites/bricks/hospital7.png");
			    			break;
			    		case 98:
			    			img = genBufferedImage("sprites/bricks/hospital1.png");
			    			break;
			    		case 99:
			    			img = genBufferedImage("sprites/bricks/hospital2.png");
			    			break;
			    		case 100:
			    			img = genBufferedImage("sprites/bricks/hospital3.png");
			    			break;
			    		case 101:
			    			img = genBufferedImage("sprites/bricks/hospital4.png");
			    			break;
			    		case 102:
			    			img = genBufferedImage("sprites/bricks/hospital5.png");
			    			break;
			    		case 103:
			    			img = genBufferedImage("sprites/bricks/hospital6.png");
			    			break;
			    		case 104:
			    			img = genBufferedImage("sprites/bricks/hospital8.png");
			    			break;
			    		case 105:
			    			img = genBufferedImage("sprites/bricks/hospital11.png");
			    			break;
			    		case 106:
			    			img = genBufferedImage("sprites/bricks/hospital9.png");
			    			break;
			    		case 107:	    			
			    			img = genBufferedImage("sprites/bricks/hospital10.png");
			    			break;
			    
				    	}
			    		g.drawImage(img,i*50,j*50,null);
			    	}
			    	j++;
			    	//System.out.println (strLine);
			    }
			    //Close the input stream
			    br.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			doors.add(new Door(WIDTH/2,HEIGHT-50,12));
			g.setColor(Color.white);
			g.fillRect((int)doors.get(0).getDoorX(),(int)doors.get(0).getDoorY(),(int)doors.get(0).getDoor().getWidth(),(int)doors.get(0).getDoor().getHeight());

			return im;
		}
	

	public boolean hasZombies(){
		return true;
	}
	
	public void populate(){
		

		
		ammo = new Item[AMMO];
		
		health = new Item[HEALTH];
		
		sandwiches = new Item[SANDWICHES];
		
		batteries = new Item[BATTERIES];
		
		survivor = new Survivor[SURVIVORS];
		helicopter = new Helicopter();
		helicopter.setX(500);
		helicopter.setY(100);
	}
	
}
