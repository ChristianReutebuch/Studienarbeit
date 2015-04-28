package TSP;

import java.util.LinkedList;


public class Algorithm {
	//private int numberOfNodes = nodesList.lenght();
	int numberOfNodes = 0;
	//int numberOfNeighborhoods = neighborhoodList.legth();
	int numberOfNeigborhoods = 0;
	private int[][] neighborhoods = new int[numberOfNodes][numberOfNodes];
	
	//Konstruktor
	public Algorithm( LinkedList<Node> nodesList, LinkedList<Neighborhood> neighborhoodList ) {
		//aktuelle Werte zuweisen
		numberOfNodes = nodesList.size();
		numberOfNeigborhoods = neighborhoodList.size();
		
		for( int i = 0; i < numberOfNeigborhoods; ++i ) {
			//Array mit Distanzen füllen
		}
	}
	
	//aktuellen Stand der neighborhoods in eigenes Array schreiben, da mehrmals auf die Werte zugegriffen werden muss
	void calculate(){
		
		
	}

}
