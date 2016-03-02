import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * 
 * @author TravisWentz
 * CSCI 476 - Lab 3
 *
 */
public class Worm{
	
	static Gui gui;
	
	private static int numComputers = 10000;
	private static Computer[] network = new Computer[numComputers];
	private static int nVulnerable; //vulnerable computers n<10,000
	private static int dInfections; //computers an infected computer tries to infect d
	private static double pReInfecProb; //random reinfection probability p
	private static int totalInfections = 0;
	
	public Worm(int n, int d, double p){
		nVulnerable = n;
		dInfections = d;
		pReInfecProb = p;
		
		addComputers();
		infect();
	}
	
	protected void addComputers(){
		int[] computers = new int[10000]; //1:white 2:black 3:orange 4:red
		
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
		for(int i = 0; i < network.length; i++){
			if(!network[i].getVulnerable()){
				computers[i] = 1;
			}else{
				computers[i] = 2;
			}
		}
		gui.frame2(computers);
	}
	
	protected void infect(){
		
		int[] computers = new int[10000]; //1:white 2:black 3:orange 4:red
		int newInfections = 0;
		int reInfections = 0;
		int overloaded = 0;
		int curComp;
		int node;
		Computer computer;
		
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
				computer = network[i];
				if(!computer.getVulnerable()){ //computer is not vulnerable
					computers[i] = 1;
				}else{
					if(computer.getInfections() < 1){ //computer is vulnerable and not infected
						computers[i] = 2;
					}else if(computer.getInfections() == 1){ //computer has been infected
						computers[i] = 3;
					}else if (computer.getInfections() > 1){	//computer has been reinfected
						computers[i] = 4;
					}
				}
			}
			gui.frame2(computers);
		}while(newInfections > 0 && overloaded < nVulnerable);
		System.out.println("Reinfections: " + reInfections);
		System.out.println("Total infections: " + totalInfections);
	}
	
	public static void main(String[] args) {
		gui = new Gui();
		gui.frame1(false);
	}
}
