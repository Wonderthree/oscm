
public class Main {

	public static void main(String[] args) {
		System.out.println("Hello world!");
		
		//Demand demand = new ConstantDemand(10);
		Demand demand=new UniformDemand(20,0);
		//Policy policy = new ConstantPolicy(5);
		Policy policy = new ReorderPointPolicy(10, 50);
		int initialInventory = 20;
		Simulator simulator = new Simulator(initialInventory,
				demand, policy, 10);
		
	}
	
}
