package geoptimize.pso;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.util.LinkedList;
import java.util.Random;

import geoptimize.GridData;
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
	
	protected PSOSolution globalBest;
	public PSOSolution getGlobalBest() { return globalBest; }
	
	protected Distribution particleDistribution;

	protected int nNodes;
	protected int range;
	
	protected int nParticles;
	protected int nIterations;
	public int getMaxIterations() { return nIterations; }
	protected int currentIteration = 0;
	public int getCurrentIteration() { return currentIteration; }
	
	protected Rectangle region;
	protected GridData data;
	
	
	public PSOSimulation(int nNodes, int range, int nParticles, int nIterations, Rectangle region, BufferedImage dataimg) {
		
		System.out.println("New Simulation!");
		System.out.println("nNodes : " + nNodes);
		System.out.println("range : " + range);
		System.out.println("Particles : " + nParticles);
		System.out.println("Iterations : " + nIterations);
		System.out.println("Region : " + region);
		System.out.println("Data : " + dataimg);
		
		this.nNodes = nNodes;
		this.range = range;
		
		this.nParticles = nParticles;
		this.nIterations = nIterations;
		
		this.region = region;
		
		this.data = new GridData(dataimg);
		
		//Create random particles
		this.particles = new LinkedList<PSOParticle>();
		for(int i = 0; i < nParticles; i++) {
			particles.add(new PSOParticle(nNodes, range, region));
		}
		
		//calculate initial fitness for particles
		for(PSOParticle p : particles) {
			p.updateFitness(data);
		}
		
		//update initial global best
		float globalBestFitness = particles.getFirst().localBest.fitness;
		int globalBestIndex = 0;
		for(int i = 0; i < particles.size(); i++) {
			if(particles.get(i).localBest.fitness > globalBestFitness) {
				globalBestIndex = i;
				globalBestFitness = particles.get(i).localBest.fitness;
			}
		}
		globalBest = (PSOSolution)particles.get(globalBestIndex).localBest.clone();
	}
	
	/***
	 * Simulation step, move all particles, update their fitness,
	 * calculate newer localbests and global bests
	 */
	public void step() {
		//move all particles
		for(PSOParticle p : particles) {
			p.step(globalBest);
			p.updateFitness(data);
		}
		
		//update global best
		float globalBestFitness = particles.getFirst().localBest.fitness;
		int globalBestIndex = 0;
		for(int i = 0; i < particles.size(); i++) {
			if(particles.get(i).localBest.fitness > globalBestFitness) {
				globalBestIndex = i;
				globalBestFitness = particles.get(i).localBest.fitness;
			}
		}
		globalBest = (PSOSolution)particles.get(globalBestIndex).localBest.clone();
		
		currentIteration++;
	}
}
