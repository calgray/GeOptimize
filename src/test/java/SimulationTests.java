import java.io.File;
import java.io.IOException;

import org.junit.Test;
import static org.junit.Assert.*;

import geoptimize.SimulationManager;

public class SimulationTests {
	@Test
	public void testLoadingPopulationGrid() {
		SimulationManager m = new SimulationManager();
		File f = new File("./images/populationgrid-binary.png");
		try {
			m.loadPopulationGrid(f);
		} catch(IOException e) {
			
		} finally {
			assertNotNull("Image did not load.", m.getPopulationGrid());
		}
	}
}
