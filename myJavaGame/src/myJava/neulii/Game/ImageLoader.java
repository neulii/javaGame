package myJava.neulii.Game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * 
 * @author neulii
 *
 *Load a image from File
 */
public class ImageLoader {
	
	public static Image loadImage(String fileName) {
		Image image = null;
		
		 try {
			image = new Image("images/" + fileName);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return image;
	}
}
