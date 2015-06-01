////Studienarbeit "Visualisierung graphentheoretischer Algorithmen"
////Christian Reutebuch, Silke Hildebrand
////28.10.2014 - 
//
//
//package TSP;
//
//import java.util.*;
//
//public class AlgoTest {
//	int gewichtung[][],num,route[],kosten,numOfLinks, start;
//	final int INF = 1000;
//	
//	public AlgoTest() {
//		num = GUI.nodes.size();
//		gewichtung = new int[ num ][ num ];
//		route = new int[ num - 1 ];
//		numOfLinks = GUI.links.size();
//		start = GUI.startNode;
//		
////		for (int i = 0; i < num; i++) {
////			for (int j = 0; j < num; j++) {
////				if ( i != j) {
////					int first = link.getFirstNode().getIntName() - 1;
////					int second = link.getSecondNode().getIntName() - 1;
////					int dist = link.getDistance();
////					gewichtung[i][j]
////				}
////			}
////		}
//		for( int i = 0; i < numOfLinks; ++i) {
//			Link link = GUI.links.get(i);
//			int first = link.getFirstNode().getIntName() - 1;
//			int second = link.getSecondNode().getIntName() - 1;
//			int dist = link.getDistance();
//			gewichtung[first][second] = dist;
//			gewichtung[second][first] = dist;
//		}
//		
//		eval();
//	}
//	
//	public int cost(int currentNode, int inputSet[], int setSize){
//		if(setSize == 0) {
//			return gewichtung[currentNode][0];
//		}
//		int min = INF, minindex = 0;
//		int setToBePassedOnToNextCallOfCost[] = new int[ num - 1 ];
//		for ( int i = 0; i<setSize; i++ ) {
//			int k = 0; 
//			for ( int j = 0; j < setSize; j++ ) {
//				if( inputSet[i] != inputSet[j] ) {
//					setToBePassedOnToNextCallOfCost[k++]=inputSet[j];
//				}
//				int temp = cost(inputSet[i], setToBePassedOnToNextCallOfCost, setSize -1);
//				if ((gewichtung[currentNode][inputSet[i]]+temp)<min) {
//					min = gewichtung[currentNode][inputSet[i]]+temp;
//					minindex = inputSet[i];
//				}
//			}
//		}
//		return min;
//	}
//	public int mini( int currentNode, int inputSet[], int setSize) {
//		if(setSize==0){
//			return gewichtung[currentNode][0];				
//			}
//			int min = INF, minindex = 0;
//			int setToBePassedOnToNextCallOfCost[] = new int[num-1];
//			//alle Knoten in inputSet
//			for(int i = 0; i<setSize;i++) {
//				int k = 0;
//				for (int j = 0;j<setSize;j++) {
//					if(inputSet[i] != inputSet[j]) {
//						setToBePassedOnToNextCallOfCost[k++] = inputSet[j];
//					}
//					int temp = cost(inputSet[i], setToBePassedOnToNextCallOfCost, setSize-1);
//					if((gewichtung[currentNode][inputSet[i]]+temp)<min) {
//						min = gewichtung[currentNode][inputSet[i]]+temp;
//						minindex = inputSet[i];
//					}
//				}
//			}
//			return minindex;
//		}
//	public void eval() {
//		int dummySet[] = new int[num-1];
//		for (int i = 1; i<num;i++) {
//			dummySet[i-1]=i;
//			kosten=cost(0,dummySet,num-1);
//			constructRoute();
//		}
//	}
//	public void constructRoute(){
//		int previousSet[] = new int[num-1];
//		int nextSet[] = new int[num-2];
//		previousSet[i-1]=i;
//		int setSize = num-1;
//		route[0]= mini(0, previousSet, setSize);
//		for(int i=1;i<num;i++) {
//			int k = 0;
//			for (int j = 0;j<setSize;j++) {
//				if(route[j-1]!=previousSet[j])  {
//					nextSet[k++]=previousSet[j];
//				}
//			}
//			--setSize;
//			route[i] = mini(route[i-1],nextSet,setSize);
//			for(int j=0;j<setSize;j++) {
//				previousSet[j] = nextSet[j];
//			}
//		}
//	}
//}
//
