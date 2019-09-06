import java.io.File;
import java.util.Scanner;

public class Reading2DArrayFromFile {
    public static int[][] read2DArrayFiles(String filename) {

        try {

            File f = new File(filename);
            Scanner s = new Scanner(f);
            int rows = 9;
            int columns = 9;
            int[][] myArray = new int[rows][columns];

            Scanner s1 = new Scanner(f);
            while (s.hasNextLine()) {
                for (int i = 0; i < myArray.length; i++) {
                    String[] line = s.nextLine().trim().split(" ");
                    for (int j = 0; j < line.length; j++) {
                        myArray[i][j] = Integer.parseInt(line[j]);
                    }
                }
            }
            return myArray;
        } catch (Exception e) {
            return null;
        }
    }
}




