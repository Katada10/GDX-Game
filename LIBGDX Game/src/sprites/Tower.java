package sprites;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class Tower extends Sprite {
	/*
	 * Subclass of sprite which defines values that all towers use, and allows differentiation between
	 * the original tower and other towers through "type".
	 */
	
	public boolean shouldShoot = false, didDrawBullet = false;
	public int maxAmmo = 4;
	public List<Sprite> bullets;
	public Sprite currentBullet;
	public Label ammoLabel;
	
	public Tower(int gridX, int gridY, int type) {
		super("tower.png", gridX, gridY);
		bullets = new ArrayList<>();
		this.type = type;
	}
	
	/**
	 * Adds a bullet to the list of bullets drawn(fired).
	 * @param x
	 * @param y
	 * @return
	 */
	public Sprite createBullet(int x, int y)
	{
		Sprite bullet = new Sprite("bullet.png", x, y);
		bullets.add(bullet);
		currentBullet = bullet;
		return bullet;
	}
}
