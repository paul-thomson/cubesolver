package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

/**
 * The GUI which is displayed on top of the OpenGL stuff
 *
 */
@SuppressWarnings("serial")
public class Overlay extends JPanel {
	
	public Overlay() {
		setOpaque(false); 
		setBackground(new Color(0,0,0,0.7f));
	}
	
	public void paintComponent(Graphics g) {
        g.setColor(getBackground());
        Rectangle r = g.getClipBounds();
        g.fillRect(r.x, r.y, r.width, r.height);
        super.paintComponent(g);
    }

}
