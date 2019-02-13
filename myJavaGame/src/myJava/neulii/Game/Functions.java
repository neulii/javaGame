package myJava.neulii.Game;

import java.awt.Point;

/**
 * A Utily Class from Neulii
 * @author neulii
 *
 */
public class Functions {
	/**
	 * Moves The Point in direction x and y
	 * @param p	is the Point to Move
	 * @param x pixels in X direction
	 * @param y pixels in Y direction
	 */
	public static void movePoint(Point p, double x, double y) {
		p.setLocation(p.getX()+x, p.getY()+y);
		//System.out.println(p.toString());
	
	}
	

}
