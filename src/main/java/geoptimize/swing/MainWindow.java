package geoptimize.swing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import geoptimize.SimulationManager;
import geoptimize.ServiceNode;
import geoptimize.SimulationController;


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
	protected SimulationController controller;
	
	//TODO: these might need to move to context
	protected boolean showSwarm;
	protected boolean showGBest;
	protected LinkedList<ServiceNode> nodes;
	//protected BufferedImage backgroundImage;
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
	private JPanel mainPanel;
	protected JPanel statusBar;
	private JLabel lblCursor;
	protected JLabel lblStatusBar;
	
	public MainWindow(SimulationManager context) {
		this.context = context;
		
		//Controllers
		openPopulationAction = new OpenPopulationAction(this);
		openBackgroundAction = new OpenBackgroundAction(this);
		
		context.addPropertyChangeListener(new MainWindowPCL(this));
		
		this.setMinimumSize(new Dimension(600, 400));
		initUI();
	}
	
	private void initUI() {
		setTitle("GeOptimize");
		
		menuBar = new MainMenuBar(this);
		this.setJMenuBar(menuBar);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0, 0));
		this.setContentPane(mainPanel);
		
		splitPane = new JSplitPane();
		splitPane.setContinuousLayout(true);
		mainPanel.add(splitPane);
		
		tabbedPane = new JTabbedPane();
		splitPane.setRightComponent(tabbedPane);
		
		simulationCanvas = new SimulationCanvas(this);
		simulationScrollPane = new JScrollPane(simulationCanvas);
		tabbedPane.addTab("Simulation", simulationScrollPane);

		heatmapCanvas = new ImageCanvas();
		heatmapScrollPane = new JScrollPane(heatmapCanvas);
		tabbedPane.addTab("HeatMap", heatmapScrollPane);
		
		statusBar = new JPanel();
		mainPanel.add(statusBar, BorderLayout.SOUTH);
		GridBagLayout gbl_statusBar = new GridBagLayout();
		gbl_statusBar.columnWidths = new int[]{0, 0};
		gbl_statusBar.rowHeights = new int[]{14};
		gbl_statusBar.columnWeights = new double[]{0.0, 1.0};
		gbl_statusBar.rowWeights = new double[]{0.0};
		statusBar.setLayout(gbl_statusBar);
		
		lblStatusBar = new JLabel("");
		GridBagConstraints gbc_lblCursorcoords = new GridBagConstraints();
		gbc_lblCursorcoords.insets = new Insets(0, 5, 0, 5);
		gbc_lblCursorcoords.gridx = 0;
		gbc_lblCursorcoords.gridy = 0;
		statusBar.add(lblStatusBar, gbc_lblCursorcoords);
		
		
		toolbox = new SimulationToolbox(this);
		splitPane.setLeftComponent(toolbox);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		revalidate();
		repaint();
	}
	
	//This handles events triggered by the model
	private class MainWindowPCL implements PropertyChangeListener {
		protected MainWindow window;
		public MainWindowPCL(MainWindow window) {
			this.window = window;
		}
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			String propertyName = evt.getPropertyName();
			
			switch(propertyName) {
				case "populationGridFile": {
					File f = (File)evt.getNewValue();
					window.toolbox.txtPopulation.setText(f.getAbsolutePath());
					break;
				}
				case "backgroundImageFile": {
					File f = (File)evt.getNewValue();
					window.toolbox.txtPopulation.setText(f.getAbsolutePath());
					break;
				}
				case "backgroundImage": {
					BufferedImage img = (BufferedImage)evt.getNewValue();
					window.simulationCanvas.setBackgroundImage(img);
					break;
				}
				case "simulationRegion": {
					Rectangle rgn = (Rectangle)evt.getNewValue();
					window.toolbox.spnRegionX.setValue(rgn.x);
					window.toolbox.spnRegionY.setValue(rgn.y);
					window.toolbox.spnRegionW.setValue(rgn.width);
					window.toolbox.spnRegionH.setValue(rgn.height);
					window.simulationCanvas.setRegion(rgn);
					break;
				}
				//TODO: fire event for when the simulation state changes
			}
			
		}
		
	}
	
}
