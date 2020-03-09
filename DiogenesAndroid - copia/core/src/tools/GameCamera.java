package tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameCamera {

    private OrthographicCamera cam;// The orthographic camera is to be used in 2D environments only as it implements a parallel (orthographic) projection and there will be no scale factor for the final image regardless where the objects are placed in the world.
    private StretchViewport viewport;//Creates a new viewport using a new OrthographicCamera


    /**
     *The constructor of the GameCamera
     * @param width
     * @param height
     */
    public GameCamera (int width, int height) {
        cam = new OrthographicCamera();
        viewport = new StretchViewport(width, height, cam);
        viewport.apply();
        cam.position.set(width / 2, height / 2, 0);
        cam.update();
    }

    /**
     * Encapsulates a column major 4 by 4 matrix.
     * @return cam.combined
     */
    public Matrix4 combined() {
        return cam.combined;
    }


    /**
     *Updates the GameCamera
     * @param width
     * @param height
     */
    public void update (int width, int height) {

        viewport.update(width, height);
    }


    /**
     *The values that the camera gives us are physics coordinates and we can convert them with the method unprotected
     * @return Vector2(unprojected.x, unprojected.y)
     */
    public Vector2 getInputInGameWorld () {
        Vector3 inputScreen = new Vector3(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 0);
        Vector3 unprojected = cam.unproject(inputScreen);
        return new Vector2(unprojected.x, unprojected.y);
    }

}
