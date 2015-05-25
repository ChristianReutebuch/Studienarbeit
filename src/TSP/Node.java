package TSP;

import java.awt.Color;
import java.awt.Graphics;

public class Node{
	private int xpos;
	private int ypos;
	private String name;
	private int intname;
	public final int RADIUS = 40;
	public boolean isStartnode = false;
	public boolean isSelected = false;
	public boolean isDeleted = false;

	//Konstruktor
	public Node( int xpos, int ypos, boolean isStartnode) {
		this.xpos = xpos;
		this.ypos = ypos;
		this.isStartnode = isStartnode;
	}
	
	public void addNode(int xpos, int ypos, boolean isStartnode){
		this.xpos = xpos;
		this.ypos = ypos;
		this.isStartnode = isStartnode;
	}
	
	public void setName(int number){
		intname = number;
		this.name = Integer.toString(number);
	}
	public String getName(){
		return name;
	}
	
	public int getIntName(){
		return intname;
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
	public void setStartNode(){
		isStartnode = true;
	}
	public void delStartNode(){
		isStartnode = false;
	}
	
	public boolean isStartNode() {
		return isStartnode;
	}
	public int getRadius(){
		return RADIUS;
	}
	public void paintNode(Graphics g){
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
}
