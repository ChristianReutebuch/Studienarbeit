//Studienarbeit "Visualisierung graphentheoretischer Algorithmen"
//Christian Reutebuch, Silke Hildebrand
//28.10.2014 - 10.07.2014

package TSP;

import java.awt.Color;
import java.awt.Graphics;

public class Link {
	private Node firstNode;
	private Node secondNode;
	private int distance;
	private float xPosLbl;
	private float yPosLbl;
	private boolean isMarked = false;
	
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
		int xAbstand = (Math.abs((firstNode.getXPos()+firstNode.RADIUS/2) - (secondNode.getXPos()+secondNode.RADIUS/2) ))/2;
		int yAbstand = (Math.abs((firstNode.getYPos()+firstNode.RADIUS/2) - (secondNode.getYPos()+secondNode.RADIUS/2) ))/2;
		if ( (firstNode.getXPos()+ firstNode.RADIUS/2) <= (secondNode.getXPos()+secondNode.RADIUS/2)){
			xPosLbl = (firstNode.getXPos() + firstNode.RADIUS/2) + xAbstand;
		}
		else{
			xPosLbl = (secondNode.getXPos()+secondNode.RADIUS/2) + xAbstand;
		}
		if ( (firstNode.getYPos() + firstNode.RADIUS/2) <= (secondNode.getYPos()+secondNode.RADIUS/2)){
			yPosLbl = (firstNode.getYPos() + firstNode.RADIUS/2) + yAbstand;
		}
		else{
			yPosLbl = (secondNode.getYPos()+secondNode.RADIUS/2) + yAbstand;
		}
	}
	
	public void setMarkStatus(boolean status){
		isMarked = status;
	}
	
	public boolean getMarkStatus(){
		return isMarked;
	}
	
	public void paintLink(Graphics g) {
		if(isMarked == true){
			g.setColor(Color.GREEN);
		}
		if(isMarked == false){
			g.setColor(Color.BLACK);
		}
		g.drawLine(firstNode.getXPos()+ firstNode.RADIUS/2, firstNode.getYPos()+ firstNode.RADIUS/2, secondNode.getXPos()+ secondNode.RADIUS/2, secondNode.getYPos()+secondNode.RADIUS/2);
		String text = Integer.toString(distance);
		calcPosLbl();
		g.drawString(text, (int)xPosLbl, (int)yPosLbl);
	}
}
