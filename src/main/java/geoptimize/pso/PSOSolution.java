package geoptimize.pso;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import geoptimize.GridData;
import geoptimize.ServiceNode;

public class PSOSolution {
	protected List<ServiceNode> nodes;
	protected float fitness;
	public float getFitness() { return fitness; }
	
	public List<ServiceNode> getNodes() { return nodes; }
	
	private  PSOSolution() { }
	
	/***
	 * Create a random solution for a given number of nodes, all 
	 * with the same proprties.
	 * @param nodes
	 * @param range
	 * @return
	 */
	public static PSOSolution CreateRandom(int nodes, int range, Rectangle region) {
		PSOSolution s = new PSOSolution();
		s.nodes = new ArrayList<ServiceNode>(nodes);
		Random r = new Random();
		for(int i = 0; i < nodes; i++) {
			s.nodes.add(new ServiceNode(
					region.x + r.nextInt(region.width), 
					region.y + r.nextInt(region.height), 
					range));
		}
		return s;
	}
	
	@Override
	public Object clone() {
		PSOSolution s = new PSOSolution();
		s.nodes = new ArrayList<ServiceNode>(this.nodes.size());
		for(ServiceNode n : this.nodes) {
			s.nodes.add((ServiceNode)n.clone());
		}
		s.fitness = this.fitness;
		return s;
	}
}
