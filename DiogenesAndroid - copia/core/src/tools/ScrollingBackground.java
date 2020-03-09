package tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.diogenesandroid.JuegoDiogenesVersionFail;

public class ScrollingBackground {

    public static final int DEFAULT_SPEED = 80;//the speed of the starts of the background
    public static final int ACCELERATION = 50;//the acceleration of the starts of the background
    public static final int GOAL_REACH_ACCELERATION = 200;//the acceleration goal of the starts of the background

    Texture image;//the image of the background
    float y1, y2;//
    int speed;//In pixels / second
    int goalSpeed;//int that represents the acceleration goal of the starts of the background
    float imageScale;//float that represents the scale of the image
    boolean speedFixed;//boolean that represent if the speed is fixed

    /**
     *Constructor of the ScrollingBackground
     */
    public ScrollingBackground () {
        image = new Texture("stars_background.png");
        y1 = 0;
        y2 = image.getHeight();
        speed = 0;
        goalSpeed = DEFAULT_SPEED;
        imageScale = JuegoDiogenesVersionFail.WIDTH / image.getWidth();
        speedFixed = true;
    }


    /**
     *This function update and render the stars of the background
     * @param deltaTime
     * @param batch
     */
    public void updateAndRender (float deltaTime, SpriteBatch batch) {
        //Speed adjustment to reach goal
        if (speed < goalSpeed) {
            speed += GOAL_REACH_ACCELERATION * deltaTime;
            if (speed > goalSpeed)
                speed = goalSpeed;
        } else if (speed > goalSpeed) {
            speed -= GOAL_REACH_ACCELERATION * deltaTime;
            if (speed < goalSpeed)
                speed = goalSpeed;
        }

        if (!speedFixed)
            speed += ACCELERATION * deltaTime;

        y1 -= speed * deltaTime;
        y2 -= speed * deltaTime;

        //if image reached the bottom of screen and is not visible, put it back on top
        if (y1 + image.getHeight() * imageScale <= 0)
            y1 = y2 + image.getHeight() * imageScale;

        if (y2 + image.getHeight() * imageScale <= 0)
            y2 = y1 + image.getHeight() * imageScale;

        //Render
        batch.draw(image, 0, y1, JuegoDiogenesVersionFail.WIDTH, image.getHeight() * imageScale);
        batch.draw(image, 0, y2, JuegoDiogenesVersionFail.WIDTH, image.getHeight() * imageScale);
    }


    /**
     *This function resize the image with the correct scale
     * @param width
     * @param height
     */
    public void resize(int width, int height){

        imageScale = width / image.getWidth();

    }

    /**
     *This function sets the goalSpeed
     * @param goalSpeed
     */
    public void setSpeed (int goalSpeed) {
        this.goalSpeed = goalSpeed;
    }


    /**
     *This function sets the speedFixed
     * @param speedFixed
     */
    public void setSpeedFixed (boolean speedFixed) {
        this.speedFixed = speedFixed;
    }
}
