package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.diogenesandroid.JuegoDiogenesVersionFail;

import java.util.ArrayList;
import java.util.Random;

import entities.Asteroid;
import entities.Bullet;
import entities.Explosions;
import tools.CollisionRect;

public class GameScreen implements Screen {


    public static final float SPEED = 300;

    public static final float SHIP_ANIMATION_SPEED = 0.5f;

    public static final float ROLL_TIMER_SWITCH_TIME = 0.25f;//How much time it takes between each roll of the ship
    public static final float SHOOT_WAIT_TIME = 0.3f;

    public static final int SHIP_WIDTH_PIXEL = 17;
    public static final int SHIP_HEIGHT_PIXEL = 32;

    public static final int SHIP_WIDTH = SHIP_WIDTH_PIXEL * 3;
    public static final int SHIP_HEIGHT = SHIP_HEIGHT_PIXEL * 3;

    public static final float MIN_ASTEROID_SPAWN_TIME = 0.03f;//This is going to be the minimum time that the game have to wait to spawn an asteroid
    public static final float MAX_ASTEROID_SPAWN_TIME = 0.6f;//This is going to be the maximum time that the game have to wait to spawn an asteroid


    Animation[] rolls;

    private float y;
    private float x;
    float stateTime; // How long an animation is being running
    float rollTimer; //Actual time that is taking to roll
    int roll;
    float shootTimer;
    float asteroidSpawnTimer; // timer for the asteroid
    BitmapFont scoreFont;


    int score;
    float health = 1;//0 = dead, 1 = full health
    Texture blank;

    CollisionRect playerRect;
    Random random;

    ArrayList<Bullet> bullets;
    ArrayList<Asteroid> asteroids;
    ArrayList<Explosions> explosions;
    private Music music;

    JuegoDiogenesVersionFail game;

