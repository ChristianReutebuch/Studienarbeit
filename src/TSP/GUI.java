package TSP;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI extends JFrame {
	final int radius = 40;
	ArrayList<Node> nodes = new ArrayList<Node>();
	ArrayList<Node> selectednodes = new ArrayList<Node>();
	ArrayList<Link> links = new ArrayList<Link>();
	JPanel titelpanel = new JPanel();
	JPanel paintpanel = new JPanel();
	JPanel checkpanel = new JPanel();
	JLabel titel = new JLabel("Travelling Salesman Problem");
	ButtonGroup btngr = new ButtonGroup();
	JCheckBox cbnode = new JCheckBox("Paint Node");
	JCheckBox cbstart = new JCheckBox("Paint StartNode");
	JCheckBox cblink = new JCheckBox("Paint Link");

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
			}
		});

		// CheckPanel Settings
		btngr.add(cbnode);
		btngr.add(cbstart);
		btngr.add(cblink);
		checkpanel.add(cbnode);
		checkpanel.add(cbstart);
		checkpanel.add(cblink);

		// Design Frame
		this.add(titelpanel, BorderLayout.NORTH);
		this.add(paintpanel, BorderLayout.CENTER);
		this.add(checkpanel, BorderLayout.EAST);
	}

	public void createNode(int xpos, int ypos, boolean isStart) {
		Node node = new Node(xpos, ypos, radius, isStart);
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
			int startx = startlink.getXPos()+20;
			int starty = startlink.getYPos()+20;
			int endx = endlink.getXPos()+20;
			int endy = endlink.getYPos()+20;
			createLink(startx, starty, endx, endy);
			paintLinks();
			selectednodes.clear();
		} else {
//			Es kann nicht verbunden werden"
		}
	}

	public void createLink(int startx, int starty, int endx, int endy){
		Link link = new Link(startx, starty, endx, endy);
		links.add(link);
	}
	
	public void paintLinks() {
		for (int i = 0; i < links.size(); i++) {
			Link link = links.get(i);
			link.paintLink(paintpanel.getGraphics());
		}
	}
	
	public void nodeSelected(int xposMouse, int yposMouse) {
		for (int i = 0; i < nodes.size(); i++) {
			Node node = nodes.get(i);
			if (xposMouse >= node.getXPos()
					&& xposMouse <= node.getXPos() + radius
					&& yposMouse >= node.getYPos()
					&& yposMouse <= node.getYPos() + radius) {
				selectednodes.add(node);
			}
		}
	}
}
