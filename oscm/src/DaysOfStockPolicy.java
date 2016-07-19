
/**
 * If you have less than x days of stock, order >= y days of inventory.
 * 
 * Example: mean demand = 10 units per period, x = 3, y = 10.
 * 
 * @author GuoHao
 *
 */
public class DaysOfStockPolicy extends Policy {

	private final int baseDays;
	private final int orderDays;
	
	// TODO
	public DaysOfStockPolicy() {
		
	}
	
	@Override
	public int[] order(int[] inv, int[] boxSize, double[] meanDemand) {
		// K : number of products
		int K = inv.length;
		
		int[] q = new int[K];
		
		// q[k] must be a multiple of boxSize[k]
		// Example: if boxSize[1] = 50, q[1] must be 50, 100, 150, ...
		
		return null;
	}

}