    public GameScreen(JuegoDiogenesVersionFail game){
        this.game = game;
        y = 15;
        x = JuegoDiogenesVersionFail.WIDTH / 2 - SHIP_WIDTH / 2;
        scoreFont = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
        score = 0;
        blank = new Texture("blank.png");

        asteroids = new ArrayList<Asteroid>();
        bullets = new ArrayList<Bullet>();
        explosions = new ArrayList<Explosions>();

        playerRect = new CollisionRect(0, 0, SHIP_WIDTH, SHIP_HEIGHT);

        shootTimer = 0;

        random = new Random();
        asteroidSpawnTimer = random.nextFloat() * (MAX_ASTEROID_SPAWN_TIME - MIN_ASTEROID_SPAWN_TIME) + MIN_ASTEROID_SPAWN_TIME;//This is going to generate a num between 0 and 0.3 and then we add 0.3 to it

        roll = 2;
        rollTimer = 0;
        rolls = new Animation[5];
        TextureRegion[][] rollSpriteSheet = TextureRegion.split(new Texture("ship.png"), SHIP_WIDTH_PIXEL, SHIP_HEIGHT_PIXEL);

        rolls[0] = new Animation(SHIP_ANIMATION_SPEED, rollSpriteSheet[2]);//All left
        rolls[1] = new Animation(SHIP_ANIMATION_SPEED, rollSpriteSheet[1]);
        rolls[2] = new Animation(SHIP_ANIMATION_SPEED, rollSpriteSheet[0]);//No tilt
        rolls[3] = new Animation(SHIP_ANIMATION_SPEED, rollSpriteSheet[3]);
        rolls[4] = new Animation(SHIP_ANIMATION_SPEED, rollSpriteSheet[4]);//Right



        music = JuegoDiogenesVersionFail.manager.get("music.mp3", Music.class);
        music.setLooping(true);
        music.play();


    }


    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {

        shootTimer += delta;

        //Shooting code
        if((isRight() || isLeft())  && shootTimer >= SHOOT_WAIT_TIME){
            shootTimer = 0;

            int offSet = 4;
            if (roll == 1 || roll == 3)//Slightly tilted
                offSet = 8;

            if (roll == 0 || roll == 4)//Fully tilted
                offSet = 16;

            bullets.add(new Bullet(x + offSet));
            bullets.add(new Bullet(x + SHIP_WIDTH - offSet));
        }


        //Adding asteroids spawns
        asteroidSpawnTimer -= delta;
        if (asteroidSpawnTimer <= 0) { //if this hanpends we need to reset it and create new asteroids

            asteroidSpawnTimer = random.nextFloat() * (MAX_ASTEROID_SPAWN_TIME - MIN_ASTEROID_SPAWN_TIME) + MIN_ASTEROID_SPAWN_TIME;

            asteroids.add(new Asteroid(random.nextInt(JuegoDiogenesVersionFail.WIDTH - Asteroid.WIDTH))); //the asteroid is going to spawn from a random number around the width os the screen
        }

        //Updating asteroids
        ArrayList<Asteroid> asteroidsToRemove = new ArrayList<Asteroid>();
        for (Asteroid asteroid : asteroids) {
            asteroid.update(delta);
            if (asteroid.remove)
                asteroidsToRemove.add(asteroid);
        }


        //Update bullets
        ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();
        for (Bullet bullet : bullets) {
            bullet.update(delta);
            if (bullet.remove)
                bulletsToRemove.add(bullet);
        }

        //Update explosions
        ArrayList<Explosions> explosionsToRemove = new ArrayList<Explosions>();
        for (Explosions explosion : explosions) {
            explosion.update(delta);
            if (explosion.remove)
                explosionsToRemove.add(explosion);
        }
        explosions.removeAll(explosionsToRemove);



        //Movement code
        if(isLeft()){
            x -=  SPEED * Gdx.graphics.getDeltaTime();
            if (x < 0)
                x = 0;


            //Update roll if button just clicked
            if(isJustLeft() && !isRight() && roll > 0){
                rollTimer = 0;
                roll--;

            }

            //update the roll

            rollTimer -= Gdx.graphics.getDeltaTime();

            if (Math.abs(rollTimer) > ROLL_TIMER_SWITCH_TIME && roll > 0) {
                rollTimer -= ROLL_TIMER_SWITCH_TIME;
                roll--;

            }

        }else{
            if(roll < 2){
                //update the roll
                rollTimer += Gdx.graphics.getDeltaTime();

                if (Math.abs(rollTimer) > ROLL_TIMER_SWITCH_TIME && roll < 4) {
                    rollTimer -= ROLL_TIMER_SWITCH_TIME;
                    roll++;

                }
            }
        }

        if( isRight() ){

            x +=  SPEED * Gdx.graphics.getDeltaTime();
            if (x + SHIP_WIDTH > JuegoDiogenesVersionFail.WIDTH)
                x = JuegoDiogenesVersionFail.WIDTH - SHIP_WIDTH;


            if(isJustRight() && !isLeft() && roll > 0){
                rollTimer = 0;
                roll--;

            }

            //update the roll
            rollTimer += Gdx.graphics.getDeltaTime();

            if (Math.abs(rollTimer) > ROLL_TIMER_SWITCH_TIME && roll < 4) {
                rollTimer -= ROLL_TIMER_SWITCH_TIME;
                roll++;

            }

        }else{
            if(roll >2){
                //update the roll
                rollTimer -= Gdx.graphics.getDeltaTime();

                if (Math.abs(rollTimer) > ROLL_TIMER_SWITCH_TIME && roll > 0) {
                    rollTimer -= ROLL_TIMER_SWITCH_TIME;
                    roll--;
                }
            }
        }


        //After player moves, update collision rect
        playerRect.move(x, y);

        //After all updates, check for collisions
        for (Bullet bullet : bullets) {

            for (Asteroid asteroid : asteroids) {

                if (bullet.getCollisionRect().collidesWith(asteroid.getCollisionRect())) {//Collision occured
                    bulletsToRemove.add(bullet);
                    asteroidsToRemove.add(asteroid);
                    explosions.add(new Explosions(asteroid.getX(), asteroid.getY()));
                    score += 10;
                }
            }
        }

        bullets.removeAll(bulletsToRemove);


        for (Asteroid asteroid : asteroids) {
            if (asteroid.getCollisionRect().collidesWith(playerRect)) {
                asteroidsToRemove.add(asteroid);
                health -= 0.1;
                //If health is depleted, go to game over screen
                if (health <= 0) {
                    this.dispose();
                    game.setScreen(new GameOverScreen(game, score));
                    return;
                }

            }
        }
        asteroids.removeAll(asteroidsToRemove);
        stateTime += delta;


        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.scrollingBackground.updateAndRender(delta, game.batch);

        GlyphLayout scoreLayout = new GlyphLayout(scoreFont, "" + score);
        scoreFont.draw(game.batch, scoreLayout, JuegoDiogenesVersionFail.WIDTH / 2 - scoreLayout.width / 2,
                JuegoDiogenesVersionFail.HEIGHT - scoreLayout.height - 10);

        for (Bullet bullet : bullets) {
            bullet.render(game.batch);
        }

        for (Asteroid asteroid : asteroids) {
            asteroid.render(game.batch);
        }

        for (Explosions explosion : explosions) {
            explosion.render(game.batch);
        }

        //Draw health
        if (health > 0.6f)
            game.batch.setColor(Color.GREEN);
        else if (health > 0.2f)
            game.batch.setColor(Color.ORANGE);
        else
            game.batch.setColor(Color.RED);

        game.batch.draw(blank, 0, 0, JuegoDiogenesVersionFail.WIDTH * health, 5);
        game.batch.setColor(Color.WHITE);

        game.batch.draw((TextureRegion) rolls[roll].getKeyFrame(stateTime, true), x, y, SHIP_WIDTH, SHIP_HEIGHT);

        game.batch.end();


    }


    private boolean isRight () {
        return Gdx.input.isKeyPressed(Input.Keys.RIGHT) || (Gdx.input.isTouched() && Gdx.input.getX() >= JuegoDiogenesVersionFail.WIDTH / 2);
    }

    private boolean isLeft () {
        return Gdx.input.isKeyPressed(Input.Keys.LEFT) || (Gdx.input.isTouched() &&  Gdx.input.getX() < JuegoDiogenesVersionFail.WIDTH / 2);
    }

    private boolean isJustRight () {
        return Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) || (Gdx.input.justTouched() && Gdx.input.getX() >= JuegoDiogenesVersionFail.WIDTH / 2);
    }

    private boolean isJustLeft () {
        return Gdx.input.isKeyJustPressed(Input.Keys.LEFT) || (Gdx.input.justTouched() &&  Gdx.input.getX()< JuegoDiogenesVersionFail.WIDTH / 2);
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
