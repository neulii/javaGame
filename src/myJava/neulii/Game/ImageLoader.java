package myJava.neulii.Game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author neulii
 *
 *Load a image from File
 */
public class ImageLoader {
	
	public static BufferedImage loadImage(String fileName) {
		BufferedImage image = null;
		
		try {
			 image = ImageIO.read(ImageLoader.class.getResource(fileName));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Fehler beim Bilder laden!! ");
			//System.out.println("Programm wird beednet!!");
			//System.exit(1);
		}
		
		return image;
		
	}

}
