package geoptimize.pso;

/***
 * 
 * Calculate fitness using java via performing sums on squares and using a distance function.
 * Later might want to do an openCL implementation.
 * 
 * @author Callan
 *
 */
public class PSOFitnessTaskRadiusSum implements PSOFitnessTask {

	protected PSOPopulationGrid grid;
	protected PSOParticle particle;
	
	public PSOFitnessTaskRadiusSum(PSOPopulationGrid grid, PSOParticle particle) {
		this.grid = grid;
		this.particle = particle;
	}
	
	@Override
	public double runTask() {
		return 0;
	}

	
}
