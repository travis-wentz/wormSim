
public class Computer {

	private boolean vulnerable = false;
	private boolean infected = false;
	private int numInfections = 0;
	private int activeRound = 0;
	
	public Computer(){
		
	}
	
	public void makeVulnerable(){
		vulnerable = true;
	}
	
	public boolean getVulnerable(){
		return vulnerable;
	}
	
	public void infect(){
		infected = true;
		numInfections++;
	}
	
	public boolean getStatus(){
		return infected;
	}
	
	public int getInfections(){
		return numInfections;
	}
	
	public void wormActiveRound(int round){
		activeRound = round;
	}
	
	public int getWormActiveRound(){
		return activeRound;
	}
}
