package geoptimize;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import geoptimize.pso.PSOSimulation;
import geoptimize.swing.MainWindow;


/**
 * Main logic for the MainWindowView
 * TODO: move more of the logic here.
 * @author Callan
 *
 */
public class SimulationManager {
	
	protected MainWindow mainWindow;
	protected BufferedImage populationGrid;
	protected BufferedImage backgroundImage;
	
	protected PSOSimulation simulation;
	

	public BufferedImage getPopulationGrid() { return populationGrid; }
	
	public SimulationManager() {
		simulation = new PSOSimulation();
	}
	
	/***
	 * Creates and binds a new window to this manager.
	 * Use this this method to ensure both the window and
	 * manager objects have reference to each other.
	 * @return
	 */
	public MainWindow createWindow() {
		
		mainWindow = new MainWindow(this);
		return mainWindow;
	}
	
	public void loadPopulationGrid(File f) throws IOException {
		BufferedImage pg = ImageIO.read(f);
		populationGrid = pg;
	}
	
	public void loadBackground(File f) throws IOException {
		BufferedImage pg = ImageIO.read(f);
		if(pg.getSampleModel().getDataType() == 4) {
			throw new IOException("Image raster format must be integer type");
		}
		backgroundImage = pg;
		if(mainWindow != null) {
			mainWindow.setImage(pg);
		}
		mainWindow.revalidate();
	}
	
	public void startSimulation() {
		simulation.run();
	}
}
