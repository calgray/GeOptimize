package geoptimize.pso.fitness;

import java.awt.Rectangle;

import geoptimize.GridData;
import geoptimize.ServiceNode;
import geoptimize.pso.PSOSolution;

public class PSOFitnessSimple extends PSOFitnessFunction {

	public PSOFitnessSimple(GridData grid, Rectangle region) {
		super(grid, region);
	}
	
	/***
	 * Basic/test fitness function simply adding up pixels the
	 * tower is located on.
	 * Nodes should simply move to highest population pixels.
	 * @param data
	 * @return
	 */
	@Override
	public float calcFitness(PSOSolution solution) {
		//basic fitness (fitness is population the nodes are placed on)
		float fitness = 0;
		for(ServiceNode node : solution.getNodes()) {
			fitness += grid.get(node.getPosition());
		}
		return fitness;
		
	}
	
}
