package geoptimize.pso.fitness;

import java.awt.Rectangle;

import geoptimize.GridData;
import geoptimize.pso.PSOSolution;

/**
 * Assign the heatmap and ServiceNode locations, and return the fitness
 * 
 * @author Callan
 *
 */
public abstract class PSOFitnessFunction {

	protected GridData grid;
	protected Rectangle region;
	
	public PSOFitnessFunction(GridData grid, Rectangle region) {
		this.grid = grid;
		this.region = region;
	}
	
	public float calcFitness(PSOSolution solution) {
		return 0f;
	}

}
