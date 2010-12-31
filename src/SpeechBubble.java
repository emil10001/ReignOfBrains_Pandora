import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SpeechBubble extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String text = "Holy shit kill all the zombies";
	
	public void setText(String newText)
	{
		text = newText;
	}

    @Override public void paintComponent(Graphics g) {
        super.paintComponent(g); 
	Graphics2D g2d = (Graphics2D)g;

    g2d.setColor(Color.black);
    RoundRectangle2D r1 = new RoundRectangle2D.Float(3, 3, 304, 54, 40, 40);
    g2d.fill(r1);
    
    Polygon tri = new Polygon();
    tri.addPoint(307, 24);
    tri.addPoint(322, 18);
    tri.addPoint(307, 40);
    
    g.fillPolygon(tri);
	
    g2d.setColor(Color.white);
    RoundRectangle2D r2 = new RoundRectangle2D.Float(5, 5, 300, 50, 40, 40);
    g2d.fill(r2);

    Polygon triangle = new Polygon();
    triangle.addPoint(305, 24);
    triangle.addPoint(320, 18);
    triangle.addPoint(305, 38);
    
    g.fillPolygon(triangle);
    
    JLabel theText = new JLabel();
    theText.setPreferredSize(new Dimension(280,44));
    
    g2d.setColor(Color.black);    
    g.drawString(text, 15, 25);
    
    
    
}
}