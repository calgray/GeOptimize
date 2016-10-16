package geoptimize;

import java.awt.Rectangle;
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
 * Main logic for the Application. When key information changes, trigger the 
 * firePropertyChanged event and the GUI will update.
 * @author Callan
 *
 */
public class SimulationManager extends AbstractModel {
	
	protected MainWindow mainWindow;
	protected PSOSimulation simulation;
	
	protected File populationGridFile;
	protected BufferedImage populationGrid;
	
	protected File backgroundImageFile;
	protected BufferedImage backgroundImage;
	
	//TODO: may want view to update these values when initialized
	protected Rectangle region = new Rectangle();
	protected int nNodes = 1;
	protected int range = 50;
	protected int nParticles = 10;
	protected int nIterations = 100;
	
	/* Properties */
	public Rectangle getRegion() { return region; }
	
	public void setRegion(Rectangle r) { 
		region.setBounds(r);
		this.firePropertyChange("simulationRegion", null, region);
	}
	
	public void setNodes(int n) { nNodes = n; }
	public void setRange(int n) { range = n; }
	public void setIterations(int n) { nIterations = n; }
	public void setParticles(int n) { nParticles = n; }
	
	public BufferedImage getPopulationGrid() { return populationGrid; }
	public PSOSimulation getSimulation() { return simulation; }
	
	public SimulationManager() {
		
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
		populationGridFile = f;
		BufferedImage pg = ImageIO.read(f);
		populationGrid = pg;
		this.firePropertyChange("populationGridFile", null, populationGridFile);
	}
	
	public void loadBackground(File f) throws IOException {
		backgroundImageFile = f;
		BufferedImage pg = ImageIO.read(f);
		if(pg.getSampleModel().getDataType() == 4) {
			throw new IOException("Image raster format must be integer type");
		}
		backgroundImage = pg;
		this.firePropertyChange("backgroundImageFile", null, backgroundImageFile);
		this.firePropertyChange("backgroundImage", null, backgroundImage);
		
	}
	
	/***
	 * Start and run the entire simulation
	 * @throws Exception
	 */
	public void newSimulation() throws Exception {
		System.out.println("Region : " + region.toString());
		if(populationGrid == null) throw new Exception("Population Grid not set.");
		
		simulation = new PSOSimulation(nNodes, range, nParticles, nIterations, region, populationGrid);
		this.firePropertyChange("simulation", null, simulation);
		
	}

	public void runSimulation() {
		// TODO: call stepSimulation multiple times, allowing for screen updates
		while(simulation.getCurrentIteration() < simulation.getMaxIterations()) {
				simulation.step();
				this.firePropertyChange("simulation", null, simulation);
				try {
					//Thread.sleep(10);
				} catch(Exception e) {
					
				}
		}
	}

	public void stepSimulation() throws Exception {
		if(simulation == null) throw new Exception("must create a new simuation");
		simulation.step();
		this.firePropertyChange("simulation", null, simulation);
		
	}
}
