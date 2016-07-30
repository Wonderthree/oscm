
import java.io.PrintStream;

/**
 * Scenario 1: developed country (e.g., Hong Kong)
 * 
 * @author GuoHao
 *
 */
public class Exp1 {

	public static void main(String[] args) throws Exception {
		int[] initialInventory = {50, 30};
		// max inventory = 30 days of mean demand
		int maxInventory = 450;

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
		int leadTime = 1;

		int periods = 5 * 365;

		int baseDays = 2;
		int orderUpToDays = 30;
		Policy[] policies = {
				new SmartPolicy(baseDays),
				new DaysOfStockPolicy(baseDays, orderUpToDays)
		};
		
		
		try (PrintStream out = new PrintStream("exp1.csv")) {
			for (Policy policy : policies) {
				Simulator simulator = new Simulator(cycle, leadTime, 
						initialInventory, maxInventory, boxSize,
						demand, policy, periods);

				out.printf("%d,%.4f,%d%n", baseDays,                                 
						simulator.getUnmetDemandProportion(),
						simulator.getNumberOfShipments());
			
			}
		}
	}
}

