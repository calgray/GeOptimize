package geoptimize.swing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import javax.swing.*;

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
	//private static final TIFFImageReaderSpi SPI = new TIFFImageReaderSpi();
	
	protected SimulationManager context;
	
	protected LinkedList<ServiceNode> nodes;
	protected BufferedImage backgroundImage;
	protected BufferedImage heatmap;
	
	
	//Panes
	protected JSplitPane splitPane;
	protected JTabbedPane tabbedPane;
	protected JScrollPane simulationScrollPane;
	protected SimulationCanvas simulationCanvas;
	protected JScrollPane heatmapScrollPane;
	protected ImageCanvas heatmapCanvas;
	
	//Menu
	protected MainMenuBar menuBar;

	//Toolbox
	protected JPanel panel;
	protected JButton btnStart;
	protected JLabel lblPsoSettings;
	protected JLabel lblNodeSettings;
	protected JTextField textField;
	protected JTextField textField_1;
	protected JLabel lblCount;
	protected JLabel lblRadius;

	
	
	public MainWindow(SimulationManager context) {
		this.context = context;
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
		
		initToolbox();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		repaint();
		revalidate();
	}
	
	//TODO: move this
	private void initToolbox() {
		panel = new JPanel();
		splitPane.setLeftComponent(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {48, 69};
		gbl_panel.rowHeights = new int[] {30, 30, 0, 0, 0, 0, 30, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		panel.setLayout(gbl_panel);
		
		lblNodeSettings = new JLabel("Node Settings");
		GridBagConstraints gbc_lblNodeSettings = new GridBagConstraints();
		gbc_lblNodeSettings.gridwidth = 2;
		gbc_lblNodeSettings.insets = new Insets(0, 0, 5, 0);
		gbc_lblNodeSettings.gridx = 0;
		gbc_lblNodeSettings.gridy = 2;
		panel.add(lblNodeSettings, gbc_lblNodeSettings);
		
		lblCount = new JLabel("Count");
		GridBagConstraints gbc_lblCount = new GridBagConstraints();
		gbc_lblCount.insets = new Insets(0, 0, 5, 5);
		gbc_lblCount.anchor = GridBagConstraints.EAST;
		gbc_lblCount.gridx = 0;
		gbc_lblCount.gridy = 3;
		panel.add(lblCount, gbc_lblCount);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 3;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		lblRadius = new JLabel("Radius");
		GridBagConstraints gbc_lblRadius = new GridBagConstraints();
		gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
		gbc_lblRadius.anchor = GridBagConstraints.EAST;
		gbc_lblRadius.gridx = 0;
		gbc_lblRadius.gridy = 4;
		panel.add(lblRadius, gbc_lblRadius);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 4;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		lblPsoSettings = new JLabel("PSO Settings");
		GridBagConstraints gbc_lblPsoSettings = new GridBagConstraints();
		gbc_lblPsoSettings.gridwidth = 2;
		gbc_lblPsoSettings.insets = new Insets(0, 0, 5, 0);
		gbc_lblPsoSettings.gridx = 0;
		gbc_lblPsoSettings.gridy = 7;
		panel.add(lblPsoSettings, gbc_lblPsoSettings);
		
		btnStart = new JButton("Start");
		btnStart.setAlignmentY(Component.TOP_ALIGNMENT);
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.insets = new Insets(0, 0, 5, 0);
		gbc_btnStart.gridwidth = 2;
		gbc_btnStart.gridx = 0;
		gbc_btnStart.gridy = 8;
		panel.add(btnStart, gbc_btnStart);

	}
	
	
	public void setImage(BufferedImage img) {
		backgroundImage = img;
		simulationCanvas.setImage(backgroundImage);
		
		simulationCanvas.revalidate();
		simulationScrollPane.revalidate();
		MainWindow.this.revalidate();
	}
	
	


}
