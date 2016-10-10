package geoptimize.pso;

import java.util.LinkedList;

import geoptimize.ServiceNode;

/***
 * Put actual simulation logic in here,
 * Configure all the variables using the simulation manager.
 * 
 * @author Callan
 *
 */
public class PSOSimulation implements Runnable {
	
	protected LinkedList<PSOParticle> particles;
	protected int nParticles = 1;
	protected int nIterations = 10;
	
	public PSOSimulation() {
		
	}
	
	

	@Override
	public void run() {
		
	}
	
}
