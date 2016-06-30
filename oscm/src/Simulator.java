
public class Simulator {

	public Simulator(Demand demand, Policy policy, int periods) {
		// TODO add box size, max inventory level
		// TODO lead times for shipments
		// TODO much much later: demand and policy for multiple drugs
		int inv = 0;
		for (int t = 0; t < periods; ++t) {
			System.out.printf("i[%d] = %d", t, inv);
			int q = policy.order(inv);
			inv += q;
			System.out.printf("q[%d] = %d", t, q);
			int d = demand.getDemand();
			System.out.printf("d[%d] = %d", t, d);
			inv -= q;
			System.out.printf("d[%d] = %d\n", t, inv);
		}
	}
	
}
