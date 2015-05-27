package TSP;

import java.util.ArrayList;

//TODO
//- Distances-Array füllen-->DONE
//- Startknoten bei Brute-Force rausnehmen-->DONE
//- errechnete Pfade in Wege-Array eintragen-->DONE
//- Gesamtkosten ausrechnen und in Wege-Array eintragen-->DONE
//- kleinste Wege rausfinden

public class Algorithm {
	//private ArrayList<Integer> bestRoute;
	int numOfNodes = GUI.nodes.size();
	int numOfLinks = GUI.links.size();
	int[][] distances = new int[ numOfNodes ][ numOfNodes ];
	
	//routes: ist zu Beginn leer und enthält am Ende alle möglichen Pfade
	//nodesNotInRoute: enthält zu Beginn alle Knoten und wird jeweils um den Knoten reduziert, der bereits eingefügt wurde
	public void bruteForce(ArrayList<Integer> routes, ArrayList<Integer> nodesNotInRoute){
		//prüfen, ob die übergebene Knotenliste Knoten enthält
		if(!nodesNotInRoute.isEmpty()){
			//Schleife über noch nicht in den aktuellen Pfad eingefügte Knoten
			for(int i = 0; i < nodesNotInRoute.size(); ++i){
				int justRemoved = nodesNotInRoute.remove(0);
				//übergebene Liste kopieren
				ArrayList<Integer> newRoute = (ArrayList<Integer>) routes.clone();
				//erstes, zuvor gelöschtes Element hinten anhängen
				newRoute.add(justRemoved);
				bruteForce(newRoute, nodesNotInRoute);
				nodesNotInRoute.add(justRemoved);
			}
		}else{
			showRoutes( routes );
			setPaths( routes );
		}
	}
	
	
	private boolean showRoutes(ArrayList<Integer> route){
		System.out.println(route.toString());
		return false;
	}
	
	private void setDistances() {
		for( int i = 0; i < numOfLinks; ++i) {
			Link link = GUI.links.get(i);
			int first = link.getFirstNode().getIntName();
			int second = link.getSecondNode().getIntName();
			int dist = link.getDistance();
			distances[first][second] = dist;
			distances[second][first] = dist;
		}
		
	}
	
	//routes enthält alle möglichen Wege, wobei der Startknoten(und Endknoten) nicht enthalten ist
	private int[][] setPaths(ArrayList<Integer> routes) {
		//distances-Array wird befüllt
		setDistances();
		
		//Anzahl der Wege wird zur Array-Erstellung errechnet
		int numOfWays = 1;
		for( int i = 1; i < numOfNodes; i++ ) {
			numOfWays *= i;
		}
		int[][] ways = new int[ numOfNodes ][ numOfWays ];
		//eintragen der möglichen Wege
		int counter = 0;
		for( int i = 0; i < ( numOfNodes - 1 ); ++i ) {
			for( int j = 0; j < numOfWays; ++j) {
				if( counter < routes.size()) {
					ways[i][j] = routes.get(counter);
					++counter;
				}
			}
		}
		return calcCosts( ways );
	}
	
	//Berechnung der Kosten der einzelnen Wege
	private int[][] calcCosts( int[][] ways ){
		int leftNode = 0;
		int rightNode = 0;
		int dist = 0;
		int distSum = 0;
		for ( int j = 0; j < ( ways.length - 1 ); ++j ) {
			for ( int i = 0; i < ( ways.length - 1 ); ++i ) {
				leftNode = ways[ i ][ j ];
				rightNode = ways[ i + 1 ][ j ];
				dist = distances[leftNode][rightNode];
				distSum += dist;
			}
			ways[ ways.length - 1 ][ j ] = distSum;
			distSum = 0;
		}
		
		return ways;
	}

}
