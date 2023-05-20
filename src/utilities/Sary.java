package utilities;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sary {
	
	public Image Resize(String sary,int width,int height) {
		BufferedImage img = null;
		
		try {
			img = ImageIO.read(new File(sary));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return resizedImg;
	}

}
