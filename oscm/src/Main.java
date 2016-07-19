
public class Main {

	public static void main(String[] args) {
		System.out.println("Hello world!");
		
		int seed = 0;
		int i;// test**************************************************
		int[] initialInventory = {50, 30};
		
		//Demand demand = new ConstantDemand(10);
		Demand[] demand = {
				new UniformDemand(20, seed),
				new UniformDemand(30, seed)				
		};
		
		//Policy policy = new ConstantPolicy(50);
		Policy policy = new ReorderPointPolicy(42, 50);
		
		Simulator simulator = new Simulator(initialInventory,
				demand[0], policy, 20);  //demand[i]************************
		
	}
	
}
