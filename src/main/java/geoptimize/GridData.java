package geoptimize;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;

/***
 * Sampling from a buffered image is too slow
 * @author Callan
 *
 */
public class GridData {
	
	public int width;
	public int height;
	public int size;
	
	//ATM, is the entire image, not just a region
	public float[] data;
	
	public GridData(BufferedImage img) {
		width = img.getWidth();
		height = img.getHeight();
		size = width * height;
		
		data = new float[img.getWidth() * img.getHeight()];
		
		
		DataBuffer b = img.getRaster().getDataBuffer();
		for(int i = 0; i < size; i++) {
			data[i] = b.getElemFloat(i);
		}
	}
	
	
	public float get(Point p) {
		return data[p.y*width+p.x];
	}
	
	public float get(int x, int y) {
		return data[y*width+x];
	}
}
