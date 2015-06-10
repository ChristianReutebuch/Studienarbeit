//Studienarbeit "Visualisierung graphentheoretischer Algorithmen"
//Christian Reutebuch, Silke Hildebrand
//28.10.2014 - 10.07.2014

package TSP;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class CreateComp {
	private LinkedList<Node> nodes = GUI.nodes;
	private LinkedList<Link> dellinks = GUI.dellinks;
	private LinkedList<Link> links = GUI.links;

	
	public void createNode(int xpos, int ypos, boolean isStartnode) {
		if (new Checks().checkPos(xpos, ypos)==true){
			Node node = new Node(xpos, ypos, isStartnode);
			if(GUI.delpos == -1){
				node.setName(GUI.nodecounter);
				GUI.nodecounter++;
				nodes.add(node);
				createLinks();
			}else{
				node.setName(GUI.delname);
				nodes.add(GUI.delpos, node);
				for(int i = 0; i<dellinks.size();i++){
					Link dellink = dellinks.get(i);
					if(dellink.getFirstNode().getName().equals( node.getName())){
						Node first = node;
						Node second = dellink.getSecondNode();
						Link newLink = new Link(first, second);
						newLink.setDistance(dellink.getDistance());
						links.add(newLink);
					}
					if(dellink.getSecondNode().getName().equals( node.getName())){
						Node first = dellink.getFirstNode();
						Node second = node;
						Link newLink = new Link(first, second);
						newLink.setDistance(dellink.getDistance());
						links.add(newLink);
					}
				}
			}
			}else{
			JOptionPane.showMessageDialog(GUI.frame, "An dieser Stelle kann kein Knoten gezeichnet werden.");
			new PaintComp();
		}
		dellinks.clear();
	}
	
	public void createLinks(){
		if(nodes.size()>=2){
			Node nnode = nodes.getLast();
			for(int i=0; i < nodes.size(); i++){
				Node lnode = nodes.get(i);
				if(nnode != lnode){
					Link nlink = new Link(lnode, nnode);
					links.add(nlink);
				}
			}
		}
	}
}
