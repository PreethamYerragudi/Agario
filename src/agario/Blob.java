package agario;

import java.awt.Color;
import java.awt.Graphics;

public class Blob {
	
	protected int x;
	protected int y;
	protected double xVel;
	protected double yVel;
	protected int speed;
	protected double mass;
	protected Color color;
	
	public int getX() { return x; }
	public int getY() { return y; }
	public double getMass() { return mass; }
	
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }

	public Blob() {
		x = (int)(Math.random() * 1000);
		y = (int)(Math.random() * 700);
		mass = 8;
		color = new Color((int)(Math.random() * 200), (int)(Math.random() * 200), (int)(Math.random() * 200));
	}
	
	public Blob(int x, int y, int mass, Color color) {
		this.x = x;
		this.y = y;
		this.mass = mass;
		this.color = color;
		this.speed = 16;
		
	}
	
	public void feed(int mouseX, int mouseY) {
		double deltaX = mouseX - x;
		double deltaY = mouseY - y;
		double hypotenuse = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
		
		xVel = (int)(deltaX * (speed / hypotenuse));
		yVel = (int)(deltaY * (speed / hypotenuse));
	}
	
	public void move() {
		x += xVel;
		y += yVel;
		xVel *= 0.9;
		yVel *= 0.9;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x - (int) mass, y - (int) mass, (int) mass * 2, (int) mass * 2);
	}
}
