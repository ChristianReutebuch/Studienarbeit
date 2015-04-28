package TSP;

//import Node;


public class Neighborhood {
	
	//Eigenschaften einer Nachbarschaft
	//Nachbarschaft besteht aus zwei Knoten und deren Entfernung
	private int distance = 0;
	private Node firstNode;
	private Node secondNode;
	
	//Konstruktor
	public Neighborhood( Node first, Node second, int dist ) {
		distance = dist;
		firstNode = first;
		secondNode = second;
	}

	//gibt die Entfernung der beiden Knoten an, die an dieser Nachbarschaft beteiligt sind
	int getDistance(){
		return distance;
	}
	//setzt die Entfernung der beiden Knoten, die an dieser Nachbarschaft beteiligt sind
	void setDistance( int dist ){
		distance = dist;
	}
	
	//gibt einen der beiden Knoten zurück, die an dieser Nachbarschaft beteiligt sind
	Node getFirstNode(){
		return firstNode;
	}
	//setzt einen der beiden Knoten, die an dieser Nachbarschaft beteiligt sind
	void getFirstNode( Node node ){
		firstNode = node;
	}
	
	//gibt einen der beiden Knoten zurück, die an dieser Nachbarschaft beteiligt sind
	Node getSecondNode(){
		return secondNode;
	}
	//setzt einen der beiden Knoten, die an dieser Nachbarschaft beteiligt sind
	void getSecondNode( Node node ){
		secondNode = node;
	}


}
