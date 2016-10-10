import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import static org.junit.Assert.*;
import com.sun.media.imageioimpl.plugins.tiff.TIFFImageReaderSpi;

import org.junit.Test;

public class ImageTest {
	@Test
	public void testUint16tif() {
		File f = new File("./images/populationgrid-uint16.png");
		try {
			BufferedImage img = ImageIO.read(f);
			assertNotNull(img);
			assertEquals(img.getWidth()*img.getHeight(), 24385622);
			
			int color = img.getRGB(0, 0);
			assertEquals(color, 0xFF000000);
			
			Raster r = img.getData();
			DataBuffer b = r.getDataBuffer();
			assertNotNull(b);
			assertEquals(b.getSize(), 24385622);
			
			color = b.getElem(0);
			assertEquals(color, 0);
			
			
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testFloat32tif() {
		//requires com.sun.media.jai_imageio
		File f = new File("./images/GeoMapData/Australian_Population_Grid_2011.tif");
		try {
			BufferedImage img = ImageIO.read(f);
			assertNotNull(img);
			
			Raster r = img.getData();
			DataBuffer b = r.getDataBuffer();
			assertNotNull(b);
			assertEquals(b.getSize(), 24385622);
			
			float color = b.getElemFloat(0);
			assertTrue(color == 0f);
			
			
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}
