package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.diogenesandroid.JuegoDiogenesVersionFail;

public class Bullet {
    public static final int SPEED = 500;
    public static final int DEFAULT_Y = 40;
    private static Texture texture;
    public boolean remove = false;//Is going to check if the object should be remove from the list

    float x, y;

    public Bullet(float x) {
        this.x = x;
        this.y = y;
        this.y = DEFAULT_Y;
        if (texture == null)
            texture = new Texture("bullet.png");
    }

    public void update (float deltaTime) {

        y += SPEED * deltaTime;

        if (y > JuegoDiogenesVersionFail.HEIGHT)
            remove = true;

    }

    public void render (SpriteBatch batch) {
        batch.draw(texture, x, y);
    }
}
