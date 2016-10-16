package geoptimize;

import java.awt.image.BufferedImage;

/***
 * Sampling from a buffered image is too slow
 * @author Callan
 *
 */
public class DataGrid {
	
	public float width;
	public float height;
	public float[] data;
	
	public DataGrid(BufferedImage img) {
		data = new float[img.getWidth() * img.getHeight()];
	}
}
