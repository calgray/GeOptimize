package geoptimize.pso;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import geoptimize.ServiceNode;

public class PSOSolution {
	protected List<ServiceNode> nodes;
	protected float fitness;
	public float getFitness() { return fitness; }
	
	public List<ServiceNode> getNodes() { return nodes; }
	
	public PSOSolution() {
		
	}
	
	@Override
	public Object clone() {
		PSOSolution s = new PSOSolution();
		s.nodes = new ArrayList<ServiceNode>();
		for(ServiceNode n : this.nodes) {
			s.nodes.add((ServiceNode)n.clone());
		}
		s.fitness = this.fitness;
		return s;
	}
	
	/***
	 * Mutate the current object using localbest and globalbest
	 * @param localBest
	 * @param globalBest
	 */
	public void step(PSOSolution localBest, PSOSolution globalBest) {
		
	}
	
	/**
	 * could extend this using a bridge or abstract factory pattern
	 * @param data
	 */
	public float updateFitness(float[] data, Rectangle region) {
		//fitness = simpleFitness(data);
		fitness = betterFitness(data, region);
		return fitness;
	}
	
	/***
	 * Basic/test fitness function simply adding up pixels the
	 * tower is located on.
	 * Nodes should simply move to highest population pixels.
	 * @param data
	 * @return
	 */
	private float simpleFitness(float[] data) {
		//basic fitness (fitness is population the nodes are placed on)
		
		fitness = 0;
		
		//TODO: change to datagrid object, has width and height
		int width = 6382;
		int height = 3821;
		
		for(ServiceNode node : nodes) {
			int x = node.getPosition().x;
			int y = node.getPosition().y;
			
			fitness += data[width*y+x];
		}
		return fitness;
		
	}
	
	/***
	 * TODO: currently broken
	 * @param data
	 * @param region
	 * @return
	 */
	private float betterFitness(float[] data, Rectangle region) {
		//======================================
		//pixel by pixel fitness
		
		//TODO: change to datagrid object, has width and height
		int width = 6382;
		int height = 3821;
		
		
		float[] inrange = new float[region.width * region.height];
		
		
		for(int y = (int)region.getMinY(); y < region.getMaxY(); y++) {
			for(int x = (int)region.getMinX(); x < region.getMaxX(); x++) {
				for(ServiceNode n : nodes) {
					int xdist = x - n.getPosition().x;
					int ydist = y - n.getPosition().y;
					int distsqr = xdist*xdist+ydist*ydist;
					
					if(distsqr < n.getRange()*n.getRange()) {
						int rx = x - region.x;
						int ry = y - region.y;
						inrange[ry*region.width + rx] = data[y*width+x];
					}
					
				}
			}
		}
		
		fitness = 0;
		for(int ry = 0; ry < region.height; ry++) {
			for(int rx = 0; rx < region.width; rx++) {
				fitness += inrange[ry*region.width+rx];
			}
		}
		
		return fitness;
	}
	
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
}
