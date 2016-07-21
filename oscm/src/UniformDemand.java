import java.util.Random;

/**
 * Random number in {0, 1, 2, ..., max}.
 * 
 *
 */
public class UniformDemand extends Demand {

	private final int max;
	private Random random;
	
	public UniformDemand(int max,int seed){
		this.max=max;
		random=new Random();
		random.setSeed(seed);
	}
	
	@Override
	public int getDemand(){
		double r=random.nextDouble();
		return(int) Math.floor(r* (max + 1));
	}

	@Override
	public double getMean() {
		return max / 2.0; //
	}

}
