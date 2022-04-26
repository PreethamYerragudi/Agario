package agario;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

public class PlayerBlob extends Blob {
	
	private boolean potato;
	
	public double getXVel() { return xVel; }
	public double getYVel() { return yVel; }
	public Color getColor() { return color; }
	public boolean getPotato() { return potato; }
	
	public void switchPotato() { this.potato = !potato; }
	
	public PlayerBlob() {
		x = (int)(Math.random() * 3000);
		y = (int)(Math.random() * 3000);
		color = new Color((int) (Math.random() * 240), (int) (Math.random() * 240), (int) (Math.random() * 240));
		mass = 100;
		speed = 6;
	}
	
	public PlayerBlob(double x, double y, double mass, Color color, boolean potato) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.mass = mass;
		this.potato = potato;
		speed = 6;
	}
	/**
	 * Increases the mass of {@code PlayerBlob} by a specified amount.
	 * 
	 * @param increase a double that mass is increased by
	 */
	public void increaseMass(double increase) {
		this.mass += increase;
	}
	
	/**
	 * Returns whether {@code PlayerBlob} completely overlaps a specified circle.
	 * 
	 * @param x1 the x position of the center of the circle
	 * @param y1 the y position of the center of the circle
	 * @param r1 the radius of the circle
	 * @return {@code true} if {@code PlayerBlob} overlaps the circle; {@code false} otherwise
	 */
	public boolean overlaps(double x1, double y1, double r1) {
		double distSq = (int) Math.sqrt(((x - x1) * (x - x1)) + ((y - y1) * (y - y1))); 
		if (distSq + r1 < mass) 
            return true;
		return false;
	}
	
	/**
	 * Updates the velocities of {@code PlayerBlob} to move towards the specified
	 * mouse coordinates.
	 * 
	 * @param mouseX the x position of the mouse
	 * @param mouseY the y position of the mouse
	 */
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
	
	/**
	 * Updates the position of {@code PlayerBlob}.
	 */
	public void move() {
		x += xVel;
		y += yVel;
	}
	
	/**
	 * Decreases the mass of {@code PlayerBlob} when it feeds a {@code Blob}.
	 */
	public void feed() {
		mass -= 1.5;
	}
	
	public void split() {
		mass /= 2;
		
	}
	
	/**
	 * Draws {@code PlayerBlob}.
	 * 
	 * @param camera the {@code Camera}
	 * @param g the {@code Graphics} object that is used to draw
	 * @param io the {@code ImageObserver} object that is used to draw
	 */
	public void draw(Camera camera, Graphics g, ImageObserver io) {
		if (potato)
			g.drawImage(Images.POTATO, (int) (camera.getX() - x + 1200 - mass), (int) (camera.getY() - y + 700 - mass), (int) mass * 2, (int) mass * 2, io);
		else {
			g.setColor(color);
			g.fillOval((int) (camera.getX() - x + 1200 - mass), (int) (camera.getY() - y + 700 - mass), (int) mass * 2, (int) mass * 2);
		}
	}
	
	/**
	 * Draws the mass of {@code PlayerBlob} at the center of the blob.
	 * 
	 * @param camera the {@code Camera}
	 * @param g the {@code Graphics} object that is used to draw
	 */
	public void drawMass(Camera camera, Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("" + (int) mass, (int) (camera.getX() - x) + 1200 - (("" + (int) mass).length() * 5) + 5, (int) (camera.getY() - y) + 700);
	}
}
