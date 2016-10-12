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
		
		//TODO: might want to clarify what this does,
		//or maybe behave as a sort of project loading.
		mntmOpen = new JMenuItem("Open");
		mntmOpen.setAction(parent.openBackgroundAction);
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
