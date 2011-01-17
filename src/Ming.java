import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Ming extends Sprite{
	
	private int direction;
	private int directionFacing;
//	private int oldDirection;
	private int oldDirectionFacing;
	private int health;
	private int healthPack;
	private int ammo;
	private int battery;
	private int sandwiches;
	public boolean action;
	public boolean changed;
	public boolean gun;
	public boolean oldGun;
	public boolean isDead;
	public boolean run;
	private boolean attacked;
	protected boolean attack;
	private BufferedImage LeftAxe1;
	private BufferedImage LeftAxe2;
	private BufferedImage LeftAxe3;
	private BufferedImage LeftAxe4;
	private BufferedImage LeftGun1;
	private BufferedImage LeftGun2;
	private BufferedImage LeftGun3;
	private BufferedImage LeftGun4;
	private BufferedImage RightAxe1;
	private BufferedImage RightAxe2;
	private BufferedImage RightAxe3;
	private BufferedImage RightAxe4;
	private BufferedImage RightGun1;
	private BufferedImage RightGun2;
	private BufferedImage RightGun3;
	private BufferedImage RightGun4;
	private BufferedImage BackAxe1;
	private BufferedImage BackAxe2;
	private BufferedImage BackAxe3;
	private BufferedImage BackAxe4;
	private BufferedImage BackGun1;
	private BufferedImage BackGun2;
	private BufferedImage BackGun3;
	private BufferedImage BackGun4;
	private BufferedImage FrontAxe1;
	private BufferedImage FrontAxe2;
	private BufferedImage FrontAxe3;
	private BufferedImage FrontAxe4;
	private BufferedImage FrontGun1;
	private BufferedImage FrontGun2;
	private BufferedImage FrontGun3;
	private BufferedImage FrontGun4;
	private BufferedImage AxeAttack2;
	private BufferedImage AxeAttack1;
	private BufferedImage LeftFire;
	private BufferedImage RightFire;
	private BufferedImage BackFire;
	private BufferedImage FrontFire;
	private BufferedImage upRightFire;
	private BufferedImage downRightFire;
	private BufferedImage downLeftFire;
	private BufferedImage upLeftFire;
	private BufferedImage upLeftA1;
	private BufferedImage upLeftA2;
	private BufferedImage upLeftA3;
	private BufferedImage upLeftA4;
	private BufferedImage downLeftA1;
	private BufferedImage downLeftA2;
	private BufferedImage downLeftA3;
	private BufferedImage downLeftA4;
	private BufferedImage upRightA1;
	private BufferedImage upRightA2;
	private BufferedImage upRightA3;
	private BufferedImage upRightA4;
	private BufferedImage downRightA1;
	private BufferedImage downRightA2;
	private BufferedImage downRightA3;
	private BufferedImage downRightA4;
	private BufferedImage upLeftG1;
	private BufferedImage upLeftG2;
	private BufferedImage upLeftG3;
	private BufferedImage upLeftG4;
	private BufferedImage downLeftG1;
	private BufferedImage downLeftG2;
	private BufferedImage downLeftG3;
	private BufferedImage downLeftG4;
	private BufferedImage upRightG1;
	private BufferedImage upRightG2;
	private BufferedImage upRightG3;
	private BufferedImage upRightG4;
	private BufferedImage downRightG1;
	private BufferedImage downRightG2;
	private BufferedImage downRightG3;
	private BufferedImage downRightG4;
	public boolean moving;
	private boolean oldMoving;
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	public int stepTime;
	protected Ribbon rb;
	private long attackTime;
	private boolean attackChange;
	
	public Ming(Ribbon rib){
		super();
		this.stepTime = 200;
		this.attackTime = 0;
		this.health = 100;
		this.ammo = 50;
		this.sandwiches = 0;
		this.battery = 50;
		this.healthPack = 0;
//		this.loadImages();
		action = false;
		changed = false;
		up = false;
		down = false;
		left = false;
		right = false;
		moving = false;
		run = false;
		attack = false;
		attacked = false;
		attackChange = false;
		rb = rib;
		FrontGun1 = genBufferedImage("sprites/ming/FrontGun1.png");
		FrontGun2 = genBufferedImage("sprites/ming/FrontGun2.png");
		FrontGun3 = genBufferedImage("sprites/ming/FrontGun3.png");
		FrontGun4 = genBufferedImage("sprites/ming/FrontGun4.png");
		this.downG();
		this.setX(0);
		this.setY(0);
		this.visible = true;
		this.direction = 4;
		this.gun = true;
		this.oldGun = this.gun;
		this.oldMoving = false;
		isDead = false;
		this.hitBox = new Rectangle2D.Double(this.getX()+20, this.getY(), this.getWidth()-40, this.getHeight()/2);
		new Thread( 
		  new Runnable(){
			  public void run() {
				  loadImages();
			  }
		  }).start();
	}

	
	public int getDirectionMoving() {
		return direction;
	}


	public void setDirectionMoving(int directionMoving) {
		this.direction = directionMoving;
	}


	public void setDirectionFacing(int directionFacing) {
		this.directionFacing = directionFacing;
	}


	public int getDirectionFacing(){
		return directionFacing;
	}
	
	@Override
	public void update(long timePassed){
		super.update(timePassed);
		if (attack){
			this.attackTime(timePassed);
		}
		this.move();
		if (directionChanged()){
			this.face();
		}
		rb.update();
		if (this.health <= 50){
			if (this.healthPack>=1){
				this.useHealth();
			}
			else if (this.getSandwiches()>= 1){
				this.eatSandwich();
			}
		}
		this.hitBox.setRect(this.getX()+30, this.getY(),this.getWidth()-60, this.getHeight()/2);
	}

	private void leftA(){
		this.clearScenes();
		this.addScene(LeftAxe1, stepTime);
		this.addScene(LeftAxe2, stepTime);
		this.addScene(LeftAxe3, stepTime);
		this.addScene(LeftAxe4, stepTime);
	}
	
	public void leftStopA(){
		this.clearScenes();
		this.addScene(LeftAxe1, stepTime);
	}
	
	private void leftG(){
		this.clearScenes();
		this.addScene(LeftGun1, stepTime);
		this.addScene(LeftGun2, stepTime);
		this.addScene(LeftGun3, stepTime);
		this.addScene(LeftGun4, stepTime);
	}
	
	private void leftStopG(){
		this.clearScenes();
		this.addScene(LeftGun1, stepTime);
	}
	
	private void rightA(){
		this.clearScenes();
		this.addScene(RightAxe1, stepTime);
		this.addScene(RightAxe2, stepTime);
		this.addScene(RightAxe3, stepTime);
		this.addScene(RightAxe4, stepTime);
	}
	
	private void rightStopA(){
		this.clearScenes();
		this.addScene(RightAxe1, stepTime);
	}
	
	private void rightG(){
		this.clearScenes();
		this.addScene(RightGun1, stepTime);
		this.addScene(RightGun2, stepTime);
		this.addScene(RightGun3, stepTime);
		this.addScene(RightGun4, stepTime);
	}
	
	private void rightStopG(){
		this.clearScenes();
		this.addScene(RightGun1, stepTime);
	}
	
	private void upA(){
		this.clearScenes();
		this.addScene(BackAxe1, stepTime);
		this.addScene(BackAxe2, stepTime);
		this.addScene(BackAxe3, stepTime);
		this.addScene(BackAxe4, stepTime);
	}

	private void upStopA(){
		this.clearScenes();
		this.addScene(BackAxe1, stepTime);
	}
	
	private void upG(){
		this.clearScenes();
		this.addScene(BackGun1, stepTime);
		this.addScene(BackGun2, stepTime);
		this.addScene(BackGun3, stepTime);
		this.addScene(BackGun4, stepTime);
	}
	
	private void upStopG(){
		this.clearScenes();
		this.addScene(BackGun1, stepTime);
	}
	
	private void downA(){
		this.clearScenes();
		this.addScene(FrontAxe1, stepTime);
		this.addScene(FrontAxe2, stepTime);
		this.addScene(FrontAxe3, stepTime);
		this.addScene(FrontAxe4, stepTime);
	}
	
	private void downStopA(){
		this.clearScenes();
		this.addScene(FrontAxe1, stepTime);
	}
	private void downG(){
		this.clearScenes();
		this.addScene(FrontGun1, stepTime);
		this.addScene(FrontGun2, stepTime);
		this.addScene(FrontGun3, stepTime);
		this.addScene(FrontGun4, stepTime);
	}
	private void downStopG(){
		this.clearScenes();
		this.addScene(FrontGun1, stepTime);
	}
	private void axeHit(){
		this.clearScenes();
		this.addScene(AxeAttack2, stepTime/2);
	}
	private void axeMiss(){
		this.clearScenes();
		this.addScene(AxeAttack1, stepTime);
	}
	private void fireLeft(){
		this.clearScenes();
		this.addScene(LeftFire, stepTime);
		this.addScene(LeftFire, stepTime);
	}
	private void fireRight(){
		this.clearScenes();
		this.addScene(RightFire, stepTime);
		this.addScene(RightFire, stepTime);
	}
	private void fireUp(){
		this.clearScenes();
		this.addScene(BackFire, stepTime);
	}
	private void fireDown(){
		this.clearScenes();
		this.addScene(FrontFire, stepTime);
	}
	private void fireUpRight(){
		this.clearScenes();
		this.addScene(upRightFire, stepTime);
	}
	private void fireDownRight(){
		this.clearScenes();
		this.addScene(downRightFire, stepTime);
	}
	private void fireDownLeft(){
		this.clearScenes();
		this.addScene(downLeftFire, stepTime);
		this.addScene(downLeftFire, stepTime);
	}
	private void fireUpLeft(){
		this.clearScenes();
		this.addScene(upLeftFire, stepTime);
	}

	private void upLeftA(){
		this.clearScenes();
		this.addScene(upLeftA1, stepTime);
		this.addScene(upLeftA2, stepTime);
		this.addScene(upLeftA3, stepTime);
		this.addScene(upLeftA4, stepTime);
	}
	private void upLeftStopA(){
		this.clearScenes();
		this.addScene(upLeftA1, stepTime);
	}
	private void downLeftA(){
		this.clearScenes();
		this.addScene(downLeftA1, stepTime);
		this.addScene(downLeftA2, stepTime);
		this.addScene(downLeftA3, stepTime);
		this.addScene(downLeftA4, stepTime);
	}
	private void downLeftStopA(){
		this.clearScenes();
		this.addScene(downLeftA1, stepTime);
	}
	private void upRightA(){
		this.clearScenes();
		this.addScene(upRightA1, stepTime);
		this.addScene(upRightA2, stepTime);
		this.addScene(upRightA3, stepTime);
		this.addScene(upRightA4, stepTime);
	}
	private void upRightStopA(){
		this.clearScenes();
		this.addScene(upRightA1, stepTime);
	}
	private void downRightA(){
		this.clearScenes();
		this.addScene(downRightA1, stepTime);
		this.addScene(downRightA2, stepTime);
		this.addScene(downRightA3, stepTime);
		this.addScene(downRightA4, stepTime);
	}
	private void downRightStopA(){
		this.clearScenes();
		this.addScene(downRightA1, stepTime);
	}
	private void upLeftG(){
		this.clearScenes();
		this.addScene(upLeftG1, stepTime);
		this.addScene(upLeftG2, stepTime);
		this.addScene(upLeftG3, stepTime);
		this.addScene(upLeftG4, stepTime);
	}
	private void upLeftStopG(){
		this.clearScenes();
		this.addScene(upLeftG1, stepTime);
	}
	private void downLeftG(){
		this.clearScenes();
		this.addScene(downLeftG1, stepTime);
		this.addScene(downLeftG2, stepTime);
		this.addScene(downLeftG3, stepTime);
		this.addScene(downLeftG4, stepTime);
	}
	private void downLeftStopG(){
		this.clearScenes();
		this.addScene(downLeftG1, stepTime);
	}
	private void upRightG(){
		this.clearScenes();
		this.addScene(upRightG1, stepTime);
		this.addScene(upRightG2, stepTime);
		this.addScene(upRightG3, stepTime);
		this.addScene(upRightG4, stepTime);
	}
	private void upRightStopG(){
		this.clearScenes();
		this.addScene(upRightG1, stepTime);
	}
	private void downRightG(){
		this.clearScenes();
		this.addScene(downRightG1, stepTime);
		this.addScene(downRightG2, stepTime);
		this.addScene(downRightG3, stepTime);
		this.addScene(downRightG4, stepTime);
	}
	private void downRightStopG(){
		this.clearScenes();
		this.addScene(downRightG1, stepTime);
	}
	

	@Override
	public void loadImages(){
		LeftAxe1 = genBufferedImage("sprites/ming/LeftAxe1.png");
		LeftAxe2 = genBufferedImage("sprites/ming/LeftAxe2.png");
		LeftAxe3 = genBufferedImage("sprites/ming/LeftAxe3.png");
		LeftAxe4 = genBufferedImage("sprites/ming/LeftAxe4.png");
		LeftGun1 = genBufferedImage("sprites/ming/LeftGun1.png");
		LeftGun2 = genBufferedImage("sprites/ming/LeftGun2.png");
		LeftGun3 = genBufferedImage("sprites/ming/LeftGun3.png");
		LeftGun4 = genBufferedImage("sprites/ming/LeftGun4.png");
		RightAxe1 = genBufferedImage("sprites/ming/RightAxe1.png");
		RightAxe2 = genBufferedImage("sprites/ming/RightAxe2.png");
		RightAxe3 = genBufferedImage("sprites/ming/RightAxe3.png");
		RightAxe4 = genBufferedImage("sprites/ming/RightAxe4.png");
		RightGun1 = genBufferedImage("sprites/ming/RightGun1.png");
		RightGun2 = genBufferedImage("sprites/ming/RightGun2.png");
		RightGun3 = genBufferedImage("sprites/ming/RightGun3.png");
		RightGun4 = genBufferedImage("sprites/ming/RightGun4.png");
		BackAxe1 = genBufferedImage("sprites/ming/BackAxe1.png");
		BackAxe2 = genBufferedImage("sprites/ming/BackAxe2.png");
		BackAxe3 = genBufferedImage("sprites/ming/BackAxe3.png");
		BackAxe4 = genBufferedImage("sprites/ming/BackAxe4.png");
		BackGun1 = genBufferedImage("sprites/ming/BackGun1.png");
		BackGun2 = genBufferedImage("sprites/ming/BackGun2.png");
		BackGun3 = genBufferedImage("sprites/ming/BackGun3.png");
		BackGun4 = genBufferedImage("sprites/ming/BackGun4.png");
		FrontAxe1 = genBufferedImage("sprites/ming/FrontAxe1.png");
		FrontAxe2 = genBufferedImage("sprites/ming/FrontAxe2.png");
		FrontAxe3 = genBufferedImage("sprites/ming/FrontAxe3.png");
		FrontAxe4 = genBufferedImage("sprites/ming/FrontAxe4.png");
		FrontGun1 = genBufferedImage("sprites/ming/FrontGun1.png");
		FrontGun2 = genBufferedImage("sprites/ming/FrontGun2.png");
		FrontGun3 = genBufferedImage("sprites/ming/FrontGun3.png");
		FrontGun4 = genBufferedImage("sprites/ming/FrontGun4.png");
		AxeAttack2 = genBufferedImage("sprites/ming/AxeAttack2.png");
		AxeAttack1 = genBufferedImage("sprites/ming/AxeAttack1.png");
		LeftFire = genBufferedImage("sprites/ming/firing/LeftFire.png");
		LeftFire = genBufferedImage("sprites/ming/firing/LeftFire.png");
		RightFire = genBufferedImage("sprites/ming/firing/RightFire.png");
		RightFire = genBufferedImage("sprites/ming/firing/RightFire.png");
		BackFire = genBufferedImage("sprites/ming/firing/BackFire.png");
		FrontFire = genBufferedImage("sprites/ming/firing/FrontFire.png");
		upRightFire = genBufferedImage("sprites/ming/firing/upRightFire.png");
		downRightFire = genBufferedImage("sprites/ming/firing/downRightFire.png");
		downLeftFire = genBufferedImage("sprites/ming/firing/downLeftFire.png");
		upLeftFire = genBufferedImage("sprites/ming/firing/upLeftFire.png");
		upLeftA1 = genBufferedImage("sprites/ming/upLeftA1.png");
		upLeftA2 = genBufferedImage("sprites/ming/upLeftA2.png");
		upLeftA3 = genBufferedImage("sprites/ming/upLeftA3.png");
		upLeftA4 = genBufferedImage("sprites/ming/upLeftA4.png");
		downLeftA1 = genBufferedImage("sprites/ming/downLeftA1.png");
		downLeftA2 = genBufferedImage("sprites/ming/downLeftA2.png");
		downLeftA3 = genBufferedImage("sprites/ming/downLeftA3.png");
		downLeftA4 = genBufferedImage("sprites/ming/downLeftA4.png");
		upRightA1 = genBufferedImage("sprites/ming/upRightA1.png");
		upRightA2 = genBufferedImage("sprites/ming/upRightA2.png");
		upRightA3 = genBufferedImage("sprites/ming/upRightA3.png");
		upRightA4 = genBufferedImage("sprites/ming/upRightA4.png");
		downRightA1 = genBufferedImage("sprites/ming/downRightA1.png");
		downRightA2 = genBufferedImage("sprites/ming/downRightA2.png");
		downRightA3 = genBufferedImage("sprites/ming/downRightA3.png");
		downRightA4 = genBufferedImage("sprites/ming/downRightA4.png");
		upLeftG1 = genBufferedImage("sprites/ming/upLeftG1.png");
		upLeftG2 = genBufferedImage("sprites/ming/upLeftG2.png");
		upLeftG3 = genBufferedImage("sprites/ming/upLeftG3.png");
		upLeftG4 = genBufferedImage("sprites/ming/upLeftG4.png");
		downLeftG1 = genBufferedImage("sprites/ming/downLeftG1.png");
		downLeftG2 = genBufferedImage("sprites/ming/downLeftG2.png");
		downLeftG3 = genBufferedImage("sprites/ming/downLeftG3.png");
		downLeftG4 = genBufferedImage("sprites/ming/downLeftG4.png");
		upRightG1 = genBufferedImage("sprites/ming/upRightG1.png");
		upRightG2 = genBufferedImage("sprites/ming/upRightG2.png");
		upRightG3 = genBufferedImage("sprites/ming/upRightG3.png");
		upRightG4 = genBufferedImage("sprites/ming/upRightG4.png");
		downRightG1 = genBufferedImage("sprites/ming/downRightG1.png");
		downRightG2 = genBufferedImage("sprites/ming/downRightG2.png");
		downRightG3 = genBufferedImage("sprites/ming/downRightG3.png");
		downRightG4 = genBufferedImage("sprites/ming/downRightG4.png");
	}
	
	public boolean directionChanged(){
		if (attack){
			return false;
		}
		else if (directionFacing != oldDirectionFacing){
			oldDirectionFacing = directionFacing;
			return true;
		}
		else if ((moving == oldMoving) && (gun == oldGun) && (!attacked) && !changed && !attackChange){
			return false;
		}
		attacked = false;
		attackChange = false;
		changed = false;
		//oldDirectionFacing = directionFacing;
		oldMoving = moving;
		//oldDirectionFacing = directionFacing;
		oldGun = gun;
		//oldDirection = direction;
		return true;
		
	}

	
	//Ming is moving which direction?
	public void move(){
		//determineDirection();
		double hypotenuse,scale_x,scale_y,speed,goX,goY;
		if (run){
			speed = 0.1;
		}
		else {
			speed = 0.04;
		}
		goX = 0;
		goY = 0;
		switch (direction){
		//up
		case 0:
			if (moving){
				goX = 0;
				goY = -20;
			}
			else {
				rb.stayStill();
			}	
			break;
		//up-right	
		case 1:
			if (moving){
				goX = 10;
				goY = -10;
			}
			else {
				rb.stayStill();
			}
			break;
		//right
		case 2:	
			if (moving){
				goX = 20;
				goY = 0;
			}
			else {
				rb.stayStill();
			}
			break;
		//down-right
		case 3:
			if (moving){
				goX = 10;
				goY = 10;
			}
			else {
				rb.stayStill();
			}
			break;
		//down	
		case 4:
			if (moving){
				goX = 0;
				goY = 20;
			}
			else {
				rb.stayStill();
			}
			break;
		//down-left
		case 5:
			if (moving){
				goX = -10;
				goY = 10;
			}
			else {
				rb.stayStill();
			}
			break;
		//left
		case 6:
			if (moving){
				goX = -20;
				goY = 0;
			}
			else {
				rb.stayStill();
			}
			break;
		//up-left	
		case 7:
			if (moving){
				goX = -10;
				goY = -10;
			}
			else {
				rb.stayStill();
			}
			break;
		}
		if (moving){
			hypotenuse = Math.hypot((double)(goX), (double)(goY));
			scale_x = (goX)/hypotenuse;
			scale_y = (goY)/hypotenuse;
			if (run){
				speed = 0.1;
			}
			else {
				speed = 0.04;
			}
			this.setVx((float)(scale_x*speed));
			this.setVy((float)(scale_y*speed));
		}
		else {
			rb.stayStill();
			this.setVx(0);
			this.setVy(0);
		}	

	}
	
	//Ming is facing which direction?
	public void face(){
		switch (directionFacing){
		//up
		case 0:
			if (moving){
					if (gun){
						this.upG();
						//System.out.println("upG");
					
					}
					else {
						this.upA();
						//System.out.println("upA");
					}
			}
			else {
					if (gun){
						this.upStopG();
						//System.out.println("upStopG");
					}
					else {
						this.upStopA();
						//System.out.println("upStopA");
					}	
			}	
			break;
		//up-right	
		case 1:
			if (moving){
					if (gun){
						this.upRightG();
						//System.out.println("upRightG");
					}
					else {
						this.upRightA();	
						//System.out.println("upRightA");
					}
			}
			else {
					if (gun){
						this.upRightStopG();
						//System.out.println("upRightStopG");
					}
					else {
						this.upRightStopA();
						//System.out.println("upRightStopA");
					}	
			}
			break;
		//right
		case 2:	
			if (moving){
					if (gun){
						this.rightG();
						//System.out.println("rightG");		
						}
					else {
						this.rightA();
						//System.out.println("rightA");
					}
			}
			else {
					if (gun){
						this.rightStopG();
						//System.out.println("rightStopG");
					}
					else {
						this.rightStopA();
						//System.out.println("rightStopA");
					}
			}
			break;
		//down-right
		case 3:
			if (moving){
					if (gun){
						this.downRightG();
						//System.out.println("downRightG");
					}
					else {
						this.downRightA();
						//System.out.println("downRightA");
					}
			}
			else {
					if (gun){
						this.downRightStopG();
						//System.out.println("downRightStopG");
					}
					else {
						this.downRightStopA();
						//System.out.println("downRightStopA");
					}	
			}
			break;
		//down	
		case 4:
			if (moving){
					if (gun){
						this.downG();
						//System.out.println("downG");
					}
					else {
						this.downA();
						//System.out.println("downA");
					}
			}
			else {
					if (gun){
						this.downStopG();
						//System.out.println("downStopG");
					}
					else {
						this.downStopA();
						//System.out.println("downStopA");
					}	
			}
			break;
		//down-left
		case 5:
			if (moving){
					if (gun){
						this.downLeftG();
						//System.out.println("downLeftG");
					}
					else {
						this.downLeftA();
						//System.out.println("downLeftA");
					}
			}
			else {
					if (gun){
						this.downLeftStopG();
						//System.out.println("downLeftStopG");
					}
					else {
						this.downLeftStopA();
						//System.out.println("downLeftStopA");
					}	
			}
			break;
		//left
		case 6:
			if (moving){
					if (gun){
						this.leftG();
						//System.out.println("leftG");
					}
					else {
						this.leftA();
						//System.out.println("leftA");
					}
			}
			else {
					if (gun){
						this.leftStopG();
						//System.out.println("leftStopG");
					}
					else {
						this.leftStopA();
						//System.out.println("leftStopA");
					}
			}
			break;
		//up-left	
		case 7:
			if (moving){
					if (gun){
						this.upLeftG();
						//System.out.println("upLeftG");
					}
					else {
						this.upLeftA();
						//System.out.println("upLeftA");
					}	
			}
			else {
					if (gun){
						this.upLeftStopG();
						//System.out.println("upLeftStopG");
					}
					else {
						this.upLeftStopA();
						//System.out.println("upLeftStopA");
					}
			}
			break;
		}
	}
	
	
	public void attackTime(long timePassed){
		attackTime += timePassed;
		//System.out.println("Still attacking ...");
		if (attackTime >= this.stepTime/2){
			//System.out.println("attack reset");
			attackTime = 0;
			attackChange = true;
			attack = false;
		}
	}
	
	public boolean detectSurvivor(Survivor s) {
		//if (this.getX() <= (s.getX()+s.getWidth()) && this.getX() >= s.getX() && this.getY() >= s.getY() && this.getY() <= (s.getY()+s.getHeight())){
		if (this.hitBox.intersects(s.hitBox)){
			return true;
		}
		return false;
	}
	
	public void detectAttack(Zombie z) {
		if (!z.isHit && z.visible && this.hitBox.intersects(z.hitBox)){
//			&& this.getX() <= (z.getX()+z.getWidth()) && (this.getX()+this.getWidth()) >= z.getX() && (this.getY()+this.getHeight()) >= z.getY() && this.getY() <= (z.getY()+z.getHeight()) && z.visible){
			attacked = true;
			hit();
		}
	}
	
	public boolean detectDoor(Area.Door d) {
		Rectangle2D grabBox = new Rectangle2D.Double(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		if (action && grabBox.intersects(d.getDoor())){
			action = false;
			return true;
		}
		return false;
	}
	
	public boolean detectItems(Item i) {
		Rectangle2D grabBox = new Rectangle2D.Double(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		if (i.visible && grabBox.intersects(i.hitBox) && action){
			action = false;
			switch (i.getItemType()){
			case 0:
				//Ammunition
				this.foundAmmo(i.getValue());
				return true;
			case 1:
				//Health
				this.foundHealth(i.getValue());
				return true;
			case 2:
				//Sandwich
				this.foundSandwich(i.getValue());
				return true;
			case 3:
				//Battery
				this.foundBattery(i.getValue());
				return true;
			}
		}
		return false;
	}
	
	public void attack(int toX,int toY){
		//System.out.println("Ming.attack()");
		attack = true;
		if (gun && ammo <= 0) {
			switchWeapon();
		}
		if (gun && ammo > 0) {	
			ammo -= 1;
			
			int angle1;
			angle1  = (int)(Math.atan2(-toY, toX)*(180/Math.PI)) % 360;
			//angle1 -= 90;
			if (angle1 < 0 ){
				angle1 += 360;
			}
			//System.out.println("Angle = " + angle1);
			if (angle1 >= 0 && angle1 <= 22){
				direction = 2;
				//System.out.println("ming is facing right");
			}
			else if (angle1 > 22 && angle1 <= 67){
				direction =1;
				//System.out.println("ming is facing up-right");
			}
			else if (angle1 > 67 && angle1 <= 112){
				direction = 0;
				//System.out.println("ming is facing up");
			}
			else if (angle1 > 112 && angle1 <= 157){
				direction = 7;
				//System.out.println("ming is facing up-left");
			}
			else if (angle1 > 157 && angle1 <= 202){
				direction = 6;
				//System.out.println("ming is facing left");
			}
			else if (angle1 > 202 && angle1 <= 247){
				direction = 5;
				//System.out.println("ming is facing down-left");
			}
			else if (angle1 > 247 && angle1 <= 292){
				direction = 4;
				//System.out.println("ming is direction down");
			}
			else if (angle1 > 292 && angle1 <= 337){
				direction = 3;
				//System.out.println("ming is facing down-right");
			}
			else if (angle1 > 337 && angle1 <= 360){
				direction = 2;
				//System.out.println("ming is facing right");
			}
			
			switch(direction){
			case 0:
				this.fireUp();
				break;
			case 1:
				this.fireUpRight();
				break;
			case 2:
				this.fireRight();
				break;
			case 3:
				this.fireDownRight();	
				break;
			case 4:
				this.fireDown();
				break;
			case 5:
				//System.out.println("fireDownLeft");
				this.fireDownLeft();
				break;
			case 6:
				//System.out.println("fireLeft");
				this.fireLeft();
				break;
			case 7:
				//System.out.println("fireUpLeft");
				this.fireUpLeft();	
				break;
			}
		}	
	}

	public boolean axing(Zombie z){
		if (attack){
			moving = false;
			//attack = false;
			return axeHit(z);
		}
		return false;
	}
	
	public boolean axeHit(Zombie z) {
		//attack = true;
		
		try {
			Rectangle2D attackBox = new Rectangle2D.Double(this.getX(), this.getY(), this.getWidth(), this.getHeight());

			//if (this.getX()-10 <= (z.getX()+z.getWidth()) && (this.getX()+this.getWidth()+10) >= z.getX() && (this.getY()+this.getHeight()+10) >= z.getY() && this.getY()-10 <= (z.getY()+30) && z.visible){
			if (z.visible && !z.isHit && attackBox.intersects(z.hitBox)){
				direction = 4;
				this.axeHit();
				return true;
			}
			else { 
				direction = 4;
				this.axeMiss();
				return false;
			}
		} catch (Exception ex){
			System.out.println(ex);
			return false;
		}		
	}
	
	public void switchWeapon(){
		if (gun){
			gun=false;
			switch(direction){
				case 0:
					this.upA();
					break;
				case 1:
					this.upRightA();
					break;
				case 2:
					this.rightA();
					break;
				case 3:
					this.downRightA();	
					break;
				case 4:
					this.downA();
					break;
				case 5:
					this.downLeftA();
					break;
				case 6:
					this.leftA();
					break;
				case 7:
					this.upLeftA();	
					break;
			}
		}
		else {
			gun=true;
			switch(direction){
			case 0:
				this.upG();
				break;
			case 1:
				this.upRightG();
				break;
			case 2:
				this.rightG();
				break;
			case 3:
				this.downRightG();	
				break;
			case 4:
				this.downG();
				break;
			case 5:
				this.downLeftG();
				break;
			case 6:
				this.leftG();
				break;
			case 7:
				this.upLeftG();	
				break;
			}
		}
	}
	
	// everything over 100 counts as supplies for survivors at the end of the level
	public void foundHealth(int x) {
		if (health < 100){
			this.health += x;
			if (health > 100){
				health = 100;
			}
		}
		else {
			this.healthPack++;
		}
		//System.out.println("Found health: " + this.getHealth());
	}
	
	public void hit() {
		direction = 4;
		
		this.clearScenes();
		this.addScene("sprites/ming/Hit1.png", stepTime);
		this.addScene("sprites/ming/Hit2.png", stepTime);
		//this.health -= 25;
		this.health--;
		if (health <= 0){
			health = 0;
			isDead = true;
		}
		//System.out.println("Hit - health: " + this.getHealth());
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getHealthPack() {
		return healthPack;
	}
	
	public void foundAmmo(int ammo) {
		this.ammo += ammo;
		//System.out.println("Found ammo: " + this.getAmmo());
	}

	public int getAmmo() {
		return ammo;
	}
	
	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}
	
	// everything over 100 counts as supplies for survivors at the end of the level
	public void foundBattery(int x) {
		this.battery += x;
		//System.out.println("Found battery: " + this.getBattery());
	}
	
	public void batteryDecrement(){
		this.battery -= 2;
	}

	public int getBattery() {
		return battery;
	}

	// all sandwiches that are in inventory can be  
	public void foundSandwich(int x) {
		if (health < 50) {
			health += (x*10);
		}
		else {
			this.sandwiches += x;
		}
		//System.out.println("Found sandwich: " + this.getSandwiches());
	}
	
	public void eatSandwich(){
		if (sandwiches > 0){
			sandwiches--;
			health += 10;
			if (health > 100){
				health = 100;
			}
		}
	}

	public void useHealth(){
		this.healthPack--;
		this.health += 25;
		if (health > 100){
			health = 100;
		}
	}
	
	public int getSandwiches() {
		return sandwiches;
	}
	
	public boolean getGun()
	{
		return gun;
	}
	
	public boolean getMoving()
	{
		return moving;
	}
}
