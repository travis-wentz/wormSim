import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * 
 * @author TravisWentz
 * CSCI 476 - Lab 3
 *
 */
class RectPanel extends JPanel {
	private int x;
	private int y;
	private int curComp = -1;
	private int[] computers = new int[10000];
	
	//creates 10000 squares of the correct color to represent computers
	public RectPanel(int[] computers){
		this.computers = computers;
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 1; i <= 100; i++) {
        	y = i;
        	for (int j = 1; j <= 100; j++) {
        		curComp++;
        		x = j;
        		draw(g);
        	}
        }
    }

    public void draw(Graphics g) {
        
        switch(computers[curComp]){
        case 1:
        	g.setColor(Color.white);
        	break;
        case 2:
        	g.setColor(Color.black);
        	break;
        case 3:
        	g.setColor(Color.orange);
        	break;
        case 4:
        	g.setColor(Color.red);
        	break;
        default:
        	g.setColor(Color.green);
        	break;
        }
        g.fillRect(x*7, y*7, 5, 5);
    }
}