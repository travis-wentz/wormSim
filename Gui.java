
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

	JFrame f1;
	JFrame f2;
	JPanel p1;
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
	Worm worm;
	
	private int n = 0;
	private int d = 0;
	private double p = 0;
	
	public Gui(){
		
	}
	
	//creates the first jframe
	public void frame1(boolean emptyField) {

		f1 = new JFrame();
		if(emptyField){
			emptyFieldLabel = new JLabel("***PLEASE MAKE SURE YOU HAVE ENTERED VALUES FOR ALL FIELDS***");
			emptyFieldLabel.setBounds(275, 375, 500, 20);
			f1.add(emptyFieldLabel);
		}
		vulnerable = new JTextField(20);  //columns, rows
		vulnerable.setBounds(50,230,100,20);  //x axis, y axis, width, height
		infecAttempts = new JTextField(20);
		infecAttempts.setBounds(400,230,100,20);
		
		vulnerableLabel = new JLabel("Number of vulnerable computers:");
		vulnerableLabel.setBounds(50, 200, 300, 20);
		infecLabel = new JLabel("Number of computers a worm tries to infect:");
		infecLabel.setBounds(400, 200, 300, 20);
		
		bgLabel = new JLabel("ReInfection Probability:");
		bgLabel.setBounds(800, 200, 300, 20);
		rb1 = new JRadioButton("p = 0");
		rb2 = new JRadioButton("p = 0.5");
		rb3 = new JRadioButton("p = 1");
		rb1.setBounds(800,230,80,30);
		rb2.setBounds(800,260,80,30);
		rb3.setBounds(800,290,80,30);
		rb3.setSelected(true);
		
		bg = new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);
		bg.add(rb3);
		
		b = new JButton("Go");
		b.setBounds(450,400,100, 40);  //x axis, y axis, width, height
		b.addActionListener(this);
		
		f1.add(b);
		f1.add(rb1);
		f1.add(rb2);
		f1.add(rb3);
		f1.add(vulnerable);
		f1.add(infecAttempts);
		f1.add(vulnerableLabel);
		f1.add(infecLabel);
		f1.add(bgLabel);
		
		f1.setSize(1000,735);  //width, height
		f1.setLayout(null);
		f1.setLocationRelativeTo(null);
		f1.setVisible(true);
		          
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//creates the second jframe
	public void frame2(int[] computers){
		JLabel whiteLabel = new JLabel("white squares - regular computers");
		JLabel blackLabel = new JLabel("black squares - vulnerable computers");
		JLabel orangeLabel = new JLabel("orange squares - infected computers");
		JLabel redLabel = new JLabel("red squares - reinfected computers");
		f2 = new JFrame("wormy worm worm worm");

		whiteLabel.setBounds(750, 20, 250, 20);
		blackLabel.setBounds(750, 40, 250, 20);
		orangeLabel.setBounds(750, 60, 250, 20);
		redLabel.setBounds(750, 80, 250, 20);
		
		f2.add(whiteLabel);
		f2.add(blackLabel);
		f2.add(orangeLabel);
		f2.add(redLabel);
		
		f2.getContentPane().add(new RectPanel(computers), BorderLayout.CENTER);
		
		f2.setSize(1000,735);  //width, height
  		f2.setLocationRelativeTo(null);
		f2.setVisible(true);
		          
		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e){
		if(vulnerable.getText().equals("") || infecAttempts.getText().equals("")){
			f1.dispose();
			frame1(true);
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
			f1.dispose();
			worm = new Worm(n, d, p);
//			frame2();
		}
	}
	
//	public static void main(String[] args) {
//		Gui gui = new Gui();
//		gui.frame1(false);
//	}
}
