package agario;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import code.AnimationPanel;

public class AgarioGame extends AnimationPanel {

	ArrayList<Blob> blobs;
	ArrayList<PlayerBlob> playerBlobs;

	public AgarioGame() {
		super("agar.io", 1000, 700);
		playerBlobs = new ArrayList<>();
		blobs = new ArrayList<>();
		for (int i = 0; i < 100; i++)
			blobs.add(new Blob());
		playerBlobs.add(new PlayerBlob());
	}

	protected void renderFrame(Graphics g) {

		ArrayList<Blob> blobsEaten = new ArrayList<>();
		
		for (Blob blob : blobs) {
			for (PlayerBlob player : playerBlobs) {
				if (player.overlaps(blob.getX(), blob.getY(), blob.getMass())) {
					player.increaseMass(blob.getMass() / 10);
					blobsEaten.add(blob);
				}
			}
			blob.move();
			blob.draw(g);
		}
		
		for (int i = 0; i < blobsEaten.size(); i++)
			if (blobs.indexOf(blobsEaten.get(i)) != -1)
				blobs.remove(blobs.indexOf(blobsEaten.get(i)));
		
		for (PlayerBlob player : playerBlobs) {
			player.follow(mouseX, mouseY);
			player.move();
			player.draw(g);
			player.drawMass(g);
		}

		if (blobs.size() <= 100 && blobs.size() / 2 != 0 && frameNumber % (blobs.size() / 2) == 0) {
			blobs.add(new Blob());
		}

	}

	public void keyPressed(KeyEvent k) {
		int v = k.getKeyCode();
		switch (v) {
		case KeyEvent.VK_W:
			for (PlayerBlob player : playerBlobs) {
				if (player.getMass() > 20) {
					player.feed();
					Blob blob = new Blob(player.getX(), player.getY(), 15, player.getColor());
					blob.feed(mouseX, mouseY, player);
					blobs.add(blob);
				}
			}

			break;
		case KeyEvent.VK_SPACE:
			ArrayList<PlayerBlob> blobsSplit = new ArrayList<>();
			for (PlayerBlob player : playerBlobs)
				if (player.getMass() > 30)
					blobsSplit.add(player);
				
			for (int i = 0; i < blobsSplit.size(); i++) {
				PlayerBlob player = blobsSplit.get(i);
				player.split();
				playerBlobs.add(new PlayerBlob(player.getX() - player.getMass(), player.getY() - player.getMass(), player.getMass(), player.getColor()));
			}
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		}
	}
}