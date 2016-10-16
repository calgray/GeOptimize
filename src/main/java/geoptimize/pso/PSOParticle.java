package geoptimize.pso;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import geoptimize.ServiceNode;

/***
 * 
 * A particle containing a possible solution
 * 
 * @author Callan
 *
 */
public class PSOParticle {
	protected List<ServiceNode> nodes;
	protected int nNodes;
	protected int range;
	protected Rectangle region;
	
	public List<ServiceNode> getNodes() { return nodes; }
	
	public int[] getCoordinates() {
		int[] coords = new int[nNodes * 2];
		int i = 0;
		for(ServiceNode node : nodes) {
			coords[i++] = node.getPosition().x;
			coords[i++] = node.getPosition().y;
		}
		return coords;
	}
	
	/***
	 * Coords must be at least twice the size of nNodes.
	 * @param coords
	 */
	public void setCoordinates(int[] coords) {
		int i = 0;
		for(ServiceNode node : nodes) {
			node.setPosition(coords[i++], coords[i++]);
		}
	}
	

	
	public PSOParticle(int nNodes, int range, Rectangle region) {
		this.nNodes = nNodes;
		this.range = range;
		this.region = region;
		this.nodes = new ArrayList<ServiceNode>(nNodes);
		Random r = new Random();
		for(int i = 0; i < nNodes; i++) {
			this.nodes.add(new ServiceNode(
					region.x + r.nextInt(region.width), 
					region.y + r.nextInt(region.height), 
					range));
		}
	}
	
}
