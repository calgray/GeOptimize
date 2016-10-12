package geoptimize.swing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import geoptimize.SimulationManager;
import geoptimize.ServiceNode;


/**
 * TODO: move toolbox content to SimulationToolbox.
 * 
 * @author Callan
 *
 */
public class MainWindow extends JFrame {
	private static final long serialVersionUID = -2244890355971879323L;

	//Business Objects
	protected SimulationManager context;
	
	//TODO: these might need to move to context
	protected boolean showSwarm;
	protected boolean showGBest;
	protected LinkedList<ServiceNode> nodes;
	protected BufferedImage backgroundImage;
	protected BufferedImage heatmap;
	
	//Controllers
	protected OpenPopulationAction openPopulationAction;
	protected OpenBackgroundAction openBackgroundAction;
	
	//Views
	protected MainMenuBar menuBar;
	protected SimulationToolbox toolbox;
	protected JSplitPane splitPane;
	protected JTabbedPane tabbedPane;
	protected JScrollPane simulationScrollPane;
	protected SimulationCanvas simulationCanvas;
	protected JScrollPane heatmapScrollPane;
	protected ImageCanvas heatmapCanvas;
	
	public MainWindow(SimulationManager context) {
		this.context = context;
		
		//Controllers
		openPopulationAction = new OpenPopulationAction(this);
		openBackgroundAction = new OpenBackgroundAction(this);
		
		
		this.setMinimumSize(new Dimension(600, 400));
		initUI();
	}
	
	private void initUI() {
		setTitle("GeOptimize");
		
		menuBar = new MainMenuBar(this);
		setJMenuBar(menuBar);
		
		splitPane = new JSplitPane();
		splitPane.setContinuousLayout(true);
		this.setContentPane(splitPane);
		
		tabbedPane = new JTabbedPane();
		splitPane.setRightComponent(tabbedPane);
		
		simulationCanvas = new SimulationCanvas();
		simulationScrollPane = new JScrollPane(simulationCanvas);
		tabbedPane.addTab("Simulation", simulationScrollPane);

		heatmapCanvas = new ImageCanvas();
		heatmapScrollPane = new JScrollPane(heatmapCanvas);
		tabbedPane.addTab("HeatMap", heatmapScrollPane);
		
		toolbox = new SimulationToolbox(this);
		splitPane.setLeftComponent(toolbox);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		revalidate();
		repaint();
	}
	
	//TODO: triggered by the toolbox
	public void setShowSwarm(boolean show) {
		System.out.println("Show Swarm : " + show);
	}
	
	public void setShowGBest(boolean show) {
		System.out.println("Show GBest : " + show);
		
	}
	
	public void setImage(BufferedImage img) {
		backgroundImage = img;
		simulationCanvas.setImage(backgroundImage);
		
		simulationCanvas.revalidate();
		simulationScrollPane.revalidate();
		simulationScrollPane.repaint();
		
		MainWindow.this.revalidate();
	}
	
	
	@SuppressWarnings("serial")
	private class OpenPopulationAction extends AbstractAction {
		MainWindow window;
		
		public OpenPopulationAction(MainWindow window) {
			this.window = window;
			putValue(NAME, "Open");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fc.setFileFilter(new FileNameExtensionFilter("Image Formats (*.png, *.tif, *.bmp)","png","tif","bmp"));
			fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
			int result = fc.showOpenDialog(window);
			if(result == JFileChooser.APPROVE_OPTION) {
				File f = fc.getSelectedFile();
				window.toolbox.txtPopulation.setText(f.getPath());
				
				try {
					window.context.loadPopulationGrid(f);
					window.toolbox.txtPopulation.setText(f.getPath());
				}  catch (IOException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Failed to load population grid\n" + e.toString());
				}
			}
		}
	}
	
	@SuppressWarnings("serial") 
	class OpenBackgroundAction extends AbstractAction {
		MainWindow window;
		
		public OpenBackgroundAction(MainWindow window) {
			this.window = window;
			putValue(NAME, "Open");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fc.setFileFilter(new FileNameExtensionFilter("Image Formats (*.png, *.tif, *.bmp)","png","tif","bmp"));
			fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
			int result = fc.showOpenDialog(window);
			if(result == JFileChooser.APPROVE_OPTION) {
				File f = fc.getSelectedFile();
				try {
					window.context.loadBackground(f);
					window.toolbox.txtBackground.setText(f.getPath());
				}  catch (IOException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Failed to load background image\n" + e1.getMessage());
				}
			}
		}
	}

}
