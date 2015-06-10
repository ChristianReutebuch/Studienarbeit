//Studienarbeit "Visualisierung graphentheoretischer Algorithmen"
//Christian Reutebuch, Silke Hildebrand
//28.10.2014 - 


package TSP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GUI{
	
	// Datendeklaration
	public static LinkedList<Node> nodes = new LinkedList<Node>();
	public static LinkedList<Node> selectednodes = new LinkedList<Node>();
	public static LinkedList<Link> links = new LinkedList<Link>();
	public static LinkedList<Link> dellinks = new LinkedList<Link>();
	public static JFrame frame = new JFrame();
	public static int delpos = -1;
	public static int nodecounter = 1;
	public static int delname;
	
	JPanel titelpanel = new JPanel();
	public static JPanel paintpanel = new JPanel();
	JPanel editpanel = new JPanel();
	JPanel txtpanel = new JPanel();
	JPanel planepanel = new JPanel();
	JTextArea txtarea = new JTextArea();
//	JButton btncalc = new JButton("Berechne");
	JButton btnchng = new JButton("Ändern");
	JButton btnstart = new JButton("Start setzen");
	JButton btndel = new JButton("Löschen");
	JButton btnbf = new JButton("Brute Force");
	public static int startNode = -1;
	private int pcosts = Integer.MAX_VALUE;
	private int[][] shortestpaths;
	private int pc;
	

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
						new PaintComp();
						delMarkLink();
					}
					else{
						CreateComp comp = new CreateComp();
						comp.createNode(e.getX(), e.getY(), false);
						new PaintComp();
						delMarkLink();
						if (nodes.size() == 5) {
							JOptionPane.showMessageDialog(frame, "Algorithmus wird sehr langsam mit mehr als 5 Knoten");
							new PaintComp();
						}
					}
				}
				if (e.getButton() == 3){ //Knoten auswählen via Rechtsklick
					if(new Checks().checkPos(e.getX(), e.getY())==true){
						clearSelectedNode();
					}else{
						selectNode(e.getX(), e.getY());
					}
					new PaintComp();
					delMarkLink();
				}
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
					new PaintComp();
				}
				clearSelectedNode();
				new PaintComp();
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
					new PaintComp();
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
					new PaintComp();
				}
			}
		});
		editpanel.add(btnbf);
		btnbf.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent de){
				for(int i = 0; i < nodes.size(); ++i) {
					if( nodes.get( i ).isStartNode() == true){
						startNode = i;
					}
				}
				if( startNode > -1 ){
					System.out.println("Test");
					ArrayList<Integer> lst = new ArrayList<Integer>();
		        	for (int i = 0; i < nodes.size(); ++i)
		            	lst.add(i);
		        	ArrayList<Integer> route = new ArrayList<Integer>();
		        	BruteForce bf = new BruteForce();
		        	shortestpaths = bf.buildRoutes(bf.calcRoutes(route, lst));
		        	pc = bf.rowCounter;
		        	pcosts = bf.costs;
		        	filltxtarea();
		        	delMarkLink();
		        	markLink();
				}else {
					JOptionPane.showMessageDialog(frame, "Kein Startknoten gesetzt.");
					new PaintComp();
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
		if(selectednodes.size()>= 2){
			clearSelectedNode();
		}
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
		dellinks.clear();
		Node sel = selectednodes.getFirst();
		deleteNode(sel);
		CreateComp comp = new CreateComp();
		comp.createNode(xposMouse, yposMouse, false);
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
		txt = "Kosten: \n";
		txt += Integer.toString(pcosts)+"\n";
		txt += "Kürzeste Route: ";
		for ( int j = 0; j < pc; j++ ) {
			txt += " \n";
			for ( int i = 0; i < ( nodes.size() +1); i++){
				Integer helpI = shortestpaths[ i ][ j ];
				txt += helpI.toString();
				txt += " ";
			}
		}
		txtarea.setText(txt);
	}
	
	public void markLink(){
		int fnn = 0;
		int snn = 0;
		Link link;
		Node fn;
		Node sn;
		for ( int j = 0; j < pc; j++ ) { //Hoehe
			for ( int i = 0; i < ( nodes.size()+2); i++){ //Breite
				if(i<nodes.size()){
					fnn = shortestpaths[ i ][ j ];
					snn = shortestpaths[i+1][j];
					if(fnn > snn){
						int tmp = fnn;
						fnn = snn;
						snn = tmp;
					}
					for(int k =0; k<links.size();k++){
						link = links.get(k);
						fn = link.getFirstNode();
						sn = link.getSecondNode();
						if(fn.getIntName()==fnn && sn.getIntName()==snn){
							link.setMarkStatus(true);
						}
					}
				}
			}
		}
		new PaintComp();
	}
	public void delMarkLink(){
		for ( int i = 0; i< links.size(); i++){
			Link link = links.get(i);
			link.setMarkStatus(false);
		}
	}
}