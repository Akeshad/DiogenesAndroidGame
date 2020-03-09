package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.diogenesandroid.JuegoDiogenesVersionFail;

import tools.CollisionRect;

public class Bullet {

    public static final int WIDTH = 3;//the width of the bullet
    public static final int HEIGHT = 12;//The height of the bullet
    public static final int SPEED = 500;//the speed of the bullet movement
    public static final int DEFAULT_Y = 40;//
    private static Texture texture;//The texture of the bullet
    CollisionRect rect;//A collisionRect that detects is there is a collision with another entity
    public boolean remove = false;//Is going to check if the object should be remove from the list

    float x, y;//the position of the bullet


    /**
     * This function is the Bullet´s constructor
     * @param x
     */
    public Bullet(float x) {
        this.x = x;
        this.y = y;
        this.y = DEFAULT_Y;
        this.rect = new CollisionRect(x, y, WIDTH, HEIGHT);
        if (texture == null)
            texture = new Texture("bullet.png");
    }

    /**
     *this update the movement of the bullet
     * @param deltaTime
     */
    public void update (float deltaTime) {

        y += SPEED * deltaTime;

        if (y > JuegoDiogenesVersionFail.HEIGHT)
            remove = true;

        rect.move(x, y);

    }

    /**
     *This function renders the texture of the bullet
     * @param batch
     */
    public void render (SpriteBatch batch) {

        batch.draw(texture, x, y);
    }

    /**
     *This function returns the CollisionRect of the bullet
     * @return a CollisionRect
     */
    public CollisionRect getCollisionRect () {
        return rect;
    }
}
