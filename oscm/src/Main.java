
public class Main {

	public static void main(String[] args) {
		System.out.println("Hello world!");
		
		int[] initialInventory = {50, 30};
		int[] boxSize = {30, 20};
		
		//Demand demand = new ConstantDemand(10);
		int seed0 = 0;
		int seed1 = 42; 
		Demand[] demand = {
				new UniformDemand(25, seed0),
				new UniformDemand(20, seed1) 			
		};
		
		Policy policy = new ConstantPolicy(50);
		//Policy policy = new DaysOfStockPolicy();
		
		Simulator simulator = new Simulator(initialInventory, boxSize,
				demand, policy, 20);
		
	}	
}
