import java.io.File;
import javax.imageio.ImageIO;

public class Survivor extends Sprite {
	private Sprite left;
	private Sprite right;
	private Sprite up;
	private Sprite down;
	private Sprite dead;
	//private Sprite scared;
	public boolean isDead;
	public boolean isFound;
	
	public Survivor(){
		super();
		loadImages();
		init();
	}
	
	@Override
	public synchronized void loadImages(){
		left = new Sprite();
		right = new Sprite();
		up = new Sprite();
		down = new Sprite();
		dead = new Sprite();
		try {
			left.addScene("sprites/survivor/left1.png", 250);
			left.addScene("sprites/survivor/left2.png", 450);
			
			right.addScene("sprites/survivor/right1.png", 250);
			right.addScene("sprites/survivor/right2.png", 450);
			
			down.addScene("sprites/survivor/down1.png", 400);
			down.addScene("sprites/survivor/down2.png", 200);
			down.addScene("sprites/survivor/down3.png", 400);
			down.addScene("sprites/survivor/down4.png", 500);
			
			up.addScene("sprites/survivor/up1.png", 400);
			up.addScene("sprites/survivor/up2.png", 200);
			up.addScene("sprites/survivor/up3.png", 400);
			up.addScene("sprites/survivor/up4.png", 500);
					
			dead.addScene("sprites/survivor/dead.png", 250);
		} catch (Exception ex){}
	}
	
	public void init(){
		int x = 0;
		int y = 0;
		this.copy(down);
		this.setX(x);
		this.setY(y);
		this.visible = true;
		isFound = false;
		isDead = false;
	}
	
	//help the survivor follow Ming
	public void follow(Sprite m){
		if (!isDead){
			double hypotenuse,scale_x,scale_y,speed;
			hypotenuse = Math.hypot((double)(m.getX()-this.getX()), (double)(m.getY()-this.getY()));
			scale_x = (m.getX()-this.getX())/hypotenuse;
			scale_y = (m.getY()-this.getY())/hypotenuse;
			speed = 0.01;
			this.setVx((float)(scale_x*speed));
			this.setVy((float)(scale_y*speed));
		}
		direction();
	}
	
	//draw the survivor moving in the proper direction
	public void direction(){
		if (!isDead && isFound){
			if (this.getVy() < 0 && Math.abs(this.getVy()) > Math.abs(this.getVx())){
				this.copy(up);
			}
			else if (this.getVx() < 0 && Math.abs(this.getVy()) < Math.abs(this.getVx())){
				this.copy(left);
			}
			else if (this.getVx() > 0 && Math.abs(this.getVy()) < Math.abs(this.getVx())){
				this.copy(right);
			}
			else {
				this.copy(down);
			}
		}
		
		else {
			this.copy(dead);	
			this.setVx(0);
			this.setVy(0);
		}
	}
	
}
