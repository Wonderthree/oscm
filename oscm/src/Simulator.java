
public class Simulator {

	private int cycle = 5;
    private int leadTime = 2;
	
	public Simulator(int initialInventory, 
			Demand demand, Policy policy, int periods) {
		// Go to lesson 7.2
		int[] pipeline = new int[leadTime + 1];
		// pipeline[k] = shipment to arrive in period t + k
		
		// TODO add box size, max inventory level
		// TODO lead times for shipments
		// TODO much much later: demand and policy for multiple drugs
		int inv = initialInventory;
		for (int t = 0; t < periods; ++t) {
			// i: beginning-of-period inventory
			System.out.printf("i[%d] = %d, ", t, inv);

			int q = 0;
			if (t % cycle == 0) {
				// Step 1: order new inventory
				q = policy.order(inv);
				pipeline[leadTime] = q;
			}

			// Step 2: inventory arrives (after lead time)
			inv += q;
			// q: order quantity
			System.out.printf("q[%d] = %d, ", t, q);
			// Step 3: demand appears
			int d = demand.getDemand();
			// d: demand
			System.out.printf("d[%d] = %d, ", t, d);
			inv -= d;
			
			// TODO keep inventory positive, set unmet demand
			
			// j: end-of-period inventory
			System.out.printf("j[%d] = %d\n", t, inv);
			
			// update the pipeline
			// TODO
		}

		// TODO print metrics such as:
		// average inventory level
		// unmet demand
		// met demand
		// number of shipments
		// etc.
	}

}
