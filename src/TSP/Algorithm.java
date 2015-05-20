package TSP;

import java.util.ArrayList;


public class Algorithm {
	private static ArrayList<Integer> bestRoute;
	
	//routes: ist zu Beginn leet und enth�lt am Ende alle m�glichen Pfade
	//nodesNotInRoute: enth�lt zu Beginn alle Knoten und wird jeweils um den Knoten reduziert, der bereits eingef�gt wurde
	public static void bruteForce(ArrayList<Integer> routes, ArrayList<Integer> nodesNotInRoute){
		//pr�fen, ob die �bergebene Knotenliste Knoten enth�lt
		if(!nodesNotInRoute.isEmpty()){
			//Schleife �ber noch nicht in den aktuellen Pfad eingef�gte Knoten
			for(int i = 0; i<nodesNotInRoute.size();i++){
				Integer justRemoved = (Integer) nodesNotInRoute.remove(0);
				//�bergebene Liste kopieren
				ArrayList<Integer> newRoute = (ArrayList<Integer>) routes.clone();
				//erstes, zuvor gel�schtes Element hinten anh�ngen
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
