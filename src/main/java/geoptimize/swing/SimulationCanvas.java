package geoptimize.swing;

import java.util.LinkedList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JComponent;
import geoptimize.ServiceNode;

public class SimulationCanvas extends JComponent {
	private static final long serialVersionUID = -2480881754680088455L;

	private LinkedList<ServiceNode> nodes;
	
	private Image image;
	private int magnification = 1;
	
	public void setMagnification(int mag) {
		if(magnification != mag && image != null) {
			magnification = mag;
			Dimension d = this.getSize();
			d.width = image.getWidth(null) * magnification;
			d.height = image.getHeight(null) * magnification;
			this.setPreferredSize(d);
			this.revalidate();
			this.repaint();
		}
	}
	
	public void zoom(int zoom) {
		setMagnification(Math.max(1, Math.min(10, magnification+zoom)));
	}
	
	public void zoomIn() { 
		setMagnification((magnification < 10) ? magnification+1 : magnification);
	} 
	
	public void zoomOut() { 
		setMagnification((magnification > 1) ? magnification-1 : magnification);
	}
	
	public void setImage(Image image) {
		this.image = image;
		setMagnification(2);
	}
	
	public SimulationCanvas() {
		
		nodes = new LinkedList<ServiceNode>();
		nodes.add(new ServiceNode(140, 160, 40));
		nodes.add(new ServiceNode(160, 180, 40));
		nodes.add(new ServiceNode(160, 200, 40));
		
		this.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				SimulationCanvas.this.zoom(e.getWheelRotation());
			}
		});
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		
		drawBackground(graphics);
		drawNodes(graphics);
	}
	
	private void drawNodes(Graphics graphics) {
		graphics.setColor(Color.red);
		for(ServiceNode node : nodes) {
			Point p = node.getPosition();
			float r = node.getRange();
			graphics.drawOval(
					p.x * magnification,
					p.y * magnification,
					(int)r * magnification,
					(int)r * magnification);
		}
	}
	
	private void drawBackground(Graphics graphics) {
		if (image != null) {
			Dimension size = this.getSize();
			int width = image.getWidth(null) * magnification;
			int height = image.getHeight(null) * magnification;
			
			graphics.drawImage(image, 0, 0, width, height, null);
		}
	}
}
