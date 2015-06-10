package TSP;

import java.util.ArrayList;

public class BruteForce {

	private ArrayList<Integer> listOfRoutes = new ArrayList<Integer>();
	private int numOfWays = 0;
	private int numOfNodes = GUI.nodes.size();
	public int rowCounter = 0;
	private static final int MAXINT = Integer.MAX_VALUE;
	public int costs = MAXINT;

	public ArrayList<Integer> calcRoutes(ArrayList<Integer> routes, ArrayList<Integer> nodesNotInRoute){
		if(!nodesNotInRoute.isEmpty()){
			for(int i = 0; i < nodesNotInRoute.size(); i++){
				int justRemoved = nodesNotInRoute.remove(0);
				ArrayList<Integer> newRoute = (ArrayList<Integer>) routes.clone();
				newRoute.add(justRemoved);
				calcRoutes(newRoute, nodesNotInRoute);
				nodesNotInRoute.add(justRemoved);
			}
		}
        else{
			for(int i = 0; i < routes.size();i++) {
				listOfRoutes.add(routes.get(i));
			}
        }
        return listOfRoutes;
    }
	
	public int[][] buildRoutes( ArrayList<Integer> listOfRoutes ){
		numOfWays = (listOfRoutes.size()/numOfNodes)/2;
		int[][] ways = new int[ numOfNodes ][ numOfWays ];
		int counter = 0;
		for( int j = 0; j < numOfWays; j++ ) {
			for( int i = 0; i < numOfNodes; i++) {
				if( counter < listOfRoutes.size()) {
					ways[i][j] = listOfRoutes.get(counter);
					counter++;
				}
			}
		}//ways[][] gefüllt
		
		int numRout = calculate();
		int[][] selways = new int[numOfNodes][numRout];
		int pathcount=0;
		for(int i = 0;i<numOfWays;i++){
			if(ways[0][i]==GUI.startNode){
				for(int j =0;j<numOfNodes;j++){
					selways[j][pathcount]=ways[j][i];
					
				}
				pathcount++;
			}
		}
		int[][] allPaths = fillwithNames(selways);
		int[][] allwithCosts = addCosts(allPaths);
//		for ( int j = 0; j < numRout; j++ ) {
//			String helpStr = "";
//			for ( int i = 0; i < numOfNodes+2; i++){
//				Integer helpI = allwithCosts[i][j];
//				helpStr += helpI.toString();
//				helpStr += " ";
//			}
//			System.out.println( helpStr );
//		}
		int[][] shortestPaths = findShortestPaths(allwithCosts);
		for ( int j = 0; j < rowCounter; j++ ) {
			String helpStr = "";
			for ( int i = 0; i < numOfNodes+2; i++){
				Integer helpI = shortestPaths[i][j];
				helpStr += helpI.toString();
				helpStr += " ";
			}
			System.out.println( helpStr );
		}
		return shortestPaths;
	}
	
	private int calculate() {
		int fakultaet = 1;
		for (int zahl=1; zahl<=numOfNodes-1; zahl++) {
			fakultaet = fakultaet * zahl;
		}
		return fakultaet;
	}
	
	private int[][] fillwithNames(int[][]shortestPaths){
		int numRout = calculate();
		int[][] sP = new int[numOfNodes+1][numRout];
		for ( int j = 0; j < numRout; j++ ) {
			for ( int i = 0; i < numOfNodes; i++){
				sP[i][j]=shortestPaths[i][j];
				int name = GUI.nodes.get(sP[i][j]).getIntName();
				sP[i][j]=name;
			}
			sP[numOfNodes][j]=GUI.nodes.get(GUI.startNode).getIntName();
		}
		return sP;
	}
	
	private int[][] addCosts(int[][]sPwithCost){
		int numRout = calculate();
		int[][] spwc = new int[numOfNodes+2][numRout];
		int fnn = 0;
		int snn = 0;
		int cost=0;
		Link link;
		Node fn;
		Node sn;
		for ( int j = 0; j < numRout; j++ ) {
			for ( int i = 0; i < numOfNodes+1; i++){
				spwc[i][j] = sPwithCost[i][j];
			}
		}
		for ( int j = 0; j < numRout; j++ ) {
			for ( int i = 0; i < numOfNodes+1; i++){
				if(i<numOfNodes+1){
					fnn = spwc[i][j];
					snn = spwc[i+1][j];
					if(fnn > snn){
						int tmp = fnn;
						fnn = snn;
						snn = tmp;
					}
					for(int k =0; k<GUI.links.size();k++){
						link = GUI.links.get(k);
						fn = link.getFirstNode();
						sn = link.getSecondNode();
						if(fn.getIntName()==fnn && sn.getIntName()==snn){
							cost += link.getDistance();
						}
					}
				}
			}
			spwc[numOfNodes+1][j]=cost;
			cost = 0;
		}
		return spwc;
	}
	
	private int[][]findShortestPaths(int[][]spwc){
		int numRout = calculate();
		costs=MAXINT;
		int pathCounter=0;
		for( int j = 0; j < numRout; j++ ){
			int newCosts = spwc[numOfNodes+1][ j ];
			if( costs >= newCosts ) {
				costs = newCosts;
				pathCounter++;
			}
		}
		System.out.println("Geringste Kosten: " + costs );
		int[][] rsp = new int[numOfNodes+2][pathCounter];
		rowCounter = 0;
		for( int j = 0; j < numRout; ++j ){
			if( spwc[ numOfNodes+1 ][ j ] == costs ) {
				for ( int i = 0; i < ( numOfNodes+2 ); i++ ) {
					rsp[ i ][ rowCounter ] = spwc[ i ][ j ];
				}
				rowCounter++;
			}
		}
		return rsp;
	}
}
