
/**
 * If the order is non-zero, then order up to the max inventory level.
 * @author GuoHao
 *
 */
public class SmartPolicy extends Policy {

	private final int baseDays;
	private final int orderUpToDays;
	
	@Override
	public int[] order(int maxInventory, int[] inv, int[] boxSize, double[] meanDemand) {
		// TODO Auto-generated method stub
		return null;
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
	
}













