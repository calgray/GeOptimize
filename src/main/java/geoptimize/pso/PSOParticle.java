package geoptimize.pso;

import java.util.List;
import geoptimize.ServiceNode;

/***
 * 
 * A particle containing a possible solution
 * 
 * @author Callan
 * @author blcfbggc
 *
 */
public class PSOParticle {
	protected List<ServiceNode> nodes;
	protected int nNodes;
	
	public PSOParticle(int nodes) {
		this.nNodes = nodes;
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
}
