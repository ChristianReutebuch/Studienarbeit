package TSP;

public class Graph {
	private int numberOfNodes = 0;
	private int[][] neighborhoods;
	private int startNode = 0;
	
	void setNumberOfNodes( int number ){
		neighborhoods = new int[number][number];
	}
	
	int getNumberOfNodes(){
		return numberOfNodes;
	}
	
	void setNeighborhood( int firstNode, int secondNode, int dist ){
		if( firstNode < secondNode ) {
			neighborhoods[firstNode][secondNode] = dist;
		} else {
			neighborhoods[secondNode][firstNode] = dist;
		}				
	}
		
	int getNeighborhood( int firstNode, int secondNode ){
		return neighborhoods[firstNode][secondNode];
	}
	
	void setStartnode( int start ){
		startNode = start;
	}
	
	int getStartNode(){
		return startNode;
	}


}
