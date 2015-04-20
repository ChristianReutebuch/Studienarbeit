package Testumgebung;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ZeichnenPanel implements MouseListener {
	JFrame frame = new JFrame("Travelling Salesman Problem");
	JPanel zeipan = new JPanel();
	JPanel conpan = new JPanel();
	JPanel chepan = new JPanel();
	JTextArea txtpos = new JTextArea();
	JTextArea txtche = new JTextArea();
	ButtonGroup btngrche = new ButtonGroup();
	JCheckBox cbkreis = new JCheckBox("Kreis");
	JCheckBox cblinie = new JCheckBox("Linie");
	int xpos, ypos;
	List<ZeichneObjekt> list = new ArrayList<ZeichneObjekt>();
//	ZeichneObjekt zo = new ZeichneObjekt(xpos,ypos,100,100);
	
	public ZeichnenPanel(){
		frame.setLayout(new BorderLayout());
		frame.add(conpan, BorderLayout.EAST);
		frame.add(zeipan, BorderLayout.CENTER);
		frame.add(chepan, BorderLayout.WEST);
		frame.setBounds(0,0,800,400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		zeipan.setLayout(new BorderLayout());
//		zeipan.add(zo, BorderLayout.CENTER);
		zeipan.addMouseListener(this);
		zeipan.setVisible(true);
		
		conpan.setLayout(new BorderLayout());
		conpan.add(txtpos, BorderLayout.CENTER);
		conpan.add(txtche, BorderLayout.SOUTH);
		conpan.setVisible(true);
		
		btngrche.add(cblinie);
		btngrche.add(cbkreis);
		chepan.add(cblinie);
		chepan.add(cbkreis);
	}
	@Override
	public void mousePressed(MouseEvent me) {
		xpos = me.getX();
		ypos = me.getY();
		txtpos.setText("X: " + xpos + " Y: " + ypos);
//		frame.repaint();
		checkFigur();
		zeichnen();
	}
	public void checkFigur(){
		if(cblinie.isSelected() == true){
			txtche.setText("Figur Linie ist ausgewählt.");
//			list.add(new ZeichneLinie(xpos,ypos,100,100));
		}
		if(cbkreis.isSelected() == true){
			txtche.setText("Figur Kreis ist ausgewählt.");
			list.add(new ZeichneKreis(xpos, ypos,100,100));
		}
	}
	public void zeichnen(){
		for(int i = 0; i < list.size(); i++){
//			zo = new ZeichneLinie(xpos,ypos,100,100);
			ZeichneObjekt zo = list.get(i);
			zo.zeichnen(zeipan.getGraphics());
			System.out.println("ZeichneLinie. Listengröße: "+list.size());
		}
	}
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
