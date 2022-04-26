package agario;

import java.util.ArrayList;

public class Camera {

	private double x;
	private double y;
	
	public double getX() { return x; };
	public double getY() { return y; };
	
	public void move(ArrayList<PlayerBlob> playerBlobs) {
		double averageX = 0;
		double averageY = 0;
		for (PlayerBlob p : playerBlobs) {
			averageX += p.getX();
			averageY += p.getY();
		}
		averageX /= playerBlobs.size();
		averageY /= playerBlobs.size();
		this.x = averageX - 600;
		this.y = averageY - 350;
	}
}
