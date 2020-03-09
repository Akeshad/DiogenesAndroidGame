package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.diogenesandroid.JuegoDiogenes;
import com.mygdx.diogenesandroid.Settings;

import controller.PlayerController;
import model.Actor;
import model.MyCamera;
import model.TERRAIN;
import model.TileMap;
import util.AnimationSet;

public class GameScreen extends AbstractScreen {

    private Texture playerFemaleSouth;
    private Texture grass1;
    private Texture grass2;
    private SpriteBatch batch;
    private MyCamera camera;
    private Actor player;
    private PlayerController controller;
    private TileMap map;

    public GameScreen(JuegoDiogenes app) {
        super(app);
        grass1 = new Texture("suelos/Grass1.png");
        grass2 = new Texture("suelos/Grass2.png");
        playerFemaleSouth = new Texture("sprites/playerFemale.png");
        batch = new SpriteBatch();

        TextureAtlas atlas = app.getAssetManager().get("graphics_packed/tiles/tilepack.atlas", TextureAtlas.class);
        Animation walksNorth;

        Animation walksSouth;
        Animation walksEast;
        Animation walksWest;
        AnimationSet animations = new AnimationSet(
                walksNorth = new Animation(0.3f / 2f, atlas.findRegion("brendan_walk_north")),
                walksSouth = new Animation(0.3f / 2f, atlas.findRegion("brendan_walk_south")),
                walksEast = new Animation(0.3f / 2f, atlas.findRegion("brendan_walk_east")),
                walksWest = new Animation(0.3f / 2f, atlas.findRegion("brendan_walk_west")),
                atlas.findRegion("brendan_stand_north"),
                atlas.findRegion("brendan_stand_south"),
                atlas.findRegion("brendan_stand_east"),
                atlas.findRegion("brendan_stand_west")
        );

        walksNorth.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        walksSouth.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        walksEast.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        walksWest.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        map = new TileMap(10, 10);
        player = new Actor(map, 0, 0, animations);
        controller = new PlayerController(player);

        camera = new MyCamera();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void render(float delta) {

        controller.update(delta);
        player.update(delta);
        camera.update(player.getWorldX() + 0.5f, player.getWorldY() + 0.5f);
        batch.begin();

        float wordlStartX = Gdx.graphics.getWidth() / 2 - camera.getCameraX() * Settings.SCALED_TILE_SIZE;
        float wordlStartY = Gdx.graphics.getHeight() / 2 - camera.getCameraY() * Settings.SCALED_TILE_SIZE;


        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {

                Texture render;

                if (map.getTile(x, y).getTerrain() == TERRAIN.GRASS_1) {
                    render = grass1;
                } else {
                    render = grass2;
                }
                batch.draw(render,
                        wordlStartX + x * Settings.SCALED_TILE_SIZE,
                        wordlStartY + y * Settings.SCALED_TILE_SIZE,
                        Settings.SCALED_TILE_SIZE,
                        Settings.SCALED_TILE_SIZE);
            }
        }

        batch.draw(player.getSprite(),
                wordlStartX + player.getWorldX() * Settings.SCALED_TILE_SIZE,
                wordlStartY + player.getWorldY() * Settings.SCALED_TILE_SIZE,
                Settings.SCALED_TILE_SIZE,
                Settings.SCALED_TILE_SIZE * 1.5f);
        batch.end();
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
