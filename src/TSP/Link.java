package TSP;

import java.awt.Color;
import java.awt.Graphics;

public class Link {
	private Node firstNode;
	private Node secondNode;
	private int distance;
	
	public Link(Node firstNode, Node secondNode, int distance){
		this.firstNode = firstNode;
		this.secondNode = secondNode;
		this.distance = distance;
	}
	
	public void paintLink(Graphics g){
		g.setColor(Color.BLACK);
		g.drawLine(firstNode.getXPos()+20, firstNode.getYPos()+20, secondNode.getXPos()+20, secondNode.getYPos()+20);
	}
	
	public Node getFirstNode(){
		return firstNode;
	}
	
	public Node getSecondNode(){
		return secondNode;
	}
}
