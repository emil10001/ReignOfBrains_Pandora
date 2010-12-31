/*
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;


public class HealthPanel extends JPanel {
	
	/**
	 * 
	 *
	private static final long serialVersionUID = 1L;
	private int state = 100;
	
	public void updateState(int newState){
		state = newState;
		this.repaint();
	}

    @Override public void paintComponent(Graphics g) {
        super.paintComponent(g); 
    	Graphics2D g2d = (Graphics2D)g;

    	
        g2d.setColor(Color.black);
        RoundRectangle2D r0 = new RoundRectangle2D.Float(11, 2, 227, 36, 30, 30);
        g2d.fill(r0);
    	GradientPaint gr = new GradientPaint(0, 0, Color.red, 220, 0, Color.green, true);
    	g2d.setPaint(gr);
    	RoundRectangle2D r1 = new RoundRectangle2D.Float(15, 5, 220, 30, 25, 25);
    	g2d.fill(r1);
    	
        if(state == 100){
        		// State 1: 100% Health
        		// Do nothing	
        }
        else if (state >= 75)
        {
        		// State 2: 75% Health
        		g2d.setColor(Color.black);
        		Rectangle r2a = new Rectangle(180, 5, 40, 30);
        		RoundRectangle2D r2b = new RoundRectangle2D.Float(200, 5, 40, 30, 25, 25);
        		g2d.fill(r2a);
        		g2d.fill(r2b);
        }
        else if (state >= 50)
        {
        	// State 3: 50% Health
        		g2d.setColor(Color.black);
        		Rectangle r3a = new Rectangle(130, 5, 100, 30);
        		RoundRectangle2D r3b = new RoundRectangle2D.Float(200, 5, 40, 30, 25, 25);
        		g2d.fill(r3a);
        		g2d.fill(r3b);
    	}
        else if (state >= 25)
       {
        		// State 4: 25% Health
        		g2d.setColor(Color.black);
        		Rectangle r4a = new Rectangle(70, 5, 150, 30);
        		RoundRectangle2D r4b = new RoundRectangle2D.Float(200, 5, 40, 30, 25, 25);
        		g2d.fill(r4a);
        		g2d.fill(r4b);
       }
       else if (state >= 0)
       {
        		// State 5: 0% Health
        		g2d.setColor(Color.black);
                RoundRectangle2D r = new RoundRectangle2D.Float(11, 2, 227, 36, 30, 30);
                g2d.fill(r);
        }
        g2d.drawString(state + "%", 0,0);

    }

	
}
*/
