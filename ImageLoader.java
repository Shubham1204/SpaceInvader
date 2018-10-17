import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader implements GameConstants{
	private static BufferedImage bg;
	static 
	{
		loadImage();
	}
	public static void loadImage() {
		try {
			bg = ImageIO.read(ImageLoader.class.getResource("IMAGE"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


public static BufferedImage[] loadAlien() {
	BufferedImage aArray [] = new BufferedImage[3];
	aArray[0] = bg.getSubimage(3, 7, 106-3, 65-7);
	aArray[1] = bg.getSubimage(142, 3, 231-142, 67-3);
	aArray[2] = bg.getSubimage(253, 5, 365-253, 63-5);
	return aArray;
}

public static BufferedImage[] loadPlayer() {
	BufferedImage aArray [] = new BufferedImage[1];
	aArray[0] = bg.getSubimage(377, 11, 464-377, 47-11);
	return aArray;
}

}
