/**
 * The ShadowBounce(), update(), main() are from sample solution.
 */

import bagel.*;
import java.io.IOException;
import java.util.ArrayList;

public class ShadowBounce extends AbstractGame {
    Board board = new Board(0);
    int turn = 0;
    public ShadowBounce() throws IOException {
    }

    @Override
    protected void update(Input input) {

        //Check if create next board
        if (board.nextBoard()){
            int boardNumBe = board.getBoardNum();

            if (boardNumBe == 4) {
                System.out.println("Success!");
                Window.close();
            }else{
                try {
                    board = new Board(++boardNumBe);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //Get access to the board element
        Bucket bucket = board.getBucket();
        ArrayList<Ball> balls= board.getBalls();
        PowerUp powerup = board.getPu();

        //Create ball and powerup with a new turn
        if (input.wasPressed(MouseButtons.LEFT) && board.isEmpty()) {
            balls.add(0,new Ball(Ball.BALL_POSITION, input.directionToMouse(Ball.BALL_POSITION)));
            board.randomPU();
        }

        /*
        If ball is created:
            1. Show the ball.
            2. If the ball out of the screen, open a new turn.
        */
        if (!balls.isEmpty()) {

            for (Ball ball : balls) {
                if (ball!=null) {
                    ball.update();
                }
            }

            if (board.allOutOfScreen()) {
                balls.clear();
                turn++;
                board.setGrey();
                board.setGreen();
                board.setPu(null);
                Board.fireState = false;
            }
        }

        //Show the power up
        if (powerup!=null){
            powerup.update();
        }

        //Game over!
        if (turn == 20){
            Window.close();
            System.out.println("FAIL!");
        }

        /*After ball created:
            1.Check if collide with bucket
            2.Check if collide with powerup
            3.Check if collide with pegs
        */
        if (!balls.isEmpty()) {

            for (Ball ball : balls) {
                if (ball!=null) {
                    if (ball.intersects(bucket)) {
                        turn--;
                    }
                }
            }

            if (powerup != null){
                for (int i = 0; i < balls.size(); i++) {
                    if (balls.get(i)!=null) {
                        if (board.getPu()!=null && balls.get(i).intersects(board.getPu())) {
                            board.destroyPU();
                            balls.set(i, new Fireball(balls.get(i)));
                            Board.fireState = true;
                        }
                    }
                }
            }

            if (!Board.fireState){
                boolean collideGreen = false;
                for (int i = 0; i < board.getPegs().size(); ++i) {
                    Peg peg = board.getPegs().get(i);

                    for (Ball ball : balls) {
                        if (peg != null && ball!=null) {
                            if (ball.intersects(peg)) {
                                ball.reverseDi(peg, ball);
                                if (Peg.redPeg(peg) || Peg.bluePeg(peg)) {
                                    board.getPegs().set(i, null);
                                } else if (Peg.greenPeg(peg)) {
                                    board.getPegs().set(i, null);
                                    collideGreen = true;
                                }
                            }
                        }
                    }
                    if (collideGreen && peg!=null) {
                        board.collideGreen(peg);
                        collideGreen = false;
                    }
                }
            }else{
                boolean collideGreen = false;
                for (int i = 0; i < board.getPegs().size(); ++i) {
                    Peg peg = board.getPegs().get(i);
                    for (Ball ball : balls) {
                        if (peg != null && ball!=null) {
                            if (ball.intersects(peg)) {
                                ball.reverseDi(peg, ball);
                                if (Peg.bluePeg(peg) || Peg.redPeg(peg)) {
                                    board.destroy70p(i);
                                } else if (Peg.greenPeg(peg)) {
                                    board.getPegs().set(i, null);
                                    collideGreen = true;
                                }
                            }
                        }
                    }
                    if (collideGreen && peg!=null) {
                        board.collideGreen(peg);
                        collideGreen = false;
                    }
                }
            }
            board.drawBalls();
        }

        for (Peg peg : board.getPegs()) {
            if (peg != null) {
                peg.update();
            }
        }

        bucket.update();
    }

    public static void main(String[] args) throws IOException {
        new ShadowBounce().run();
    }
}
