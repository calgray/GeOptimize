
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
		manager.createWindow();
	}
}
