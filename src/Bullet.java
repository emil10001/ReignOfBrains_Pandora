import java.awt.geom.Rectangle2D;
import java.io.File;
import javax.imageio.ImageIO;

public class Bullet extends Sprite {
	
	public boolean firing;
	
	public Bullet(){
		super();
		firing = false;
		loadImages();
	}
	
	@Override
	public void update(long timePassed){
		super.update(timePassed);
		if (hitBox != null){
			this.hitBox.setRect(this.getX()-10, this.getY(),this.getWidth()+20, this.getHeight());
		}
		else {
			this.hitBox = new Rectangle2D.Double(this.getX(), this.getY(),this.getWidth(), this.getHeight());
		}
	}
	
	@Override
	public synchronized void loadImages(){
		try{
			this.addScene("sprites/items/bullet.png", 250);
		} catch (Exception ex){}
	}
	
	public void fireAt(int toX, int toY, Ming m){
		double hypotenuse, scale_x, scale_y, speed;
		firing = true;
		switch(m.getDirectionFacing()){
		//up
		case 0:
			this.setX(m.getX()+20);
			this.setY(m.getY()+20);
			break;
		//up-right
		case 1:
			this.setX(m.getX()+m.getWidth()-40);
			this.setY(m.getY()+20);
			break;
		//right
		case 2:
			this.setX(m.getX()+m.getWidth()-40);
			this.setY(m.getY()+20);
			break;
		//down-right
		case 3:
			this.setX(m.getX()+20);
			this.setY(m.getY()+20);
			break;
		//down
		case 4:
			this.setX(m.getX()+m.getWidth()-20);
			this.setY(m.getY()+20);
			break;
		//down-left
		case 5:
			this.setX(m.getX()+20);
			this.setY(m.getY()+20);
			break;
		//left
		case 6:
			this.setX(m.getX()+40);
			this.setY(m.getY()+20);
			break;
		//up-left
		case 7:
			this.setX(m.getX()+20);
			this.setY(m.getY()+20);
			break;
		default:
			this.setX(m.getX());
			this.setY(m.getY());
			break;
		}
		hypotenuse = Math.hypot((double)(toX-this.getX()), (double)(toY-this.getY()));
		scale_x = (toX-this.getX())/hypotenuse;
		scale_y = (toY-this.getY())/hypotenuse;
		speed = 0.4;
		this.setVx((float)(scale_x*speed));
		this.setVy((float)(scale_y*speed));
	}
	
	public boolean detectCollide(Zombie z) {
		if (!z.isHit && z.visible && this.hitBox.intersects(z.hitBox)){
			firing = false;
			setX(0);
			setY(0);
			return true;
		}
		return false;
	}
	
}
