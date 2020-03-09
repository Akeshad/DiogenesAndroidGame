package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.diogenesandroid.JuegoDiogenesVersionFail;

import tools.CollisionRect;

public class Cupcake {

    public static final int SPEED = 250;//Constant int that represents the speed of the Cupcake movement

    public static final int WIDTH = 16;//Constant int that represents the width of the Cupcake
    public static final int HEIGHT = 32;//Constant int that represents the height of the Cupcake

    private static Texture texture;//The texture of the Cupcake
    CollisionRect rect;//A collisionRect that detects is there is a collision with another entity
    float x, y;//the position of the Cupcake

    public boolean remove = false;//Is going to check if the object should be remove from the list


    /**
     *This function is the Cupcake´s constructor
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
     *This function updates the position of the Cupcake
     * @param deltaTime
     */
    public void update (float deltaTime) {

        y -= SPEED * deltaTime;

        if (y < -HEIGHT)//We want to eliminate the cookie when they leave the screen, no when they touch the bottom
            remove = true;

        rect.move(x, y);

    }


    /**
     *This function renders the image if the Cupcake
     * @param batch
     */
    public void render (SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    /**
     *This function returns the CollisionRect of the Cupcake
     * @return
     */
    public CollisionRect getCollisionRect () {
        return rect;
    }


    /**
     *This function returns the x position of the Cupcake
     * @return
     */
    public float getX () {
        return x;
    }


    /**
     *This function returns the y position of the Cupcake
     * @return
     */
    public float getY () {
        return y;
    }

}
