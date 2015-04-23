package TSP;

import java.awt.Color;
import java.awt.Graphics;

public class Link {
	private int startx, starty, endx, endy;
	
	public Link(int startx, int starty, int endx, int endy){
		this.startx = startx;
		this.starty = starty;
		this.endx = endx;
		this.endy = endy;
	}
	
	public void paintLink(Graphics g){
		g.setColor(Color.BLACK);
		g.drawLine(startx, starty, endx, endy);
	}
}
