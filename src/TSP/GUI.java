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
	JButton btndelnode = new JButton ("Delete Node");
	ButtonGroup btngr = new ButtonGroup();
	JCheckBox cbnode = new JCheckBox("Paint Node");
	JCheckBox cbstart = new JCheckBox("Paint StartNode");
	JCheckBox cblink = new JCheckBox("Paint Link");
	JCheckBox cbmove = new JCheckBox("Move Node");
	
	JMenuBar menubar = new JMenuBar();
	JMenu paint = new JMenu("Paint");
	JMenuItem node = new JMenuItem("Paint Nodes");
	JMenuItem start = new JMenuItem("Paint StartNode");

	public GUI() {
		// Frame Settings
		this.setLayout(new BorderLayout());
		this.setBounds(0, 0, 800, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Titelpanel Settings
		titelpanel.add(menubar);
		menubar.add(paint);
		paint.add(node);
		paint.add(start);
		
		

		// Paintpanel Listener
		paintpanel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (cbnode.isSelected() == true && nodes.size() < 5) {
					createNode(e.getX(), e.getY(), false);
					paintNodes();
					if (nodes.size() == 5) {
						System.out.println("Algorithm gets slow with more than 5 nodes.");
					}
				}
				if (cbstart.isSelected() == true) {
					createNode(e.getX(), e.getY(), true);
					paintNodes();
				}
				if (cblink.isSelected() == true) {
					nodeSelLink(e.getX(), e.getY());
					checkLink();
				}
				if (e.getButton() == 3) {
					System.out.println("Rechtsklick");
				}
				if (cbmove.isSelected() == true){
					nodeSelMove(e.getX(), e.getY());
//					moveNode(e.getX(), e.getY());
				}
			}
		});

		// CheckPanel Settings
		btngr.add(cbnode);
		btngr.add(cbstart);
		btngr.add(cblink);
		btngr.add(cbmove);
		checkpanel.setBackground(Color.red);
		checkpanel.add(cbnode);
		checkpanel.add(cbstart);
		checkpanel.add(cblink);
		checkpanel.add(cbmove);
		checkpanel.add(btndelnode);

		// Design Frame
		this.add(titelpanel, BorderLayout.NORTH);
		this.add(paintpanel, BorderLayout.CENTER);
		this.add(checkpanel, BorderLayout.SOUTH);
	}

	public void createNode(int xpos, int ypos, boolean isStart) {
		Node node = new Node(xpos, ypos, isStart);
		nodes.add(node);
	}

	public void paintNodes() {
		for (int i = 0; i < nodes.size(); i++) {
			Node node = nodes.get(i);
			node.paintNode(paintpanel.getGraphics());
		}
	}

	public void checkLink() {
		if (selectednodes.size() == 2) {
			Node startlink = selectednodes.get(0);
			Node endlink = selectednodes.get(1);
			createLink(startlink, endlink);
			paintLinks();
			selectednodes.clear();
		} else {
			// Es kann nicht verbunden werden"
		}
	}

	public void createLink(Node startlink, Node endlink) {
		Link link = new Link(startlink, endlink, 1);
		links.add(link);
	}

	public void paintLinks() {
		for (int i = 0; i < links.size(); i++) {
			Link link = links.get(i);
			link.paintLink(paintpanel.getGraphics());
		}
	}

	public void nodeSelLink(int xposMouse, int yposMouse) {
		boolean selected = false;
		for (int i = 0; i < nodes.size(); i++) { //Durchsuche aktuelle Knotenliste
			Node node = nodes.get(i);			 //Aktueller Knoten tempor�r speichern	
			if (xposMouse >= node.getXPos()
					&& xposMouse <= node.getXPos() + node.RADIUS
					&& yposMouse >= node.getYPos()
					&& yposMouse <= node.getYPos() + node.RADIUS) { //Maus in Kreis?
				selectednodes.add(node);	//Knoten zu ausgew�hlte Liste hinzuf�gen
				node.isSelected = true;		//Knoten ist selektiert
				selected = true;
			}
			else{					//Maus nicht in Kreis
				node.isSelected = false;
			}
		}
		if (selected == false || selectednodes.size() == 3){
			selectednodes.clear();
		}
		paintNodes();
	}
	
	public void nodeSelMove(int xposMouse, int yposMouse){
		for (int i = 0; i < nodes.size(); i++) { //Durchsuche aktuelle Knotenliste
			Node node = nodes.get(i);			 //Aktueller Knoten tempor�r speichern	
			if (xposMouse >= node.getXPos()
					&& xposMouse <= node.getXPos() + node.RADIUS
					&& yposMouse >= node.getYPos()
					&& yposMouse <= node.getYPos() + node.RADIUS) { //Maus in Kreis?
				node.isSelected = true;
				repaint();
			}
			else{					//Maus nicht in Kreis
				node.isSelected = false;
			}
		}
		paintNodes();
	}

	public void moveNode(int xposMouse, int yposMouse) {

	}

	public void deleteNode(Node node) {
		
	}

	boolean deleteLink(Link link) {
		if (links.remove(link)) {
			return true;
		}
		return false;
	}
}
