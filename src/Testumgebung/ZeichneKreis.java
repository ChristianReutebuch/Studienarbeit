package Testumgebung;

import java.awt.Graphics;

public class ZeichneKreis extends ZeichneObjekt{
	int xstart, ystart, radius;
	public ZeichneKreis(int xstart, int ystart, int xende, int yende){
		super(xstart, ystart, xende, yende);
		this.xstart = xstart;
		this.ystart = ystart;
		this.radius = 40;
	}
	public void zeichnen(Graphics g){
		//zeichne Kreis;
		g.drawOval(xstart, ystart, radius, radius);
	}
}
