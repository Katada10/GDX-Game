package core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;

public class CameraManager {

	private Camera cam;
	
	public CameraManager() {
		cam = new PerspectiveCamera(60, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(0, 0, 0);
		
		cam.lookAt(0, 0, 0);
		cam.near = 0.1f;

		cam.far = 100f;
		cam.update();
	}
	
	
	public Camera getCamera()
	{
		return cam;
	}
	
}
