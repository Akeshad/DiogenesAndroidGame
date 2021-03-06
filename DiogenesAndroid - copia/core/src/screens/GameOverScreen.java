package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;
import com.mygdx.diogenesandroid.JuegoDiogenesVersionFail;

import database.Database;
import tools.ScrollingBackground;

public class GameOverScreen implements Screen {

    private static final int BANNER_WIDTH = 350;//
    private static final int BANNER_HEIGHT = 100;//

    JuegoDiogenesVersionFail game;//

    int score, highscore;//

    Texture gameOverBanner;//
    BitmapFont scoreFont;//

    private Database database;

    /**
     *
     * @param game
     * @param database
     */
    public GameOverScreen(JuegoDiogenesVersionFail game, int score, Database database) {
        this.game = game;
        this.score = score;
        this.database = database;

        //Get highscore from save file

        this.highscore = database.loadScore();

        //Check if score beats highscore
        if (score > highscore) {
            database.saveScore(score);

        }

        //Load textures and fonts
        gameOverBanner = new Texture("game_over.png");
        scoreFont = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));

        game.scrollingBackground.setSpeedFixed(true);
        game.scrollingBackground.setSpeed(ScrollingBackground.DEFAULT_SPEED);
    }

    @Override
    public void show() {
    }


    /**
     *
     * @param delta
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        game.scrollingBackground.updateAndRender(delta, game.batch);

        game.batch.draw(gameOverBanner, JuegoDiogenesVersionFail.WIDTH / 2 - BANNER_WIDTH / 2, JuegoDiogenesVersionFail.HEIGHT - BANNER_HEIGHT - 15, BANNER_WIDTH, BANNER_HEIGHT);

        GlyphLayout scoreLayout = new GlyphLayout(scoreFont, "Score: \n" + score, Color.PURPLE, 0, Align.left, false);
        GlyphLayout highscoreLayout = new GlyphLayout(scoreFont, "Highscore: \n" + highscore, Color.PURPLE, 0, Align.left, false);
        scoreFont.draw(game.batch, scoreLayout, JuegoDiogenesVersionFail.WIDTH / 2 - scoreLayout.width / 2, JuegoDiogenesVersionFail.HEIGHT - BANNER_HEIGHT - 15 * 2);
        scoreFont.draw(game.batch, highscoreLayout, JuegoDiogenesVersionFail.WIDTH / 2 - highscoreLayout.width / 2, JuegoDiogenesVersionFail.HEIGHT - BANNER_HEIGHT - scoreLayout.height - 15 * 3);

        float touchX = game.cam.getInputInGameWorld().x, touchY = JuegoDiogenesVersionFail.HEIGHT - game.cam.getInputInGameWorld().y;

        GlyphLayout tryAgainLayout = new GlyphLayout(scoreFont, "Try Again");
        GlyphLayout mainMenuLayout = new GlyphLayout(scoreFont, "Main Menu");

        float tryAgainX = JuegoDiogenesVersionFail.WIDTH / 2 - tryAgainLayout.width / 2;
        float tryAgainY = JuegoDiogenesVersionFail.HEIGHT / 2 - tryAgainLayout.height / 2;
        float mainMenuX = JuegoDiogenesVersionFail.WIDTH / 2 - mainMenuLayout.width / 2;
        float mainMenuY = JuegoDiogenesVersionFail.HEIGHT / 2 - mainMenuLayout.height / 2 - tryAgainLayout.height - 15;

        //Checks if hovering over try again button
        if (touchX >= tryAgainX && touchX < tryAgainX + tryAgainLayout.width && touchY >= tryAgainY - tryAgainLayout.height && touchY < tryAgainY)
            tryAgainLayout.setText(scoreFont, "Try Again", Color.YELLOW, 0, Align.left, false);

        //Checks if hovering over main menu button
        if (touchX >= mainMenuX && touchX < mainMenuX + mainMenuLayout.width && touchY >= mainMenuY - mainMenuLayout.height && touchY < mainMenuY)
            mainMenuLayout.setText(scoreFont, "Main Menu", Color.YELLOW, 0, Align.left, false);

        //If try again and main menu is being pressed
        if (Gdx.input.isTouched()) {
            //Try again
            if (touchX > tryAgainX && touchX < tryAgainX + tryAgainLayout.width && touchY > tryAgainY - tryAgainLayout.height && touchY < tryAgainY) {
                this.dispose();
                game.batch.end();
                game.setScreen(new GameScreen(game,database));
                return;
            }

            //main menu
            if (touchX > mainMenuX && touchX < mainMenuX + mainMenuLayout.width && touchY > mainMenuY - mainMenuLayout.height && touchY < mainMenuY) {
                this.dispose();
                game.batch.end();
                game.setScreen(new DesktopMenuScreen(game, database));
                return;
            }
        }

        //Draw buttons
        scoreFont.draw(game.batch, tryAgainLayout, tryAgainX, tryAgainY);
        scoreFont.draw(game.batch, mainMenuLayout, mainMenuX, mainMenuY);

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }
}

