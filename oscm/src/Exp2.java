import java.io.PrintStream;

/**
 * Scenario 2: developing country (e.g., African rural countries)
 * 
 * @author GuoHao
 *
 */
public class Exp2 {

	public static void main(String[] args) throws Exception {
		// Note: cycle must be greater than lead time!
		int cycle = 10;		
		int leadTime = 8;
		// order up to days should be less than M 
		//int orderUpToDays = Math.min(M, 30);
		int orderUpToDays = 30;
		
		int baseDaysMax = 30;
		
		int[] initialInventory = {40, 80, 70, 90, 80, 60, 45, 65, 100, 140};

		int[] boxSize = {100, 100, 100, 100, 100, 
				100, 100, 100, 100, 100};

		Demand[] demand = {
				new UniformDemand(10, 0),
				new UniformDemand(10, 1),
				new UniformDemand(10, 2),
				new UniformDemand(10, 3),
				new UniformDemand(10, 4),
				new UniformDemand(10, 5),
				new UniformDemand(10, 6),
				new UniformDemand(10, 7),
				new UniformDemand(10, 8),
				new UniformDemand(10, 9) 			
		};
		//Demand demand = new ConstantDemand(10);

		int M = 30;
		double totalMean = 0;
		for (Demand d : demand) {
			totalMean += d.getMean();
		}
		// max inventory = M days of mean demand
		int maxInventory = (int) totalMean * M;

		int periods = 5 * 365;

		// run the smart policy with different base days values
		try (PrintStream out = new PrintStream("exp2-smartpolicy.csv")) {
			for (int baseDays = 1; baseDays <= baseDaysMax; baseDays++) {
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
			for (int baseDays = 1; baseDays <= baseDaysMax; baseDays++) {
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

