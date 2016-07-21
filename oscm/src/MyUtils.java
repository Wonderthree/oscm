import java.util.Arrays;

public final class MyUtils {

	
	
	/**
	 * Create a copy of an array.
	 */
	public static int[] copy(int[] a) {
		return Arrays.copyOf(a, a.length);
	}
	
	
	
	/**
	 * Return the sum of the elements of an array.
	 */
	public static int sum(int[] a) {
		int sum = 0;
		for (int i : a) {
			sum += i;
		}
		return sum;
	}
	
}
