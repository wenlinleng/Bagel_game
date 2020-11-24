/**
 * Peg is the class for all types of peg which inhered from
 * Sprite class.
 * Peg class allow an element to check the color of peg,
 * generate a Peg with random-chose shape.
 * This state information includes:
 * <ul>
 * <li>The filename of pegs
 * </ul>
 * <p>
 *
 * @author Wenlin Leng
 */

import bagel.util.Point;
import java.awt.*;
import java.util.Random;
import bagel.Image;

public class Peg extends Sprite {

    private String filename;


    /**
     * Construct the Peg object
     *
     * @param point position on screen
     * @param imageSrc String filename
     * @param filename filename of peg
     */
    public Peg(Point point, String imageSrc, String filename) {
        super(point, imageSrc);
        this.filename = filename;
    }

    /**
     * Show the peg
     */
    @Override
    public void update() {
        super.draw();
    }

    /**
     * Return a random-generated number
     *
     * @param n constrain number
     * @return int random number
     */
    public static int randomNum(int n){
        Random r = new Random();
        int nextInt = r.nextInt(n);
        return nextInt;
    }

    /**
     * Check the peg color
     *
     * @param name
     * @return String filename
     */
    public static String checkColor(String name){
        String filename = null;
        if (name.equals("blue_peg")){
            filename = "res/peg.png";
        }else if (name.equals("grey_peg")){
            filename = "res/grey-peg.png";
        }else if (name.equals("blue_peg_vertical")){
            filename = "res/vertical-peg.png";
        }else if (name.equals("blue_peg_horizontal")) {
            filename = "res/horizontal-peg.png";
        }else if (name.equals("grey_peg_vertical")){
            filename = "res/grey-vertical-peg.png";
        }else if (name.equals("grey_peg_horizontal")){
            filename = "res/grey-horizontal-peg.png";
        }
        return filename;

    }

    /**
     * Check if the peg is red
     *
     * @param peg
     * @return boolean
     */
    public static boolean redPeg(Peg peg){
        if (peg.getFilename().equals("res/red-horizontal-peg.png")||
                peg.getFilename().equals("res/red-peg.png")||
                peg.getFilename().equals("res/red-vertical-peg.png")
        ){
            return true;
        }
        return false;
    }

    /**
     * Check if the peg is green
     *
     * @param peg
     * @return boolean
     */
    public static boolean greenPeg(Peg peg){
        if (peg.getFilename().equals("res/green-horizontal-peg.png")||
                peg.getFilename().equals("res/green-peg.png")||
                peg.getFilename().equals("res/green-vertical-peg.png")
        ){
            return true;
        }
        return false;
    }

    /**
     * Check if the peg is grey
     *
     * @param peg
     * @return boolean
     */
    public static boolean greyPeg(Peg peg){
        if (peg.getFilename().equals("res/grey-horizontal-peg.png")||
                peg.getFilename().equals("res/grey-peg.png")||
                peg.getFilename().equals("res/grey-vertical-peg.png")
        ){
            return true;
        }
        return false;
    }

    /**
     * Check if the peg is blue
     *
     * @param peg
     * @return boolean
     */
    public static boolean bluePeg(Peg peg){
        if (peg.getFilename().equals("res/horizontal-peg.png")||
                peg.getFilename().equals("res/peg.png")||
                peg.getFilename().equals("res/vertical-peg.png")
        ){
            return true;
        }
        return false;
    }

    /**
     * Generate a Peg with random-chose shape
     *
     * @param point
     * @param color
     * @return Peg
     */
    public static Peg ranPeg(Point point, String color){
        int num = Peg.randomNum(3);
        if (num==0){
            return new Peg(point,"res/normal-"+color+"-peg.png","res/normal-"+color+"-peg.png");
            } else if (num==1){
            return new Peg(point,"res/horizontal-"+color+"-peg.png","res/horizontal-"+color+"-peg.png");
            }else{
            return new Peg(point,"res/vertical-"+color+"-peg.png","res/vertical-"+color+"-peg.png");
            }
    }

    /**
     * Gets the value of filename
     *
     * @return the value of filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Sets the filename
     * <p>You can use getFilename() to get the value of filename</p>
     *
     * @param filename filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }
}

