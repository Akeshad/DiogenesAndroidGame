package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Explosions {

    public static final float FRAME_LENGTH = 0.2f;//after this time the animation is going to finidh
    public static final int OFFSET = 8;//we need to get the position of the cookie
    public static final int SIZE = 64;//
    public static final int IMAGE_SIZE = 32;//

    private static Animation anim = null;//the animation
    float x, y;//
    float statetime;//

    public boolean remove = false;//check if we need to remove this object on the gameScreen

    /**
     *
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
     *
     * @param deltatime
     */
    public void update (float deltatime) {
        statetime += deltatime;
        if (anim.isAnimationFinished(statetime))
            remove = true;
    }

    /**
     *
     * @param batch
     */
    public void render (SpriteBatch batch) {
        batch.draw((TextureRegion) anim.getKeyFrame(statetime), x, y, SIZE, SIZE);
    }
}
