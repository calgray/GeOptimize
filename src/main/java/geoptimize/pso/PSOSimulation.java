package geoptimize.pso;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
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
	protected int nParticles;
	protected int nIterations;
	protected Rectangle region;
	protected BufferedImage data;
	
	public PSOSimulation(int nParticles, int nIterations, Rectangle region, BufferedImage data) {
		
		this.nParticles = nParticles;
		this.nIterations = nIterations;
		this.particles = new LinkedList<PSOParticle>();
		this.region = region;
		this.data = data;
	}
	
	

	@Override
	public void run() {
		System.out.println("Running!");
	}
	
}
