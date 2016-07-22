
public class Main {

	public static void main(String[] args) {
		System.out.println("Hello world!");
		
		int[] initialInventory = {50, 30};
		
		int maxInventory = 300;
		
		int[] boxSize = {30, 20};
		
		
		int seed0 = 0;
		int seed1 = 42; 
		Demand[] demand = {
				new UniformDemand(20, seed0),
				new UniformDemand(10, seed1) 			
		};
		//Demand demand = new ConstantDemand(10);
		
		
		
		Policy policy = new DaysOfStockPolicy(7, 10);
		//Policy policy = new ConstantPolicy(50);
		//Policy policy = new SmartPolicy(7, 10);
		
		
		int periods = 20;
		
		
		Simulator simulator = new Simulator(initialInventory, maxInventory, boxSize,
				demand, policy, periods);
		
		// TODO for Maggie: Percentage of unmet demand??
		
	}	
}
