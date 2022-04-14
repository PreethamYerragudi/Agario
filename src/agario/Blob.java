package agario;

import java.awt.Color;
import java.awt.Graphics;

public class Blob {
	
	protected int x;
	protected int y;
	protected int mass;
	protected Color color;
	
	public int getX() { return x; }
	public int getY() { return y; }
	public int getMass() { return mass; }
	
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }

	public Blob() {
		x = (int)(Math.random() * 900);
		y = (int)(Math.random() * 700);
		mass = (int)(Math.random() * 4) + 8;
		color = new Color((int)(Math.random() * 200), (int)(Math.random() * 200), (int)(Math.random() * 200));
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x - mass, y - mass, mass * 2, mass * 2);
	}
}
