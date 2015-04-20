package TSP;

import java.awt.Color;
import java.awt.Graphics;

public class PaintCircle extends Paint {
	int x = 100;
	int y = 100;
	int r = 40;
	
	public PaintCircle() {
		super();
	}
	public void paintComponent(Graphics g){
		Color customColor = new Color(10,10,255); 
        g.setColor(customColor);
        g.drawOval(x, y, r, r);
	}
}
