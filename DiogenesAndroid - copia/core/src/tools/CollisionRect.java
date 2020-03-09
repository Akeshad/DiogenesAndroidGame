package tools;

/**
 * is going to check if there is a collision
 */
public class CollisionRect {

    float x, y;//the position of the sprite
    int width, height;// the width and the height of the sprite


    /**
     *The constructor os the collisionRect
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public CollisionRect (float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * The x and the y have to being able to move the collision rect
     *
     * @param x
     * @param y
     */
    public void move (float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * IS going to say if the rect is colliding or not with another rect
     *
     * @param rect
     * @return true or false
     */
    public boolean collidesWith (CollisionRect rect) {
        return x < rect.x + rect.width && y < rect.y + rect.height && x + width > rect.x && y + height > rect.y;
    }
}
