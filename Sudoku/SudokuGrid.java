import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class SudokuGrid extends JFrame {

    // change samplesudoku1.txt to any file you want in the variable file chosen to get get another file to play
    public static String filechosen = "samplesudoku5.txt";

    private static final Font fontForNumbers = new Font("Monospaced", Font.BOLD, 20);
    private static final Color quitColor = new Color(222, 150, 123);
    private static final Color correctColor = new Color(64, 222, 38);
    private static final Color recheckColor = new Color(133, 176, 224);
    private static final Color standardColor = new Color(215, 208, 159);
    private static final Color winningColor = new Color(160, 73, 215);

    private static final boolean[] arrayTruth = {
            true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true, true,
    };

    public static final boolean [] comparisonArray = new boolean [81];

    long startTime = System.nanoTime();

    Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

    ReadingFiles loadProblems = new ReadingFiles();
    public int[] sample = loadProblems.readFiles(filechosen);

    Reading2DArrayFromFile SampleProblem = new Reading2DArrayFromFile();
    int[][] GRID_TO_SOLVE = SampleProblem.read2DArrayFiles(filechosen);

    SudokuSolver solution = new SudokuSolver(GRID_TO_SOLVE);
    //needed to fill a grid with solutions
    boolean placeholder = solution.solve();
    //used to check for comparisons
    private final int[] solved1DArrayforComparison = solution.solved1DArray();

    private final JTextField[] SudokuCells = new JTextField[81];
    private int currentIndex = 0;

    public SudokuGrid() {

        setLayout(new GridLayout(3, 3));

    /*    JLabel information = new JLabel();
        information.setText("Press Q to quit and reveal answers. Press C to check all Answers");
        information.setVisible(true);*/

        for (int i = 0; i < 81; i++) {
            SudokuCells[i] = new JTextField();
            SudokuCells[i].setHorizontalAlignment(JTextField.CENTER);
            if (sample[i] == 0) {
                SudokuCells[i].setText("");
                SudokuCells[i].setEditable(true);
                SudokuCells[i].setBackground(Color.WHITE);
            } else {
                SudokuCells[i].setText(sample[i] + "");
                SudokuCells[i].setEditable(false);
                SudokuCells[i].setBackground(standardColor);
                SudokuCells[i].setForeground(Color.BLACK);
            }
            SudokuCells[i].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_RIGHT:
                            requestFocusForIndex(currentIndex + 1);
                            break;
                        case KeyEvent.VK_LEFT:
                            requestFocusForIndex(currentIndex - 1);
                            break;
                        case KeyEvent.VK_UP:
                            requestFocusForIndex(currentIndex - 9);
                            break;
                        case KeyEvent.VK_DOWN:
                            requestFocusForIndex(currentIndex + 9);
                            break;
                        case KeyEvent.VK_C:
                            checkAnswer();
                            break;
                        case KeyEvent.VK_Q:
                            keytoQuit();
                            break;
                    }
                }
            });
            SudokuCells[i].setFont(fontForNumbers);
            add(SudokuCells[i]);
            SudokuCells[i].setBorder(border);
        }

       Border b = BorderFactory.createLineBorder(Color.BLACK, 5);

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(3,3));
        p1.add(SudokuCells[0]);
        p1.add(SudokuCells[1]);
        p1.add(SudokuCells[2]);
        p1.add(SudokuCells[9]);
        p1.add(SudokuCells[10]);
        p1.add(SudokuCells[11]);
        p1.add(SudokuCells[18]);
        p1.add(SudokuCells[19]);
        p1.add(SudokuCells[20]);
        p1.setBorder(b);
        add(p1);

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(3,3));
        p2.add(SudokuCells[3]);
        p2.add(SudokuCells[4]);
        p2.add(SudokuCells[5]);
        p2.add(SudokuCells[12]);
        p2.add(SudokuCells[13]);
        p2.add(SudokuCells[14]);
        p2.add(SudokuCells[21]);
        p2.add(SudokuCells[22]);
        p2.add(SudokuCells[23]);
        p2.setBorder(b);
        add(p2);

        JPanel p3 = new JPanel();
        p3.setLayout(new GridLayout(3,3));
        p3.add(SudokuCells[6]);
        p3.add(SudokuCells[7]);
        p3.add(SudokuCells[8]);
        p3.add(SudokuCells[15]);
        p3.add(SudokuCells[16]);
        p3.add(SudokuCells[17]);
        p3.add(SudokuCells[24]);
        p3.add(SudokuCells[25]);
        p3.add(SudokuCells[26]);
        p3.setBorder(b);
        add(p3);

        JPanel p4 = new JPanel();
        p4.setLayout(new GridLayout(3,3));
        p4.add(SudokuCells[27]);
        p4.add(SudokuCells[28]);
        p4.add(SudokuCells[29]);
        p4.add(SudokuCells[36]);
        p4.add(SudokuCells[37]);
        p4.add(SudokuCells[38]);
        p4.add(SudokuCells[45]);
        p4.add(SudokuCells[46]);
        p4.add(SudokuCells[47]);
        p4.setBorder(b);
        add(p4);

        JPanel p5 = new JPanel();
        p5.setLayout(new GridLayout(3,3));
        p5.add(SudokuCells[30]);
        p5.add(SudokuCells[31]);
        p5.add(SudokuCells[32]);
        p5.add(SudokuCells[39]);
        p5.add(SudokuCells[40]);
        p5.add(SudokuCells[41]);
        p5.add(SudokuCells[48]);
        p5.add(SudokuCells[49]);
        p5.add(SudokuCells[50]);
        p5.setBorder(b);
        add(p5);

        JPanel p6 = new JPanel();
        p6.setLayout(new GridLayout(3,3));
        p6.add(SudokuCells[33]);
        p6.add(SudokuCells[34]);
        p6.add(SudokuCells[35]);
        p6.add(SudokuCells[42]);
        p6.add(SudokuCells[43]);
        p6.add(SudokuCells[44]);
        p6.add(SudokuCells[51]);
        p6.add(SudokuCells[52]);
        p6.add(SudokuCells[53]);
        p6.setBorder(b);
        add(p6);

        JPanel p7 = new JPanel();
        p7.setLayout(new GridLayout(3,3));
        p7.add(SudokuCells[54]);
        p7.add(SudokuCells[55]);
        p7.add(SudokuCells[56]);
        p7.add(SudokuCells[63]);
        p7.add(SudokuCells[64]);
        p7.add(SudokuCells[65]);
        p7.add(SudokuCells[72]);
        p7.add(SudokuCells[73]);
        p7.add(SudokuCells[74]);
        p7.setBorder(b);
        add(p7);

        JPanel p8 = new JPanel();
        p8.setLayout(new GridLayout(3,3));
        p8.add(SudokuCells[57]);
        p8.add(SudokuCells[58]);
        p8.add(SudokuCells[59]);
        p8.add(SudokuCells[66]);
        p8.add(SudokuCells[67]);
        p8.add(SudokuCells[68]);
        p8.add(SudokuCells[75]);
        p8.add(SudokuCells[76]);
        p8.add(SudokuCells[77]);
        p8.setBorder(b);
        add(p8);

        JPanel p9 = new JPanel();
        p9.setLayout(new GridLayout(3,3));
        p9.add(SudokuCells[60]);
        p9.add(SudokuCells[61]);
        p9.add(SudokuCells[62]);
        p9.add(SudokuCells[69]);
        p9.add(SudokuCells[70]);
        p9.add(SudokuCells[71]);
        p9.add(SudokuCells[78]);
        p9.add(SudokuCells[79]);
        p9.add(SudokuCells[80]);
        p9.setBorder(b);
        add(p9);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(540, 540));
        setTitle("Group Assignment Sudoku");
        pack();

    }

    private void keytoQuit(){
        for(int i =0; i <81; i++){
            SudokuCells[i].setText(Integer.toString(solved1DArrayforComparison[i]));
            SudokuCells[i].setBackground(quitColor);
            SudokuCells[i].setEditable(false);
            SudokuCells[i].setHorizontalAlignment(JTextField.CENTER);
        }
    }

    private void checkAnswer() {
        for (int i = 0; i < 81; i++) {
            if (SudokuCells[i].isEditable() == true) {
                if (SudokuCells[i].getText().equals(" ") == false) {
                    if (SudokuCells[i].getText().equals(Integer.toString(solved1DArrayforComparison[i]))) {
                        SudokuCells[i].setBackground(correctColor);
                        SudokuCells[i].setEditable(false);
                    }
                    else if (SudokuCells[i].getText().equals("")) {
                    }
                    else if (!SudokuCells[i].getText().equals(Integer.toString(solved1DArrayforComparison[i]))) {
                        SudokuCells[i].setBackground(recheckColor);
                    }
                }
            }
        }
        for( int counter =0 ; counter < 81; counter++) {
            if (SudokuCells[counter].isEditable()==false){
                comparisonArray[counter] = true;
            }
            else if(SudokuCells[counter].isEditable()==true){
                comparisonArray[counter] = false;
            }
        }
        if(Arrays.equals(comparisonArray,arrayTruth)){
            for (int counter2=0; counter2 <81; counter2 ++){
                SudokuCells[counter2].setBackground(winningColor);
            }
            System.out.println("You've Won. Cells being shaded to purple is proof of that :)");
            long stopTime = System.nanoTime();
            long elaspedTime = stopTime - startTime;
            System.out.println("Time took to solve was " + (elaspedTime) + " nanoseconds");
        }
    }

    private void requestFocusForIndex(int index) {
        if (index < 0 || index > (SudokuCells.length - 1)) {
            return;
        }
       /* while( SudokuCells[index].isEditable() == false){
        currentIndex += index;
        SudokuCells[index].requestFocus();
        System.out.println("Below is output for where the arrow keys are");
        System.out.println(currentIndex);
        }*/
        SudokuCells[index].requestFocus();
        currentIndex = index;
        System.out.println("Below is output for where the arrow keys are. 0 being the first top left corner and 80 being bottom right");
        System.out.println(currentIndex);
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                SudokuGrid trial = new SudokuGrid();
                trial.setVisible(true);
                Reading2DArrayFromFile SampleProblem = new Reading2DArrayFromFile();
                int[][] GRID_TO_SOLVE = SampleProblem.read2DArrayFiles(filechosen);
                SudokuSolver solution = new SudokuSolver(GRID_TO_SOLVE);
                System.out.println("You are playing the file " + filechosen);
                System.out.println("Press C to check answers. Q to quit");
                solution.solve();
                System.out.println("Below is the solution for the grid");
                solution.display();
            }
        });
    }
}