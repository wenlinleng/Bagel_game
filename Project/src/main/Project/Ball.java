/**
 * Ball is the class for all types of ball which inhered from
 * Sprite class.
 * Ball class allow an element to check if the ball is out of
 * the screen, reverse direction after colliding with wall.
 * This state information includes:
 * <ul>
 * <li>The velocity of ball
 * <li>The static final field GRAVITY
 * <li>The static final field SPEED
 * <li>The static final field BALL_POSITION represented initial
 * position
 * </ul>
 * <p>
 *
 * @author Wenlin Leng (The outOfScreen method is from sample code.)
 */

import bagel.Window;
import bagel.util.Point;
import bagel.util.Side;
import bagel.util.Vector2;
import java.util.ArrayList;
import static bagel.util.Side.*;

public class Ball extends Sprite {

    private Vector2 velocity;

    /**
     * The constantly added speed on ball velocity
     */
    public static final double GRAVITY = 0.15;

    /**
     * The constant speed of ball
     */
    public static final double SPEED = 10;

    /**
     * The constant initial position of ball
     */
    public static final Point BALL_POSITION = new Point(512, 32);

    /**
     * Construct the Sprite object
     *
     * @param point position on screen
     * @param direction input velocity
     */
    public Ball(Point point, Vector2 direction) {
        super(point, "res/ball.png");
        velocity = direction.mul(SPEED);
       }

    /**
     * Check if the ball is out of the screen
     */
    public boolean outOfScreen() {
        return super.getRect().top() > Window.getHeight();
    }

    /**
     * Reverse direction after colliding with wall
     */
    @Override
    public void update() {
        velocity = velocity.add(Vector2.down.mul(GRAVITY));
        super.move(velocity);
        if (super.getRect().left() < 0 || super.getRect().right() > Window.getWidth()) {
            velocity = new Vector2(-velocity.x, velocity.y);
        }
        if (super.getRect().top() < 0){
            velocity = new Vector2(velocity.x, -velocity.y);
        }
    }

    /**
     * Reverse direction after colliding with peg
     *
     * @param peg
     * @param ball
     */
    public void reverseDi(Peg peg, Ball ball){
        if (findIntersact(peg,ball)!=null) {
            Side side = ball.getRect().intersectedAt(findIntersact(peg, ball), ball.getVelocity());
            if (side.equals(LEFT) || side.equals(RIGHT)) {
                reverseHor();
                } else if (side.equals(BOTTOM) || side.equals(TOP)) {
                reverseVer();
            }
        }
    }

    /**
     * Helper function to reverse horizontal direction
     */
    public void reverseHor(){
        velocity = new Vector2(-velocity.x, velocity.y);
    }

    /**
     * Helper function to reverse vertical direction
     */
    public Vector2 reverseVer(){
        velocity = new Vector2(velocity.x, -velocity.y);
        return velocity;
    }

    /**
     * Helper function to find intersact point on ball
     *
     * @param peg
     * @param ball
     */
    public Point findIntersact(Peg peg,Ball ball) {
        ArrayList<Point> points = new ArrayList<>();
        Point cornerPoint[] = {
                ball.getRect().bottomRight(),
                ball.getRect().topRight(),
                ball.getRect().topLeft(),
                ball.getRect().bottomLeft() };
        for (Point point : cornerPoint) {
            if (peg.getRect().intersects(point)) {
                points.add(point);
            }
        }
        if (points.size()==1){
            return points.get(0);
        }else{
            Point p1 = points.get(0);
            Point p2 = points.get(1);
            return new Point((p1.x+p2.x)/2,((p1.y+p2.y)/2));
        }
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
}
