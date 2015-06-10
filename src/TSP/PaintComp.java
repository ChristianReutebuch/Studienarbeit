//Studienarbeit "Visualisierung graphentheoretischer Algorithmen"
//Christian Reutebuch, Silke Hildebrand
//28.10.2014 - 10.07.2014

package TSP;

import java.util.LinkedList;

import javax.swing.JPanel;

public class PaintComp {
	private JPanel paintpanel = GUI.paintpanel;
	private LinkedList<Node> nodes = GUI.nodes;
	private LinkedList<Link> links = GUI.links;
	
	public PaintComp(){
		paintpanel.paint(paintpanel.getGraphics());
		paintLinks();
		paintNodes();
	}
	
	public void paintNodes(){
		for (int i = 0; i < nodes.size(); i++){
			Node node = nodes.get(i);
			node.paintNode(paintpanel.getGraphics());
		}
	}
	
	public void paintLinks(){
		for (int i = 0; i < links.size(); i++) {
			Link link = links.get(i);
			link.paintLink(paintpanel.getGraphics());
		}
	}
}
