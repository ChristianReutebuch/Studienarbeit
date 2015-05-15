package TSP;

import java.awt.Color;
import java.awt.Graphics;

public class Node{
	private int xpos;
	private int ypos;
	private String name;
	public final int RADIUS = 40;
	private boolean isStartnode = false;
	public boolean isSelected = false;
	public boolean isDeleted = false;

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
		String text = "";
		if(isStartnode == true){
			g.setColor(Color.BLUE);
		}
		if(isStartnode == false){
			g.setColor(Color.BLACK);
		}
		if(isSelected == true){
			g.setColor(Color.GREEN);
		}
		g.fillOval(xpos, ypos, RADIUS, RADIUS);
		g.setColor(Color.WHITE);
		g.drawString(name, xpos + RADIUS/2, ypos + RADIUS/2);
	}
	
	public void setName(int number){
		this.name = Integer.toString(number);
	}
	public String getName(){
		return name;
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
