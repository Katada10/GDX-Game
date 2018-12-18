package game;

import com.badlogic.gdx.math.Vector2;

import core.Input;
import structs.Grid;
import structs.Sprite;
import structs.Tile;

public class TowerManager {

	Sprite currentTower = null;
	boolean towerSelected = false;

	public void update() {
		Tile t = Grid.getTile(new Vector2(Input.mouseX, Input.mouseY));

		if (!t.isEmpty && Input.dragging && !towerSelected) {
			currentTower = MapManager.sprites.get(t.objectIndex);
		
			if(currentTower.spriteTag == "modelTower") {
				towerSelected = true;
			}
		}

		if (towerSelected && Input.dragging) {
			drag(currentTower);
		}

		if (!Input.dragging) {
			towerSelected = false;
		}
	}

	public void drag(Sprite tower) {
		Tile newTile = Grid.getTile(new Vector2(Input.mouseX, Input.mouseY));
		Tile currentTile = Grid.tiles[(int) tower.getGridPosition().y][(int) tower.getGridPosition().x];

		if (newTile.isEmpty) {
			if (newTile.xCoord != currentTile.xCoord) {
				tower.setPos(newTile.xCoord, (int) tower.getGridPosition().y);
				currentTile.isEmpty = true;
				newTile.objectIndex = MapManager.sprites.indexOf(tower);
			}
			if (newTile.yCoord != currentTile.yCoord) {
				tower.setPos((int) tower.getGridPosition().x, newTile.yCoord);
				currentTile.isEmpty = true;
				newTile.objectIndex = MapManager.sprites.indexOf(tower);
				if (tower.spriteTag == "modelTower") {
					tower.spriteTag = "default";
					MapManager.addObject("tower.png", 4, 0, false, "modelTower");
				}
			}
		}
	}

}
