package TSP;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;


public class Algorithm {
	private static ArrayList<Integer> bestRoute;
	
	public static void bruteForce(ArrayList<Integer> route, ArrayList<Integer> NodesNotInRoute){
		if(!NodesNotInRoute.isEmpty()){
			for(int i = 0; i<NodesNotInRoute.size();i++){
				Integer justRemoved = (Integer) NodesNotInRoute.remove(0);
				ArrayList<Integer> newRoute = (ArrayList<Integer>) route.clone();
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
