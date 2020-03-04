package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.diogenesandroid.JuegoDiogenesVersionFail;

import tools.CollisionRect;

public class Cupcake {

    public static final int SPEED = 250;//

    public static final int WIDTH = 16;//
    public static final int HEIGHT = 32;//

    private static Texture texture;//
    CollisionRect rect;//
    float x, y;//

    public boolean remove = false;//


    /**
     *
     * @param x
     */
    public Cupcake(float x) {
        this.x = x;
        this.y = JuegoDiogenesVersionFail.HEIGHT;
        this.rect = new CollisionRect(x, y, WIDTH, HEIGHT);

        if (texture == null)
            texture = new Texture("cupcake.png");
    }

    /**
     *
     * @param deltaTime
     */
    public void update (float deltaTime) {

        y -= SPEED * deltaTime;

        if (y < -HEIGHT)//We want to eliminate the cookie when they leave the screen, no when they touch the bottom
            remove = true;

        rect.move(x, y);

    }


    /**
     *
     * @param batch
     */
    public void render (SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    /**
     *
     * @return
     */
    public CollisionRect getCollisionRect () {
        return rect;
    }


    /**
     *
     * @return
     */
    public float getX () {
        return x;
    }


    /**
     *
     * @return
     */
    public float getY () {
        return y;
    }

}
