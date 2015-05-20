package TSP;

import java.util.ArrayList;

//TODO
////- Distances-Array f�llen
//- Startknoten bei Brute-Force rausnehmen
//- errechnete Pfade in Wege-Array eintragen
//- Gesamtkosten ausrechnen und in Wege-Array eintragen
//- kleinste Wege rausfinden

public class Algorithm {
	private ArrayList<Integer> bestRoute;
	int numOfNodes = GUI.nodes.size();
	int numOfLinks = GUI.links.size();
	int numOfWays = 1;
	int[][] distances = new int[ numOfNodes ][ numOfNodes ];
	
	//routes: ist zu Beginn leer und enth�lt am Ende alle m�glichen Pfade
	//nodesNotInRoute: enth�lt zu Beginn alle Knoten und wird jeweils um den Knoten reduziert, der bereits eingef�gt wurde
	public void bruteForce(ArrayList<Integer> routes, ArrayList<Integer> nodesNotInRoute){
		//pr�fen, ob die �bergebene Knotenliste Knoten enth�lt
		if(!nodesNotInRoute.isEmpty()){
			//Schleife �ber noch nicht in den aktuellen Pfad eingef�gte Knoten
			for(int i = 0; i<nodesNotInRoute.size();i++){
				int justRemoved = nodesNotInRoute.remove(0);
				//�bergebene Liste kopieren
				ArrayList<Integer> newRoute = (ArrayList<Integer>) routes.clone();
				//erstes, zuvor gel�schtes Element hinten anh�ngen
				newRoute.add(justRemoved);
				bruteForce(newRoute, nodesNotInRoute);
				nodesNotInRoute.add(justRemoved);
			}
		}else{
			showRoutes(routes);
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
	
	private boolean calculateCosts(ArrayList<Integer> routes) {
		setDistances();
		for( int i = 1; i < numOfNodes; i++ ) {
			numOfWays *= i;
		}
		int[][] ways = new int[ numOfNodes ][ numOfWays ];
		
		return false;
	}

}
