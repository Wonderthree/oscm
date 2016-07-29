import java.util.Random;

/**
 * Random number in {0, 1, 2, ..., max}.
 * @author GuoHao
 *
 */
public class UniformDemand extends Demand {

	private final int max;
	private Random random;
	private final int seed;
	
	public UniformDemand(int max, int seed){
		this.max = max;
		this.seed = seed;
		random = new Random();
		reset();
	}
	
	@Override
	public int getDemand(){
		double r=random.nextDouble();
		return(int) Math.floor(r* (max + 1));
	}

	@Override
	public double getMean() {
		return max / 2.0; 
	}

	@Override
	public void reset() {
		random.setSeed(seed);
	}

}
