
public class Main {

	public static void main(String[] args) {
		System.out.println("Hello world!");
		
		int[] initialInventory = {50, 30};
		int[] boxSize = {30, 20};
		
		//Demand demand = new ConstantDemand(10);
		int seed0 = 42;
		int seed1 = 42; 
		Demand[] demand = {
				new UniformDemand(20, seed0),
				new UniformDemand(10, seed1) 			
		};
		
		//Policy policy = new ConstantPolicy(50);
		Policy policy = new DaysOfStockPolicy(7, 10);
		
		Simulator simulator = new Simulator(initialInventory, boxSize,
				demand, policy, 5);
		
		// TODO for Maggie: Percentage of unmet demand??
		
	}	
}
