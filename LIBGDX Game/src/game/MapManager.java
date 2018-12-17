package game;

import structs.Sprite;

public class MapManager extends GameMap{
	private TowerManager towerManager;
	
	public MapManager(String backgroundName) {
		super(backgroundName);
		towerManager = new TowerManager(this);
	}
	
	public void update() {
		towerManager.update();
	}

	public void addObjects() {
		addObject("tower.png", 4, 0, false, "modelTower");
	}

}
