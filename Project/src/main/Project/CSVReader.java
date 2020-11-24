/**
 * CSVReader is the class for parse the CSV file to Peg list
 * with default setting.
 *
 * @author Wenlin Leng
 */

import bagel.util.Point;
import java.io.*;
import java.util.ArrayList;

public class CSVReader {

    //Get Peg list with CSV file
    public ArrayList<Peg> read(String fileName) throws IOException {

        File csv = new File(fileName);
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = "";
        String[] everyLine;
        String type;
        double x, y;
        ArrayList<Peg> pegs = new ArrayList<>();

        //Read data and add peg to the list
        while ((line = br.readLine()) != null) {
            everyLine = line.split(",");
            type = everyLine[0];
            x = Integer.parseInt(everyLine[1]);
            y = Integer.parseInt(everyLine[2]);
            Point point = new Point(x,y);
            String filename = Peg.checkColor(type);
            Peg peg = new Peg(point,filename,filename);
            pegs.add(peg);
        }

        //Create red peg with 1/5 proportion
        for (int i = 0; i < pegs.size() / 5; i++) {
            int j = Peg.randomNum(pegs.size());
            String filenameOriginal = pegs.get(j).getFilename();
            if (filenameOriginal.split("-")[0].split("/")[1].equals("grey")||
                    filenameOriginal.split("-")[0].split("/")[1].equals("red")||
                    filenameOriginal.split("-")[0].split("/")[1].equals("green")
            ){
                int n = filenameOriginal.split("-")[0].length();
                String firenameNew = "res/red"+filenameOriginal.subSequence(n,filenameOriginal.length()).toString();
                Peg greenPeg = new Peg(pegs.get(j).getPoint(),firenameNew, firenameNew);
                pegs.set(j,greenPeg);
            }else{
                String firenameNew = "res/red-"+filenameOriginal.substring(4);
                Peg greenPeg = new Peg(pegs.get(j).getPoint(),firenameNew, firenameNew);
                pegs.set(j,greenPeg);
            }
        }

        //Create one green peg
        int k = Peg.randomNum(pegs.size());
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

        return pegs;
    }
}
