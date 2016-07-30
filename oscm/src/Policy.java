import java.util.Arrays;

public abstract class Policy {

	public int[] order(int maxInventory, int[] inventoryPosition,
			int[] boxSize, double[] meanDemand) throws Exception {

		int[] q = myOrder(maxInventory, inventoryPosition, boxSize,
				meanDemand);

		print(maxInventory, inventoryPosition, inventoryPosition);

		// Check if q[i] < 0, if so, throw an exception
		for (int i = 0; i < q.length; ++i) {
			if (q[i] < 0) {
				throw new Exception("q[i] < 0 for some i");
			}
		}
		return q;
	}

	public abstract int[] myOrder(int maxInventory, int[] inventoryPosition,
			int[] boxSize, double[] meanDemand);

	public void print(int maxInventory, int[] inventoryPosition,
			int[] order) {
		boolean shouldPrint = false;
		if (shouldPrint) {
			System.out.printf("max inv      = %d%n", maxInventory);
			System.out.printf("inv position = %s%n",
					Arrays.toString(inventoryPosition));
			System.out.printf("order        = %s%n",
					Arrays.toString(order));
		}
	}

	/**
	 * Get number of days of stock of each product, assuming zero
	 * shipments.
	 */
	public double[] getDaysOfStock(int[] inv, double[] meanDemand) {
		int[] shipment = new int[inv.length];
		return getDaysOfStock(inv, shipment, meanDemand);
	}

	/**
	 * Get number of days of stock of each product.
	 */
	public double[] getDaysOfStock(int[] inv, int[] shipment, double[] meanDemand) {
		int K = inv.length;
		// d: days of stock vector
		double[] d = new double[K];
		for (int k = 0; k < K; ++k) {
			d[k] = (inv[k] + shipment[k]) / meanDemand[k];  // methods within the same class
			// could be applied mutually 
			// without considering the time
			// sequence?
		}
		return d;
	}

}
