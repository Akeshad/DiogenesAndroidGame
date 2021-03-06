package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Explosions {

    public static final float FRAME_LENGTH = 0.2f;//after this time the animation is going to finidh
    public static final int OFFSET = 8;//we need to get the position of the cookie
    public static final int SIZE = 64;//the size of the Explosion
    public static final int IMAGE_SIZE = 32;//The size of the image of the explosions

    private static Animation anim = null;//the animation
    float x, y;//floats that represents the position of the explosions
    float statetime;//

    public boolean remove = false;//check if we need to remove this object on the gameScreen

    /**
     *This is the constructor of the explosions
     * @param x
     * @param y
     */
    public Explosions (float x, float y) {
        this.x = x - OFFSET;//The explosion is center where is the asteroid
        this.y = y - OFFSET;
        statetime = 0;

        if (anim == null)
            anim = new Animation(FRAME_LENGTH, TextureRegion.split(new Texture("explosion.png"), IMAGE_SIZE, IMAGE_SIZE)[0]);
    }


    /**
     *This function updates the explosion
     * @param deltatime
     */
    public void update (float deltatime) {
        statetime += deltatime;
        if (anim.isAnimationFinished(statetime))
            remove = true;
    }

    /**
     *This function renders the explosion
     * @param batch
     */
    public void render (SpriteBatch batch) {
        batch.draw((TextureRegion) anim.getKeyFrame(statetime), x, y, SIZE, SIZE);
    }
}
