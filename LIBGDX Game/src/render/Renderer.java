package render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import map.GameMap;
import sprites.Sprite;
import ui.UIRenderer;

public class Renderer{
	
	SpriteRenderer spriterender;
	UIRenderer uirender;
	GameMap gameMap;
	
	public void init()
	{
		spriterender = new SpriteRenderer();
		uirender = new UIRenderer();
		gameMap = new GameMap();
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
	}
	
	public void render()
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		uirender.draw();
		gameMap.update();
		for (Sprite sprite : GameMap.sprites) {
			spriterender.render(sprite);
		}
	}
	
	
	public void cleanUp()
	{
		spriterender.dispose();
		gameMap.dispose();
		uirender.dispose();
	}
}
