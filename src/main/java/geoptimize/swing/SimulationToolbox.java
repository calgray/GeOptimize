package geoptimize.swing;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeListener;

import javax.swing.Action;

/***
 * TODO: Move simulation stuff from MainWindow to here
 * 
 * @author Callan
 *
 */
public class SimulationToolbox extends JPanel {
	private static final long serialVersionUID = -8895451369549070077L;

	private MainWindow parent;
	
	private JButton btnStart;
	private JLabel lblPsoSettings;
	private JLabel lblNodeSettings;
	private JLabel lblCount;
	private JLabel lblParticles;
	private JSpinner spinner;
	private JSpinner spinner_1;
	private JLabel lblRadius_1;
	private JSpinner spinner_2;
	private JLabel lblIterations;
	private JSpinner spinner_3;
	private JLabel lblDatamap;
	private JLabel lblBackground;
	public JTextField txtPopulation;
	protected JTextField txtBackground;
	private JButton btnOpenPopulation;
	private JButton btnOpenBackground;
	private JCheckBox chckbxShowSwarm;
	private JCheckBox chckbxShowGBest;
	private JLabel lblRegion;
	protected JSpinner spnRegionX;
	protected JSpinner spnRegionY;
	protected JSpinner spnRegionW;
	protected JSpinner spnRegionH;
	private JLabel lblRegionwH;
	private JButton btnStep;
	
	public SimulationToolbox(MainWindow mainWindow) {
		this.parent = mainWindow;
		initUI();
		
	}
	
	public void initUI() {
		
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0, 0, 0, 0};
		gbl_panel.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		this.setLayout(gbl_panel);
		
		lblDatamap = new JLabel("Population");
		GridBagConstraints gbc_lblDatamap = new GridBagConstraints();
		gbc_lblDatamap.anchor = GridBagConstraints.EAST;
		gbc_lblDatamap.insets = new Insets(0, 0, 5, 5);
		gbc_lblDatamap.gridx = 0;
		gbc_lblDatamap.gridy = 1;
		add(lblDatamap, gbc_lblDatamap);
		
		txtPopulation = new JTextField();
		GridBagConstraints gbc_txtBestest = new GridBagConstraints();
		gbc_txtBestest.gridwidth = 2;
		gbc_txtBestest.insets = new Insets(0, 0, 5, 5);
		gbc_txtBestest.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBestest.gridx = 1;
		gbc_txtBestest.gridy = 1;
		add(txtPopulation, gbc_txtBestest);
		txtPopulation.setColumns(10);
		
