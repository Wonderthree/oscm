
public class ConstantPolicy extends Policy {

	private final int value;
	
	public ConstantPolicy(int value) {
		this.value = value;
	}
	
	@Override
	public int order(int inventory) {
		return value;
	}

}
