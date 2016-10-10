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
	
	public void createWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				mainWindow = new MainWindow(SimulationManager.this);
				mainWindow.setVisible(true);
			}});
	}
	
	public void loadPopulationGrid(File f) throws IOException {
		BufferedImage pg = ImageIO.read(f);
		populationGrid = pg;
		if(mainWindow != null) {
			mainWindow.setImage(pg);
		}
	}
	
	public void startSimulation() {
		simulation.run();
	}
}
