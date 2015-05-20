package TSP;

import java.util.ArrayList;


public class Algorithm {
	private static ArrayList<Integer> bestRoute;
	
	//routes: ist zu Beginn leet und enthält am Ende alle möglichen Pfade
	//nodesNotInRoute: enthält zu Beginn alle Knoten und wird jeweils um den Knoten reduziert, der bereits eingefügt wurde
	public static void bruteForce(ArrayList<Integer> routes, ArrayList<Integer> nodesNotInRoute){
		//prüfen, ob die übergebene Knotenliste Knoten enthält
		if(!nodesNotInRoute.isEmpty()){
			//Schleife über noch nicht in den aktuellen Pfad eingefügte Knoten
			for(int i = 0; i<nodesNotInRoute.size();i++){
				Integer justRemoved = (Integer) nodesNotInRoute.remove(0);
				//übergebene Liste kopieren
				ArrayList<Integer> newRoute = (ArrayList<Integer>) routes.clone();
				//erstes, zuvor gelöschtes Element hinten anhängen
				newRoute.add(justRemoved);
				bruteForce(newRoute, nodesNotInRoute);
				nodesNotInRoute.add(justRemoved);
			}
		}else{
			if(isBestRoute(routes))
				bestRoute = routes;
		}
	}
	private static boolean isBestRoute(ArrayList<Integer> route){
		System.out.println(route.toString());
		return false;
	}

}
