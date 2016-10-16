
package geoptimize;

import java.awt.Rectangle;
import java.io.File;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import geoptimize.swing.MainWindow;

/**
 * Static entry point to load the Swing MainWindow class.
 * 
 * @author Callan
 *
 */
public class Program {
	
	public static void main(String[] args) {
		SimulationManager manager = new SimulationManager();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainWindow window = manager.createWindow();
				manager.setRegion(new Rectangle(2000, 2000, 1000, 1000));
				
				//save some manual testing time by loading these at startup
				try {
					manager.loadPopulationGrid(new File("./images/populationgrid-uint16.png"));
					manager.loadBackground(new File("./images/highres_combined.png"));
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				window.setVisible(true);
			}
		});
	}
}
