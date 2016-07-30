
/**
 * If the order is non-zero, then order up to the max inventory level.
 * @author GuoHao
 *
 */
public class SmartPolicy extends Policy {

	private final int baseDays;
	
	public SmartPolicy(int baseDays) {
		this.baseDays = baseDays;
	}

	
	
	
	@Override
	public int[] myOrder(int maxInventory, int[] inv, int[] boxSize, double[] meanDemand) {
		int[] shipment = new int[inv.length];

		if (getMinDaysOfStock(inv, meanDemand) >= baseDays) {
			return shipment;                     // the 1st return representing the order
			                                     // not on the cycle days.
		}

		// Otherwise, ship as much as possible
		while(true) {
			double[] d = getDaysOfStock(inv, shipment, meanDemand);
			int k = MyUtils.findMinIndex(d);
			if (MyUtils.sum(inv) + MyUtils.sum(shipment) + boxSize[k] > maxInventory) {
				break;
			} else {
				shipment[k] += boxSize[k];       
			}
		}
		
		return shipment;                        // the 2nd return representing the order
		                                        // on the cycle days.
	}
	
	
	private double getMinDaysOfStock(int[] inv, double[] meanDemand) {
		double[] a = new double[inv.length];
		for (int k = 0; k < inv.length; ++k) {
			a[k] = inv[k] / meanDemand[k];
		}
		return MyUtils.min(a);
	}

	
	
	
	@Override
	public String toString() {
		return String.format("SmartPolicy(%d)", baseDays);
	}
	
}

