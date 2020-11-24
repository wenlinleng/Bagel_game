/**
 * PowerUp is the class for powerup which inhered from
 * Sprite class.
 * PowerUp class allow an element to move with random
 * generated position and change position of it arrived
 * at 1[ of destination.
 * This state information includes:
 * <ul>
 * <li>The velocity of powerup
 * <li>The static final field SPEED
 * <li>The destination position of powerup
 * </ul>
 * <p>
 *
 * @author Wenlin Leng
 */

import bagel.Window;
import bagel.util.Point;
import bagel.util.Rectangle;
import bagel.util.Vector2;
import java.util.Random;


public class PowerUp extends Sprite{

    /**
     * The constant speed of ball
     */
    public static final double SPEED = 3;

    private Vector2 velocity;

    private Point destPoint;

    /**
     * Construct the PowerUp object
     *
     * @param destPoint destination position of powerup
     */
    public PowerUp(Point destPoint) {
        super(new Point(), "res/powerup.png");
        Point point = randomPoint();
        super.setPoint(point);
        super.setRect(super.getImage().getBoundingBoxAt(point));
        this.destPoint = destPoint;
        double distance = Math.sqrt((point.x-destPoint.x)*(point.x-destPoint.x)+
                (point.y-destPoint.y)*(point.y-destPoint.y));
        velocity = new Point((destPoint.x-point.x) / distance * SPEED, (destPoint.y-point.y) / distance * SPEED).asVector();
        }

    /**
     * Make powerup move within 1p of destination
     */
    @Override
    public void update() {

        super.setPoint(new Point(velocity.x+super.getPoint().x,velocity.y+super.getPoint().y));
        super.setRect(super.getImage().getBoundingBoxAt(super.getPoint()));

        Rectangle rectangle = new Rectangle(destPoint,1,1);
        if (super.getRect().intersects(rectangle)){
            destPoint =randomPoint();
            double distance = Math.sqrt((super.getPoint().x-destPoint.x)*(super.getPoint().x-destPoint.x)+
                    (super.getPoint().y-destPoint.y)*(super.getPoint().y-destPoint.y));
            velocity = new Point((destPoint.x-super.getPoint().x) / distance * SPEED, (destPoint.y-super.getPoint().y) / distance * SPEED).asVector();
        }
        super.getImage().draw(super.getPoint().x,super.getPoint().y);
    }

    /**
     * Random generating a point
     *
     * @return Point
     */
    public static Point randomPoint() {
        Random rand = new Random();
        int x = rand.nextInt(Window.getWidth()+1);
        int y = rand.nextInt(Window.getHeight()+1);
        Point p = new Point(x,y);
        return p;
    }

    /**
     * Gets the value of SPEED
     *
     * @return the value of SPEED
     */
    public static double getSPEED() {
        return SPEED;
    }

    /**
     * Gets the value of velocity
     *
     * @return the value of velocity
     */
    public Vector2 getVelocity() {
        return velocity;
    }

    /**
     * Sets the velocity
     * <p>You can use getVelocity() to get the value of velocity</p>
     *
     * @param velocity velocity
     */
    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    /**
     * Gets the value of destPoint
     *
     * @return the value of destPoint
     */
    public Point getDestPoint() {
        return destPoint;
    }

    /**
     * Sets the destPoint
     * <p>You can use getDestPoint() to get the value of destPoint</p>
     *
     * @param destPoint destPoint
     */
    public void setDestPoint(Point destPoint) {
        this.destPoint = destPoint;
    }
}
