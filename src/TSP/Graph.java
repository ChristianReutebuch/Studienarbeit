package TSP;

import java.util.LinkedList;

public class Graph {
	
	private int numberOfNodes = 0;
	LinkedList<Node> nodesList;
	LinkedList<Neighborhood> neighborhoodList;
	
	
	
	void setNumberOfNodes( int number ) {
		numberOfNodes = number;
	}
	
	int getNumberOfNodes(){
		return numberOfNodes;
	}
	
	
	boolean insertNode( Node node ) {
		if( nodesList.add( node ) ) {
			return true;
		}
		return false;
	}
	boolean deleteNode( Node node ) {
		if ( nodesList.remove( node ) ) {
			return true;
		}
		return false;
	}
	
	boolean insertNeighborhood( Neighborhood neighbor ) {
		if( neighborhoodList.add( neighbor ) ) {
			return true;
		}
		return false;
	}
	boolean deleteNeighborhood( Neighborhood neighbor ) {
		if ( nodesList.remove( neighbor ) ) {
			return true;
		}
		return false;
	}
	


}
