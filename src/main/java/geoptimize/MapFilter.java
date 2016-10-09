package geoptimize;

import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;

import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;

/**
 * This is a static class just for processing the web rasterized image of the data.
 * Will want to load the GeoTIFF directly if possible.
 * 
 * @author Callan
 *
 */
public class MapFilter {
	private MapFilter() { }
	
	//ARGB
	private static final int ocean = 0xFF5E5E5E;
	private static final int low = 0xFFFFFFB2;
	private static final int lmed = 0xFFFECC5C;
	private static final int med = 0xFFfd8d3c;
	private static final int hmed = 0xFFf03b20;
	private static final int high = 0xFFbd0026;
	
	private static int cmp(int x, int y) {
		int xr = (0x000000FF & x);
		int xg = (0x0000FF00 & x) >> 8;
		int xb = (0x00FF0000 & x) >> 16;
		
		int yr = (0x000000FF & y);
		int yg = (0x0000FF00 & y) >> 8;
		int yb = (0x00FF0000 & y) >> 16;
		
		return Math.abs(xr - yr) + Math.abs(xg - yg) + Math.abs(xb - yb);
	}
	
	public static BufferedImage heatmapFilter(BufferedImage img) {	
		
		int width = img.getWidth(null);
		int height = img.getHeight(null);
		int[] nBits = { 8 };
		
		
		byte[] buffer = new byte[width * height];
		
		int b = img.getRGB(0,0);
		System.out.println(Integer.toHexString(b));
		System.out.println("r : " + Integer.toHexString(0x000000FF & b));
		System.out.println("g : " + Integer.toHexString((0x0000FF00 & b) >> 8));
		System.out.println("b : " + Integer.toHexString((0x00FF0000 & b) >> 16));
		System.out.println("dist : " + cmp(b, ocean));
		
		for(int j = 0; j < height; j++) {
			for(int i = 0; i < width; i++) {
				int c = img.getRGB(i, j);
				
				if(cmp(c, ocean) < 32) buffer[j*width+i] = (byte)0x00;
				else if(cmp(c,  low) < 128) buffer[j*width+i] = (byte)0xBF;
				else if(cmp(c, lmed) < 128) buffer[j*width+i] = (byte)0xCF;
				else if(cmp(c,  med) < 128) buffer[j*width+i] = (byte)0xDF;
				else if(cmp(c, hmed) < 128) buffer[j*width+i] = (byte)0xEF;
				else if(cmp(c, high) < 128) buffer[j*width+i] = (byte)0xFF;
				else buffer[j*width+i] = (byte)127;
			}
		}
		
		ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
		
		ColorModel cm = new ComponentColorModel(cs, nBits, false, true,
				Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
		
		SampleModel sm = cm.createCompatibleSampleModel(width, height);
		
		DataBufferByte db = new DataBufferByte(buffer, width * height);
		
		WritableRaster raster = Raster.createWritableRaster(sm, db, null);
		
		BufferedImage heatmap = new BufferedImage(cm, raster, false, null);
		
		return heatmap;
	}
}
