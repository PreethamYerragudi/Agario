package agario;

import java.awt.Color;

public class PlayerBlob extends Blob {
	
	private int speed;
	private int xVel;
	private int yVel;
	
	public PlayerBlob() {
		x = (int)(Math.random()*600);
		y = (int)(Math.random()*600);
		mass = 40;
		color = new Color((int)(Math.random() * 200), (int)(Math.random() * 200), (int)(Math.random() * 200));
		speed = 6;
	}
	
	public void increaseMass(int increase) {
		this.mass += increase;
	}
	
	public boolean overlaps(int x1, int y1, int r1) {
		int distSq = (int)Math.sqrt(((x - x1) 
                * (x - x1)) 
                + ((y - y1) 
                * (y - y1))); 
		if (distSq + r1 < mass) 
        {
            return true;
        }
		return false;
		
	}
	public void follow(int mouseX, int mouseY) {
		double deltaX = mouseX - x;
		double deltaY = mouseY - y;
		double hypotenuse = Math.sqrt((deltaX*deltaX)+(deltaY*deltaY));
		
		xVel = (int)(deltaX * (speed/hypotenuse));
		yVel = (int)(deltaY * (speed/hypotenuse));
	}
	
	public void move() {
		x += xVel/(mass*.01);
		y += yVel/(mass*.01);
	}
}
