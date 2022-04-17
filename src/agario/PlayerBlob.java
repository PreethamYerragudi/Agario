package agario;

import java.awt.Color;
import java.awt.Graphics;

public class PlayerBlob extends Blob {
	
	public double getXVel() { return xVel; }
	public double getYVel() { return yVel; }
	public Color getColor() { return color; }
	
	public PlayerBlob() {
		x = (int)(Math.random() * 300) + 350;
		y = (int)(Math.random() * 300) + 300;
		color = new Color((int)(Math.random() * 200), (int)(Math.random() * 200), (int)(Math.random() * 200));
		mass = 30;
		speed = 6;
	}
	
	public void increaseMass(double increase) {
		this.mass += increase;
	}
	
	public boolean overlaps(double x1, double y1, double r1) {
		double distSq = (int) Math.sqrt(((x - x1) * (x - x1)) + ((y - y1) * (y - y1))); 
		if (distSq + r1 < mass) 
            return true;
		return false;
		
	}
	public void follow(int mouseX, int mouseY) {
		double deltaX = mouseX - x;
		double deltaY = mouseY - y;
		double hypotenuse = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
		
		// prevents blob from oscillating between mouse
		if (Math.abs(hypotenuse) < 4) {
			xVel = 0;
			yVel = 0;
		} else {
			xVel = (int)(deltaX * (speed / hypotenuse));
			yVel = (int)(deltaY * (speed / hypotenuse));
		}
	}
	
	public void move() {
		x += xVel;
		y += yVel;
	}
	
	public void feed() {
		mass -= 1.5;
	}
	
	public void split() {
		
	}
	
	public void drawMass(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("" + (int) mass, (int) x - (("" + (int) mass).length() * 5) + 5, (int) y + 5);
	}
}
