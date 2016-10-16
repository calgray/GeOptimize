package geoptimize.pso;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

import geoptimize.ServiceNode;
import geoptimize.helper.MathHelper;

/***
 * Put actual simulation logic in here,
 * Configure all the variables using the simulation manager.
 * 
 * @author Callan
 *
 */
public class PSOSimulation {
	
	protected LinkedList<PSOParticle> particles;
	public LinkedList<PSOParticle> getParticles() { return particles; }
	
	protected PSOParticle globalBest;
	public PSOParticle getGlobalBet() { return globalBest; }
	
	protected Distribution particleDistribution;

	protected int nNodes;
	protected int range;
	
	protected int nParticles;
	protected int nIterations;
	public int getMaxIterations() { return nIterations; }
	protected int currentIteration = 0;
	public int getCurrentIteration() { return currentIteration; }
	
	protected Rectangle region;
	protected BufferedImage data;
	
	
	public PSOSimulation(int nNodes, int range, int nParticles, int nIterations, Rectangle region, BufferedImage data) {
		this.nNodes = nNodes;
		this.range = range;
		
		this.nParticles = nParticles;
		this.nIterations = nIterations;
		
		this.region = region;
		this.data = data;
		
		this.particles = new LinkedList<PSOParticle>();
		for(int i = 0; i < nParticles; i++) {
			particles.add(new PSOParticle(nNodes, range, region));
		}
		
		System.out.println("Running!");
		System.out.println("nNodes : " + nNodes);
		System.out.println("range : " + range);
		System.out.println("Particles : " + nParticles);
		System.out.println("Iterations : " + nIterations);
		System.out.println("Region : " + region);
		System.out.println("Data : " + data);
	}
	
	public void step() {
		Random r = new Random();
		for(PSOParticle p : particles) {
			
			for(ServiceNode n : p.getNodes())
			{
				n.setPosition(
					MathHelper.clamp(region.x, region.x + region.width, n.getPosition().x - 5 + r.nextInt(11)), 
					MathHelper.clamp(region.y, region.y + region.height, n.getPosition().y - 5 + r.nextInt(11))
				);
			}
		}
		
		System.out.println("iteration : " + currentIteration + "/" + nIterations);
		
		currentIteration++;
		
	}
}
