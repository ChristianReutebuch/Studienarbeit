package TSP;

import java.awt.Color;
import java.awt.Graphics;

public class Node{
	private int xpos;
	private int ypos;
	public final int RADIUS = 40;
	private boolean isStartnode = false;
	public boolean isSelected = false;

	//Konstruktor
	public Node( int xpos, int ypos, boolean start) {
		this.xpos = xpos;
		this.ypos = ypos;
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
			g.fillOval(xpos, ypos, RADIUS, RADIUS);
		}
		if(isStartnode == false){
			g.setColor(Color.BLACK);
			g.fillOval(xpos, ypos, RADIUS, RADIUS);
		}
		if(isSelected == true){
			g.setColor(Color.GREEN);
			g.fillOval(xpos, ypos, RADIUS, RADIUS);
		}
		if(isSelected == false){
			g.setColor(Color.BLACK);
			g.fillOval(xpos, ypos, RADIUS, RADIUS);
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
	public boolean isStartNode() {
		return isStartnode;
	}
	
}
