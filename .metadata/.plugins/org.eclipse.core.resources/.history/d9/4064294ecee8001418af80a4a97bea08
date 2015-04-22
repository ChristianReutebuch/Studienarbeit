package TSP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;

public class GraphArea implements MouseListener{
	//* Data Declaration*//
	JFrame frame = new JFrame("Travelling Salesman Problem");
    JPanel toolbar = new JPanel();
	JPanel checkbar = new JPanel();
	JPanel titelbar = new JPanel();
	JPanel controlbar = new JPanel();
	JPanel drawbar = new JPanel();
	ButtonGroup buttonGroup = new ButtonGroup();
	JCheckBox cbpoint = new JCheckBox("Point");
	JCheckBox cbline = new JCheckBox("Line");
	JButton btnhelp = new JButton("Help");
	JButton btnsave = new JButton("Save");
	JButton btncalc = new JButton("Calculate");
	JLabel lbltitel = new JLabel("Travelling Salesman Problem");
	int x,y, kreiszaehler;
	int kreise[][] = new int[10][10];
	boolean checkpoint;
	boolean checkline;
	
	Paint p = new Paint();
	
	JTable tblcontrol = new JTable(10,2);

	  public GraphArea() {
		lbltitel.setFont(new Font("Courier", Font.BOLD,40));
		buttonGroup.add(cbpoint);
		checkbar.add(cbpoint);
		buttonGroup.add(cbline);
		checkbar.add(cbline);                
		toolbar.add(btnhelp);
		toolbar.add(btnsave);
		toolbar.add(btncalc);
		drawbar.setLayout(new BorderLayout());

		drawbar.add(p);

		drawbar.addMouseListener(this);
		titelbar.add(lbltitel);
		frame.setBounds(0, 0, 800, 800);
		frame.setLayout(new BorderLayout());
		frame.add(drawbar, BorderLayout.CENTER);
		frame.add(checkbar, BorderLayout.EAST);
		frame.add(toolbar, BorderLayout.SOUTH);
		frame.add(titelbar, BorderLayout.NORTH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  }
	  
	  public void mousePressed(MouseEvent evt) {
//		  	if (cbpoint.isSelected() == false && cbline.isSelected() == false){
//		  		JOptionPane.showMessageDialog(frame,"Select graphics");
//		  	}
//		    if (kreiszaehler < 5 && cbpoint.isSelected() == true){
//			  	x = evt.getX();
//			    y = evt.getY();
//			    kreise[kreiszaehler][0] = x;
//			    kreise[kreiszaehler][1] = y;
//			    for (int i = 0; i<=kreiszaehler; i++){
//			    	System.out.println( kreise[i][0] + ", " + kreise[i][1]);
//			    }
//			    kreiszaehler++;
//			    checkpoint = cbpoint.isSelected();
//			    System.out.println(checkpoint);
////			    pc = new PaintCircle(x,y,20);
//			    frame.repaint();
//		    }
//		    if (cbline.isSelected() == true){
//		    	System.out.println("Line " + checkline);
//		    	checkline = cbline.isSelected();
////		    	pl = new PaintLine(x,y, 200, 200);
//		    }
//		    if( kreiszaehler == 5 && cbpoint.isSelected() == true){
//		    	JOptionPane.showMessageDialog(frame,"Reached max points.");
//		    }
		  }

	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
