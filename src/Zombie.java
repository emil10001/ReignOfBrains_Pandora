import java.awt.geom.Rectangle2D;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class Zombie extends Sprite {

//	protected Sprite zLeft;
//	protected Sprite zRight;
//	protected Sprite zUp;
//	protected Sprite zDown;
//	protected Sprite hit;
//	protected Sound brains;
//	protected Sound brains2;
//	protected Sound uhragh;
	protected int direction;
	protected int oldDirection;
	public boolean isHit;
	protected int step1;
	protected int step2;
	public boolean visible;
	public boolean oldVisible;
	//public boolean loaded;
	public long deadTime;
	public Rectangle2D visBox;
	private int whichOne=0;
	
	public Zombie(){
		super();
		step1 = 200;
		step2 = 350;
		deadTime = 0;
		oldVisible = false;
		visible = false;
		isHit = false;
		setX(0);
		setY(0);
		visBox = new Rectangle2D.Double(this.getX(), this.getY(),100, 100);
		SecureRandom rnd = null;
		try {
			rnd = SecureRandom.getInstance("SHA1PRNG");
			whichOne = rnd.nextInt(2);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		this.zLeft();
	}
	
	public void unload(){
		this.clear();
	}

	private void zLeft(){
		this.clearScenes();
		switch (whichOne){
		case 0:
			this.addScene("sprites/zombie/1/left1.png", step1);
			this.addScene("sprites/zombie/1/left2.png", step2);
			break;
		case 1:
			this.addScene("sprites/zombie/2/left1.png", step1);
			this.addScene("sprites/zombie/2/left2.png", step2);
			break;
		}
	}
	private void zRight(){
		this.clearScenes();
		switch (whichOne){
		case 0:
			this.addScene("sprites/zombie/1/right1.png", step1);
			this.addScene("sprites/zombie/1/right2.png", step2);
			break;
		case 1:
			this.addScene("sprites/zombie/2/right1.png", step1);
			this.addScene("sprites/zombie/2/right2.png", step2);
			break;
		}
	}
	private void zUp(){
		this.clearScenes();
		switch (whichOne){
		case 0:
			this.addScene("sprites/zombie/1/up1.png", step1);
			this.addScene("sprites/zombie/1/up2.png", step2);
			this.addScene("sprites/zombie/1/up3.png", step1);
			this.addScene("sprites/zombie/1/up4.png", step2);
			break;
		case 1:
			this.addScene("sprites/zombie/2/up1.png", step1);
			this.addScene("sprites/zombie/2/up2.png", step2);
			this.addScene("sprites/zombie/2/up3.png", step1);
			this.addScene("sprites/zombie/2/up4.png", step2);
			break;
		}
	}
	private void zDown(){
		this.clearScenes();
		switch (whichOne){
		case 0:
			this.addScene("sprites/zombie/1/down1.png", step1);
			this.addScene("sprites/zombie/1/down2.png", step2);
			this.addScene("sprites/zombie/1/down3.png", step1);
			this.addScene("sprites/zombie/1/down4.png", step2);
			break;
		case 1:
			this.addScene("sprites/zombie/2/down1.png", step1);
			this.addScene("sprites/zombie/2/down2.png", step2);
			this.addScene("sprites/zombie/2/down3.png", step1);
			this.addScene("sprites/zombie/2/down4.png", step2);
			break;
		}
	}
	private void hit(){
		this.clearScenes();
		switch (whichOne){
		case 0:
			this.addScene("sprites/zombie/1/hit.png", step1/2);
			this.addScene("sprites/zombie/1/hit2.png", step1/2);
			break;
		case 1:
			this.addScene("sprites/zombie/2/hit.png", step1/2);
			this.addScene("sprites/zombie/2/hit2.png", step1/2);	
			break;
		}
	}
	
		
	public void dead(long timePassed){
		deadTime += timePassed;
		if (deadTime >= step1*4){
			SecureRandom rnd = null;
			try {
				rnd = SecureRandom.getInstance("SHA1PRNG");
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			}
			this.setX(this.getX()+rnd.nextInt(800));
			this.setY(rnd.nextInt(475*3/2)+300);
			deadTime = 0;
			isHit = false;
			visible = false;
			this.zLeft();
		}
	}
	
	public void update(long timePassed, Sprite liveBrain){
		super.update(timePassed);
		this.findBrains(liveBrain);
		if (isHit){
			dead(timePassed);
		}
		if (hitBox != null){
			this.hitBox.setRect(this.getX()+40, this.getY(),this.getWidth()-80, this.getHeight()/3);			
		}
		else {
			this.hitBox = new Rectangle2D.Double(this.getX()+40, this.getY(),this.getWidth()-80, this.getHeight()/3);
		}
		this.visBox.setRect(this.getX(), this.getY(),100, 100);
		if (!visible && !isHit && this.getX() < 790 && this.getX() > -500){
			SecureRandom rnd = null;
			try {
				rnd = SecureRandom.getInstance("SHA1PRNG");
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			}
			this.setX(this.getX()+800);
			this.setY(rnd.nextInt(475*3/2)+300);
		}
		
	}
	
	//help the zombie find some food
	public void findBrains(Sprite liveBrain){
		@SuppressWarnings("unused")
		SecureRandom rnd = null;
		try {
			rnd = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		if (!isHit){
			double hypotenuse,scale_x,scale_y,speed;
			hypotenuse = Math.hypot((double)(liveBrain.getX()-this.getX()), (double)(liveBrain.getY()-this.getY()));
			scale_x = (liveBrain.getX()-this.getX())/hypotenuse;
			scale_y = (liveBrain.getY()-this.getY())/hypotenuse;
			if (hypotenuse < 200){
				speed = 0.05;
			}
			if (hypotenuse < 100){
				speed = 0.07;
			}
			else {
				speed = 0.02;
			}
//			if (visible){
//				uhragh.running();
//			}
//			else {
//				uhragh.pause();
//			}
			this.setVx((float)(scale_x*speed));
			this.setVy((float)(scale_y*speed));
			
		}
		direction();
	}
	
	public boolean directionChanged(){
		if ((direction == oldDirection) && (visible == oldVisible)){
			return false;
		}
		oldDirection = direction;
		oldVisible = visible;
		return true;
	}
	
	//draw the zombie moving in the proper direction
	public void direction(){
		if (!isHit){
			//up
			if (this.getVy() < 0 && Math.abs(this.getVy()) >= Math.abs(this.getVx())){
				direction = 0;
			}
			//left
			else if (this.getVx() < 0 && Math.abs(this.getVy()) < Math.abs(this.getVx())){
				direction = 1;
			}
			//right
			else if (this.getVx() > 0 && Math.abs(this.getVy()) < Math.abs(this.getVx())){
				direction = 2;
			}
			//down
			else if (this.getVy() > 0 && Math.abs(this.getVy()) >= Math.abs(this.getVx())){
				direction = 3;
			}
		}
		
		else {
			direction = 4;
			this.setVx(0);
			this.setVy(0);
		}
		if (directionChanged() && this.visible){
			switch (direction){
			case 0:
				this.zUp();
				break;
			case 1:
				this.zLeft();
				break;
			case 2:
				this.zRight();
				break;
			case 3:
				this.zDown();
				break;
			case 4:
				this.hit();
				break;
			}
		}
	}
	
	
}
