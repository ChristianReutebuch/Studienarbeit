import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

public class GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	
	GUI() {
		initComponents();
	}
	
	private void initComponents() {
		JPanel toolbarPanel = new JPanel();
		JButton btnHelp = new JButton("Help");
		JButton btnSave = new JButton("Save");
		JButton btnOpen = new JButton("Open");
		JToolBar toolbar = new JToolBar("JToolBar");
		
		//---------LearnTable-------------//
		// Die Daten für das Table
		Vector data = new Vector();
			Vector rowA = new Vector();
				rowA.add(  "1" );
				rowA.add(  "2" );
				rowA.add(  "3" );
				rowA.add(  "4" );
			Vector rowB = new Vector();
				rowB.add(  "5" );
				rowB.add(  "6" );
				rowB.add(  "7" );
				rowB.add(  "8" );
			Vector rowC = new Vector();
				rowC.add(  "9" );
				rowC.add( "10" );
				rowC.add( "11" );
				rowC.add( "12" );
 
			data.add( rowA );
			data.add( rowB );
			data.add( rowC );
 
		// Die Titel für das Table
		Vector title = new Vector();
			title.add( "A" );
			title.add( "B" );
			title.add( "C" );
			title.add( "D" );
		//---------LearnTable-------------//
			
		//---------Verzeichnis------------//
		//5-zeiliges und 20-spaltiges Textfeld wird erzeugt
	    JTextArea textfeld = new JTextArea(5, 20);
	 
	    //Text für das Textfeld wird gesetzt
	    textfeld.setText("Lorem ipsum dolor sit amet, " +
	        		"consetetur sadipscing elitr, sed diam nonumy " +
	        		"eirmod tempor invidunt ut labore et " +
	        		"dolore magna aliquyam erat, sed diam voluptua. " +
	        		"At vero eos et accusam et justo duo dolores et " +
	                        "ea rebum.");	
	  //Zeilenumbruch wird eingeschaltet
        textfeld.setLineWrap(true);
	    //---------Verzeichnis------------//
	    
		JTable table = new JTable(  data, title );
		toolbar.add(btnHelp);
		toolbar.add(btnSave);
		toolbar.add(btnOpen);
		toolbarPanel.add(toolbar);
		this.setLayout(new BorderLayout());
		this.add(toolbarPanel, BorderLayout.NORTH);
		this.add(table, BorderLayout.EAST);
		this.add(textfeld, BorderLayout.WEST);
		this.add(new Paint(), BorderLayout.CENTER);
		this.setSize(400,400);
		this.setVisible(true);
	}
}