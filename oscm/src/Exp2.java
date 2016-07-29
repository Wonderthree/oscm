import java.io.PrintStream;

/**
 * Scenario 2: developing country (e.g., African rural countries)
 * 
 * @author GuoHao
 *
 */
public class Exp2 {

	public static void main(String[] args) throws Exception {
		int[] initialInventory = {50, 30};

		int[] boxSize = {30, 20};
		int seed0 = 0;
		int seed1 = 42; 

		// average of 20 patients per day
		Demand[] demand = {
				// MMR
				new UniformDemand(20, seed0),
				// DTaP
				new UniformDemand(10, seed1) 			
		};
		//Demand demand = new ConstantDemand(10);

		int M = 30;
		double totalMean = 0;
		for (Demand d : demand) {
			totalMean += d.getMean();
		}
		// max inventory = M days of mean demand
		int maxInventory = (int) totalMean * M;
		// order up to days should be less than M 
		int orderUpToDays = Math.min(M, 30);

		// Note: cycle must be greater than lead time!
		int cycle = 5;		
		int leadTime = 3;

		int periods = 5 * 365;

		// run the smart policy with different base days values
		try (PrintStream out = new PrintStream("exp2-smartpolicy.csv")) {
			for (int baseDays = 1; baseDays <= 10; baseDays++) {
				Policy policy = new SmartPolicy(baseDays);

				Simulator simulator = new Simulator(cycle, leadTime, 
						initialInventory, maxInventory, boxSize,
						demand, policy, periods);

				out.printf("%d,%d,%.4f%n", baseDays,                                 
						simulator.getNumberOfShipments(),
						simulator.getUnmetDemandProportion());
			}
		}
		
		// run the days of stock policy with different base days values
		try (PrintStream out = new PrintStream("exp2-daysofstockpolicy.csv")) {
			for (int baseDays = 1; baseDays <= 10; baseDays++) {
				Policy policy = new DaysOfStockPolicy(baseDays, orderUpToDays);

				Simulator simulator = new Simulator(cycle, leadTime, 
						initialInventory, maxInventory, boxSize,
						demand, policy, periods);

				out.printf("%d,%d,%.4f%n", baseDays,                                 
						simulator.getNumberOfShipments(),
						simulator.getUnmetDemandProportion());
			}
		}
				
	}
	
}

