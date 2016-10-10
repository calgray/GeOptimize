package geoptimize;

import java.awt.Point;

/***
 * A service device that provides service to the area around it.
 * In this case a TV/Radio tower.
 * 
 * Make sure this treats position like a struct. 
 * (maybe even create a local x and y to save heap allocation).
 * 
 * @author Callan Gray
 *
 */
public class ServiceNode {
	
	private Point position;
	private int range;
	
	public Point getPosition() { return position; }
	public void setPosition(Point position) { 
		this.position.setLocation(position);
	}
	public void setPosition(int x, int y) {
		this.position.setLocation(x, y);
	}
	
	public int getRange() { return range; }
	public void setRange(int range) { this.range = range; }
	
	
	public ServiceNode() {
		this(new Point(0,0), 1);
	}
	
	public ServiceNode(int x, int y, int range) {
		this(new Point(x,y), range);
	}
	
	public ServiceNode(Point position, int range) {
		this.position = new Point(position);
		this.range = range;
	}
}
