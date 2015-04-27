package TSP;

import java.awt.Color;
import java.awt.Graphics;

public class Node{
	private int xpos;
	private int ypos;
	private int radius;
	private boolean isStartnode = false;

	//Konstruktor
	public Node( int xpos, int ypos, int radius, boolean start) {
		this.xpos = xpos;
		this.ypos = ypos;
		this.radius = radius;
		isStartnode = start;
	}
	
	public void addNode(int xpos, int ypos, boolean start){
		this.xpos = xpos;
		this.ypos = ypos;
		isStartnode = start;
	}
	
	public void paintNode(Graphics g){
		if(isStartnode == true){
			g.setColor(Color.BLUE);
			g.fillOval(xpos, ypos, radius, radius);
		}
		if(isStartnode == false){
			g.setColor(Color.BLACK);
			g.fillOval(xpos, ypos, radius, radius);
		}
	}
	public int getXPos(){
		return xpos;
	}
	public int getYPos(){
		return ypos;
	}
	public void setXPos(int xpos){
		this.xpos = xpos;
	}
	public void setYPos(int ypos){
		this.ypos = ypos;
	}
	
}
