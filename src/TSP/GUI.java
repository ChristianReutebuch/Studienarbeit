package TSP;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	JLabel titel = new JLabel("Travelling Salesman Problem");
	ButtonGroup btngr = new ButtonGroup();
	JCheckBox cbnode = new JCheckBox("Paint Node");
	JCheckBox cbstart = new JCheckBox("Paint StartNode");
	JCheckBox cblink = new JCheckBox("Paint Link");
	JCheckBox cbmove = new JCheckBox("Move Node");
	JPopupMenu popup = new JPopupMenu();

	public GUI() {
		// Frame Settings
		this.setLayout(new BorderLayout());
		this.setBounds(0, 0, 800, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Titelpanel Settings
		titelpanel.add(titel);

		// Paintpanel Listener
		paintpanel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (cbnode.isSelected() == true && nodes.size() < 5) {
					createNode(e.getX(), e.getY(), false);
					paintNodes();
					if (nodes.size() == 5) {
						JOptionPane.showMessageDialog(paintpanel,
								"Algorithm cannot handle more than 5 Nodes.");
					}
				}
				if (cbstart.isSelected() == true) {
					createNode(e.getX(), e.getY(), true);
					paintNodes();
				}
				if (cblink.isSelected() == true) {
					nodeSelected(e.getX(), e.getY());
					checkLink();
				}
				if (e.getButton() == 3) {
					System.out.println("Rechtsklick");
				}
				if (cbmove.isSelected() == true){
					nodeSelected(e.getX(), e.getY());
//					moveNode(e.getX(), e.getY());
				}
			}
		});

		// CheckPanel Settings
		btngr.add(cbnode);
		btngr.add(cbstart);
		btngr.add(cblink);
		btngr.add(cbmove);
		checkpanel.add(cbnode);
		checkpanel.add(cbstart);
		checkpanel.add(cblink);
		checkpanel.add(cbmove);

		// Design Frame
		this.add(titelpanel, BorderLayout.NORTH);
		this.add(paintpanel, BorderLayout.CENTER);
		this.add(checkpanel, BorderLayout.EAST);

		// Design PopupMenu
		popup.setLabel("Testpopup");
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

	public void nodeSelected(int xposMouse, int yposMouse) {
		boolean selected = false;
		for (int i = 0; i < nodes.size(); i++) { //Durchsuche aktuelle Knotenliste
			Node node = nodes.get(i);			 //Aktueller Knoten temporär speichern	
			if (xposMouse >= node.getXPos()
					&& xposMouse <= node.getXPos() + node.RADIUS
					&& yposMouse >= node.getYPos()
					&& yposMouse <= node.getYPos() + node.RADIUS) { //Maus in Kreis?
				selectednodes.add(node);	//Knoten zu ausgewählte Liste hinzufügen
				node.isSelected = true;		//Knoten ist selektiert
				selected = true;
			}
			else{					//Maus nicht in Kreis
				node.isSelected = false;
			}
		}
		if (selected == false){
			selectednodes.clear();
		}
		paintNodes();
	}

	public void moveNode(int xposMouse, int yposMouse) {
		if (selectednodes.size() == 1){ //ein Knoten ist ausgewählt
			Node selnode = selectednodes.get(0); //ausgewählten Knoten temporär speichern
			for (int i = 0; i < nodes.size(); i++){ //Knotenliste durchsuchen
				Node node = nodes.get(i);//aktueller Knoten temporär speichern
				if (selnode == node){ //ausgewählter Knoten gefunden
					deleteNode(node);
					createNode(xposMouse, yposMouse, false);
//					node.setXPos(xposMouse);
//					node.setYPos(yposMouse);
					System.out.println("Knoten in Tabelle gefunden.");
				}
			}
		}
		}

	boolean deleteNode(Node node) {
		System.out.println(nodes.size());
		nodes.remove(node);
		System.out.println(nodes.size());
		paintNodes();
		return false;
	}

	boolean deleteLink(Link link) {
		if (links.remove(link)) {
			return true;
		}
		return false;
	}
}
