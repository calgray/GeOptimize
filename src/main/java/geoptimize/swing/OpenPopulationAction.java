package geoptimize.swing;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class OpenPopulationAction extends AbstractAction {
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