import java.util.Arrays;

public final class MyUtils {

	
	
	/**
	 * Create a copy of an array.
	 */
	public static int[] copy(int[] a) {
		return Arrays.copyOf(a, a.length);
	}
	
	/**
	 * Round up x to a multiple of n.
	 */
	public static int roundUpToMultiple(int x, int n) {
		return (int) (Math.ceil(x / 1.0 / n) * n);      
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
	
	/**
	 * Find the minimum index, e.g., min index of {2, 1, 3} is 1,  min
	 * index of {2, 4, 1, 3} is 2.
	 * @param a
	 * @return
	 */
	public static int findMinIndex(double[] a) {
		int minIndex = 0;
		for (int i = 1; i < a.length; ++i) {
			if (a[i] < a[minIndex]) {
				minIndex = i;
			}
		}
		return minIndex;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 30; ++i) {
			System.out.printf("roundUpToMultiple(%d,8) = %d\n", i,
					roundUpToMultiple(i, 8));
		}
	}
	
}
