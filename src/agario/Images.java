package agario;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {

	public static Image SCHIEFER;
	public static Image POTATO;
	
	public void load() {
		try {
			SCHIEFER = ImageIO.read(getClass().getResource("/images/schiefer.png"));
			POTATO = ImageIO.read(getClass().getResource("/images/potato.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
