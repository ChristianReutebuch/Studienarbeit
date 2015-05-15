package TSP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class GUI extends JFrame {

	// Datendeklaration
	public static LinkedList<Node> nodes = new LinkedList<Node>();
	LinkedList<Node> selectednodes = new LinkedList<Node>();
	LinkedList<Link> links = new LinkedList<Link>();
	JPanel titelpanel = new JPanel();
	JPanel paintpanel = new JPanel();
	JPanel checkpanel = new JPanel();
	ButtonGroup btngr = new ButtonGroup();
	JCheckBox cbnode = new JCheckBox("Paint Node");
	JCheckBox cbstart = new JCheckBox("Paint StartNode");
	JCheckBox cbdel = new JCheckBox("Delete Node");
	JCheckBox cbsel = new JCheckBox("Select Node");

	public GUI() {
		// Frame Settings
		this.setLayout(new BorderLayout());
		this.setBounds(0, 0, 800, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Paintpanel Listener
		paintpanel.setBackground(Color.WHITE);
		paintpanel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (cbnode.isSelected() == true) {
					createGraph(e.getX(), e.getY(), false);
					paintAll();
					if (nodes.size() == 5) {
						System.out.println("Algorithm gets slow with more than 5 nodes.");
					}
				}
				if (cbstart.isSelected() == true) {
					createGraph(e.getX(), e.getY(), true);
					paintAll();
				}
				if (cbdel.isSelected() == true){
					deleteNode(e.getX(), e.getY());
					paintAll();
				}
				if (cbsel.isSelected() == true){
					selectNode(e.getX(), e.getY());
					paintAll();
				}
				if (e.getButton() == 3){
					moveNode(e.getX(), e.getY());
					paintAll();
				}
			}
		});

		// CheckPanel Settings
		btngr.add(cbnode);
		btngr.add(cbstart);
		btngr.add(cbdel);
		btngr.add(cbsel);
		checkpanel.setBackground(Color.red);
		checkpanel.add(cbnode);
		checkpanel.add(cbstart);
		checkpanel.add(cbdel);
		checkpanel.add(cbsel);
		
		// Design Frame
		this.add(titelpanel, BorderLayout.NORTH);
		this.add(paintpanel, BorderLayout.CENTER);
		this.add(checkpanel, BorderLayout.SOUTH);
	}
	
	public void createGraph(int xpos, int ypos, boolean isStart){
		createNode(xpos, ypos, isStart);
		createLinks();
	}

	public void createNode(int xpos, int ypos, boolean isStart) {
		Node node = new Node(xpos, ypos, isStart);
		nodes.add(node);
	}
	
	public void createLinks(){
		links.clear();
		for (int i = 0; i < nodes.size(); i++){
			Node firstnode = nodes.get(i);
			for (int j = 0; j < nodes.size(); j++){
				Node secondnode = nodes.get(j);
				if (firstnode != secondnode){
					Link link = new Link(firstnode, secondnode, 1);
					links.add(link);
				}
			}
		}
	}

	public void paintAll(){
		paintpanel.paint(paintpanel.getGraphics());
		paintLinks();
		paintNodes();
	}
	
	public void paintNodes(){
		for (int i = 0; i < nodes.size(); i++){
			Node node = nodes.get(i);
			node.setName(i);
			node.paintNode(paintpanel.getGraphics());
		}
	}
	
	public void paintLinks(){
		for (int i = 0; i < links.size(); i++) {
			Link link = links.get(i);
			link.paintLink(paintpanel.getGraphics());
		}
	}

	public void deleteNode(int xposMouse, int yposMouse) {
		for (int i = 0; i < nodes.size(); i++){
			Node node = nodes.get(i);
			if (xposMouse >= node.getXPos()
					&& xposMouse <= node.getXPos() + node.RADIUS
					&& yposMouse >= node.getYPos()
					&& yposMouse <= node.getYPos() + node.RADIUS) {
				nodes.remove(i);
				for (int j = 0; j < links.size(); j++){
					Link link = links.get(j);
					if (link.getFirstNode() == node || link.getSecondNode() == node){
						links.remove(j);
					}
				}
			}
		}
	}

	public void selectNode(int xposMouse, int yposMouse){
		for (int i = 0; i < nodes.size(); i++){
			Node node = nodes.get(i);
			if (xposMouse >= node.getXPos()
					&& xposMouse <= node.getXPos() + node.RADIUS
					&& yposMouse >= node.getYPos()
					&& yposMouse <= node.getYPos() + node.RADIUS) {
				selectednodes.add(node);
				node.isSelected = true;
				System.out.println("Node "+node.getName()+" is selected. New Position with click right.");
			}
		}
	}
	
	public void moveNode(int xposMouse, int yposMouse){
		Node sel = selectednodes.getFirst();
		deleteNode(sel.getXPos(),sel.getYPos());
		createNode(xposMouse, yposMouse, false);
		selectednodes.clear();
	}
}