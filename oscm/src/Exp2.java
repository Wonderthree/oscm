import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Exp2 {

	public static void main(String[] args) throws Exception {
		int[] initialInventory = {50, 30};
		int maxInventory = 300;

		int[] boxSize = {30, 20};


		int seed0 = 0;
		int seed1 = 42; 
		Demand[] demand = {
				new UniformDemand(20, seed0),
				new UniformDemand(10, seed1) 			
		};
		//Demand demand = new ConstantDemand(10);
		
		

		int periods = 40;



		try (PrintStream out = new PrintStream("exp2.csv")) {
			for (int b = 1; b <= 10; ++b) {
				Policy policy = new SmartPolicy(b, 10);
				//Policy policy = new DaysOfStockPolicy(b,10);


				Simulator simulator = new Simulator(initialInventory, maxInventory, boxSize,
						demand, policy, periods);

				out.printf("%d,%.4f,%d%n", b,                                 
						simulator.getUnmetDemandProportion(),
						simulator.getNumberOfShipments());
				
				// number of shipments
			}
		}
	}
}
