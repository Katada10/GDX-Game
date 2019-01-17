package render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationManager {
	private float time;
	Texture img;
	TextureRegion[] tex;
	Animation anim;

	
	public AnimationManager()
	{
		img = new Texture(Gdx.files.internal("assets/sprites.png"));
		
		TextureRegion[][] tmpFrames = TextureRegion.split(img, 256 / 2, 256 / 4);
		tex = new TextureRegion[4];
		int index = 0;
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				tex[index++] = tmpFrames[j][i];
			}
		}
		
		anim = new Animation(1f/4f,tex);
	}
	
	
}
