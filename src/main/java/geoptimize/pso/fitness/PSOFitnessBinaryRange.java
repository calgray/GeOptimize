package geoptimize.pso.fitness;

import java.awt.Rectangle;

import geoptimize.GridData;
import geoptimize.ServiceNode;
import geoptimize.pso.PSOSolution;


public class PSOFitnessBinaryRange extends PSOFitnessFunction {

	public PSOFitnessBinaryRange(GridData grid, Rectangle region) {
		super(grid, region);
	}
	
	/***
	 * Steps through entire population and and stores the a grid fitness
	 * in an array. This is more extendable than quickfitness.
	 * @param grid
	 * @param region
	 * @return
	 */
	@Override
	public float calcFitness(PSOSolution solution) {

		float[] inrange = new float[region.width * region.height];
		
		for(int y = (int)region.getMinY(); y < region.getMaxY(); y++) {
			for(int x = (int)region.getMinX(); x < region.getMaxX(); x++) {
				
				//fast return, most cells have population of 0
				if(grid.get(x, y) == 0) continue;
					
				for(ServiceNode n : solution.getNodes()) {
					int xdist = x - n.getPosition().x;
					int ydist = y - n.getPosition().y;
					int distsqr = xdist*xdist+ydist*ydist;
					
					if(distsqr < n.getRange() * n.getRange()) {
						int rx = x - region.x;
						int ry = y - region.y;
						inrange[ry*region.width + rx] = grid.get(x, y);
					}
					
				}
			}
		}
		
		float fitness = 0;
		for(int ry = 0; ry < region.height; ry++) {
			for(int rx = 0; rx < region.width; rx++) {
				fitness += inrange[ry*region.width+rx];
			}
		}
		return fitness;
	}

}
