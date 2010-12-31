import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.image.BufferStrategy;


public class InputHandler extends Canvas implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int facing;
	protected int moving;
	public boolean firing;
	private boolean action;
	private int x = 0;
	private int y = 0;
	public boolean up,down,left,right;
	private int oldFacing;
	private int oldMoving;
	public boolean paused;
	private Thread thread;
	@SuppressWarnings("unused")
	private BufferStrategy strategy;
	int WIDTH;
	int HEIGHT;
	public Ming mingG;
	private boolean attack;
	
	public InputHandler(){
		firing = false;
		up = false;
		down = false;
		left = false;
		right = false;
		attack = false;
		action = false;
		WIDTH = 800;
		HEIGHT = 480;
		oldFacing = 0;
		oldMoving = 0;
		setVisible(true);
		setIgnoreRepaint(true);
		setFont(new Font("Arail", Font.PLAIN, 30));
		setBackground(Color.DARK_GRAY);
		setForeground(Color.WHITE);
		requestFocus();
	}
	
	public void start()
	{
		thread = new Thread(this);
		thread.setName("InputHandler");
		thread.start();
	}
	
	public void stop()
	{
		thread = null;
	}
	
	private void shutDown (String message)
	{
		if (thread != null) {
			thread = null;
		}
	}
	
	public void run(){
		while (thread!=null){
//			System.out.println("InputHandler running");
			this.gamepadPressed();
		}
//		System.out.println("InputHandler was killed");
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public boolean isPaused() {
		return paused;
	}

	public Ming getMingG() {
		return mingG;
	}

	public void setMingG(Ming mingG) {
		this.mingG = mingG;
	}

	public int getFacing() {
		return facing;
	}

	public void setFacing(int facing) {
		mingG.setDirectionFacing(facing);
		this.facing = facing;
	}

	public int getMoving() {
		return moving;
	}

	public void setMoving(int moving) {
		this.moving = moving;
	}
	
	public void determineDirection(){
		Ming m = mingG;
		//move up
		if (up && !down && !left && !right){
			m.setDirectionMoving(0);
			m.moving = true;
			moving =0;
			//rb.stayStill();
//			System.out.println("ming is moving up");
			return;
		}
		//move up-right
		else if (up && !down && !left && right){
			m.setDirectionMoving(1);
			m.moving = true;
			moving=1;
//			System.out.println("ming is moving up-right");
			return;
		}
		//move right
		else if (!up && !down && !left && right){
			m.setDirectionMoving(2);
			m.moving = true;
			moving=2;
			//System.out.println("ming is moving right");
			return;
		}
		//move down-right
		else if (!up && down && !left && right){
			m.setDirectionMoving(3);
			m.moving = true;
			moving=3;
//			System.out.println("ming is moving down-right");
			return;
		}
		//move down
		else if (!up && down && !left && !right){
			m.setDirectionMoving(4);
			m.moving = true;
			moving=4;
//			System.out.println("ming is moving down");
			return;
		}
		//move down-left
		else if (!up && down && left && !right){
			m.setDirectionMoving(5);
			m.moving = true;
			moving=5;
//			System.out.println("ming is moving down-left");
			return;
		}
		//move left
		else if (!up && !down && left && !right){
			m.setDirectionMoving(6);
			m.moving = true;
			moving=6;
//			System.out.println("ming is moving left");
			return;
		}
		//move up-left
		else if (up && !down && left && !right){
			m.setDirectionMoving(7);
			m.moving = true;
			moving=7;
//			System.out.println("ming is moving up-left");
			return;
		}
		else {
//			System.out.println("ming is not moving");
			m.moving = false;
			this.up = false;
			this.down = false;
			this.left = false;
			this.right = false;
			return;
		}
	}
			
	public boolean firing(){
		return firing;
	}
	
	public Point fireAt(){
		firing = false;
		System.out.println("fireAt " + x + ", " + y);
		mingG.setDirectionFacing(facing(x,y));
		mingG.changed = true;
		return new Point(x,y);
	}	
	
	public int facing(int x, int y){
		int angle1;
		angle1  = (int)(Math.atan2(-y, x)*(180/Math.PI)) % 360;
		//angle1 -= 90;
		if (angle1 < 0 ){
			angle1 += 360;
		}
		//System.out.println("Angle = " + angle1);
		if (angle1 >= 0 && angle1 <= 22){
			facing = 2;
			//System.out.println("ming is facing right");
			return 2;
		}
		else if (angle1 > 22 && angle1 <= 67){
			facing =1;
			//System.out.println("ming is facing up-right");
			return 1;
		}
		else if (angle1 > 67 && angle1 <= 112){
			facing = 0;
			//System.out.println("ming is facing up");
			return 0;
		}
		else if (angle1 > 112 && angle1 <= 157){
			facing = 7;
			//System.out.println("ming is facing up-left");
			return 7;
		}
		else if (angle1 > 157 && angle1 <= 202){
			facing = 6;
			//System.out.println("ming is facing left");
			return 6;
		}
		else if (angle1 > 202 && angle1 <= 247){
			facing = 5;
			//System.out.println("ming is facing down-left");
			return 5;
		}
		else if (angle1 > 247 && angle1 <= 292){
			facing = 4;
			//System.out.println("ming is facing down");
			return 4;
		}
		else if (angle1 > 292 && angle1 <= 337){
			facing = 3;
			//System.out.println("ming is facing down-right");
			return 3;
		}
		else if (angle1 > 337 && angle1 <= 360){
			facing = 2;
			//System.out.println("ming is facing right");
			return 2;
		}
		return 0;
	}
	
	public boolean directionChanged(){
		if (firing()){
			mingG.changed = true;
			return true;
		}
		else {
			if (moving == oldMoving){
				mingG.changed = false;
				return false;
			}
			mingG.changed = true;
			oldMoving = moving;
			return true;
		}
	
	}
	
	public void attack(){
		if (attack){
			attack = false;
		}
		else {
			attack = true;
		}
	}
	
	public void action(){
		if (action){
			action = false;
		}
		else {
			action = true;
		}
	}

	public void gamepadPressed(){
		if (mingG == null){
//			System.out.println("No ming. =(");
			return;
		}
		determineDirection();
		facing(x,y);
		mingG.setDirectionFacing(moving);
		if (mingG.attack){
			mingG.setDirectionFacing(facing);
		}
	}
	
	public void pause(){
		if (paused){
			paused = false;
		}
		else {
			paused = true;
		}
	}
	
}
