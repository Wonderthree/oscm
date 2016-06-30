
public class ConstantDemand extends Demand {

	private final int value;
	
	public ConstantDemand(int value) {
		this.value = value;
	}
	
	@Override
	public int getDemand() {
		return value;
	}

}
