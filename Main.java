import java.util.Scanner;

/**
 * 
 * @author TravisWentz
 * CSCI 476 - Lab 3
 *
 */
public class Main {
	
	private static int numComputers = 10;
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
		int[] computersToInfect = new int[dInfections];
		int curComp;
		//the worm begins
		int node = (int) (Math.random() * (network.length));
		network[node].infect();
		totalInfections++;
		//process stops if there is a round that didn't newly infect any computers
		//or if all vulnerable computers are overloaded
		do{
			overloaded = 0;
			newInfections = 0;
			for(int i = 0; i < network.length; i++){
				//count how many computers are overloaded
				if(network[i].getInfections() > 100){
					overloaded++;
				}
				//check if the current computer is infected
				if(network[i].getStatus() == true){
					//first randomly choose which computers to attack 
					//choose d random computers, a computer can't choose itself
					for(int j = 0; j < computersToInfect.length; j++){
						node = (int) (Math.random() * (network.length));
						if(node == i && i == 0){
							node++;
						}else if(node == i){
							node--;
						}
						computersToInfect[j] = node;
					}
					//try to infect the chosen computers
					for(int j = 0; j < computersToInfect.length; j++){
						curComp = computersToInfect[j];
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
								newInfections++;
								totalInfections++;
							}
						}
					}
				}
			}
			round++;
			System.out.println(round);
		}while(newInfections > 0 && overloaded < nVulnerable);
		if(newInfections > 0){
			System.out.println("no new infections");
		}else if(overloaded < nVulnerable){
			System.out.println("all vulnerable computers overloaded");
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Number of vulnerable computers (n<10,000): ");
		nVulnerable = in.nextInt();
		System.out.println("Number of computers a computer with a worm tries to infect (d): ");
		dInfections = in.nextInt();
		System.out.println("Random reinfection probability (0, 0.5, or 1): ");
		pReInfecProb = in.nextDouble();
		
		addComputers();
		infect();
		
		for(int i = 0; i < network.length; i++){
			System.out.println(network[i].getVulnerable());
		}
	}

}
