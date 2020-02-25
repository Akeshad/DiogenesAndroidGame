package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.diogenesandroid.JuegoDiogenesVersionFail;

public class GameScreen implements Screen {


    public static final float SPEED = 300;

    public static final float SHIP_ANIMATION_SPEED = 0.5f;
    public static final float ROLL_TIMER_SWITCH_TIME = 0.25f;//How much time it takes between each roll of the ship

    public static final int SHIP_WIDTH_PIXEL = 17;
    public static final int SHIP_HEIGHT_PIXEL = 32;

    public static final int SHIP_WIDTH = SHIP_WIDTH_PIXEL * 3;
    public static final int SHIP_HEIGHT = SHIP_HEIGHT_PIXEL * 3;


    Animation[] rolls;

    private float y;
    private float x;
    float stateTime; // How long an animation is being running
    float rollTimer; //Actual time that is taking to roll
    int roll;


    JuegoDiogenesVersionFail game;

    public GameScreen(JuegoDiogenesVersionFail game){
        this.game = game;
        y = 15;
        x = JuegoDiogenesVersionFail.WIDTH / 2 - SHIP_WIDTH / 2;
        roll = 2;
        rollTimer = 0;
        rolls = new Animation[5];
        TextureRegion[][] rollSpriteSheet = TextureRegion.split(new Texture("ship.png"), SHIP_WIDTH_PIXEL, SHIP_HEIGHT_PIXEL);

        rolls[0] = new Animation(SHIP_ANIMATION_SPEED, rollSpriteSheet[2]);//All left
        rolls[1] = new Animation(SHIP_ANIMATION_SPEED, rollSpriteSheet[1]);
        rolls[2] = new Animation(SHIP_ANIMATION_SPEED, rollSpriteSheet[0]);//No tilt
        rolls[3] = new Animation(SHIP_ANIMATION_SPEED, rollSpriteSheet[3]);
        rolls[4] = new Animation(SHIP_ANIMATION_SPEED, rollSpriteSheet[4]);//Right


    }


    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            x -=  SPEED * Gdx.graphics.getDeltaTime();
            if (x < 0)
                x = 0;

            //update the roll

            rollTimer -= Gdx.graphics.getDeltaTime();

            if (Math.abs(rollTimer) > ROLL_TIMER_SWITCH_TIME) {
                rollTimer -= 0;
                roll--;

                if(roll < 0)
                    roll = 0;
            }

        }else{
            if(roll < 2){
                //update the roll
                rollTimer += Gdx.graphics.getDeltaTime();

                if (Math.abs(rollTimer) > ROLL_TIMER_SWITCH_TIME) {
                    rollTimer -= 0;
                    roll++;

                    if(roll > 4)
                        roll = 4;
                }
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            x +=  SPEED * Gdx.graphics.getDeltaTime();
            if (x + SHIP_WIDTH > JuegoDiogenesVersionFail.WIDTH)
                x = JuegoDiogenesVersionFail.WIDTH - SHIP_WIDTH;

            //update the roll
            rollTimer += Gdx.graphics.getDeltaTime();

            if (Math.abs(rollTimer) > ROLL_TIMER_SWITCH_TIME) {
                rollTimer -= 0;
                roll++;

                if(roll > 4)
                    roll = 4;
            }

        }else{
            if(roll >2){

                //update the roll

                rollTimer -= Gdx.graphics.getDeltaTime();

                if (Math.abs(rollTimer) > ROLL_TIMER_SWITCH_TIME) {
                    rollTimer -= 0;
                    roll--;

                    if(roll < 0)
                        roll = 0;
                }
            }
        }

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stateTime += delta;

        game.batch.begin();
        game.batch.draw((TextureRegion) rolls[roll].getKeyFrame(stateTime, true), x, y, SHIP_WIDTH, SHIP_HEIGHT);

        game.batch.end();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
