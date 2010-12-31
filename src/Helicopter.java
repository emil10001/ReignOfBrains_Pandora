import java.awt.geom.Rectangle2D;

public class Helicopter extends Item{
	
	public Helicopter(){
		super();
		init();
		this.update(0);
	}
	
	public void init(){
		int x = 5300;
		int y = 100;
		this.setX(x);
		this.setY(y);
		this.visible = false;
	}

	@Override
	public void update(long timePassed){
		super.update(timePassed);
		if (hitBox != null){
			this.hitBox.setRect(this.getX()+100, this.getY()+50,this.getWidth()-200, this.getHeight()-100);
		}
		else {
			this.hitBox = new Rectangle2D.Double(this.getX()+100, this.getY()+50,this.getWidth()-200, this.getHeight()-100);
		}
	}
	
	public synchronized void loadImages(){
		this.addScene("sprites/helicopter1.png", 250);
		this.addScene("sprites/helicopter2.png", 250);
		visible=  true;
	}
}
