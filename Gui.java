
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * 
 * @author TravisWentz
 * CSCI 476 - Lab 3
 *
 */
public class Gui extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	JFrame f;
	JButton b;
	JRadioButton rb1;
	JRadioButton rb2;
	JRadioButton rb3;
	ButtonGroup bg;
	JTextField vulnerable;
	JTextField infecAttempts;
	JLabel vulnerableLabel;
	JLabel infecLabel;
	JLabel bgLabel;
	JLabel emptyFieldLabel;
	
	int n = 0;
	int d = 0;
	double p = 0;
	
	public Gui(boolean foo) {
		int vx = 50; //vulnerable comps x
		int vy = 200; //vulnerable comps y
		int ix = 400; //infect comps x
		int iy = vy; //incect comps y
		int rbx = 800; //probability buttons x
		int rby = vy; //probability buttons y
		
		f = new JFrame("wormy worm worm");
		
		if(foo){
			emptyFieldLabel = new JLabel("***PLEASE MAKE SURE YOU HAVE ENTERED VALUES FOR ALL FIELDS***");
			emptyFieldLabel.setBounds(275, 375, 500, 20);
			f.add(emptyFieldLabel);
		}
		
		vulnerable = new JTextField(20);  //columns, rows
		vulnerable.setBounds(vx,vy + 30,100,20);  //x axis, y axis, width, height
		infecAttempts = new JTextField(20);
		infecAttempts.setBounds(ix,iy + 30,100,20);
		
		vulnerableLabel = new JLabel("Number of vulnerable computers:");
		vulnerableLabel.setBounds(vx, vy, 300, 20);
		infecLabel = new JLabel("Number of computers a worm tries to infect:");
		infecLabel.setBounds(ix, iy, 300, 20);
		
		bgLabel = new JLabel("ReInfection Probability:");
		bgLabel.setBounds(rbx, rby, 300, 20);
		rb1 = new JRadioButton("p = 0");
		rb2 = new JRadioButton("p = 0.5");
		rb3 = new JRadioButton("p = 1");
		rb1.setBounds(rbx,rby + 30,80,30);
		rb2.setBounds(rbx,rby + 60,80,30);
		rb3.setBounds(rbx,rby + 90,80,30);
		rb3.setSelected(true);
		
		bg = new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);
		bg.add(rb3);
		
		b = new JButton("Go");
		b.setBounds(450,400,100, 40);  //x axis, y axis, width, height
		b.addActionListener(this);
		
		f.add(b);
		f.add(rb1);
		f.add(rb2);
		f.add(rb3);
		f.add(vulnerable);
		f.add(infecAttempts);
		f.add(vulnerableLabel);
		f.add(infecLabel);
		f.add(bgLabel);
		
		f.setSize(1000,600);  //width, height
		f.setLayout(null);  
		f.setVisible(true);  
		          
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e){
		if(vulnerable.getText().equals("") || infecAttempts.getText().equals("")){
			f.dispose();
			new Gui(true);
		}else{
			String x = vulnerable.getText();
			String y = infecAttempts.getText();
			n = Integer.parseInt(x);
			d = Integer.parseInt(y);
			if(rb1.isSelected()){
				p = 0;
			}else if(rb2.isSelected()){
				p = 0.5;
			}else if(rb3.isSelected()){
				p = 1;
			}
			f.dispose();
			Worm worm = new Worm();
			worm.activateWorm(n, d, p);
		}
	}
	
	public static void main(String[] args) {
		new Gui(false);
	}
}
