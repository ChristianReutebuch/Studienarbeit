package TSP;

import java.util.LinkedList;
import java.util.ListIterator;


public class Algorithm {
	private int numOfNodes, numOfLinks;
	private LinkedList<Node> nodes = new LinkedList<Node>();
	private LinkedList<Link> links = new LinkedList<Link>();
	private int startNode = 0;
	
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
	        	link = listIterator.next();
	        	int first = nodes.indexOf(link.getFirstNode());
	        	int second = nodes.indexOf(link.getSecondNode());
	        	int dist = link.getDistance();
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
		Node node;
		ListIterator<Node> listIterator = nodes.listIterator();
		while (listIterator.hasNext()) {
			node = listIterator.next();
			if( node.isStartNode() ) {
				startNode = nodes.indexOf(node);
				break;
			}
        }
	}
	
	void calculate(){
		//Sonderfallbehandlung:
		//3 Knoten: nur eine Möglichkeit ( 1231 = 1321 )
		
		//Anzahl der Möglichkeiten
		int numberOfWays = numOfNodes-1;
		for (int i=1; i<=numOfNodes; i++){
			numberOfWays = numberOfWays * i;
        }
		numberOfWays = numberOfWays / 2;
		
		//Array für Reisemöglichkeiten
		//Startknoten wird nicht aufgeschrieben, sondern lediglich die Reihenfolge der anderen Knoten
		//letzte Spalte enthält die Kosten des gesamten Wegs
		int[][] ways = new int[numOfNodes][numberOfWays];
		//
		int row = 0;
		//erste Möglichkeit
		for( int n = 1; n < numOfNodes; ++n) {
			if( n != startNode) {
				ways[n][row] = n;
				++row;
			}
		}
		//Möglichkeiten
		//Zeilenweise über die Spalten schleifen
		for(int rowCount = row; rowCount < numberOfWays; ++rowCount) {
			
			for( int n = 1; n < ( numOfNodes - 1 ); ++n ) {
				//letzte zwei Knoten wechseln immer ab
				//if( n > ( numOfNodes - 2 ) ) {
				//	ways[n][row] = ways[n+1][row-n];
				//	ways[n+1][row+1] = ways[n][row-n];
				//}			
			}
		}
	}

}
