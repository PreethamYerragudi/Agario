package agario;

import java.awt.Color;
import java.awt.Graphics;

public class Blob {
	
	protected double x;
	protected double y;
	protected double xVel;
	protected double yVel;
	protected int speed;
	protected double mass;
	protected Color color;
	
	public double getX() { return x; }
	public double getY() { return y; }
	public double getMass() { return mass; }
	
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }

	public Blob() {
		x = (int)(Math.random() * 3000);
		y = (int)(Math.random() * 3000);
		mass = 8;
		color = new Color((int)(Math.random() * 200), (int)(Math.random() * 200), (int)(Math.random() * 200));
	}
	
	public Blob(double x, double y, double mass, Color color) {
		this.x = x;
		this.y = y;
		this.mass = mass;
		this.color = color;
		this.speed = 14;
	}
	
	/**
	 * Sets the velocities of {@code Blob} to move towards the specified mouse
	 * coordinates and sets the position to the edge of {@code PlayerBlob}.
	 * 
	 * @param mouseX the x position of the mouse
	 * @param mouseY the y position of the mouse
	 * @param player the {@code PlayerBlob} that {@code Blob} is fed from
	 */
	public void feed(int mouseX, int mouseY, PlayerBlob player) {
		double deltaX = mouseX - x;
		double deltaY = mouseY - y;
		double hypotenuse = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
		
		xVel = (int)(deltaX * (speed / hypotenuse)) + player.getXVel();
		yVel = (int)(deltaY * (speed / hypotenuse)) + player.getYVel();
		
		double angle = Math.atan2(player.getY() - mouseY, player.getX() - mouseX) - Math.PI;
		x = (player.getMass() * Math.cos(angle)) + player.getX();
		y = (player.getMass() * Math.sin(angle)) + player.getY();
	}
	
	/**
	 * Updates the position of {@code Blob} and slows down the velocities.
	 */
	public void move() {
		x += xVel;
		y += yVel;
		xVel *= 0.9;
		yVel *= 0.9;
	}
	
	/**
	 * Draws {@code Blob}.
	 * 
	 * @param camera the {@code Camera}
	 * @param g the {@code Graphics} object that is used to draw
	 */
	public void draw(Camera camera, Graphics g) {
		g.setColor(color);
		g.fillOval((int) (x - mass - camera.getX()), (int) (y - mass - camera.getY()), (int) mass * 2, (int) mass * 2);
	}
}
