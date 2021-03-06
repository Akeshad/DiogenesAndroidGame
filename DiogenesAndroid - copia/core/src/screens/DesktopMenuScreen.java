package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.diogenesandroid.JuegoDiogenesVersionFail;

import database.Database;
import tools.ScrollingBackground;


public class DesktopMenuScreen implements Screen {

    private static final int EXIT_BUTTON_WIDTH = 250;//The width of the exit button
    private static final int EXIT_BUTTON_HEIGHT = 120;//The height of the exit button
    private static final int PLAY_BUTTON_WIDTH = 300;//The width of the play button
    private static final int PLAY_BUTTON_HEIGHT = 120;//The height of the play button
    private static final int EXIT_BUTTON_Y = 100;//The position of the exit button
    private static final int PLAY_BUTTON_Y = 230;//The position of the play button
    private static final int LOGO_WIDTH = 400;//The width of the logo button
    private static final int LOGO_HEIGHT = 250;//The height of the logo button
    private static final int LOGO_Y = 450;//The position of the exit button

    final JuegoDiogenesVersionFail game;//a JuegoDiogenesVersionFail that is going to be played

    Texture playButtonActive;//The texture os the play button when is activate
    Texture playButtonInactive;//The texture os the play button when is inactivate
    Texture exitButtonActive;//The texture os the exit button when is activate
    Texture exitButtonInactive;//The texture os the exit button when is inactivate
    Texture logo;//The texture os the logo

    private Database database;//The database of the game

    /**
     *The constructor of the DesktopMenuScreen
     * @param database
     * @param game
     */
    public DesktopMenuScreen(final JuegoDiogenesVersionFail game, final Database database) {
        this.game = game;
        playButtonActive = new Texture("play_button_active.png");
        playButtonInactive = new Texture("play_button_inactive.png");
        exitButtonActive = new Texture("exit_button_active.png");
        exitButtonInactive = new Texture("exit_button_inactive.png");
        logo = new Texture("logo2.png");

        this.database = database;
        game.scrollingBackground.setSpeedFixed(true);
        game.scrollingBackground.setSpeed(ScrollingBackground.DEFAULT_SPEED);

        final DesktopMenuScreen menuScreen = this;

        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                //Exit button
                int x = JuegoDiogenesVersionFail.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2;
                if (game.cam.getInputInGameWorld().x < x + EXIT_BUTTON_WIDTH && game.cam.getInputInGameWorld().x > x
                        && JuegoDiogenesVersionFail.HEIGHT - game.cam.getInputInGameWorld().y < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT
                        && JuegoDiogenesVersionFail.HEIGHT - game.cam.getInputInGameWorld().y > EXIT_BUTTON_Y) {
                    menuScreen.dispose();
                    Gdx.app.exit();
                }

                //Play game button
                x = JuegoDiogenesVersionFail.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2;
                if (game.cam.getInputInGameWorld().x < x + PLAY_BUTTON_WIDTH && game.cam.getInputInGameWorld().x > x
                        && JuegoDiogenesVersionFail.HEIGHT - game.cam.getInputInGameWorld().y < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT
                        && JuegoDiogenesVersionFail.HEIGHT - game.cam.getInputInGameWorld().y > PLAY_BUTTON_Y) {
                    menuScreen.dispose();
                    game.setScreen(new GameScreen(game, database));
                }

                return super.touchUp(screenX, screenY, pointer, button);
            }

        });
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
        Gdx.gl.glClearColor(103/255f, 173/255f, 191/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        game.scrollingBackground.updateAndRender(delta, game.batch);

        int x = JuegoDiogenesVersionFail.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2;
        if (game.cam.getInputInGameWorld().x < x + EXIT_BUTTON_WIDTH && game.cam.getInputInGameWorld().x > x
                && JuegoDiogenesVersionFail.HEIGHT - game.cam.getInputInGameWorld().y < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT
                && JuegoDiogenesVersionFail.HEIGHT - game.cam.getInputInGameWorld().y > EXIT_BUTTON_Y) {
            game.batch.draw(exitButtonActive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        } else {
            game.batch.draw(exitButtonInactive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        }

        x = JuegoDiogenesVersionFail.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2;
        if (game.cam.getInputInGameWorld().x < x + PLAY_BUTTON_WIDTH
                && game.cam.getInputInGameWorld().x > x && JuegoDiogenesVersionFail.HEIGHT - game.cam.getInputInGameWorld().y < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT
                && JuegoDiogenesVersionFail.HEIGHT - game.cam.getInputInGameWorld().y > PLAY_BUTTON_Y) {
            game.batch.draw(playButtonActive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        } else {
            game.batch.draw(playButtonInactive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        }

        game.batch.draw(logo, JuegoDiogenesVersionFail.WIDTH / 2 - LOGO_WIDTH / 2, LOGO_Y, LOGO_WIDTH, LOGO_HEIGHT);

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

    /**
     *
     */
    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
    }
}

