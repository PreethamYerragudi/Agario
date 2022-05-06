package agario;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Minimap {

	private final int WIDTH = 200;
	private final int HEIGHT = 200;
	
	private int x;
	private int y;
	
	public Minimap() {
		x = 970;
		y = 450;
	}
	
	/**
	 * Draws the {@code Minimap}.
	 * 
	 * @param g the {@code Graphics} object that is used to draw
	 */	
	public void drawMap(Graphics g) {
		g.setColor(new Color(0, 0, 0, 0.5f));
		g.fillRect(x, y, WIDTH, HEIGHT);
	}
	
	/**
	 * Draws the list of {@code PlayerBlob} on the {@code Minimap}.
	 * 
	 * @param playerBlobs the list of {@code PlayerBlob} drawn
	 * @param g the {@code Graphics} object that is used to draw
	 */
	public void draw(ArrayList<PlayerBlob> playerBlobs, Graphics g) {
		g.setColor(playerBlobs.get(0).getColor());
		for (PlayerBlob p : playerBlobs) {
			g.fillOval(x + (int) (0.05 * (p.getX() - p.getMass())), y + (int) (0.05 * (p.getY() - p.getMass())), (int) (0.1 * p.getMass()), (int) (0.1 * p.getMass()));
		}
	}
}
