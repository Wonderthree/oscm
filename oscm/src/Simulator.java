import java.util.Arrays;

public class Simulator {

	private int cycle = 5;
	private int leadTime = 2;

	private int[] totalDemand;
	private int totalShipments=0;
	private int unmetDemands=0;

	public Simulator(int[] initialInventory, int[] boxSize, Demand[] demand, 
			Policy policy, int periods)
	{
		// K : number of products
		int K = initialInventory.length;
		// totalDemand[k] : total demand (met and unmet) for product k
		totalDemand = new int[K];
		
		// d : demand
		// t : time period
		// q[t][k] : order quantity in period t for product k
		// x[t][k] : quantity to arrive in period t for product k
		// x[t + leadTime][k] = q[t][k]
		int[][] q = new int[periods][K];
		int[][] x = new int[periods + leadTime][K];
		int[][] d = new int[periods][K];
		
		double[] meanDemand = new double[K];
		for (int k = 0; k < K; ++k) {
			meanDemand[k] = demand[k].getMean();
		}
		
		// TODO add box size, max inventory level
		// TODO lead times for shipments
		// TODO much much later: demand and policy for multiple drugs
		
		// inv[k] : inventory level of product k
		int[] inv = initialInventory;
		
		for (int t = 0; t < periods; ++t)
		{
			// i : beginning-of-period inventory level
			// create a copy so it will not be overwritten later in Step 3
			int[] i = MyUtils.copy(inv); 
			
			if (t % cycle == 0)
			{                          
				// Step 1: order new inventory every cycle period
				q[t] = policy.order(inv, boxSize, meanDemand);
				x[t + leadTime] = q[t];
				totalShipments+= q[t][1];
			}

			// Step 2: inventory arrives (after lead time)
			for (int k = 0; k < K; ++k) {
				inv[k] += x[t][k];
			}

			// Step 3: demand appears
			for (int k = 0; k < K; ++k) {
				d[t][k] = demand[k].getDemand();
				int nd=0;

				if (inv[k] >= d[t][k])
				{
					inv[k] -= d[t][k];
					totalDemand[k] +=d[t][k];	                  
				}
				else
				{
					unmetDemands += d[t][k] - inv[k];
					nd=inv[k];
					inv[k]-=nd;
					totalDemand[k] += d[t][k];
				}
			}

			// debugging code
			System.out.printf("//--------------------------\n");
			System.out.printf("// period = %d\n", t);
			System.out.printf("//--------------------------\n");
			for (int k = 0; k < K; ++k) {
				System.out.printf("product %d: i = %d, q = %d, d = %d, j = %d\n",
						k, i[k], q[t][k], d[t][k], inv[k]);
			}
			System.out.printf("\n\n\n");
		}                             

		// TODO print metrics such as:
		// average inventory level
		// unmet demand
		// met demand
		// TODO number of shipments using MyUtils.sum(x[t])
		
		
		System.out.println();
		System.out.println("Simulation periods: "+periods);
//		System.out.println("Total demands: "+ totalDemands);
		System.out.println("Total unmet demands: "+unmetDemands);
		System.out.println("Total shipments: "+totalShipments);

	}
}
