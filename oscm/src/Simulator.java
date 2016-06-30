
public class Simulator {

	public Simulator(int initialInventory, 
			Demand demand, Policy policy, int periods) {
		// TODO add box size, max inventory level
		// TODO lead times for shipments
		// TODO much much later: demand and policy for multiple drugs
		int inv = initialInventory;
		for (int t = 0; t < periods; ++t) {
			// i: beginning-of-period inventory
			System.out.printf("i[%d] = %d, ", t, inv);
			// Step 1: order new inventory
			int q = policy.order(inv);
			// Step 2: inventory arrives (after lead time)
			inv += q;
			// q: order quantity
			System.out.printf("q[%d] = %d, ", t, q);
			// Step 3: demand appears
			int d = demand.getDemand();
			// d: demand
			System.out.printf("d[%d] = %d, ", t, d);
			inv -= d;
			// j: end-of-period inventory
			System.out.printf("j[%d] = %d\n", t, inv);
		}
		
		// TODO print metrics such as:
		// average inventory level
		// unmet demand
		// met demand
		// number of shipments
		// etc.
	}
	
}
