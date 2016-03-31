package imageManagement;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

/**
 * This is a Panel that holds an PixelImage and displays it. 
 * It allows for interaction with the mouse, but you will be responsible
 * for implementing this functionality.  In addition, you may want 
 * to modify it/add additional methods to implement BoundingBox.
 */
public class PixelImagePanel extends JPanel implements MouseListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PixelImage image;
	/**
	 * Creates a new PixelImagePanel.
	 * @param image
	 */
	public PixelImagePanel(PixelImage image){
		this.setImage(image);
		this.setBounds(0, 0, image.getWidth(), image.getHeight());
		this.addMouseListener(this);
	}

	/**
	 * 
	 * @return the ACTUAL image contained in this panel, NOT a copy.
	 */
	public PixelImage getPixelImage()
	{
		return image;
	}
	/**
	 * Sets the image represented by this object to the parameter image.
	 * @param image
	 */
	public void setImage(PixelImage image){
		
		this.image = image;
		this.setSize(image.getWidth(),image.getHeight());
		Dimension d = this.getSize();
		this.setPreferredSize(d);
		this.setMaximumSize(d);
		this.setMinimumSize(d);
		this.repaint();	
	}
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(image.getImage(), 0, 0, null);
		//TODO: possibly add additional functionality to the 
		//paintComponent function.
	}
	//TODO:
	//The following methods are from implementing MouseListener; you probably
	//want to implement some of them (although you only need to implement ones that
	//you actually utilize in your GUI).
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {	
	}
}
