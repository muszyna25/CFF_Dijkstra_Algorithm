package src;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JRootPane;

/*
 * Class Point2D is responsible for creating a window in map is drawn.
 */

public class Point2D extends JFrame{

	private static final long serialVersionUID = 1L;
	int x,y;
	List<Vertex> points = new ArrayList<Vertex>();
	List<Edge> lines = new ArrayList<Edge>();
	
	public Point2D(int pointX, int pointY){
		this.x = pointX;
		this.y = pointY;
	}
	
	public Point2D(){
        
        // Call the superclass constructor
        super();
        // Set window title
        this.setTitle("Switzerland map of cities");
        add(new Coordinate());
        
        this.setSize(1000, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
	}

        protected JRootPane createRootPane() {
            JRootPane rp = new JRootPane() {

				private static final long serialVersionUID = 1L;

				public void paint(Graphics g) {
                    BufferedImage im = new BufferedImage(this.getWidth(), this.getHeight(),
                            BufferedImage.TYPE_3BYTE_BGR);
                    // Paint normally but on the image
                    super.paint(im.getGraphics());

                    // Reverse the image
                    AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
                    tx.translate(0, -im.getHeight());
                    AffineTransformOp op = new AffineTransformOp(tx,
                            AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                    im = op.filter(im, null);

                    // Draw the reversed image on the screen
                    g.drawImage(im, 0, 0, null);
                }
            };
            rp.setOpaque(true);
            return rp;
        }
}

