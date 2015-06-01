package TSP;

import java.util.ArrayList;

//TODO
//- Distances-Array f�llen-->DONE
//- Startknoten bei Brute-Force rausnehmen-->DONE
//- errechnete Pfade in Wege-Array eintragen-->DONE
//- Gesamtkosten ausrechnen und in Wege-Array eintragen-->DONE
//- kleinste Wege rausfinden--> inArbeit: derzeit wird nur ein Weg gefunden.

public class Algorithm {
	//private ArrayList<Integer> bestRoute;
	int numOfNodes = GUI.nodes.size();
	int numOfLinks = GUI.links.size();
	int[][] distances = new int[ numOfNodes + 1 ][ numOfNodes + 1 ];
	int numOfWays = 0;
	
	//routes: ist zu Beginn leer und enth�lt am Ende alle m�glichen Pfade, ohne Start- bzw. Endknoten
	//nodesNotInRoute: enth�lt zu Beginn alle Knoten und wird jeweils um den Knoten reduziert, der bereits eingef�gt wurde
	public void bruteForce(ArrayList<Integer> routes, ArrayList<Integer> nodesNotInRoute){
		//pr�fen, ob die �bergebene Knotenliste Knoten enth�lt
		if(!nodesNotInRoute.isEmpty()){
			//Schleife �ber noch nicht in den aktuellen Pfad eingef�gte Knoten
			for(int i = 0; i < nodesNotInRoute.size(); ++i){
				int justRemoved = nodesNotInRoute.remove(0);
				//�bergebene Liste kopieren
				ArrayList<Integer> newRoute = (ArrayList<Integer>) routes.clone();
				//erstes, zuvor gel�schtes Element hinten anh�ngen
				newRoute.add(justRemoved);
				bruteForce(newRoute, nodesNotInRoute);
				nodesNotInRoute.add(justRemoved);
			}
		}else{
			//showRoutes( routes );
			setDistances();
			int [][] ways = setPaths( routes );
			//ways = calcCosts( ways );
			//int shortestPath = findShortestPath(ways);
			ausgabe( routes, ways, 0 );
		}
	}
	
	//Testklasse
	private boolean showRoutes( ArrayList<Integer> route ){
		String routeStr = route.toString();
		System.out.println( routeStr );
		return false;
	}
	
	//Klasse bef�llt ein zweidimensionales Array, welches die Entfernungen zwischen den Knoten enth�lt
	//getestet
	private void setDistances() {
		for( int i = 0; i < numOfLinks; ++i) {
			Link link = GUI.links.get(i);
			int first = link.getFirstNode().getIntName() - 1;
			int second = link.getSecondNode().getIntName() - 1;
			int dist = link.getDistance();
			distances[first][second] = dist;
			distances[second][first] = dist;
		}
	}
	
	//routes enth�lt alle m�glichen Wege, wobei der Startknoten(und Endknoten) nicht enthalten ist
	//getestet
	//TODO: in der GUI-Klasse �berpr�fen, ob ein Startknoten gesetzt ist
	private int[][] setPaths(ArrayList<Integer> routes) {
		
		//Anzahl der Wege wird zur Array-Erstellung errechnet
		numOfWays = 1;
		for( int i = 1; i < numOfNodes; i++ ) {
			numOfWays *= i;
		}
		//Array erstellen
		int[][] ways = new int[ numOfNodes + 1 ][ numOfWays - 1];
		
		//eintragen der m�glichen Wege
		int counter = 0;
		for( int j = 0; j < ( numOfWays - 1 ); ++j ) {
			for( int i = 0; i < ( numOfNodes - 1 ); ++i) {
				if( counter < routes.size()) {
					ways[i][j] = routes.get(counter);
					++counter;
				}
			}
			//Statt Kosten in letzter Spalte 0 eintragen
			ways[ numOfNodes ][ j ] = 0;
		}
		return ways;
	}
	
	//Berechnung der Kosten der einzelnen Wege
	private int[][] calcCosts( int[][] ways ){
		int leftNode = 0;
		int rightNode = 0;
		int dist = 0;
		int distSum = 0;
		for ( int j = 0; j < numOfWays; ++j ) {
			//TODO:Entfernung zu Startknoten hinzuf�gen
			for ( int i = 0; i < ( numOfNodes - 1 ); ++i ) {
				leftNode = ways[ i ][ j ]; //ArrayIndexOutOfBoundsException
				rightNode = ways[ i + 1 ][ j ];
				dist = distances[leftNode][rightNode];
				distSum += dist;
			}
			ways[ numOfNodes ][ j ] = distSum;
			distSum = 0;
		}
		
		return ways;
	}
	
	//in Arbeit
	//TODO: mehrere gleich lange Pfade!!!
	private int findShortestPath( int[][] ways ) {
		int shortestPath = 0;		
		int path = 0;
		for( int j = 0; j < numOfWays; ++j ){
			path = ways[ numOfNodes ][ j ];
		}
		return shortestPath;
	}
	
	//Testklasse
	private void ausgabe( ArrayList<Integer> routes, int[][] ways, int shortestPath){
		//Routen
		//System.out.println( "Routen: \n" + routes.toString() );
		
		//Kosten der Verbindungen
//		System.out.println( "Entfernungen: \n" );
//		for( int i = 0; i < numOfNodes; ++i){
//			for( int j = 0; j < numOfNodes; ++j){
//				System.out.println("[" + ( i + 1 ) +"][" + ( j + 1 ) + "] = " + distances[i][j]);
//			}
//		}
		
		//m�gliche Wege
//		System.out.println( "m�gliche Wege:" );
//		String way = "";
//		for (int i = 0; i < numOfNodes; ++i ){
//			for (int j = 0; j < numOfWays; ++j ){
//				Integer wayI = ways[i][j];
//				way += wayI.toString();
//			}
//			System.out.println( way );
//			way = "";
//		}
		
		
		//einer der k�rzesten Wege
		//System.out.println( "ShortestPath: " + shortestPath );
		
	}

}
