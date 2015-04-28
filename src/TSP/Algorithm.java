package TSP;

import java.util.LinkedList;
import java.util.ListIterator;


public class Algorithm {
	private int numOfNodes, numOfLinks;
	private LinkedList<Node> nodes = new LinkedList<Node>();
	private LinkedList<Link> links = new LinkedList<Link>();
	
	//Konstruktor
	public Algorithm( LinkedList<Node> nodes, LinkedList<Link> links ) {
		//aktuelle Werte zuweisen
		this.nodes = nodes;
		this.links = links;
		numOfNodes = nodes.size();
		numOfLinks = links.size();
		int[][] distances = new int[numOfNodes][numOfNodes];
		
		//aktuelle Entfernumgen in Array eintragen,zur Weiternutzung
		for( int i = 0; i < numOfLinks; ++i ) {
			Link link;
			ListIterator<Link> listIterator = links.listIterator();
	        while (listIterator.hasNext()) {
	        	Node firstNode, secondNode;
				int dist = 0;
	        	link = listIterator.next();
	        	firstNode = link.getFirstNode();
	        	int first = nodes.indexOf(firstNode);
	        	secondNode = link.getSecondNode();
	        	int second = nodes.indexOf(secondNode);
	        	dist = link.getDistance();
	        	int bigger, smaller;
	        	if( first > second ) {
	        		bigger = first;
	        		smaller = second;
	        	} else {
	        		bigger = second;
	        		smaller = first;
	        	}
	        	distances[bigger][smaller] = dist;
	        }
		}
	}
	
	void calculate(){
		
		
	}

}
