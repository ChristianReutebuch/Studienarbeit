package TSP;

import java.util.ArrayList;


public class Algorithm {
	private static ArrayList<Integer> bestRoute;
	
	public static void bruteForce(ArrayList<Integer> route, ArrayList<Integer> NodesNotInRoute){
		//pr�fen, ob die �bergebene Knotenliste Knoten enth�lt
		if(!NodesNotInRoute.isEmpty()){
			//Schleife �ber Knoten
			for(int i = 0; i<NodesNotInRoute.size();i++){
				Integer justRemoved = (Integer) NodesNotInRoute.remove(0);
				//�bergebene Liste kopieren
				ArrayList<Integer> newRoute = (ArrayList<Integer>) route.clone();
				//erstes, zuvor gel�schtes Element hinten anh�ngen
				newRoute.add(justRemoved);
				bruteForce(newRoute, NodesNotInRoute);
				NodesNotInRoute.add(justRemoved);
			}
		}else{
			if(isBestRoute(route))
				bestRoute = route;
		}
	}
	private static boolean isBestRoute(ArrayList<Integer> route){
		System.out.println(route.toString());
		return false;
	}

}
