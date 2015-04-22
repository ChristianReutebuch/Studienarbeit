package Testumgebung;

import java.awt.Graphics;

public class ZeichneLinie extends ZeichneObjekt{
	int x1,y1,x2,y2;
	
	public ZeichneLinie(int xstart, int ystart, int xende, int yende){
		super(xstart, ystart, xende, yende);
		x1 = xstart;
		y1 = ystart;
		x2 = xstart;
		y2 = ystart;
	}
	
	@Override
	public void zeichnen(Graphics g){
		System.out.println("X1:"+x1+"Y1"+y1+"X2"+x2+"Y2"+y2);
		g.drawLine(x1, y1, x2, y2);
	}
}
