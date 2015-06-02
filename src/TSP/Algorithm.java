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
	static final int MAXINT = Integer.MAX_VALUE;
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
			//Testausgabe
			//System.out.println("route " + routes.toString());
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
	
	//erstellt aus der ArrayList, die alle Routen beinhaltet, ein 2D-Array
	//In die letzte Spalte werden später die Kosten dieser Route eingetragen
	//getestet
	public int[][] buildRoutes( ArrayList<Integer> listOfRoutes ){
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
			ways[ numOfNodes - 1 ][ j ] = 0;
		}
		//Testausgabe
//		System.out.println( "buildRoutes/Routeslist: \n" + listOfRoutes.toString() );
//		System.out.println( "buildRoutes/Routesarray: " );
//		System.out.println("#Wege: " + numOfWays);
//		for ( int j = 0; j < numOfWays; j++ ) {
//			String helpStr = "";
//			for ( int i = 0; i < numOfNodes; i++){
//				Integer helpI = ways[i][j];
//				helpStr += helpI.toString();
//				helpStr += " ";
//			}
//			System.out.println( helpStr );
//		}
		return ways;
	}
	
	//Klasse befüllt ein zweidimensionales Array, welches die Entfernungen zwischen den Knoten enthält
	//getestet
	public void setDistances() {
//		for( int i = 0; i < numOfLinks; ++i) {
//			Link link = GUI.links.get(i);
//			int first = link.getFirstNode().getIntName() - 1;
//			int second = link.getSecondNode().getIntName() - 1;
//			int dist = link.getDistance();
//			distances[first][second] = dist;
//			distances[second][first] = dist;
//		}
		
		for( int i = 0; i < numOfLinks; ++i) {
			Link link = GUI.links.get(i);
			Node firstNode = link.getFirstNode();
			int first = GUI.nodes.indexOf( firstNode );
			Node secondNode = link.getSecondNode();
			int second = GUI.nodes.indexOf( secondNode );
			int dist = link.getDistance();
			distances[first][second] = dist;
			distances[second][first] = dist;
		}
		
		//Testausgabe
//		System.out.println("setDistances/Array:");
//		for ( int j = 0; j < numOfNodes; j++ ) {
//			String helpStr = "";
//			for ( int i = 0; i < numOfNodes; i++){
//				Integer helpI = distances[i][j];
//				helpStr += helpI.toString();
//				helpStr += " ";
//			}
//			System.out.println( helpStr );
//		}
	}
	
	//Berechnung der Anzahl der möglichen Wege
	//getestet
	public int calcNumOfWays(){
		numOfWays = 1;
		for( int i = 1; i < numOfNodes; i++ ) {
			numOfWays *= i;
		}
		numOfWays /= 2;
		return numOfWays;
	}
	
	//Berechnung der Kosten der einzelnen Wege
	public int[][] calcCosts( int[][] ways ){
		int leftNode = 0;
		int rightNode = 0;
		int dist = 0;
		int distSum = 0;

		for ( int j = 0; j < numOfWays; ++j ) {
			//Entfernung vom Startknoten weg und zum Startknoten mit einbeziehen
			int startNode = GUI.startNode;
			int firstNode = ways[ 0 ][ j ];
			int lastNode = ways[ numOfNodes - 2 ][ j ];
			distSum += distances[ startNode ][ firstNode ];
			distSum += distances[ startNode ][ lastNode ];
			//Entfernungen der Knoten im Wege-Array dazurechnen
			for ( int i = 0; i < ( numOfNodes - 2 ); ++i ) {
				leftNode = ways[ i ][ j ]; 
				rightNode = ways[ i + 1 ][ j ];
				dist = distances[leftNode][rightNode];
				distSum += dist;
			}
			ways[ numOfNodes - 1 ][ j ] = distSum;
			distSum = 0;
		}
//		//Testausgabe
//		System.out.println("calcCosts/Array:");
//		for ( int j = 0; j < numOfWays; j++ ) {
//			String helpStr = "";
//			for ( int i = 0; i < ( numOfNodes - 1); i++){
//				Integer helpI = ways[i][j] + 1;
//				helpStr += helpI.toString();
//				helpStr += " ";
//			}
//			Integer helpI = ways[ numOfNodes - 1 ][ j ];
//			helpStr += helpI.toString();
//			System.out.println( helpStr );
//		}
		
		return ways;
	}
	
	//in Arbeit
	public void findShortestPath( int[][] ways ) {
		int pathCounter = 0;
		int costs = MAXINT;
		
		//geringste Kosten finden, zählen wie oft diese vorkommen
		for( int j = 0; j < numOfWays; ++j ){
			int newCosts = ways[ numOfNodes - 1 ][ j ];
			if( costs >= newCosts ) {
				costs = newCosts;
				pathCounter++;
			}
		}
//		//Testausgabe
//		System.out.println("Pfade mit geringsten Kosten: " + pathCounter );
		System.out.println("Geringste Kosten: " + costs );
		
		//Array erstellen, welches die kürzesten Pfade enthält
		int[][] shortestPaths = new int[ numOfNodes - 1 ][ pathCounter ];
		int rowCounter = 0;
		for( int j = 0; j < numOfWays; ++j ){
			if( ways[ numOfNodes - 1 ][ j ] == costs ) {
				for ( int i = 0; i < ( numOfNodes - 1 ); i++ ) {
					shortestPaths[ i ][ rowCounter ] = ways[ i ][ j ];
				}
				rowCounter++;
			}
		}
		
		int[] nodes = new int[ numOfNodes ];
//		String nodesStr = "";
		for ( int i =  0; i < numOfNodes; i++ ) {
			int nodeName = GUI.nodes.get(i).getIntName();
			nodes[i] = nodeName;
//			nodesStr += nodeName;
//			nodesStr += " ";
		}
//		System.out.println( "Nodes " + nodesStr );
		
		//Ersetzen der Indizes durch Knotennamen
		int[][] shortestPathsNames = new int[ numOfNodes - 1 ][ pathCounter ];
		for( int j = 0; j < pathCounter; ++j ){
			for ( int i = 0; i < ( numOfNodes - 1 ); i++ ) {
				int nodeIndex = shortestPaths[ i ][ j ];
				shortestPathsNames[ i ][ j ] = nodes[ nodeIndex ]; 
			}
		}
		
//		//Testausgabe
//		System.out.println("findShortestPath/shortestPaths:");
//		for ( int j = 0; j < pathCounter; j++ ) {
//			String helpStr = "";
//			for ( int i = 0; i < ( numOfNodes - 1 ); i++){
//				Integer helpI = shortestPaths[ i ][ j ];
//				helpStr += helpI.toString();
//				helpStr += " ";
//			}
//			System.out.println( helpStr );
//		}
		
		System.out.println("findShortestPath/Pfade mit geringsten Kosten (Start- und Endknoten: " + nodes[ GUI.startNode ] +"):");
		for ( int j = 0; j < pathCounter; j++ ) {
			String helpStr = "";
			for ( int i = 0; i < ( numOfNodes - 1); i++){
				Integer helpI = shortestPathsNames[ i ][ j ];
				helpStr += helpI.toString();
				helpStr += " ";
			}
			System.out.println( helpStr );
		}
	}
}
