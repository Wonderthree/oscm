import java.util.Arrays;

public class Simulator {

	private int cycle = 5;
	private int leadTime = 2;

	private int[] totalDemand;
	private int totalShipment;
	private int[] unmetDemand;
	//private int totalShipments0=0;
	//private int totalShipments1=0;
	
	
	public Simulator(int[] initialInventory, int[] boxSize, Demand[] demand, 
			Policy policy, int periods)
	{
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
		int[][] q = new int[periods][K];       //******************************************2
		int[][] x = new int[periods + leadTime][K];
		int[][] d = new int[periods][K];
		
		double[] meanDemand = new double[K];
		for (int k = 0; k <K; ++k) {          //********************************************
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
			
			
			// Step 1: order new inventory every cycle period
			if (t % cycle == 0)  
			{                          
				q[t] = policy.order(inv, boxSize, meanDemand); 
				//daily replenishment is designed, but it is subject to this ordering cycle
				                                               
				x[t + leadTime] = q[t];
				//totalShipments0 += q[t][0]; 
				//totalShipments1 += q[t][1]; 
			}
			
			
					
			// Step 2: inventory arrives (after lead time)
			for (int k = 0; k < K; ++k) {    
				i[k] += x[t][k];         
			}

			
			
			// Step 3: demand appears
			for (int k = 0; k < K; ++k) {
				d[t][k] = demand[k].getDemand();    //********
				
				totalDemand[0] += d[t][0];	   
				totalDemand[1] += d[t][1];

				if (i[k] >= d[t][k])
				{
					inv[k] = i[k] - d[t][k];
					//totalDemand[0] +=d[t][0];	   
					//totalDemand[1] +=d[t][1];
				}
				else
				{
					unmetDemand[k] += d[t][k] - i[k];
					//nd=inv[k];
					//inv[k]-=nd;
					inv[k] = i[k] - i[k]; 
					//totalDemand[0] += d[t][0];     
					//totalDemand[1] += d[t][1];
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
			System.out.printf("\n\n"); //System.out.println();
			//totalShipment[0]=MyUtils.sum(x[t+leadTime]);
		}                             
		
		// TODO print metrics such as:
		// average inventory level
		// unmet demand
		// met demand
		
		// TODO number of shipments using MyUtils.sum(x[t])
		//System.out.println(x.length);
		
		// convert 2-D Array into 1-D Array ************************************************
		// divide 2-D Array into 2 1-D Array
		int index = 0;
		int[] n = new int[x.length];                            
		int[] m = new int[x.length];
		
		// to calculate the length of new 1-D Array
		//int len = 0;
		//for(int i = 0;i < x.length; i++){
			//len += x[i].length;
		//}                       
		
	   //n = new int[len/2];
	   //m = new int[len/2];
		
		for(int i = 0; i < x.length;i++){
			//for(int j = 0; j < x[i].length/2; j++){
				n[index] = x[i][0];
				m[index] = x[i][1];
				index++;
			//}
		}
		
		//int totalShipment0 = MyUtils.sum(n);
		//int totalShipment1 = MyUtils.sum(m); 
		int[] totalShipment = {MyUtils.sum(n),MyUtils.sum(m)};//****************************
		
		
		
		System.out.println("Simulation periods: "+periods);
		System.out.printf("\n");
		System.out.println("Product 0 total demand: "+ totalDemand [0]);
		System.out.println("Product 1 total demand: "+ totalDemand [1]);
		System.out.printf("\n");
		System.out.println("Product 0 average demand: "+ meanDemand [0]);
		System.out.println("Product 1 average demand: "+ meanDemand [1]);
		System.out.printf("\n");
		System.out.println("Product 0 total unmet demand: "+unmetDemand[0]);
		System.out.println("Product 1 total unmet demand: "+unmetDemand[1]);
		System.out.printf("\n");
		System.out.println("Product 0 total shipment: "+totalShipment[0]);
		System.out.println("Product 1 total shipment: "+totalShipment[1]);
		
	}
	
}
