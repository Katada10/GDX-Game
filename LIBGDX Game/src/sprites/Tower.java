package sprites;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class Tower extends Sprite {
	
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
	
	public Sprite createBullet(int x, int y)
	{
		Sprite bullet = new Sprite("bullet.png", x, y);
		bullets.add(bullet);
		currentBullet = bullet;
		return bullet;
	}
}
