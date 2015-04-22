package TSP;

//import Node;

public class Neighborhood {
	
	private int distance = 0;
	private Node firstNode;
	private Node secondNode;
	
	int getDistance(){
		return distance;
	}
	void setDistance( int dist ){
		distance = dist;
	}
	
	Node getFirstNode(){
		return firstNode;
	}
	void getFirstNode( Node node ){
		firstNode = node;
	}
	
	Node getSecondNode(){
		return secondNode;
	}
	void getSecondNode( Node node ){
		secondNode = node;
	}


}
