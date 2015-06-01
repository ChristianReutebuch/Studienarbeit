//Studienarbeit "Visualisierung graphentheoretischer Algorithmen"
//Christian Reutebuch, Silke Hildebrand
//28.10.2014 - 


package TSP;

import java.util.ArrayList;

//TODO
//- kleinste Wege rausfinden--> inArbeit: derzeit wird nur ein Weg gefunden.
//- in der GUI-Klasse überprüfen, ob ein Startknoten gesetzt ist

public class Algorithm {
	//private ArrayList<Integer> bestRoute;
	int numOfNodes = GUI.nodes.size();
	int numOfLinks = GUI.links.size();
	int[][] distances = new int[ numOfNodes + 1 ][ numOfNodes + 1 ];
	int numOfWays = 0;

	ArrayList<Integer> listOfRoutes = new ArrayList<Integer>();
	
	//routes: ist zu Beginn leer und enthält am Ende alle möglichen Pfade, ohne Start- bzw. Endknoten
	//nodesNotInRoute: enthält zu Beginn alle Knoten und wird jeweils um den Knoten reduziert, der bereits eingefügt wurde
	public ArrayList<Integer> bruteForce(ArrayList<Integer> routes, ArrayList<Integer> nodesNotInRoute){
		//prüfen, ob die übergebene Knotenliste Knoten enthält
		if(!nodesNotInRoute.isEmpty()){
			//Schleife über noch nicht in den aktuellen Pfad eingefügte Knoten
			for(int i = 0; i < nodesNotInRoute.size(); i++){
				int justRemoved = nodesNotInRoute.remove(0);
				//übergebene Liste kopieren
				ArrayList<Integer> newRoute = (ArrayList<Integer>) routes.clone();
				//erstes, zuvor gelöschtes Element hinten anhängen
				newRoute.add(justRemoved);
				bruteForce(newRoute, nodesNotInRoute);
				nodesNotInRoute.add(justRemoved);
			}
		}else{
			System.out.println("routes" + routes.toString());
			for(int i = 0; i < routes.size();i++) {
				listOfRoutes.add(routes.get(i));
			}
			//setDistances();
			//int [][] ways = setPaths( routes );
			//ways = calcCosts( ways );
			//int shortestPath = findShortestPath(ways);
			//ausgabe( routes, ways, 0 );
		}
		return listOfRoutes;
	}
	
	public int[][] ausprobieren( ArrayList<Integer> listOfRoutes ){
		//Array erstellen
		numOfWays = calcNumOfWays();
		int[][] ways = new int[ numOfNodes ][ numOfWays ];
				
		//eintragen der möglichen Wege
		int counter = 0;
		for( int j = 0; j < numOfWays; j++ ) {
			for( int i = 0; i < ( numOfNodes - 1 ); i++) {
				if( counter < listOfRoutes.size()) {
					ways[i][j] = listOfRoutes.get(counter);
					counter++;
				}
			}
			//Statt Kosten in letzter Spalte 0 eintragen
			ways[ numOfNodes - 1 ][ j ] = 88;
		}
		System.out.println( "Routes probieren: \n" + listOfRoutes.toString() );
		System.out.println( "Kosten probieren: " );
		System.out.println("Wege" + numOfWays);
		for ( int j = 0; j < numOfWays; j++ ) {
			String helpi = "";
			for ( int i = 0; i < numOfNodes; i++){
				Integer helpiI = ways[i][j];
				helpi += helpiI.toString();
			}
			System.out.println( helpi );
		}
		return ways;
	}
	
	//Klasse befüllt ein zweidimensionales Array, welches die Entfernungen zwischen den Knoten enthält
	//getestet
	public void setDistances() {
		for( int i = 0; i < numOfLinks; ++i) {
			Link link = GUI.links.get(i);
			int first = link.getFirstNode().getIntName() - 1;
			int second = link.getSecondNode().getIntName() - 1;
			int dist = link.getDistance();
			distances[first][second] = dist;
			distances[second][first] = dist;
		}
	}
	
	//Berechnung der Anzahl der möglichen Wege
	public int calcNumOfWays(){
		numOfWays = 1;
		for( int i = 1; i < numOfNodes; i++ ) {
			numOfWays *= i;
		}
		numOfWays /= 2;
		return numOfWays;
	}
	
	//routes enthält alle möglichen Wege, wobei der Startknoten(und Endknoten) nicht enthalten ist
	public int[][] setPaths(ArrayList<Integer> routes) {
		//Array erstellen
		int[][] ways = new int[ numOfNodes + 1 ][ numOfWays - 1];
		
		//eintragen der möglichen Wege
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
		System.out.println( "Routes: \n" + routes.toString() );
		System.out.println( "Kosten setPath: " );
		for ( int j = 0; j < numOfWays; ++j ) {
			String helpi = "";
			for ( int i = 0; i < numOfNodes; ++i){
				Integer helpiI = ways[i][j];
				helpi += helpiI.toString();
			}
			System.out.println( helpi );
		}
		return ways;
	}
	
	//Berechnung der Kosten der einzelnen Wege
	public int[][] calcCosts( int[][] ways ){
		int leftNode = 0;
		int rightNode = 0;
		int dist = 0;
		int distSum = 0;
		System.out.println( "Kosten calcCosts: " );
		for ( int j = 0; j < numOfWays; ++j ) {
			String helpi = "";
			for ( int i = 0; i < numOfNodes; ++i){
				Integer helpiI = ways[i][j];
				helpi += helpiI.toString();
			}
			System.out.println( helpi );
		}
		for ( int j = 0; j < numOfWays; ++j ) {
			//Entfernung vom Startknoten weg und zum Startknoten mit einbeziehen
			int startNode = GUI.startNode;
			int firstNode = ways[ 0 ][ j ];
			int lastNode = ways[ numOfNodes - 1 ][ j ];
			distSum += distances[ startNode ][ firstNode ];
			distSum += distances[ startNode ][ lastNode ];
			
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
	public int findShortestPath( int[][] ways ) {
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
//		System.out.println( "Routen: \n" + routes.toString() );
//		
//		//Kosten der Verbindungen
//		System.out.println( "Entfernungen: \n" );
//		for( int i = 0; i < numOfNodes; ++i){
//			for( int j = 0; j < numOfNodes; ++j){
//				System.out.println("[" + ( i + 1 ) +"][" + ( j + 1 ) + "] = " + distances[i][j]);
//			}
//		}
//		
//		//mögliche Wege
//		System.out.println( "mögliche Wege:" );
//		String way = "";
//		for (int i = 0; i < numOfNodes; ++i ){
//			for (int j = 0; j < numOfWays; ++j ){
//				Integer wayI = ways[i][j];
//				way += wayI.toString();
//			}
//			System.out.println( way );
//			way = "";
//		}
		
		
		//einer der kürzesten Wege
		//System.out.println( "ShortestPath: " + shortestPath );
		
	}

}
