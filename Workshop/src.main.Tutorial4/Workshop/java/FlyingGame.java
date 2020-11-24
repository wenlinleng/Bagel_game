import bagel.*;

public class FlyingGame extends AbstractGame {
    private Image background;//no
    private Image balloon;
    private double balloonX;
    private double balloonY;

    private Player player;

    public FlyingGame() {
        background = new Image("res/land.jpeg");
        balloon = new Image("res/balloon.png");

        player = new Player();

        setBalloonPosition();
    }

    private void setBalloonPosition() {
        balloonX = Math.random() * Window.getWidth();
        balloonY = Math.random() * Window.getHeight();
    }

    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        FlyingGame game = new FlyingGame();
        game.run();
    }

    @Override
    public void update(Input input) {
        double speed = 2;
        double dx = 0, dy = 0;
        if (input.isDown(Keys.LEFT)) {
            dx -= speed;
        }
        if (input.isDown(Keys.RIGHT)) {
            dx += speed;
        }
        if (input.isDown(Keys.UP)) {
            dy -= speed;
        }
        if (input.isDown(Keys.DOWN)) {
            dy += speed;
        }
        player.move(dx, dy);

        if (Math.sqrt((player.getX() - balloonX) * (player.getX() - balloonX) + (player.getY() - balloonY) * (player.getY() - balloonY)) < 50) {
            setBalloonPosition();
        }

        background.draw(Window.getWidth() / 2, Window.getHeight() / 2);
        balloon.draw(balloonX, balloonY);
        player.render();
    }
}