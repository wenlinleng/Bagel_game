/**
 * Bucket is the class of bucket which inhered from
 * Sprite class.
 * Bucket class allow an element to move reversely
 * after colliding with wall
 * This state information includes:
 * <ul>
 * <li>A constant speed
 * <li>Velocity of bucket
 * <li>The static final field SPEED
 * <li>the status represent if bucket is moving left
 * </ul>
 * <p>
 *
 * @author Wenlin Leng
 */

import bagel.Window;
import bagel.util.Point;
import bagel.util.Vector2;

public class Bucket extends Sprite{

    /**
     * The constantly added speed on ball velocity
     */
    public static final double SPEED = 4;

    /**
     * The constant initial position
     */
    public static final Point BUCKET_POSITION = new Point(512,744);

    private Vector2 velocity;

    private boolean toLeft = true;

    /**
     * Construct the Bucket object
     *
     * @param point position on screen
     */
    public Bucket(Point point) {
        super(point, "res/bucket.png");
        velocity = new Vector2(SPEED,0);
    }

    /**
     * Move reversely after colliding with wall
     */
    @Override
    public void update() {
        super.move(velocity);
        if (super.getRect().left() < 0 || super.getRect().right() > Window.getWidth()) {
            velocity = new Vector2(-velocity.x, velocity.y);
            toLeft = !toLeft;
        }
        super.draw();
    }
}
