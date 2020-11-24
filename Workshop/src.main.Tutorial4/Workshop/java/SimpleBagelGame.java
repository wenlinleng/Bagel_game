import bagel.*;

/**
 * An example Bagel game.
 *
 * @author Eleanor McMurtry
 */
public class SimpleBagelGame extends AbstractGame {
    private Image square;
    private Image bagel;
    private double x = 100;
    private double y = 100;

    public SimpleBagelGame() {
        bagel = new Image("res/bagel.jpg");
        square = new Image("res/square.png");
    }

    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        SimpleBagelGame game = new SimpleBagelGame();
        game.run();
    }

    /**
     * Performs a state update. This simple example shows an image that can be
     * controlled with the arrow keys, and allows the game to exit when the escape key is pressed.
     */
    @Override
    public void update(Input input) {


        double speed = 2;
        if (input.isDown(Keys.LEFT)) {
            x -= speed;
        }
        if (input.isDown(Keys.RIGHT)) {
            x += speed;
        }
        if (input.isDown(Keys.UP)) {
            y -= speed;
        }
        if (input.isDown(Keys.DOWN)) {
            y += speed;
        }

        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        }

        bagel.draw(Window.getWidth() / 2, Window.getHeight() / 2);
        square.draw(x, y);
    }
}
