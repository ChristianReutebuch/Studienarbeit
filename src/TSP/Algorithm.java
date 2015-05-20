package TSP;

import java.util.ArrayList;


public class Algorithm {
	private ArrayList<Integer> bestRoute;
	int numOfNodes = nodes.size();
	
	//Anzahl der möglichen Wege
	int numberOfWays = numOfNodes-1;
	for (int i=1; i<=numOfNodes; i++){
		numberOfWays = numberOfWays * i;
	}
	numberOfWays = numberOfWays / 2;
	
	//routes: ist zu Beginn leet und enthält am Ende alle möglichen Pfade
	//nodesNotInRoute: enthält zu Beginn alle Knoten und wird jeweils um den Knoten reduziert, der bereits eingefügt wurde
	public void bruteForce(ArrayList<Integer> routes, ArrayList<Integer> nodesNotInRoute){
		//prüfen, ob die übergebene Knotenliste Knoten enthält
		if(!nodesNotInRoute.isEmpty()){
			//Schleife über noch nicht in den aktuellen Pfad eingefügte Knoten
			for(int i = 0; i<nodesNotInRoute.size();i++){
				int justRemoved = nodesNotInRoute.remove(0);
				//übergebene Liste kopieren
				ArrayList<Integer> newRoute = (ArrayList<Integer>) routes.clone();
				//erstes, zuvor gelöschtes Element hinten anhängen
				newRoute.add(justRemoved);
				bruteForce(newRoute, nodesNotInRoute);
				nodesNotInRoute.add(justRemoved);
			}
		}else{
			showRoutes(routes);
		}
	}
	private static boolean showRoutes(ArrayList<Integer> route){
		System.out.println(route.toString());
		return false;
	}

}
