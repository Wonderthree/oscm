
public class Main {

	public static void main(String[] args) {
		System.out.println("Hello world!");
		
		Demand demand = new ConstantDemand(10);
		Policy policy = new ConstantPolicy(5);
		int initialInventory = 20;
		Simulator simulator = new Simulator(initialInventory,
				demand, policy, 10);
		
	}
	
}
