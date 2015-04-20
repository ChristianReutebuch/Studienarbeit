package TSP;

import java.awt.Color;
import java.awt.Graphics;

public class PaintLine extends Paint{
	int x1 = 100;
	int y1 = 100;
	int x2 = 200;
	int y2 = 200;
	
	public PaintLine() {
		super();
	}
	public void paintComponent(Graphics g){
		Color customColor = new Color(10,10,255); 
        g.setColor(customColor);
        g.drawLine(x1, y1, x2, y2);
	}
}
