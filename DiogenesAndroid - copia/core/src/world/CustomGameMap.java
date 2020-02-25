package world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import world.customGameMap.CustomGameMapData;
import world.customGameMap.CustomGameMapLoader;


/**
 * This class create a new game map, this game map can be custom
 */
public class CustomGameMap extends GameMap {

    String id;
    String name;
    int[][][] map;

    private TextureRegion[][] tiles;
    private SpriteBatch batch;

    public CustomGameMap () {
        CustomGameMapData data = CustomGameMapLoader.loadMap("basic", "My Grass Lands!");
        this.id = data.id;
        this.name = data.name;
        this.map = data.map;
        batch = new SpriteBatch();

        /**
         * We split the image were the tiles are into the array so we can access to the tiles using this array
         */
        tiles = TextureRegion.split(new Texture("tiles.png"), TileType.TILE_SIZE, TileType.TILE_SIZE);
    }


    @Override
    public void render(OrthographicCamera camera) {

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (int layer = 0; layer < getLayers(); layer++) {
            for (int row = 0; row < getHeight(); row++) {
                for (int col = 0; col < getWidth(); col++) {
                    TileType type = this.getTileTypeByCoordinate(layer, col, row);
                    if (type != null)
                        batch.draw(tiles[0][type.getId() - 1], col * TileType.TILE_SIZE, row * TileType.TILE_SIZE);
                }
            }
        }


        batch.end();

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    /**
     *
     * @param layer
     * @param x
     * @param y
     * @return
     */
    @Override
    public TileType getTileTypeByLocation(int layer, float x, float y) {

        return this.getTileTypeByCoordinate(layer, (int) (x / TileType.TILE_SIZE), getHeight() - (int) (y / TileType.TILE_SIZE) - 1);
    }

    /**
     * This function returns the TileType by its Coordinate
     * @param layer
     * @param col
     * @param row
     * @return the TileType by its Coordinate
     */
    @Override
    public TileType getTileTypeByCoordinate(int layer, int col, int row) {

        /**
         * if the colum or the row returns out of bounds we return null, it does not exists
         */
        if (col < 0 || col >= getWidth() || row < 0 || row >= getHeight())
            return null;

        /**
         * This going to make sure that we are going to load our tiles in the correct order
         */
        return TileType.getTileTypeById(map[layer][getHeight() - row - 1][col]);
    }


    /**
     *
     * @return we get the length of the third index
     */
    @Override
    public int getWidth() {
        return map[0][0].length;
    }

    /**
     *
     * @return This is going to give us the length of the row, the second index
     */
    @Override
    public int getHeight() {
        return map[0].length;
    }

    /**
     *
     * @return we get the length of the first index, the layers
     */
    @Override
    public int getLayers() {
        return map.length;
    }
}
