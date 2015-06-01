package TSP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

//public class GUI extends JFrame {
public class GUI{
	
	// Datendeklaration
	public static LinkedList<Node> nodes = new LinkedList<Node>();
	public static LinkedList<Node> selectednodes = new LinkedList<Node>();
	public static LinkedList<Link> links = new LinkedList<Link>();
	public static LinkedList<Link> dellinks = new LinkedList<Link>();
	JFrame frame = new JFrame();
	JPanel titelpanel = new JPanel();
	JPanel paintpanel = new JPanel();
	JPanel editpanel = new JPanel();
	JPanel txtpanel = new JPanel();
	JPanel planepanel = new JPanel();
	JTextArea txtarea = new JTextArea();
	JButton btncalc = new JButton("Berechne");
	JButton btnchng = new JButton("Ändern");
	JButton btnstart = new JButton("Start setzen");
	JButton btndel = new JButton("Löschen");
	private int delpos = -1;
	private int nodecounter = 1;
	private int delname;
	public static int startNode = -1;

	public GUI() {
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Gestaltung der Oberfläche:
	// Hier werden alle GUI-Komponenten implementiert und die Interaktion des Users verarbeitet
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// Frame Settings
		frame.setLayout(new BorderLayout());
		frame.setBounds(0, 0, 1000, 600);
		frame.setTitle("Travelling Salesman Problem");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Titelpanel Settings
		titelpanel.setBackground(Color.lightGray);
		
		
		// Paintpanel Listener
		paintpanel.setBackground(Color.WHITE);
		paintpanel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton()==1) {
					if (selectednodes.size()== 1){
						moveNode(e.getX(), e.getY());
						clearSelectedNode();
						paintAll();	
					}
					else{
						createNode(e.getX(), e.getY(), false);
						paintAll();
						if (nodes.size() == 5) {
							JOptionPane.showMessageDialog(frame, "Algorithmus wird sehr langsam mit mehr als 5 Knoten");
						}
						Node node = nodes.getLast();
					}
				}
				if (e.getButton() == 3){ //Knoten auswählen via Rechtsklick
					selectNode(e.getX(), e.getY());
					paintAll();
				}
				filltxtarea();
				sortLinkList();
			}
		});

		// PlanePanel Settings
		planepanel.setBackground(Color.lightGray);
		
		//Txtpanel Settings
		txtpanel.add(txtarea);
		txtpanel.setPreferredSize(new Dimension(100,0));
		txtpanel.setBackground(Color.lightGray);
		
		//Editpanel Settings
		editpanel.setBackground(Color.lightGray);
		editpanel.add(btnchng);
		btnchng.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ce){
				if (selectednodes.size() == 2){ // Distanz ändern
					String newdist = JOptionPane.showInputDialog(frame, "Neue Distanz:");
					int distance = Integer.parseInt(newdist);
					changeValue(distance);
				}
				else{ //Ändern nicht möglich
					JOptionPane.showMessageDialog(frame, "Es müssen genau zwei Knoten ausgewählt sein.");
				}
				clearSelectedNode();
				paintAll();	
			}
		});
		editpanel.add (btncalc);
		btncalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//An dieser Stelle wird der Algorithmus aufgerufen
				
				//Startknoten finden
				for(int i = 0; i < nodes.size(); ++i) {
					if( nodes.get( i ).isStartNode() == true){
						startNode = i;
					}
				}
				//Kontenliste erstellen, ohne den Startknoten
				ArrayList<Integer> lst = new ArrayList<Integer>();
				for (int i = 0; i < nodes.size(); i++){
					if( i != startNode) {
						lst.add(i);
					}
				}
				ArrayList<Integer> route = new ArrayList<Integer>();
				Algorithm algo = new Algorithm();
				algo.bruteForce(route, lst);
			}
		});
		editpanel.add(btnstart);
		btnstart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(selectednodes.size() == 1){
					//Alte StartNodes löschen
					for (int i = 0; i<nodes.size();i++){
						Node nd = nodes.get(i);
						if(nd.isStartnode == true){
							nd.delStartNode();
						}
					}
					//Neuer StartNode setzen
					Node node = selectednodes.get(0);
					node.setStartNode();
					clearSelectedNode();
					paintAll();
				}
			}
		});
		editpanel.add(btndel);
		btndel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent de){
				if (selectednodes.size() == 1){
					Node node = selectednodes.get(0);
					deleteNode(node);
					clearSelectedNode();
					delpos = -1;
					paintAll();
				}
			}
		});
		
		// Design Frame
		frame.add(titelpanel, BorderLayout.NORTH);
		frame.add(paintpanel, BorderLayout.CENTER);
		frame.add(editpanel, BorderLayout.SOUTH);
		frame.add(txtpanel, BorderLayout.WEST);
		frame.add(planepanel, BorderLayout.EAST);
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Erstellen von Knoten und Kanten:
// Die Klasse checkNewNodePos überprüft, ob an der vom User gewählten Stelle ein Knoten gemalt werden darf.
// Die Klasse createNode und createLink implementieren Knoten und Kanten.
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean checkNewNodePos(int xpos, int ypos){
		boolean ok = true;
		if (nodes.size() == 0){
			ok = true;
		}
		else{
			for(int i = 0; i< nodes.size(); i++){
				Node cnode = nodes.get(i);
				if (xpos >= cnode.getXPos()
						&& xpos <= cnode.getXPos() + cnode.RADIUS
						&& ypos >= cnode.getYPos()
						&& ypos <= cnode.getYPos() + cnode.RADIUS) {
					ok = false;
					JOptionPane.showMessageDialog(frame, "An dieser Stelle kann kein Knoten gezeichnet werden.");
				}
			}
		}
		return ok;
	}
	
	public void createNode(int xpos, int ypos, boolean isStartnode) {
		if (checkNewNodePos(xpos, ypos)==true){
			Node node = new Node(xpos, ypos, isStartnode);
			if(delpos == -1){
				node.setName(nodecounter);
				nodecounter++;
				nodes.add(node);
				createLinks();
			}else{
				node.setName(delname);
				nodes.add(delpos, node);
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
				dellinks.clear();
			}
		}
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

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Zeichnen:
// Die Klassen paintNodes und paintLinks zeichen die entsprecheneden Elemente.
// In der Klasse paintAll sind beide Klassen zu einem Aufruf zusammengefasst.
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
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
	
	public void paintAll(){
		paintpanel.paint(paintpanel.getGraphics());
		paintLinks();
		paintNodes();
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Knoten löschen:
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void deleteNode(Node delnode) {
		for (int i = 0; i < nodes.size(); i++){
			Node node = nodes.get(i);
			if (node == delnode) {
				delname = node.getIntName();
				nodes.remove(i);
				delpos = i;
			}
		}
		LinkedList<Link> templink = links;
		for(int j = 0; j < templink.size(); j++){
			Link link = templink.get(j);
			if((link.getFirstNode() == delnode) || (link.getSecondNode() == delnode)){
				dellinks.add(link);
			}
		}
		for(int k=0;k<dellinks.size();k++){
			Link dellink =  dellinks.get(k);
			for(int l = 0; l < templink.size(); l++){
				Link clink = templink.get(l);
				if(clink == dellink){
					links.remove(l);
				}
			}
		}
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Knoten markieren:
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void selectNode(int xposMouse, int yposMouse){
		for (int i = 0; i < nodes.size(); i++){
			Node node = nodes.get(i);
			if (xposMouse >= node.getXPos()
					&& xposMouse <= node.getXPos() + node.RADIUS
					&& yposMouse >= node.getYPos()
					&& yposMouse <= node.getYPos() + node.RADIUS) {
				selectednodes.add(node);
				node.isSelected = true;
			}
		}
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Knoten verschieben:
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public void moveNode(int xposMouse, int yposMouse){
		Node sel = selectednodes.getFirst();
		deleteNode(sel);
		createNode(xposMouse, yposMouse, false);
		clearSelectedNode();
		delpos = -1; 
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Distanz verändern:
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void changeValue(int distance){
		Node firstsel = selectednodes.getFirst();
		Node secondsel = selectednodes.get(1);
		for (int i=0; i<links.size(); i++){
			Link link = links.get(i);
			Node first = link.getFirstNode();
			Node second = link.getSecondNode();
			if ((first == firstsel && second == secondsel)||(first == secondsel && second == firstsel)){
				link.setDistance(distance);
			}
		}
		clearSelectedNode();
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//	Liste mit ausgewählten Knoten löschen:
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void clearSelectedNode(){
		for (int i = 0; i<nodes.size();i++){
			Node node = nodes.get(i);
			node.isSelected = false;
		}
		selectednodes.clear();
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Linkliste aufsteigend sortieren
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void sortLinkList(){
		LinkedList<Link> templinks = new LinkedList<Link>();
		for (int i = 0; i<nodes.size();i++){
			Node node = nodes.get(i);
			for(int j = 0; j<links.size();j++){
				Link link = links.get(j);
				Node first = link.getFirstNode();
				if(node == first){
					templinks.add(link);
				}
			}
		}
		links = templinks;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Knoten und Kanten Übersicht erstellen
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void filltxtarea(){
		txtarea.removeAll();
		String txt;
		txt = "Nodes: \n";
		for (int i = 0; i < nodes.size(); i++){
			Node node = nodes.get(i);
			String nodename = node.getName();
			if(nodename.equals("1")){
				txt = txt+nodename;
			}else{
				txt = txt + ", "+nodename;
			}
		}
		txt = txt + "\nLinks: \n";
		for ( int j = 0; j < links.size();j++){
			Link link = links.get(j);
			String firstnodename = link.getFirstNode().getName();
			String secondnodename = link.getSecondNode().getName();
			txt = txt + firstnodename + " - "+secondnodename+"\n";
		}
		txtarea.setText(txt);
	}
}