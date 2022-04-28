package agario;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import code.AnimationPanel;

/**
 * {@code AgarioGame} class.
 * 
 * @author Jianing Wang
 * @author Preetham Yerragudi
 */
public class AgarioGame extends AnimationPanel {
	private InputStream input;
	private OutputStream out;
	private Socket client;
	private Socket server = new Socket();
	private ArrayList<Blob> blobs;
	private ArrayList<PlayerBlob> playerBlobs;
	private Camera camera;
	private Minimap minimap;
	private Images images;

	public AgarioGame() {
		super("agar.io", 1200, 700);
		images = new Images();
		images.load();
		playerBlobs = new ArrayList<>();
		blobs = new ArrayList<>();
		camera = new Camera();
		minimap = new Minimap();
		for (int i = 0; i < 600; i++)
			blobs.add(new Blob());
		playerBlobs.add(new PlayerBlob());
	}

	protected void renderFrame(Graphics g) {
		
		camera.move(playerBlobs);
		
		ArrayList<Blob> blobsEaten = new ArrayList<>();
		
		for (Blob blob : blobs) {
			for (PlayerBlob player : playerBlobs) {
				if (player.overlaps(blob.getX(), blob.getY(), blob.getMass())) {
					player.increaseMass(blob.getMass() / 20);
					blobsEaten.add(blob);
				}
			}
			blob.move();
			blob.draw(camera, g);
		}
		
		for (int i = 0; i < blobsEaten.size(); i++)
			if (blobs.indexOf(blobsEaten.get(i)) != -1)
				blobs.remove(blobs.indexOf(blobsEaten.get(i)));
		
		for (PlayerBlob player : playerBlobs) {
			player.follow(mouseX + (int) camera.getX(), mouseY + (int) camera.getY());
			player.move();
			player.draw(camera, g, this);
			player.drawMass(camera, g);
		}

		if (blobs.size() <= 600 && blobs.size() / 2 != 0 && frameNumber % (blobs.size() / 2) == 0) {
			blobs.add(new Blob());
		}
		
		minimap.drawMap(g);
		minimap.draw(playerBlobs, g);

	}

	public void keyPressed(KeyEvent k) {
		int v = k.getKeyCode();
		switch (v) {
		case KeyEvent.VK_W:
			for (PlayerBlob player : playerBlobs) {
				if (player.getMass() > 20) {
					player.feed();
					Blob blob = new Blob(player.getX(), player.getY(), 15, player.getColor());
					blob.feed(mouseX + (int) camera.getX(), mouseY + (int) camera.getY(), player);
					blobs.add(blob);
				}
			}

			break;
		case KeyEvent.VK_SPACE:
			ArrayList<PlayerBlob> blobsSplit = new ArrayList<>();
			for (PlayerBlob player : playerBlobs)
				if (player.getMass() > 15)
					blobsSplit.add(player);
				
			for (int i = 0; i < blobsSplit.size(); i++) {
				PlayerBlob player = blobsSplit.get(i);
				player.split();
				playerBlobs.add(new PlayerBlob(player.getX() - player.getMass(), player.getY() - player.getMass(), player.getMass(), player.getColor(), player.getPotato()));
			}
			break;
		case KeyEvent.VK_P:
			for (PlayerBlob player : playerBlobs)
				player.switchPotato();
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		}
	}
}