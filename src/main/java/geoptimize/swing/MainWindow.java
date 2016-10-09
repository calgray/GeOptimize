package geoptimize.swing;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import javax.swing.*;

import geoptimize.MapFilter;
import geoptimize.ServiceNode;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = -2244890355971879323L;

	//Business Objects
	//private static final TIFFImageReaderSpi SPI = new TIFFImageReaderSpi();
	
	protected BufferedImage backgroundImage;
	protected BufferedImage heatmap;
	protected LinkedList<ServiceNode> nodes;
	
	//Panes
	private JSplitPane splitPane;
	private JTabbedPane tabbedPane;
	private SimulationCanvas simulationCanvas;
	private ImageCanvas heatmapCanvas;
	
	//Menu
	private JMenuBar menuBar;
	private JMenu mnFile, mnView, mnSettings, mnHelp;
	private JMenuItem mntmOpen;
	private JSeparator separator;
	private JMenuItem mntmExit;
	private JMenuItem mntmZoomIn;
	private JMenuItem mntmZoomOut;
	private final Action actionZoomIn = new ZoomInAction();
	private final Action actionZoomOut = new ZoomOutAction();

	//Toolbox
	private JPanel panel;
	private JButton btnStart;
	private JLabel lblPsoSettings;
	private JLabel lblNodeSettings;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblCount;
	private JLabel lblRadius;
	
	public void setImage(BufferedImage img) {
		backgroundImage = img;
		simulationCanvas.setImage(backgroundImage);
		
		//heatmap = MapFilter.heatmapFilter(img);
		//heatmapCanvas.setImage(heatmap);
	}
	
	public MainWindow() {
		
		this.setMinimumSize(new Dimension(600, 400));
		initUI();
	}
	
	private void initUI() {
		setTitle("GeOptimize");
		
		initMenubar();
		
		splitPane = new JSplitPane();
		splitPane.setContinuousLayout(true);
		this.setContentPane(splitPane);
		
		//scrollPane = new ScrollPane();
		//this.setContentPane(scrollPane);
		
		tabbedPane = new JTabbedPane();
		splitPane.setRightComponent(tabbedPane);
		
		simulationCanvas = new SimulationCanvas();
		tabbedPane.addTab("Simulation", simulationCanvas);

		heatmapCanvas = new ImageCanvas();
		tabbedPane.addTab("HeatMap", heatmapCanvas);
		
		initToolbox();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		repaint();
		revalidate();
	}
	
	private void initMenubar() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmOpen = new JMenuItem("Open");
		mntmOpen.setAction(new OpenAction());
		mnFile.add(mntmOpen);
		
		separator = new JSeparator();
		mnFile.add(separator);
		
		mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		mnView = new JMenu("View");
		menuBar.add(mnView);
		
		mntmZoomIn = new JMenuItem();
		mntmZoomIn.setAction(actionZoomIn);
		mnView.add(mntmZoomIn);
		
		mntmZoomOut = new JMenuItem();
		mntmZoomOut.setAction(actionZoomOut);
		mnView.add(mntmZoomOut);
		
		mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);
		
		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
	}
	
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
	
	@SuppressWarnings("serial")
	private class OpenAction extends AbstractAction {
		public OpenAction() {
			putValue(NAME, "Open");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
			
			int result = fc.showOpenDialog(MainWindow.this);
			
			if(result == JFileChooser.APPROVE_OPTION) {
				File f = fc.getSelectedFile();
				try {
					//PNG/BMP WAY
					BufferedImage img = ImageIO.read(f);
					MainWindow.this.setImage(img);
					
					//TIFF
					/*
					ImageInputStream stream = new FileImageInputStream(f);
					ImageReader reader = SPI.createReaderInstance(null);
					reader.setInput(stream);
					
					
					//ImageReadParam param = reader.getDefaultReadParam();//this won't work
					ImageReadParam param = new ImageReadParam();
					
					param.setSourceRegion(new Rectangle(1000, 1000));
					
					BufferedImage img = reader.read(0);
					
					//image now in float32 format
					System.out.println("width : " + img.getWidth());
					System.out.println("colormodel: " + img.getColorModel());
					System.out.println("pixel : " + (float)(img.getRGB(0, 0)));
					
					ColorSpace colorspace = ColorSpace.getInstance(ColorSpace.CS_GRAY);
					ColorModel colorModel = new ComponentColorModel(colorspace, false, false, Transparency.OPAQUE, DataBuffer.TYPE_INT);
					BufferedImageOp converter = new ColorConvertOp(colorspace, null);
					BufferedImage newimg = converter.createCompatibleDestImage(img, colorModel);
					converter.filter(img, newimg);
					
					System.out.println("pixel : " + (newimg.getRGB(0, 0)));
					
					
					//ImageReaderSpi reader = new ImageReaderSpi();
					//TIFFImageReader reader = new TIFFImageReader(null);
					
					MainWindow.this.setImage(img);
					*/
					
					//ImageJ
					//ImagePlus img = new ImagePlus(f.getAbsolutePath());
					//System.out.println("Dimension : " + img.getDimensions().toString());
					
					
				}  catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	@SuppressWarnings("serial")
	private class ZoomInAction extends AbstractAction {
		public ZoomInAction() {
			putValue(NAME, "Zoom In");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			simulationCanvas.zoomIn();
			MainWindow.this.invalidate();
		}
	}
	
	@SuppressWarnings("serial")
	private class ZoomOutAction extends AbstractAction {
		public ZoomOutAction() {
			putValue(NAME, "Zoom Out");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			simulationCanvas.zoomOut();
			MainWindow.this.invalidate();
		}
	}
}
