package geoptimize.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JComponent;

public class ImageCanvas extends JComponent {
	private static final long serialVersionUID = -2480881754680088085L;

	private Image image;
	private int magnification = 1;
	
	public void setMagnification(int mag) {
		if(magnification != mag && image != null) {
			magnification = mag;
			Dimension d = this.getSize();
			d.width = image.getWidth(null) * magnification;
			d.height = image.getHeight(null) * magnification;
			
			setPreferredSize(d);
			revalidate();
			repaint();
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
	
	public ImageCanvas() {
		this.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				ImageCanvas.this.zoom(e.getWheelRotation());
			}
		});
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);	
		drawBackground(graphics);	
	}
	
	private void drawBackground(Graphics graphics) {
		if (image != null) {
			int width = image.getWidth(null) * magnification;
			int height = image.getHeight(null) * magnification;	
			graphics.drawImage(image, 0, 0, width, height, null);
		}
	}
	
}
