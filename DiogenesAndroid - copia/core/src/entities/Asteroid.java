package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.diogenesandroid.JuegoDiogenesVersionFail;

import tools.CollisionRect;

public class Asteroid {
    public static final int SPEED = 250;
    public static final int WIDTH = 16;
    public static final int HEIGHT = 32;
    private static Texture texture;
    CollisionRect rect;
    float x, y;

    public boolean remove = false;

    public Asteroid (float x) {
        this.x = x;
        this.y = JuegoDiogenesVersionFail.HEIGHT;
        this.rect = new CollisionRect(x, y, WIDTH, HEIGHT);

        if (texture == null)
            texture = new Texture("cookie.png");
    }

    public void update (float deltaTime) {

        y -= SPEED * deltaTime;

        if (y < -HEIGHT)//We want to eliminate the asteroid when they leave the screen, no when they touch the bottom
            remove = true;

        rect.move(x, y);

    }

    public void render (SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public CollisionRect getCollisionRect () {
        return rect;
    }

    public float getX () {
        return x;
    }

    public float getY () {
        return y;
    }

}
