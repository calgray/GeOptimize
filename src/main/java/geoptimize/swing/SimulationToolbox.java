package geoptimize.swing;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/***
 * TODO: Move simulation stuff from MainWindow to here
 * 
 * @author Callan
 *
 */
public class SimulationToolbox extends JPanel {
	
	private MainWindow parent;
	
	private JButton btnStart;
	private JLabel lblPsoSettings;
	private JLabel lblNodeSettings;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblCount;
	private JLabel lblRadius;
	
	public SimulationToolbox(MainWindow mainWindow) {
		this.parent = mainWindow;
	}
	
	public void initUI() {
		
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {48, 69};
		gbl_panel.rowHeights = new int[] {30, 30, 0, 0, 0, 0, 30, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		this.setLayout(gbl_panel);
		
		lblNodeSettings = new JLabel("Node Settings");
		GridBagConstraints gbc_lblNodeSettings = new GridBagConstraints();
		gbc_lblNodeSettings.gridwidth = 2;
		gbc_lblNodeSettings.insets = new Insets(0, 0, 5, 0);
		gbc_lblNodeSettings.gridx = 0;
		gbc_lblNodeSettings.gridy = 2;
		this.add(lblNodeSettings, gbc_lblNodeSettings);
		
		lblCount = new JLabel("Count");
		GridBagConstraints gbc_lblCount = new GridBagConstraints();
		gbc_lblCount.insets = new Insets(0, 0, 5, 5);
		gbc_lblCount.anchor = GridBagConstraints.EAST;
		gbc_lblCount.gridx = 0;
		gbc_lblCount.gridy = 3;
		this.add(lblCount, gbc_lblCount);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 3;
		this.add(textField, gbc_textField);
		textField.setColumns(10);
		
		lblRadius = new JLabel("Radius");
		GridBagConstraints gbc_lblRadius = new GridBagConstraints();
		gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
		gbc_lblRadius.anchor = GridBagConstraints.EAST;
		gbc_lblRadius.gridx = 0;
		gbc_lblRadius.gridy = 4;
		this.add(lblRadius, gbc_lblRadius);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 4;
		this.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		lblPsoSettings = new JLabel("PSO Settings");
		GridBagConstraints gbc_lblPsoSettings = new GridBagConstraints();
		gbc_lblPsoSettings.gridwidth = 2;
		gbc_lblPsoSettings.insets = new Insets(0, 0, 5, 0);
		gbc_lblPsoSettings.gridx = 0;
		gbc_lblPsoSettings.gridy = 7;
		this.add(lblPsoSettings, gbc_lblPsoSettings);
		
		btnStart = new JButton("Start");
		btnStart.setAlignmentY(Component.TOP_ALIGNMENT);
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.insets = new Insets(0, 0, 5, 0);
		gbc_btnStart.gridwidth = 2;
		gbc_btnStart.gridx = 0;
		gbc_btnStart.gridy = 8;
		this.add(btnStart, gbc_btnStart);
		
	}
}
