import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.imageio.ImageIO;


public class L1A0 extends Area{
//street
	
	
	public L1A0(){
		super();
		curArea = 1;
		filename="levels/l1a0.txt";
		WIDTH = 5600;
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
		int doorNum = 0;
		Door d = null;
		d = new Door(0,0,doorNum);
		doors.add(d);
		doorNum++;
		d = null;
			// TODO Auto-generated method stub
		  //BufferedImage im = new BufferedImage(5600,550,BufferedImage.TYPE_BYTE_INDEXED);
		  //Graphics2D g = im.createGraphics();
		BufferedImage im = genBufferedImage("sprites/street.png");
			  try {
				URL url = this.getClass().getClassLoader().getResource(filename);;
				BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			    String strLine;
			    //Read File Line By Line
			    while ((strLine = br.readLine()) != null)   {
			      // Print the content on the console
			    	for (int i=0; i< strLine.length(); i++){
			    		//BufferedImage img = null;
			    		switch(strLine.charAt(i)){
			    		/*
			    		case 65:
			    			img = genBufferedImage("sprites/bricks/sky1.png");
			    			break;
			    		case 66:
			    			img = genBufferedImage("sprites/bricks/street1.png");
			    			break;
			    		case 67:
			    			img = genBufferedImage("sprites/bricks/sidewalk1.png");
			    			break;
			    		case 68:
			    			img = genBufferedImage("sprites/bricks/sidewalk2.png");
			    			break;
			    		case 69:
			    			img = genBufferedImage("sprites/bricks/house1.png");
			    			break;
			    		case 70:
			    			img = genBufferedImage("sprites/bricks/roof1.png");
			    			break;
			    		case 71:
			    			img = genBufferedImage("sprites/bricks/street2.png");
			    			break;
			    		case 72:
			    			img = genBufferedImage("sprites/bricks/street3.png");
			    			break;
			    		case 73:
			    			img = genBufferedImage("sprites/bricks/house6.png");
			    			break;
			    		case 74:
			    			img = genBufferedImage("sprites/bricks/roof1.png");
			    			break;
			    		case 75:	    			
			    			img = genBufferedImage("sprites/bricks/sidewalk4.png");
			    			break;
			    		case 76:
			    			img = genBufferedImage("sprites/bricks/house2.png");
			    			break;
			    		case 77:
			    			img = genBufferedImage("sprites/bricks/house3.png");
			    			//add a door here
			    			d = new Door(i*50,j*50,doorNum);
			    			doors.add(d);
			    			doorNum++;
			    			d = null;
			    			break;
			    		case 78:
			    			img = genBufferedImage("sprites/bricks/house4.png");
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
			       		case 83:
			    			img = genBufferedImage("sprites/bricks/sidewalk3.png");
			    			break;
			       		case 84:
			    			img = genBufferedImage("sprites/bricks/street4.png");
			    			break;
			    		case 85:
			    			img = genBufferedImage("sprites/bricks/tree5.png");
			    			break;
			    		case 86:
			    			img = genBufferedImage("sprites/bricks/tree4.png");
			    			break;
			    		case 87:
			    			img = genBufferedImage("sprites/bricks/tree1.png");
			    			break;
			       		case 88:
			    			img = genBufferedImage("sprites/bricks/tree2.png");
			    			break;
			       		case 89:
			    			img = genBufferedImage("sprites/bricks/tree3.png");
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
			    			d = new Door(i*50,j*50,doorNum);
			    			doors.add(d);
			    			doorNum++;
			    			d = null;
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
			    		
				    	case 108:
			    			img = genBufferedImage("sprites/bricks/house7.png");
			    			break;
			    		case 109:
			    			img = genBufferedImage("sprites/bricks/house8.png");
			    			d = new Door(i*50,j*50,doorNum);
			    			doors.add(d);
			    			doorNum++;
			    			d = null;
			    			break;
			    		case 110:
			    			img = genBufferedImage("sprites/bricks/house9.png");
			    			break;
			    		case 111:
			    			img = genBufferedImage("sprites/bricks/house10.png");
			    			break;
			    		case 112:	    			
			    			img = genBufferedImage("sprites/bricks/house11.png");
			    			break;
			    			*/
			    		case 77:
			    			//img = genBufferedImage("sprites/bricks/house3.png");
			    			//add a door here
			    			d = new Door(i*50,j*50,doorNum);
			    			doors.add(d);
			    			doorNum++;
			    			d = null;
			    			break;
			    		case 102:
			    			//img = genBufferedImage("sprites/bricks/hospital5.png");
			    			d = new Door(i*50,j*50,doorNum);
			    			doors.add(d);
			    			doorNum++;
			    			d = null;
			    			break;
			    		case 109:
			    			//img = genBufferedImage("sprites/bricks/house8.png");
			    			d = new Door(i*50,j*50,doorNum);
			    			doors.add(d);
			    			doorNum++;
			    			d = null;
			    			break;	
				    	}
			    		//g.drawImage(img,i*50,j*50,null);
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
			return im;
		}
	
	public boolean hasZombies(){
		return true;
	}
	
	public void populate(){
		super.populate();
		//helicopter = new Helicopter();
		//helicopter.setX(5000);
		//helicopter.setY(200);
	}
	
}
