package TSP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI extends JFrame {

	// Datendeklaration
	public static LinkedList<Node> nodes = new LinkedList<Node>();
	LinkedList<Node> selectednodes = new LinkedList<Node>();
	LinkedList<Link> links = new LinkedList<Link>();
	JPanel titelpanel = new JPanel();
	JPanel paintpanel = new JPanel();
	JPanel checkpanel = new JPanel();
	JPanel txtpanel = new JPanel();
	JPanel editpanel = new JPanel();
	ButtonGroup btngr = new ButtonGroup();
	JCheckBox cbnode = new JCheckBox("Paint Node");
	JCheckBox cbstart = new JCheckBox("Paint StartNode");
	JCheckBox cbdel = new JCheckBox("Delete Node");
	JCheckBox cbsel = new JCheckBox("Select Node");
	JTextArea txtarea = new JTextArea();
	JLabel lblfnode = new JLabel("FirstNode: ");
	JLabel lblsnode = new JLabel("SecondNode: ");
	JTextField txtfnode = new JTextField();
	JTextField txtsnode = new JTextField();

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
					createLinks();
					paintAll();
				}
				if (cbsel.isSelected() == true){
					selectNode(e.getX(), e.getY());
					paintAll();
				}
				if (e.getButton() == 3){
					if (selectednodes.size() == 1){
						moveNode(e.getX(), e.getY());
					}
					if (selectednodes.size() == 2){
						changeValue();
					}
					if (selectednodes.size() > 2){
						System.out.println("Error");
					}
					clearSelectedNode();
					paintAll();	
				}
				filltxtarea();
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
		
		//Txtpanel Settings
		txtpanel.add(txtarea);
		//To do: von Anfang an eine festdefinierte Gr��e
		
		//Editpanel Settings
		editpanel.add(lblfnode);
		editpanel.add(txtfnode);
		editpanel.add(lblsnode);
		editpanel.add(txtsnode);
		
		// Design Frame
		this.add(titelpanel, BorderLayout.NORTH);
		this.add(paintpanel, BorderLayout.CENTER);
		this.add(checkpanel, BorderLayout.SOUTH);
		this.add(txtpanel, BorderLayout.WEST);
		this.add(editpanel, BorderLayout.EAST);
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
		LinkedList<Link> templinks = new LinkedList<Link>();
		templinks.clear();
		links.clear();
		for (int i = 0; i < nodes.size(); i++){
			Node firstnode = nodes.get(i);
			for (int j = 0; j < nodes.size(); j++){
				Node secondnode = nodes.get(j);
				if (firstnode != secondnode){
					Link templink = new Link(firstnode, secondnode, 1);
					templinks.add(templink);
				}
			}
		}
		for (int j = 0; j < templinks.size(); j++){
			Link jlink = templinks.get(j);
			Node jfirst = jlink.getFirstNode();
			Node jsecond = jlink.getSecondNode();
			if(links.size() == 0){
				links.add(jlink);
			}
			else{
				boolean set = true;
				for(int k = 0; k < links.size(); k++){
					Link klink = links.get(k);
					Node kfirst = klink.getFirstNode();
					Node ksecond = klink.getSecondNode();
					if( jfirst == ksecond && jsecond == kfirst){
						set = false;
					}
				}
				if(set == true){
					links.add(jlink);
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
		createGraph(xposMouse, yposMouse, false);
		clearSelectedNode();
	}
	
	public void changeValue(){
		Node firstsel = selectednodes.getFirst();
		Node secondsel = selectednodes.get(1);
		for (int i=0; i<links.size(); i++){
			Link link = links.get(i);
			Node first = link.getFirstNode();
			Node second = link.getSecondNode();
			if ((first == firstsel && second == secondsel)||(first == secondsel && second == firstsel)){
				link.setDistance(2);
			}
		}
		clearSelectedNode();
	}
	
	public void clearSelectedNode(){
		for (int i = 0; i<nodes.size();i++){
			Node node = nodes.get(i);
			node.isSelected = false;
		}
		selectednodes.clear();
	}
	
	public void filltxtarea(){
		txtarea.removeAll();
		String txt;
		txt = "Nodes: \n";
		for (int i = 0; i < nodes.size(); i++){
			Node node = nodes.get(i);
			String nodename = node.getName();
			txt = txt + "Node "+i+": "+nodename+"\n";
		}
		txt = txt + "Links: \n";
		for ( int j = 0; j < links.size();j++){
			Link link = links.get(j);
			String firstnodename = link.getFirstNode().getName();
			String secondnodename = link.getSecondNode().getName();
			txt = txt + firstnodename+ " " + secondnodename+" \n";
		}
		txtarea.setText(txt);
	}
}