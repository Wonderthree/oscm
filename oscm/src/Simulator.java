
public class Simulator {

	private int cycle = 5;
	private int leadTime = 2;

	private int totalDemands=0;
	private int totalShipments=0;
	private int unmetDemands=0;

	public Simulator(int initialInventory, Demand demand, Policy policy, int periods)
	{
		// d : demand
		// t : time period
		// q[t] : order quantity in period t
		// x[t] : quantity to arrive in period t
		// x[t + leadTime] = q[t]
		int[] q = new int[periods];
		int[] x = new int[periods + leadTime];

		int[] pipeline = new int[leadTime + 1];
		// pipeline[k] = shipment to arrive in period t + k

		// TODO add box size, max inventory level
		// TODO lead times for shipments
		// TODO much much later: demand and policy for multiple drugs
		int inv = initialInventory; // i: beginning-of-period inventory
		for (int t = 0; t < periods; ++t)
		{ 
			if (t % cycle == 0)
			{                          
				// Step 1: order new inventory every cycle period
				q[t] = policy.order(inv);
				x[t + leadTime] = q[t];
				totalShipments+= q[t];
			}

			// Step 2: inventory arrives (after lead time)

			inv += x[t];
			System.out.printf("i[%2d] = %2d, ", t, inv);

			System.out.printf("q[%2d] = %2d, ", t, q[t]);
			// Step 3: demand appears

			int d = demand.getDemand();
			int nd=0;

			if(inv>=d)
			{
				inv -= d;
				totalDemands+=d;	                  
			}
			else
			{
				unmetDemands+=d-inv;
				nd=inv;
				inv-=nd;
				totalDemands+=d;
			}
			System.out.printf("d[%2d] = %2d, ", t, d);

			System.out.printf("j[%2d] = %2d, ", t, inv);    // j: end-of-period inventory

			for (int l = 1; l <= leadTime; ++l) {
				System.out.printf("%d (%d)", x[t + l], l);
			}
			if ((t-0) % (cycle) == 0)                       //TODO update the pipeline
			{
				System.out.printf("p[%2d] = %2d(1)\n", t, pipeline[leadTime]);
			}
			else if((t-1) % (cycle) == 0){
				System.out.printf("p[%2d] = %2d(2)\n", t, pipeline[leadTime]);
			}
			else
			{
				System.out.printf("p[%2d] = %2d\n", t, pipeline[leadTime-1]);
			}						

		}                             

		// TODO print metrics such as:
		// average inventory level
		// unmet demand
		// met demand
		// number of shipments
		// etc.
		System.out.println();
		System.out.println("Simulation periods: "+periods);
		System.out.println("Total demands: "+totalDemands);
		System.out.println("Total unmet demands: "+unmetDemands);
		System.out.println("Total shipments: "+totalShipments);

	}
}
