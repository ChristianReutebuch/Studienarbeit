package TSP;

import java.util.LinkedList;
import java.util.ListIterator;


public class Algorithm {
	private int numOfNodes, numOfLinks;
	private Node firstNode, secondNode;
	private LinkedList<Node> nodes = new LinkedList<Node>();
	private LinkedList<Link> links = new LinkedList<Link>();
	
	//Konstruktor
	public Algorithm( LinkedList<Node> nodes, LinkedList<Link> links ) {
		//aktuelle Werte zuweisen
		this.nodes = nodes;
		this.links = links;
		numOfNodes = nodes.size();
		numOfLinks = links.size();
		
		for( int i = 0; i < numOfLinks; ++i ) {
			//Array mit Distanzen füllen
			Link link;
			ListIterator<Link> listIterator = links.listIterator();
	        while (listIterator.hasNext()) {
	        	link = listIterator.next();
	        	firstNode = link.getFirstNode();
	        	secondNode = link.getSecondNode();
	        	
	        }

		}
	}
	
	//aktuellen Stand der neighborhoods in eigenes Array schreiben, da mehrmals auf die Werte zugegriffen werden muss
	void calculate(){
		
		
	}

}
