
package geoptimize;

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
				window.setVisible(true);
			}
		});
	}
}
