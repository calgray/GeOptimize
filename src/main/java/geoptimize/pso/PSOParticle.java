package geoptimize.pso;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import javax.vecmath.Vector2f;

import geoptimize.GridData;
import geoptimize.ServiceNode;
import geoptimize.helper.MathHelper;

/***
 * 
 * A particle containing a current solution, and a local best solution
 * Also contains particle velocity and inertia.
 * 
 * @author Callan
 *
 */
public class PSOParticle {
	
	protected PSOSolution current;
	protected float[] inertias;
	protected Vector2f[] velocities;
	
	protected PSOSolution localBest;
	
	protected int nNodes;
	protected int range;
	protected Rectangle region;
	
	//TODO: might want to move these to the GUI
	static float localBestWeight = 0.5f;
	static float globalBestWeight = 0.5f;
	
	public PSOSolution getCurrent() { return current; }
	public PSOSolution getLocalBest() { return localBest; }
	
	public PSOParticle(int nNodes, int range, Rectangle region) {
		this.nNodes = nNodes;
		this.range = range;
		this.region = region;
		
		this.current = PSOSolution.CreateRandom(nNodes, range, region);
		this.inertias = new float[nNodes];
		this.velocities = new Vector2f[nNodes];
		for(int i = 0; i < nNodes; i++) {
			inertias[i] = 0.5f;
			velocities[i] = new Vector2f(0, 0);
		}
		
		this.localBest = (PSOSolution)this.current.clone();
	}
	
	private PSOParticle() { }
	
	/***
	 * Do a full clone
	 */
	public Object clone() {
		PSOParticle p = new PSOParticle();
		
		p.nNodes = this.nNodes;		
		p.range = this.range;
		p.region = (Rectangle)this.region.clone();
		
		
		p.current = (PSOSolution)this.current.clone();
		p.localBest = (PSOSolution)this.localBest.clone();
		
		return p;
	}
	
	public void updateFitness(GridData data) {
		current.updateFitness(data, region);
		
		if(current.fitness > localBest.fitness) {
			localBest = (PSOSolution)current.clone();
		}
	}
	
	/***
	 * Steps the current solution using particle data.
	 * Algorithm used from http://tracer.uc3m.es/tws/pso/basics.html
	 * @param globalBest
	 */
	public void step(PSOSolution globalBest) {
		///step using globalBest, localBest, inertias, velocities
		
		int rr = 2;
		int rrmoh = 0;
		
		Random r = new Random();
		
		for(int i = 0; i < nNodes; i++) {
			
			velocities[i].x = 
					inertias[i] * velocities[i].x +
					localBestWeight  * (r.nextInt(rr) - rrmoh) * (localBest.nodes.get(i).getPosition().x  - current.nodes.get(i).getPosition().x) +
					globalBestWeight * (r.nextInt(rr) - rrmoh) * (globalBest.nodes.get(i).getPosition().x - current.nodes.get(i).getPosition().x);
			
			velocities[i].y =
					inertias[i] * velocities[i].y +
					localBestWeight  * (r.nextInt(rr) - rrmoh) * (localBest.nodes.get(i).getPosition().y  - current.nodes.get(i).getPosition().y) +
					globalBestWeight * (r.nextInt(rr) - rrmoh) * (globalBest.nodes.get(i).getPosition().y - current.nodes.get(i).getPosition().y);
			
			int x = (int)(current.nodes.get(i).getPosition().x + velocities[i].x);
			int y = (int)(current.nodes.get(i).getPosition().y + velocities[i].y);
			
			x = MathHelper.clamp((int)region.getMinX(), (int)region.getMaxX(), x);
			y = MathHelper.clamp((int)region.getMinY(), (int)region.getMaxY(), y);
			
			current.nodes.get(i).setPosition(x, y);
			
		}
	}
	
	/***
	 * Random step test, simple moves particles randomly.
	 */
	private void randomStep() {
		Random r = new Random();

		for(ServiceNode n : current.nodes)
		{
			n.setPosition(
				MathHelper.clamp(region.x, region.x + region.width, n.getPosition().x - 5 + r.nextInt(11)), 
				MathHelper.clamp(region.y, region.y + region.height, n.getPosition().y - 5 + r.nextInt(11))
			);
		}		
	}
}
