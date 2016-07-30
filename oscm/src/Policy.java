
public abstract class Policy {

	public abstract int[] order(int maxInventory, int[] inv, int[] boxSize, double[] meanDemand);

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
