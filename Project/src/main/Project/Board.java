/**
 * Board is the class to store all elements presented on
 * the screen.
 * In terms of pegs on board, board class allow them to be
 * destroy on the screen,or destroyed within 70p of input peg.
 * Also, setting one peg to green peg at beginning of each turn.
 * Additionally, Board generates extra two balls after colliding
 * with green peg.
 * For balls on board, Board check the empty status of balls list,
 * out of bound status and show the balls on the screen.
 * For powerup element, Board class could create randomly or destroy
 * it, and update it movement.
 * This state information includes:
 * <ul>
 * <li>The peg list
 * <li>The ball list
 * <li>The powerup element
 * <li>The bucket element
 * <li>The level number uploaded
 * <li>The fireball status on the board
 * </ul>
 * <p>
 *
 * @author Wenlin Leng
 */


import bagel.util.Point;
import bagel.util.Rectangle;
import bagel.util.Vector2;
import java.io.IOException;
import java.util.ArrayList;

public class Board {

    private ArrayList<Peg> pegs = null;

    private PowerUp pu = null;

    private ArrayList<Ball> balls = new ArrayList<>(3);

    private Bucket bucket = null;

    private int boardNum = 0;

    /**
     * The fireball status presented on the ball
     */
    public static boolean fireState = false;

    /**
     * Construct the Board object
     *
     * @param boardNum order of level
     */
    public Board(int boardNum) throws IOException {
        this.boardNum = boardNum;
        CSVReader cs = new CSVReader();
        pegs = cs.read("res/"+boardNum+".csv");
        bucket = new Bucket(Bucket.BUCKET_POSITION);
    }

    /**
     * Destroy the peg
     *
     * @param position order of peg in list
     */
    public void destroy(int position){
        pegs.remove(position);
    }

    /**
     * Destroy pegs within 70p of input peg
     *
     * @param position order of peg in list
     */
    public void destroy70p(int position){
        Rectangle rect = pegs.get(position).getRect();
        Rectangle rectLarge = new Rectangle(
                rect.left()-70,
                rect.top()-70,
                rect.right()-rect.left()+140,
                rect.bottom()-rect.top()+140);
        for (int i = 0; i < pegs.size(); ++i) {
            if (pegs.get(i)!=null && rectLarge.intersects(pegs.get(i).getRect().centre()) && !Peg.greyPeg(pegs.get(i))
            ){
               pegs.set(i,null);
            }
        }
    }

    /**
     * Destroy powerup
     */
    public void destroyPU(){
        pu = null;
    }

    /**
     * Random create powerup
     */
    public void randomPU(){
        if (Peg.randomNum(10)==1){
            Point p = PowerUp.randomPoint();
            pu=(new PowerUp(p));
        }
    }

    /**
     * Update powerup
     */
    public void updatePowerup(){
        pu.setPoint(new Point(pu.getVelocity().x+pu.getPoint().x,pu.getVelocity().y+pu.getPoint().y));
        pu.setRect(pu.getImage().getBoundingBoxAt(pu.getPoint()));

        Rectangle rectangle = new Rectangle(pu.getDestPoint(),1,1);
        if (pu.getRect().intersects(rectangle)){
            System.out.println("1p以内了");
            pu.setDestPoint(PowerUp.randomPoint());
        }
        pu.getImage().draw(pu.getPoint().x,pu.getPoint().y);

    }



    /**
     * Check if move to next board
     *
     * @return boolean
     */
    public boolean nextBoard(){
        int redNum = 0;
        for (int i = 0; i < pegs.size(); i++) {
            if (pegs.get(i)!=null && Peg.redPeg(pegs.get(i))){
                redNum++;
            }
        }
        if (redNum==0){
            return true;
        }
        return false;
    }

