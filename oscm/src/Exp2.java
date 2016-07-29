import java.io.FileNotFoundException;
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
		// max inventory = 30 days of mean demand
		int maxInventory = 900;

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

		int cycle = 1;
		int leadTime = 7;

		int periods = 5 * 365;

		int baseDays = 10;
		int orderUpToDays = 60;
		Policy[] policies = {
				new SmartPolicy(baseDays),
				new DaysOfStockPolicy(baseDays, orderUpToDays)
		};

		try (PrintStream out = new PrintStream("exp2.csv")) {
			for (Policy policy : policies) {
				Simulator simulator = new Simulator(cycle, leadTime, 
						initialInventory, maxInventory, boxSize,
						demand, policy, periods);

				out.printf("%d,%.4f,%d\n", baseDays,                                 
						simulator.getUnmetDemandProportion(),
						simulator.getNumberOfShipments());

				// number of shipments
			}
		}
		
		try (PrintStream out = new PrintStream("exp2_smartPolicy1.csv")) {
			for (baseDays = 10; baseDays <= 30; baseDays++) {
				System.out.printf("**********************************\n");
				Simulator simulator = new Simulator(cycle, leadTime, 
						initialInventory, maxInventory, boxSize,
						demand, policies[0], periods);

				out.printf("%d,%d%n", baseDays,                                 
						simulator.getNumberOfShipments());
			}
		}
		
		try (PrintStream out = new PrintStream("exp2_daysofstockPolicy1.csv")) {
			for (baseDays = 10; baseDays <= 30; baseDays++) {
				System.out.printf("**********************************\n");
				Simulator simulator = new Simulator(cycle, leadTime, 
						initialInventory, maxInventory, boxSize,
						demand, policies[1], periods);

				out.printf("%d,%d%n", baseDays,                                 
						simulator.getNumberOfShipments());
			}
		}
		
		try (PrintStream out = new PrintStream("exp2_smartPolicy2.csv")) {
			for (baseDays = 10; baseDays <= 30; baseDays++) {
				Simulator simulator = new Simulator(cycle, leadTime, 
						initialInventory, maxInventory, boxSize,
						demand, policies[0], periods);
				
				out.printf("%d,%.4f%n", baseDays,                                 
						simulator.getUnmetDemandProportion());
			
			}
		}
		
		try (PrintStream out = new PrintStream("exp2_daysofstockPolicy2.csv")) {
			for (baseDays = 10; baseDays <= 30; baseDays++) {
				Simulator simulator = new Simulator(cycle, leadTime, 
						initialInventory, maxInventory, boxSize,
						demand, policies[1], periods);
				
				out.printf("%d,%.4f%n", baseDays,                                 
						simulator.getUnmetDemandProportion());
			
			}
		}
	}
}

