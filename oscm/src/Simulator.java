import java.util.Arrays;

public class Simulator {

	private final int cycle;
	private final int leadTime;

	private int[] totalDemand;
	private int totalShipment;
	private int[] unmetDemand;
	private double[] unmetDemandPercent;
	private int counterOfShipment = 0;

	private boolean shouldPrint = false;

	public Simulator(int cycle, int leadTime, int[] initialInventory, int maxInventory, int[] boxSize, 
			Demand[] demand, Policy policy, int periods)
	{
		this.cycle = cycle;
		this.leadTime = leadTime;
		
		// K : number of products
		int K = initialInventory.length;

		// totalDemand[k] : total demand (met and unmet) for product k
		totalDemand = new int[K];
		unmetDemand = new int[K]; 
		// d : demand
		// t : time period
		// q[t][k] : order quantity in period t for product k
		// x[t][k] : quantity to arrive in period t for product k
		// x[t + leadTime][k] = q[t][k]
		int[][] q = new int[periods][K];       
		int[][] x = new int[periods + leadTime][K];
		int[][] d = new int[periods][K];

		double[] meanDemand = new double[K];
		for (int k = 0; k <K; ++k) {          
			meanDemand[k] = demand[k].getMean();
		}

		// TODO add box size, max inventory level
		// TODO much much later: demand and policy for multiple drugs

		// inv[k] : inventory level of product k
		int[] inv = initialInventory;        

		for (int t = 0; t < periods; ++t)
		{
			// i : beginning-of-period inventory level
			// create a copy so it will not be overwritten later in Step 3
			int[] i = MyUtils.copy(inv);  


			// Step 1: order new inventory every cycle period
			if (t % cycle == 0)  
			{    
				//daily replenishment is set, but it is subject to this ordering cycle
				q[t] = policy.order(maxInventory, inv, boxSize, meanDemand); 
				// .order is an Array which contains 2 elements, therefore when the equation
				// is looped, q[t] becomes a 2-D Array.*************************************		                                               

				x[t + leadTime] = q[t];
				// Similarly,X[][] & q[][] is define to be 2-D Array previously which is now 
				// applied as 1-D Array here  

				// x[0](namely x[0][0] & x[0][1]) is 0, because no value is assigned and the
				// default value are 0s.
			}



			// Step 2: inventory arrives (after lead time)
			for (int k = 0; k < K; ++k) {    
				i[k] += x[t][k];     
				// i[0] = i[0](initialInventory0) + x[0][0](0) = 0
				// i[1] = i[1](initialInventory1) + x[0][1](0) = 0
				// i[k] is a changing variable in every time period	
			}



			// Step 3: demand appears
			for (int k = 0; k < K; ++k) {
				d[t][k] = demand[k].getDemand();   

				//totalDemand[0] += d[t][0];  wrong way to calculate total demand
				//totalDemand[1] += d[t][1];
				totalDemand[k] += d[t][k]; 

				if (i[k] >= d[t][k]){
					inv[k] = i[k] - d[t][k];
				}
				else{
					unmetDemand[k] += d[t][k] - i[k];
					inv[k] = i[k] - i[k]; 
				}
			}


			if (shouldPrint) {
				// debugging code
				System.out.printf("//--------------------------\n");
				System.out.printf("// period = %d\n", t);
				System.out.printf("//--------------------------\n");

				for (int k = 0; k < K; ++k) {
					System.out.printf("product %d: i = %d, q = %d, d = %d, j = %d\n",
							k, i[k], q[t][k], d[t][k], inv[k]);
				}
				System.out.printf("\n\n");
			}

			// number of shipments
			int[] numberOfShipment = new int[periods];
			for (int k = 0; k < K; ++k){
				numberOfShipment[t] += MyUtils.sum(q[t]);
			}
			if(numberOfShipment[t] != 0){
				counterOfShipment++;
			}

		}                             




		int[] totalShipment = new int[K];
		for (int k = 0; k < K; ++k) {
			int[] temp = new int[q.length];
			for (int t = 0; t < q.length; ++t) {
				temp[t] = q[t][k];
			}
			totalShipment[k] = MyUtils.sum(temp);
		}



		double[] unmetDemandPercent = new double[K];
		for (int k = 0; k < K; ++k) {
			unmetDemandPercent[k] = MyUtils.doubleDivision(unmetDemand[k], totalDemand[k]);
		}




		System.out.println("Simulation periods: "+periods);
		System.out.printf("\n");
		for (int k = 0; k < K; ++k) {
			System.out.printf("Product %d total demand: %d\n", k, totalDemand [k]);
		}
		System.out.printf("\n");
		for (int k = 0; k < K; ++k) {
			System.out.printf("Product %d total shipment: %d\n", k, totalShipment[k]);
		}
		System.out.printf("\n");
		for (int k = 0; k < K; ++k) {
			System.out.printf("Product %d average demand: %.1f\n", k, meanDemand[k]);
		}
		System.out.printf("\n");
		for (int k = 0; k < K; ++k) {
			System.out.printf("Product %d total unmet demand: %d\n", k, unmetDemand[k]);
		}
		System.out.printf("\n");
		for (int k = 0; k < K; ++k) {
			System.out.printf("Product %d unmetDemandPercentage: %.6s\n", k, unmetDemandPercent[k]);
		}
		System.out.printf("\n");
		System.out.printf("Policy = %s%n", policy.toString());    //*************************************************************
		System.out.printf("\n");
		System.out.printf("Number of Shipment: %d%n%n", counterOfShipment);

	}



	public double getUnmetDemandProportion() {
		int td = MyUtils.sum(totalDemand);
		int ud = MyUtils.sum(unmetDemand);
		return MyUtils.doubleDivision(ud, td);
	}

	public int getNumberOfShipments() {
		return counterOfShipment;
	}

}
