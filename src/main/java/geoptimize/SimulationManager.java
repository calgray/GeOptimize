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
	
	protected File populationGridFile;
	protected BufferedImage populationGrid;
	protected BufferedImage backgroundImage;
	
	protected Rectangle simulationRegion = new Rectangle();
	public Rectangle getRegion() { return simulationRegion; }
	public void setRegion(Rectangle r) { 
		simulationRegion.setBounds(r);
		this.firePropertyChange("simulationRegion", null, simulationRegion);
	}
	
	protected PSOSimulation simulation;
	

	public BufferedImage getPopulationGrid() { return populationGrid; }
	
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
		BufferedImage pg = ImageIO.read(f);
		if(pg.getSampleModel().getDataType() == 4) {
			throw new IOException("Image raster format must be integer type");
		}
		backgroundImage = pg;
		
		this.firePropertyChange("backgroundImage", null, backgroundImage);
		
		/*
		if(mainWindow != null) {
			mainWindow.setImage(pg);
		}
		mainWindow.revalidate();
		*/
	}
	
	public void startSimulation() throws Exception {
		System.out.println("Region : " + simulationRegion.toString());
		if(populationGrid == null) throw new Exception("Population Grid not set.");
		
		simulation = new PSOSimulation();
		simulation.run();
	}
}
