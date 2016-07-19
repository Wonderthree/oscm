
public class ConstantPolicy extends Policy {

	private final int value;
	
	public ConstantPolicy(int value) {
		this.value = value;
	}

	@Override
	public int[] order(int[] inv) {
		int[] q = new int[inv.length];
		for (int i = 0; i < inv.length; ++i) {
			q[i] = value;
		}
		return q;
	}
	
}
