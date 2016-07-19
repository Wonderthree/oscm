
public class Main {

	public static void main(String[] args) {
		System.out.println("Hello world!");
		
		int seed = 0;
		int i;// test**************************************************
		int[] initialInventory = {50, 30};
		int[] boxSize = {30, 20};
		
		//Demand demand = new ConstantDemand(10);
		Demand[] demand = {
				new UniformDemand(20, seed),
				new UniformDemand(30, seed)				
		};
		
		Policy policy = new ConstantPolicy(50);
		//Policy policy = new DaysOfStockPolicy();
		
		Simulator simulator = new Simulator(initialInventory, boxSize,
				demand, policy, 20);  //demand[i]************************
		
	}
	
}
