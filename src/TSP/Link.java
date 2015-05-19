package TSP;

import java.awt.Color;
import java.awt.Graphics;

public class Link {
	private Node firstNode;
	private Node secondNode;
	private int distance;
	private float xPosLbl;
	private float yPosLbl;
	
	public Link(Node firstNode, Node secondNode){
		this.firstNode = firstNode;
		this.secondNode = secondNode;
	}
	
	public Node getFirstNode() {
		return firstNode;
	}
	
	public Node getSecondNode() {
		return secondNode;
	}
	
	public void setFirstNode(Node firstNode){
		this.firstNode = firstNode;
	}
	
	public void setSecondNode(Node secondNode){
		this.secondNode = secondNode;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public void setDistance(int distance){
		this.distance = distance;
	}
	
	public void calcPosLbl(){
		int xAbstand = (Math.abs(firstNode.getXPos() - secondNode.getXPos()))/2;
		int yAbstand = (Math.abs(firstNode.getYPos() - secondNode.getYPos()))/2;
		if ( firstNode.getXPos() <= secondNode.getXPos()){
			xPosLbl = firstNode.getXPos() + xAbstand;
		}
		else{
			xPosLbl = secondNode.getXPos() + xAbstand;
		}
		if ( firstNode.getYPos() <= secondNode.getYPos()){
			yPosLbl = firstNode.getYPos() + yAbstand;
		}
		else{
			yPosLbl = secondNode.getYPos() + yAbstand;
		}
	}
	public void paintLink(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine(firstNode.getXPos()+20, firstNode.getYPos()+20, secondNode.getXPos()+20, secondNode.getYPos()+20);
		String text = Integer.toString(distance);
		calcPosLbl();
		g.drawString(text, (int)xPosLbl, (int)yPosLbl);
	}
}