		btnOpenPopulation = new JButton("");
		btnOpenPopulation.setIcon(new ImageIcon(SimulationToolbox.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		btnOpenPopulation.setAction(parent.openPopulationAction);
		GridBagConstraints gbc_btnOpen = new GridBagConstraints();
		gbc_btnOpen.insets = new Insets(0, 0, 5, 0);
		gbc_btnOpen.gridx = 3;
		gbc_btnOpen.gridy = 1;
		add(btnOpenPopulation, gbc_btnOpen);
		
		lblBackground = new JLabel("Background");
		GridBagConstraints gbc_lblBackground = new GridBagConstraints();
		gbc_lblBackground.anchor = GridBagConstraints.EAST;
		gbc_lblBackground.insets = new Insets(0, 0, 5, 5);
		gbc_lblBackground.gridx = 0;
		gbc_lblBackground.gridy = 2;
		add(lblBackground, gbc_lblBackground);
		
		txtBackground = new JTextField();
		GridBagConstraints gbc_txtBackground = new GridBagConstraints();
		gbc_txtBackground.gridwidth = 2;
		gbc_txtBackground.insets = new Insets(0, 0, 5, 5);
		gbc_txtBackground.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBackground.gridx = 1;
		gbc_txtBackground.gridy = 2;
		add(txtBackground, gbc_txtBackground);
		txtBackground.setColumns(10);
		
		btnOpenBackground = new JButton("");
		btnOpenBackground.setIcon(new ImageIcon(SimulationToolbox.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		btnOpenBackground.setAction(parent.openBackgroundAction);
		GridBagConstraints gbc_btnOpenBackground = new GridBagConstraints();
		gbc_btnOpenBackground.insets = new Insets(0, 0, 5, 0);
		gbc_btnOpenBackground.gridx = 3;
		gbc_btnOpenBackground.gridy = 2;
		add(btnOpenBackground, gbc_btnOpenBackground);
		
		lblNodeSettings = new JLabel("Node Settings");
		GridBagConstraints gbc_lblNodeSettings = new GridBagConstraints();
		gbc_lblNodeSettings.gridwidth = 4;
		gbc_lblNodeSettings.insets = new Insets(0, 0, 5, 0);
		gbc_lblNodeSettings.gridx = 0;
		gbc_lblNodeSettings.gridy = 3;
		this.add(lblNodeSettings, gbc_lblNodeSettings);
		
		lblCount = new JLabel("Count");
		GridBagConstraints gbc_lblCount = new GridBagConstraints();
		gbc_lblCount.insets = new Insets(0, 0, 5, 5);
		gbc_lblCount.anchor = GridBagConstraints.EAST;
		gbc_lblCount.gridx = 0;
		gbc_lblCount.gridy = 4;
		this.add(lblCount, gbc_lblCount);
		
		spinner_1 = new JSpinner();
		GridBagConstraints gbc_spinner_1 = new GridBagConstraints();
		gbc_spinner_1.gridwidth = 3;
		gbc_spinner_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_1.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_1.gridx = 1;
		gbc_spinner_1.gridy = 4;
		add(spinner_1, gbc_spinner_1);
		
		lblRadius_1 = new JLabel("Radius");
		GridBagConstraints gbc_lblRadius_1 = new GridBagConstraints();
		gbc_lblRadius_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblRadius_1.gridx = 0;
		gbc_lblRadius_1.gridy = 5;
		add(lblRadius_1, gbc_lblRadius_1);
		
		spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(new Float(50), new Float(0), null, new Float(1)));
		GridBagConstraints gbc_spinner_2 = new GridBagConstraints();
		gbc_spinner_2.gridwidth = 3;
		gbc_spinner_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_2.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_2.gridx = 1;
		gbc_spinner_2.gridy = 5;
		add(spinner_2, gbc_spinner_2);
		
		lblRegion = new JLabel("Region (x, y)");
		GridBagConstraints gbc_lblRegion = new GridBagConstraints();
		gbc_lblRegion.anchor = GridBagConstraints.EAST;
		gbc_lblRegion.insets = new Insets(0, 0, 5, 5);
		gbc_lblRegion.gridx = 0;
		gbc_lblRegion.gridy = 6;
		add(lblRegion, gbc_lblRegion);
		
		spnRegionX = new JSpinner();
		spnRegionX.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(1)));
		spnRegionX.addChangeListener((e) -> {
			Rectangle r = parent.context.getRegion();
			r.x = (Integer)spnRegionX.getValue();
			parent.context.setRegion(r);
		});
		GridBagConstraints gbc_spinner_4 = new GridBagConstraints();
		gbc_spinner_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_4.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_4.gridx = 1;
		gbc_spinner_4.gridy = 6;
		add(spnRegionX, gbc_spinner_4);
		
		spnRegionY = new JSpinner();
		spnRegionY.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(1)));
		spnRegionY.addChangeListener((e) -> {
			Rectangle r = parent.context.getRegion();
			r.y = (Integer)spnRegionY.getValue();
			parent.context.setRegion(r);
		});
		GridBagConstraints gbc_spinner_5 = new GridBagConstraints();
		gbc_spinner_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_5.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_5.gridx = 2;
		gbc_spinner_5.gridy = 6;
		add(spnRegionY, gbc_spinner_5);
		
		lblRegionwH = new JLabel("Region (w, h)");
		GridBagConstraints gbc_lblRegionwH = new GridBagConstraints();
		gbc_lblRegionwH.insets = new Insets(0, 0, 5, 5);
		gbc_lblRegionwH.gridx = 0;
		gbc_lblRegionwH.gridy = 7;
		add(lblRegionwH, gbc_lblRegionwH);
		
		spnRegionW = new JSpinner();
		spnRegionW.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(1)));
		spnRegionW.addChangeListener((e) -> {
			Rectangle r = parent.context.getRegion();
			r.width = (Integer)spnRegionW.getValue();
			parent.context.setRegion(r);
		});
		GridBagConstraints gbc_spinner_6 = new GridBagConstraints();
		gbc_spinner_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_6.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_6.gridx = 1;
		gbc_spinner_6.gridy = 7;
		add(spnRegionW, gbc_spinner_6);
		
		spnRegionH = new JSpinner();
		spnRegionH.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(1)));
		spnRegionH.addChangeListener((e) -> {
			Rectangle r = parent.context.getRegion();
			r.height = (Integer)spnRegionH.getValue();
			parent.context.setRegion(r);
		});
		GridBagConstraints gbc_spinner_7 = new GridBagConstraints();
		gbc_spinner_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_7.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_7.gridx = 2;
		gbc_spinner_7.gridy = 7;
		add(spnRegionH, gbc_spinner_7);
		
		lblPsoSettings = new JLabel("PSO Settings");
		GridBagConstraints gbc_lblPsoSettings = new GridBagConstraints();
		gbc_lblPsoSettings.gridwidth = 4;
		gbc_lblPsoSettings.insets = new Insets(0, 0, 5, 0);
		gbc_lblPsoSettings.gridx = 0;
		gbc_lblPsoSettings.gridy = 8;
		this.add(lblPsoSettings, gbc_lblPsoSettings);
		
		lblParticles = new JLabel("Particles");
		GridBagConstraints gbc_lblParticles = new GridBagConstraints();
		gbc_lblParticles.anchor = GridBagConstraints.EAST;
		gbc_lblParticles.insets = new Insets(0, 0, 5, 5);
		gbc_lblParticles.gridx = 0;
		gbc_lblParticles.gridy = 9;
		add(lblParticles, gbc_lblParticles);
		
		spinner = new JSpinner();
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.gridwidth = 3;
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.insets = new Insets(0, 0, 5, 0);
		gbc_spinner.gridx = 1;
		gbc_spinner.gridy = 9;
		add(spinner, gbc_spinner);
		
		lblIterations = new JLabel("Iterations");
		GridBagConstraints gbc_lblIterations = new GridBagConstraints();
		gbc_lblIterations.insets = new Insets(0, 0, 5, 5);
		gbc_lblIterations.gridx = 0;
		gbc_lblIterations.gridy = 10;
		add(lblIterations, gbc_lblIterations);
		
		spinner_3 = new JSpinner();
		GridBagConstraints gbc_spinner_3 = new GridBagConstraints();
		gbc_spinner_3.gridwidth = 3;
		gbc_spinner_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_3.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_3.gridx = 1;
		gbc_spinner_3.gridy = 10;
		add(spinner_3, gbc_spinner_3);
		
		chckbxShowSwarm = new JCheckBox("Show Swarm");
		chckbxShowSwarm.addItemListener((e) -> {
			parent.controller.setShowSwarm(e.getStateChange() == ItemEvent.SELECTED);
		});
		
		GridBagConstraints gbc_chckbxShowSwarm = new GridBagConstraints();
		gbc_chckbxShowSwarm.anchor = GridBagConstraints.WEST;
		gbc_chckbxShowSwarm.gridwidth = 3;
		gbc_chckbxShowSwarm.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxShowSwarm.gridx = 1;
		gbc_chckbxShowSwarm.gridy = 11;
		add(chckbxShowSwarm, gbc_chckbxShowSwarm);
		
		chckbxShowGBest = new JCheckBox("Show GBest");
		chckbxShowGBest.addItemListener((e) -> {
			parent.controller.setShowGBest(e.getStateChange() == ItemEvent.SELECTED);
		});
		
		GridBagConstraints gbc_chckbxShowGBest = new GridBagConstraints();
		gbc_chckbxShowGBest.anchor = GridBagConstraints.WEST;
		gbc_chckbxShowGBest.gridwidth = 3;
		gbc_chckbxShowGBest.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxShowGBest.gridx = 1;
		gbc_chckbxShowGBest.gridy = 12;
		add(chckbxShowGBest, gbc_chckbxShowGBest);
		
		btnStart = new JButton("Start");
		btnStart.addActionListener((evt) -> {
			try {
				parent.context.startSimulation();
			} catch(Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.toString());
			}
		});
		btnStart.setAlignmentY(Component.TOP_ALIGNMENT);
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnStart.insets = new Insets(0, 5, 5, 5);
		gbc_btnStart.gridwidth = 4;
		gbc_btnStart.gridx = 0;
		gbc_btnStart.gridy = 13;
		this.add(btnStart, gbc_btnStart);
		
		btnStep = new JButton("Step");
		GridBagConstraints gbc_btnStep = new GridBagConstraints();
		gbc_btnStep.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnStep.gridwidth = 4;
		gbc_btnStep.insets = new Insets(0, 5, 5, 5);
		gbc_btnStep.gridx = 0;
		gbc_btnStep.gridy = 14;
		add(btnStep, gbc_btnStep);
	}
}
