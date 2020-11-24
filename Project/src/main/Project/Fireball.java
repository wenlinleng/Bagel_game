/**
 * Fireball is the class for fireball behaviour which inhered from
 * Ball class.
 * Fireball class allow to construct a fireball element.
 *
 * @author Wenlin Leng
 */

import bagel.Image;

public class Fireball extends Ball {

    /**
     * Construct the Fireball object
     *
     * @param ball original Ball element
     */
    public Fireball(Ball ball) {
        super(ball.getPoint(), ball.getVelocity());
        super.setVelocity(ball.getVelocity());
        super.setImage(new Image("res/fireball.png"));
    }
}