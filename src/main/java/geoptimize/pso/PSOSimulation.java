package geoptimize.pso;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import geoptimize.GridData;
import geoptimize.ServiceNode;
import geoptimize.helper.MathHelper;
import geoptimize.pso.fitness.PSOFitnessBinaryRange;
import geoptimize.pso.fitness.PSOFitnessFunction;

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
	
	protected PSOFitnessFunction fitnessFunction;
	
	//TODO: atm, always using random
	protected Distribution particleDistribution;

	protected int nNodes;
	protected int range;
	protected float lbestWeight;
	protected float gbestWeight;
	protected float inertia;
	
	protected boolean parallelMode = true;
	
	protected int nParticles;
	protected int currentIteration = 0;
	public int getCurrentIteration() { return currentIteration; }
	
	protected Rectangle region;
	protected GridData data;
	
	
	public PSOSimulation(
			int nNodes, 
			int range,
			float lbestWeight,
			float gbestWeight,
			float inertia,
			int nParticles,  
			Rectangle region, 
			BufferedImage dataimg) {
		
		System.out.println("New Simulation!");
		System.out.println("nNodes : " + nNodes);
		System.out.println("range : " + range);
		System.out.println("Particles : " + nParticles);
		System.out.println("Region : " + region);
		System.out.println("Data : " + dataimg);
		
		this.nNodes = nNodes;
		this.range = range;
		this.lbestWeight = lbestWeight;
		this.gbestWeight = gbestWeight;
		this.inertia = inertia;
		
		this.nParticles = nParticles;
		
		this.region = region;
		this.data = new GridData(dataimg);
		this.fitnessFunction = new PSOFitnessBinaryRange(data, region);
		
		
		//Create particles with random solutions
		this.particles = new LinkedList<PSOParticle>();
		for(int i = 0; i < nParticles; i++) {
			particles.add(new PSOParticle(
					nNodes, 
					range, 
					lbestWeight, 
					gbestWeight, 
					inertia, 
					region));
		}
		
		//calculate initial fitness for particles
		for(PSOParticle p : particles) {
			p.updateFitness(fitnessFunction);
		}
		
		//update initial global best
		globalBest = (PSOSolution)findGlobalBest().clone();
	}
	
	/***
	 * Simulation step 
	 *  1. move all particles
	 *  2. update their fitness
	 *  3. calculate newer localbests and global bests
	 */
	public void step() {
		
		//move all particles (Synchronous)
		if(parallelMode) {
			moveParallel();
		} else {
			moveSequential();
		}
		
		//update global best
		globalBest = (PSOSolution)findGlobalBest().clone();
		
		currentIteration++;
	}
	
	private class ParticleUpdateThread extends Thread {	
		PSOParticle p;
		
		public ParticleUpdateThread(PSOParticle p) {
			this.p = p; 
		}
		@Override
		public void run() {
			p.step(globalBest);
			p.updateFitness(fitnessFunction);
		}
	}
	
	private void moveSequential() {
		for(PSOParticle p : particles) {
			p.step(globalBest);
			p.updateFitness(fitnessFunction);
		}
	}
	
	private void moveParallel() {
		ArrayList<Thread> threads = new ArrayList<Thread>(); 
		for(int i = 0; i < particles.size(); i++) {
			Thread t = new ParticleUpdateThread(particles.get(i));
			t.start();
		}
		for(Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private PSOSolution findGlobalBest() {
		float globalBestFitness = particles.getFirst().localBest.fitness;
		int globalBestIndex = 0;
		for(int i = 0; i < particles.size(); i++) {
			if(particles.get(i).localBest.fitness > globalBestFitness) {
				globalBestIndex = i;
				globalBestFitness = particles.get(i).localBest.fitness;
			}
		}
		return particles.get(globalBestIndex).localBest;
		
	}
}
