package TSP;

import java.awt.Graphics;

public class Node{
	private int posX;
	private int posY;
	private int radius;
	private boolean isStartnode = false;
	private boolean isEndnode = false;
	
	//Konstruktor für "normale" Knoten
	public Node( int x, int y, int r) {
		posX = x;
		posY = y;
		radius = r;		
	}
	//Konstruktor für Start- und Endknoten
	public Node( int x, int y, int r, boolean start, boolean end ) {
		posX = x;
		posY = y;
		radius = r;
		isStartnode = start;
		isEndnode = end;
	}
	
	public void addNode(int xpos, int ypos, int radius){
		posX = xpos;
		posY = ypos;
	}
	
	public void paintNode(Graphics g){
		g.fillOval(posX, posY, radius, radius);
	}
	
	int getPosX() {
		return posX;
	}
	void setPosX( int x ){
		posX = x;
	}
	
	int getPosY(){
		return posY;
	}
	void setPosY( int y ){
		posY = y;
	}
	
	int getRadius(){
		return radius;
	}
	void setRadius( int r ){
		radius = r;
	}
	
	boolean isStartnode(){
		return isStartnode;
	}
	void setStartnode(){
		isStartnode = true;
	}
	
	boolean isEndnode(){
		return isEndnode;
	}
	void setEndnode(){
		isEndnode = true;
	}

}
