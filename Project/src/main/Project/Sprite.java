/**
 * Sprite is the abstract base class for all movable contexts.
 * which allow an element to move with given velocity,illustrate
 * on screen and test intersecting status with other element.
 * A movable object encapsulates the information needed for the
 * various operations. This state information includes:
 * <ul>
 * <li>The image to illustrate
 * <li>The point that represent position on screen
 * <li>The rectangle object that store the margin information
 * </ul>
 * <p>
 *
 * @author Wenlin Leng (The Spirit class is from sample code.)
 */

import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;
import bagel.util.Vector2;

public abstract class Sprite {

    private Image image;

    private Rectangle rect;

    private Point point;

    /**
     * Construct the Sprite object
     *
     * @param point position on screen
     * @param imageSrc String of image address
     */
    public Sprite(Point point, String imageSrc) {
        image = new Image(imageSrc);
        rect = image.getBoundingBoxAt(point);
        this.point = point;
    }

    /**
     * Test intersecting status with other Sprite object
     *
     * @param other other Sprite
     * @return boolean intersecting status
     */
    public boolean intersects(Sprite other) {
        return rect.intersects(other.rect);
    }

    /**
     * Change the position of object with teh given velocity
     *
     * @param dx velocity
     */
    public void move(Vector2 dx) {
        rect.moveTo(rect.topLeft().asVector().add(dx).asPoint());
    }

    /**
     * Illustrate object on screen
     */
    public void draw() {
        image.draw(rect.centre().x, rect.centre().y);
    }

    /**
     * Abstract method to update the behaivour of object
     */
    public abstract void update();

    /**
     * Gets the value of rect
     *
     * @return the value of rect
     */
    public Rectangle getRect() {
        return rect;
    }

    /**
     * Gets the value of image
     *
     * @return the value of image
     */
    public Image getImage() {
        return image;
    }

    /**
     * Sets the image
     * <p>You can use getImage() to get the value of image</p>
     *
     * @param image image
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * Sets the rect
     * <p>You can use getRect() to get the value of rect</p>
     *
     * @param rect rect
     */
    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    /**
     * Gets the value of point
     *
     * @return the value of point
     */
    public Point getPoint() {
        return point;
    }

    /**
     * Sets the point
     * <p>You can use getPoint() to get the value of point</p>
     *
     * @param point point
     */
    public void setPoint(Point point) {
        this.point = point;
    }
}