    /**
     * Set one peg to green peg
     */
    public void setGreen(){
        int k = Peg.randomNum(pegs.size());
        while (pegs.get(k) == null){
            k = Peg.randomNum(pegs.size());
        }
        String filenameOriginal = pegs.get(k).getFilename();
        if (filenameOriginal.split("-")[0].split("/")[1].equals("green")||
                filenameOriginal.split("-")[0].split("/")[1].equals("red")||
                filenameOriginal.split("-")[0].split("/")[1].equals("grey")
        ){
            int n = filenameOriginal.split("-")[0].length();
            String firenameNew = "res/green"+filenameOriginal.subSequence(n,filenameOriginal.length()).toString();
            Peg greenPeg = new Peg(pegs.get(k).getPoint(),firenameNew, firenameNew);
            pegs.set(k,greenPeg);
        }else{
            String firenameNew = filenameOriginal.subSequence(0,4).toString()+"green-"+filenameOriginal.substring(4,filenameOriginal.length()).toString();
            Peg greenPeg = new Peg(pegs.get(k).getPoint(),firenameNew, firenameNew);
            pegs.set(k,greenPeg);
        }
    }

    /**
     * Change all green pegs to blue peg
     */
    public void setGrey(){
        for (int j = 0; j <pegs.size() ; j++) {
            if (pegs.get(j)!= null && Peg.greenPeg(pegs.get(j))){
                String filenameOriginal = pegs.get(j).getFilename();
                String firenameNew = "res/grey-"+filenameOriginal.subSequence(10,filenameOriginal.length()).toString();
                Peg greyPeg = new Peg(pegs.get(j).getPoint(),firenameNew, firenameNew);
                pegs.set(j,greyPeg);
            }
        }
    }

    /**
     * Check if the ball list is empty
     *
     * @return boolean
     */
    public boolean isEmpty(){
        for (Ball ball : balls) {
            if (ball != null){
                return false;
            }
        }
        return true;
    }

    /**
     * Check if all balls are out of bound
     *
     * @return boolean
     */
    public boolean allOutOfScreen(){
        for (Ball ball : balls) {
            if (!ball.outOfScreen()){
                return false;
            }
        }
        return true;
    }

    /**
     * Show balls
     */
    public void drawBalls(){
        for (Ball ball : balls) {
            if (ball != null){
                ball.draw();
            }
        }
    }

    /**
     * Generate extra two balls after colliding with green peg
     *
     * @param peg
     */
    public void collideGreen(Peg peg){
        balls.add(new Ball(peg.getPoint(),new Vector2(1,-1)));
        balls.add(new Ball(peg.getPoint(),new Vector2(-1,-1)));
    }

    /**
     * Gets the value of pegs
     *
     * @return the value of pegs
     */
    public ArrayList<Peg> getPegs() {
        return pegs;
    }

    /**
     * Sets the pegs
     * <p>You can use getPegs() to get the value of pegs</p>
     *
     * @param pegs pegs
     */
    public void setPegs(ArrayList<Peg> pegs) {
        this.pegs = pegs;
    }

    /**
     * Gets the value of pu
     *
     * @return the value of pu
     */
    public PowerUp getPu() {
        return pu;
    }

    /**
     * Sets the pu
     * <p>You can use getPu() to get the value of pu</p>
     *
     * @param pu pu
     */
    public void setPu(PowerUp pu) {
        this.pu = pu;
    }

    /**
     * Gets the value of balls
     *
     * @return the value of balls
     */
    public ArrayList<Ball> getBalls() {
        return balls;
    }

    /**
     * Sets the balls
     * <p>You can use getBalls() to get the value of balls</p>
     *
     * @param balls balls
     */
    public void setBalls(ArrayList<Ball> balls) {
        this.balls = balls;
    }

    /**
     * Gets the value of bucket
     *
     * @return the value of bucket
     */
    public Bucket getBucket() {
        return bucket;
    }

    /**
     * Sets the bucket
     * <p>You can use getBucket() to get the value of bucket</p>
     *
     * @param bucket bucket
     */
    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }

    /**
     * Gets the value of boardNum
     *
     * @return the value of boardNum
     */
    public int getBoardNum() {
        return boardNum;
    }

    /**
     * Sets the boardNum
     * <p>You can use getBoardNum() to get the value of boardNum</p>
     *
     * @param boardNum boardNum
     */
    public void setBoardNum(int boardNum) {
        this.boardNum = boardNum;
    }
}
