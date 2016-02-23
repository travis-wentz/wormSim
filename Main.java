import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

/**
 * 
 * @author TravisWentz
 * CSCI 476 - Lab 3
 *
 */
public class Main extends JFrame{
	
	private static int numComputers = 10000;
	private static Computer[] network = new Computer[numComputers];
	private static int nVulnerable; //vulnerable computers n<10,000
	private static int dInfections; //computers an infected computer tries to infect d
	private static double pReInfecProb; //random reinfection probability p
	private static int totalInfections = 0;
	
	private static void addComputers(){
		//adding all the computers to the network
		for(int i = 0; i < network.length; i++){
			Computer curComp = new Computer();
			network[i] = curComp;
		}
		for(int i = 0; i < nVulnerable; i++){
			boolean marked = false;
			while(!marked){
				int node = (int) (Math.random() * (network.length));
				if(network[node].getVulnerable() == false){
					network[node].makeVulnerable();
					marked = true;
				}
			}
		}
	}
	
	private static void infect(){
		int newInfections = 0;
		int reInfections = 0;
		int round = 0;
		int overloaded = 0;
		int curComp;
		int node;
		//the worm begins
		do{
			node = (int) (Math.random() * (network.length));
		}while(network[node].getVulnerable() == false);
		network[node].infect();
		totalInfections++;
		//process stops if there is a round that didn't newly infect any computers
		//or if all vulnerable computers are overloaded
		do{
			overloaded = 0;
			newInfections = 0;
			for(int i = 0; i < network.length; i++){
				ArrayList<Integer> computersToInfect = new ArrayList<Integer>();
				//count how many computers are overloaded
				if(network[i].getInfections() > 100){
					overloaded++;
				}
				//check if the current computer is infected
				if(network[i].getStatus() == true){
					//first randomly choose d computers to attack 
					//a computer can't choose itself or choose the same computer twice in a round
					for(int j = 0; j < dInfections; j++){
						do{
							node = (int) (Math.random() * (network.length));
						}while(node == i || computersToInfect.contains(node));
						computersToInfect.add(node);
					}
					//try to infect the chosen computers
					for(int j = 0; j < dInfections; j++){
						curComp = computersToInfect.get(j);
						//if vulnerable && !infected - infect
						if(network[curComp].getVulnerable() && !network[curComp].getStatus()){
							network[curComp].infect();
							newInfections++;
							totalInfections++;
						//else if vulnerable && infected - do probability stuff
						}else if(network[curComp].getVulnerable() && network[curComp].getStatus()){
							double infects = Math.random();
							if(infects < pReInfecProb){
								network[curComp].infect();
								reInfections++;
								totalInfections++;
							}
						}
					}
				}
			}
			round++;
			System.out.println(round);
		}while(newInfections > 0 && overloaded < nVulnerable);
		if(newInfections <= 0){
			System.out.println("no new infections");
		}else if(overloaded >= nVulnerable){
			System.out.println("all vulnerable computers overloaded");
		}else{
			System.out.println("other failure");
		}
//		System.out.println("Rounds: " + round);
		System.out.println("Reinfections: " + reInfections);
		System.out.println("Total infections: " + totalInfections);
	}

	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		
//		System.out.println("Number of vulnerable computers (n<10,000): ");
//		nVulnerable = in.nextInt();
//		System.out.println("Number of computers a computer with a worm tries to infect (d): ");
//		dInfections = in.nextInt();
//		System.out.println("Random reinfection probability (0, 0.5, or 1): ");
//		pReInfecProb = in.nextDouble();
//		
//		in.close();
//		
//		addComputers();
//		infect();
		
		JFrame f = new JFrame(); 
        
		JButton b1 = new JButton("Go");
		b1.setBounds(520,600,100, 40);//x axis, y axis, width, height
		f.add(b1);
		
//		JButton b2 = new JButton("bar");
//		b2.setBounds(520, 240, 100, 40);
//		f.add(b2);
		
		JRadioButton r1 = new JRadioButton("p = 0"); 
		r1.setBounds(50,440,100,30);
		JRadioButton r2 = new JRadioButton("p = 0.5");
		r2.setBounds(50,470,100,30);
		JRadioButton r3 = new JRadioButton("p = 1", true);
		r3.setBounds(50,500,100,30);
		  
		ButtonGroup bg1=new ButtonGroup();  
		bg1.add(r1);
		bg1.add(r2);
		bg1.add(r3);
		  
		f.add(r1);
		f.add(r2);
		f.add(r3);
		          
		f.setSize(1200,700);//1200 width and 700 height  
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible 
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
