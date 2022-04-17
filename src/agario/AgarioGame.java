package agario;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import code.AnimationPanel;

public class AgarioGame extends AnimationPanel {
	
	PlayerBlob player;
	ArrayList<Blob> blobs;
	
	public AgarioGame() {
		super("agar.io", 1000, 700);
		player = new PlayerBlob();
		blobs = new ArrayList<>();
		for (int i = 0; i < 100; i++)
			blobs.add(new Blob());
	}

	protected void renderFrame(Graphics g) {
		for (int i = 0; i < blobs.size(); i++) {
			Blob blob = blobs.get(i);
			blob.move();
			if (player.overlaps(blob.getX(), blob.getY(), blob.getMass())) {
				player.increaseMass(blob.getMass() / 10);
				blobs.remove(i);
				i--;
			}
			blob.draw(g);
		}
		if (blobs.size() <= 100 && blobs.size() / 2 != 0 && frameNumber % (blobs.size() / 2) == 0) {
			blobs.add(new Blob());
		}
		
		player.follow(mouseX, mouseY);
		player.move();
		player.draw(g);
		player.drawMass(g);
	}
	
	public void keyPressed(KeyEvent k) {
		int v = k.getKeyCode();
		switch (v) {
		case KeyEvent.VK_W:
			if (player.getMass() > 20) {
				player.feed();
				Blob blob = new Blob(player.getX(), player.getY(), 15, player.getColor());
				blob.feed(mouseX, mouseY, player);
				blobs.add(blob);
			}
			break;
		case KeyEvent.VK_SPACE:
			player.split();
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		}
	}
}