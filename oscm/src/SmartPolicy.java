
/**
 * If the order is non-zero, then order up to the max inventory level.
 * @author GuoHao
 *
 */
public class SmartPolicy extends Policy {

	private final int baseDays;
	private final int orderUpToDays;
	
	public SmartPolicy(int baseDays, int orderUpToDays) {
		this.baseDays = baseDays;
		this.orderUpToDays = orderUpToDays;
	}

	@Override
	public int[] order(int maxInventory, int[] inv, int[] boxSize, double[] meanDemand) {
		DaysOfStockPolicy dosp = new DaysOfStockPolicy(baseDays, orderUpToDays);
		int[] shipment = dosp.order(maxInventory, inv, boxSize, meanDemand);
		
		if (MyUtils.sum(shipment) == 0) {
			return shipment;
		}
		
		// Otherwise, ship as much as possible
		while(true) {
			double[] d = daysOfStock(inv, shipment, meanDemand);
			int k = MyUtils.findMinIndex(d);
			if (MyUtils.sum(inv) + MyUtils.sum(shipment) + boxSize[k] > maxInventory) {
				break;
			} else {
				shipment[k] += boxSize[k];
			}
		}
		
		return shipment;
	}

	private double[] daysOfStock(int[] inv, int[] shipment, double[] meanDemand) {
		int K = inv.length;
		// d: days of stock vector
		double[] d = new double[K];
		for (int k = 0; k < K; ++k) {
			d[k] = (inv[k] + shipment[k]) / meanDemand[k];
		}
		return d;
	}
	
	@Override
	public String toString() {
		return String.format("SmartPolicy(%d,%d)", baseDays, orderUpToDays);
	}
	
}













