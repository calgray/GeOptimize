package geoptimize.pso;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;

/**
 * Collection of all the map data needed to perform
 * PSO fitness with.
 * 
 * @author Callan
 *
 */
public class PSOPopulationGrid {
	//TODO: decide which format to read as
	//Check the scale
	short[] populationData;

	
	PSOPopulationGrid(BufferedImage gridImage) {
		
		int width = gridImage.getWidth();
		int height = gridImage.getHeight();
		
		populationData = new short[width * height];
		Raster r = gridImage.getData();
		DataBuffer b = r.getDataBuffer();
		
	}
}
