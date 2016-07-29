import java.util.Arrays;

/**
 * Let inv denote the current inventory level.  If inv is less than x
 * days of stock, order q units, such that:
 *  (i) q is a multiple of the box size;
 *  (2) and inv + q >= y days of inventory.
 * 
 * Example: inv = 12, box size = 10, mean demand = 10 units per period,
 * x = 3, y = 10, then the order amount is 90.
 * 
 * @author GuoHao
 *
 */
public class DaysOfStockPolicy extends Policy {

	private final int baseDays;
	private final int orderUpToDays;

	public DaysOfStockPolicy(int baseDays, int orderUpToDays) {
		this.baseDays = baseDays;
		this.orderUpToDays = orderUpToDays;
	}


	public int getBaseDays() {
		return baseDays;
	}


	private int order(int inv, int boxSize, double meanDemand, int remaining) {
		int q = 0;
		if (inv <= baseDays * meanDemand) {
			q = (int) (orderUpToDays * meanDemand - inv);
			// round q up to a multiple of the box size 
			q = MyUtils.roundUpToMultiple(q, boxSize);
		}
		
		// If q exceeds the remaining inventory space, then take away a
		// box repeatedly until it is less than the remaining inventory
		// space
		while (q > remaining) {
			q -= boxSize;
		}
		return q;
	}




	@Override
	public int[] order(int maxInventory, int[] inv, int[] boxSize, double[] meanDemand) {
		// K : number of products
		int K = inv.length;

		int[] q = new int[K];

		// amount of inventory space remaining
		int remaining = maxInventory;
		remaining -= MyUtils.sum(inv);

		for (int k = 0; k < K; ++k) {
			q[k] = order(inv[k], boxSize[k], meanDemand[k], remaining);
			remaining -= q[k];
		}
		// q[k] must be a multiple of boxSize[k]
		// Example: if boxSize[1] = 50, q[1] must be 50, 100, 150, ...

		System.out.println("inv = " + Arrays.toString(inv));
		System.out.println("q   = " + Arrays.toString(q));
		
		return q;
	}	


	//******************************************************************************************
	@Override                          
	public String toString() {
		return String.format("DaysOfStockPolicy(%d,%d)", baseDays, orderUpToDays);
	}
	//******************************************************************************************


}
