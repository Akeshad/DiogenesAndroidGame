package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.diogenesandroid.JuegoDiogenesVersionFail;

public class GameScreen implements Screen {


    public static final float SPEED = 300;

    public static final float SHIP_ANIMATION_SPEED = 0.5f;

    public static final int SHIP_WIDTH_PIXEL = 17;
    public static final int SHIP_HEIGHT_PIXEL = 32;

    public static final int SHIP_WIDTH = SHIP_WIDTH_PIXEL * 3;
    public static final int SHIP_HEIGHT = SHIP_HEIGHT_PIXEL * 3;


    Animation[] rolls;

    private float y;
    private float x;
    float stateTime; // How long an animation is being running
    int roll;


    JuegoDiogenesVersionFail game;

    public GameScreen(JuegoDiogenesVersionFail game){
        this.game = game;
        y = 15;
        x = JuegoDiogenesVersionFail.WIDTH / 2 - SHIP_WIDTH / 2;
        roll = 2;
        rolls = new Animation[5];
        TextureRegion[][] rollSpriteSheet = TextureRegion.split(new Texture("ship.png"), SHIP_WIDTH_PIXEL, SHIP_HEIGHT_PIXEL);
        rolls[roll] = new Animation(SHIP_ANIMATION_SPEED, rollSpriteSheet[0]);


    }


    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            x -=  SPEED * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            x +=  SPEED * Gdx.graphics.getDeltaTime();
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
