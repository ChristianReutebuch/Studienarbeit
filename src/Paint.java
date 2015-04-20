import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Paint extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public Paint(){ 
        super(); 
    } 
	public void paint(Graphics g){ 
		Color customColor = new Color(10,10,255); 
        g.setColor(customColor);
        g.fillOval(40, 40, 40, 40);
        g.fillOval(80, 80, 40, 40);
        g.fillOval(40, 120, 40, 40);
    } 
	public Dimension getPreferredSize() {
      return new Dimension(400, 400);
	}
}
