import java.io.File;
import java.util.Scanner;

public class ReadingFiles{

    public static int[] readFiles(String file) {

        try {

            File f = new File(file);
            Scanner s = new Scanner(f);
            int ctr = 0;
            while (s.hasNextInt()) {
                ctr++;
                s.nextInt();
            }
            int[] arr = new int[ctr];

            Scanner s1 = new Scanner(f);

            for (int i = 0; i < arr.length; i++)
                arr[i] = s1.nextInt();

            return arr;
        } catch (Exception e) {
            return null;
        }
    }
}


