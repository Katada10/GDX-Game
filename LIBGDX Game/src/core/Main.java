package core;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * @author Katada
 *
 */
public class Main {
	public static final int SCALE = 800;
	
	public static void main(String[] args)
	{
		LwjglApplicationConfiguration cfg =  new LwjglApplicationConfiguration();
		cfg.title = "Dawn Of The Chickens V 0.0 Alpha";
		cfg.width = SCALE;
		cfg.height = SCALE;
		cfg.resizable = false;
		
		new LwjglApplication(new Game(), cfg);
	}
}
