package geoptimize.swing;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainMenuBar extends JMenuBar {
	
	private MainWindow parent;
	
	private JMenu mnFile, mnView, mnSettings, mnHelp;
	private JMenuItem mntmOpen;
	private JSeparator separator;
	private JMenuItem mntmExit;
	private JMenuItem mntmZoomIn;
	private JMenuItem mntmZoomOut;
	
	
	public MainMenuBar(MainWindow window) {
		super();
		parent = window;
		initUI();
	}
	
	private void initUI() {
		
		mnFile = new JMenu("File");
		this.add(mnFile);
		
		mntmOpen = new JMenuItem("Open");
		mntmOpen.setAction(new OpenAction(parent));
		mnFile.add(mntmOpen);
		
		separator = new JSeparator();
		mnFile.add(separator);
		
		mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		mnView = new JMenu("View");
		this.add(mnView);
		
		mntmZoomIn = new JMenuItem();
		mntmZoomIn.setAction(new ZoomInAction(parent));
		mnView.add(mntmZoomIn);
		
		mntmZoomOut = new JMenuItem();
		mntmZoomOut.setAction(new ZoomOutAction(parent));
		mnView.add(mntmZoomOut);
		
		mnSettings = new JMenu("Settings");
		this.add(mnSettings);
		
		mnHelp = new JMenu("Help");
		this.add(mnHelp);
	}
	
	@SuppressWarnings("serial")
	private class OpenAction extends AbstractAction {
		MainWindow window;
		
		public OpenAction(MainWindow window) {
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
					window.context.loadPopulationGrid(f);
				}  catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	@SuppressWarnings("serial")
	private class ZoomInAction extends AbstractAction {
		private MainWindow window;
		
		public ZoomInAction(MainWindow window) {
			this.window = window;
			putValue(NAME, "Zoom In");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			window.simulationCanvas.zoomIn();
			window.invalidate();
		}
	}
	
	@SuppressWarnings("serial")
	private class ZoomOutAction extends AbstractAction {
		private MainWindow window;
		
		public ZoomOutAction(MainWindow window) {
			this.window = window;
			putValue(NAME, "Zoom Out");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			window.simulationCanvas.zoomOut();
			window.invalidate();
		}
	}
}
