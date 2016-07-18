
public class Main {

	public static void main(String[] args) {
		System.out.println("Hello world!");
		
		int initialInventory = 50;
		//Demand demand = new ConstantDemand(10);
		Demand demand=new UniformDemand(20,42);
		//Policy policy = new ConstantPolicy(5);
		Policy policy = new ReorderPointPolicy(42, 50);
		Simulator simulator = new Simulator(initialInventory,
				demand, policy, 20);
		
	}
	
}
