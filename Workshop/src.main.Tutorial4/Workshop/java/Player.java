import bagel.*;

public class Player {
    private Image image;
    private double x;
    private double y;

    public Player() {
        x = Window.getWidth() / 2;
        y = Window.getHeight() / 2;
        image = new Image("res/plane.png");
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    public void render() {
        image.draw(x, y);
    }
}
