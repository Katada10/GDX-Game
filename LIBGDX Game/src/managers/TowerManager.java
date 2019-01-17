package managers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import core.Input;
import map.GameMap;
import sprites.Sprite;
import sprites.Tower;
import structs.Grid;
import structs.Tile;
import ui.FontRender;

public class TowerManager extends IManager<Tower> {
	boolean towerSelected = false;
	Label moneyLabel;
	int money = 150, cost = 20;
	Tower toDrag = null;

	public TowerManager() {
		super();
		moneyLabel = FontRender.addFont("Money: " + money, 700, 750);
	}

	public void update() {
		Tile t = Grid.getTile(new Vector2(Input.mouseX, Input.mouseY));

		if (Input.dragging && !towerSelected) {
			if (t.xCoord == GameMap.sprites.get(1).getGridX() && t.yCoord == GameMap.sprites.get(1).getGridY() && money >= cost) {
				toDrag = (Tower) GameMap.addObject(new Tower(4, 0, Sprite.TOWER), false);
				list.add(toDrag);
				towerSelected = true;

				money-=cost;
			}
		}

		if (towerSelected && Input.dragging) {
			drag(toDrag);
		} else if (towerSelected && !Input.dragging) {
			toDrag.ammoLabel = FontRender.addFont("", toDrag.position.x + 10, toDrag.position.y + toDrag.scale + 10);
			Grid.tiles[toDrag.getGridY()][toDrag.getGridX()].isEmpty = false;
		}

		if (!Input.dragging) {
			towerSelected = false;
		}

		moneyLabel.setText("Money: " + money);
	}

	public void drag(Tower tower) {
		Tile newTile = Grid.getTile(new Vector2(Input.mouseX, Input.mouseY));
		Tile currentTile = Grid.tiles[(int) toDrag.getGridY()][(int) toDrag.getGridX()];

		if (newTile.xCoord == currentTile.xCoord && newTile.yCoord == currentTile.yCoord) {
			return;
		} else {
			if (newTile.isEmpty) {
				if (newTile.xCoord != currentTile.xCoord) {
					toDrag.setPos(newTile.xCoord, (int) toDrag.getGridY());
				}
				if (newTile.yCoord != currentTile.yCoord) {
					toDrag.setPos((int) toDrag.getGridX(), newTile.yCoord);
				}
			}
		}
	}
}
