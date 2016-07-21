
public class ReorderPointPolicy extends Policy {

	private final int reorderPoint;
	private final int value;
	
	public ReorderPointPolicy(int reorderPoint, int value) {
		this.reorderPoint = reorderPoint;
		this.value = value;
	}
	
	@Override
	public int order(int inventory) {     
		if (inventory <= reorderPoint) {      
			return value;
		} else {
			return 0;
		}
	}

}
