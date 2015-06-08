//Studienarbeit "Visualisierung graphentheoretischer Algorithmen"
//Christian Reutebuch, Silke Hildebrand
//28.10.2014 - 

package TSP;

import java.util.LinkedList;

public class Checks {
	private LinkedList<Node> nodes = GUI.nodes;
	
	public boolean checkPos(int xpos, int ypos){
		boolean paint = true;
		if (nodes.size() == 0){
			paint = true;
		}
		else{
			for(int i = 0; i< nodes.size(); i++){
				Node cnode = nodes.get(i);
				if (xpos >= cnode.getXPos()
						&& xpos <= cnode.getXPos() + cnode.RADIUS
						&& ypos >= cnode.getYPos()
						&& ypos <= cnode.getYPos() + cnode.RADIUS) {
					paint = false;
				}
			}
		}
		return paint;
	}
}
