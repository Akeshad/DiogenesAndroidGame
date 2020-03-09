package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.diogenesandroid.JuegoDiogenesVersionFail;

import tools.CollisionRect;

public class Cookies {

    public static final int SPEED = 250;//the speed of the bullet movement

    public static final int WIDTH = 16;//the width of the bullet
    public static final int HEIGHT = 32;//The height of the bullet

    private static Texture texture;//The texture of the bullet
    CollisionRect rect;//A collisionRect that detects is there is a collision with another entity
    float x, y;//the position of the bullet

    public boolean remove = false;//Is going to check if the object should be remove from the list


    /**
     *This function is the Cookies´s constructor
     * @param x
     */
    public Cookies(float x) {
        this.x = x;
        this.y = JuegoDiogenesVersionFail.HEIGHT;
        this.rect = new CollisionRect(x, y, WIDTH, HEIGHT);

        if (texture == null)
            texture = new Texture("cookie.png");
    }

    /**
     *This function updates the position of the Cookies
     * @param deltaTime
     */
    public void update (float deltaTime) {

            y -= SPEED * deltaTime;

        if (y < -HEIGHT)//We want to eliminate the cookie when they leave the screen, no when they touch the bottom
            remove = true;

        rect.move(x, y);

    }


    /**
     *This function renders the image if the Cookies
     * @param batch
     */
    public void render (SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    /**
     *This function returns the CollisionRect of the Cookies
     * @return
     */
    public CollisionRect getCollisionRect () {
        return rect;
    }


    /**
     *This function returns the x position of the Cookies
     * @return
     */
    public float getX () {
        return x;
    }


    /**
     *This function returns the y position of the Cookies
     * @return
     */
    public float getY () {
        return y;
    }

}
