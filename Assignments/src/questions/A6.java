package questions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author      Mustafa Abdul-Kader <abdulkader.m01@mymail.sxu.edu>
 * @version     1.0
 * @since       1.0
 * https://github.com/meMuszr
 */
public class A6 implements IRunner {
    /**
     * Gets the question ID
     * @return Question ID
     */
    public String getQuestionID() {
        return "A6";
    }
    // matrix for reservoir 
    private char[][] Reservoir;

    public IRunner Run(String args) {
        // start disposable scanner reading file input
        try (Scanner scanner = new Scanner(new FileReader(args))) {
            // get trials
            int trials = scanner.nextInt();
            while (trials > 0) {
                // get height 
                int height = scanner.nextInt();
                // skip to token on new line
                scanner.nextLine();
                // initalize reservoir
                Reservoir = new char[height][];
                // parse each line into a character array
                for (int i = 0; i < height; i++) Reservoir[i] = scanner.nextLine().toCharArray();
                // call checkReservoir if true print yes else no
                System.out.println(checkReservoir() ? "YES" : "NO");
                // decrement trials 
                trials--;
            }
            // catch exceptions
        } catch (InputMismatchException e) {
            System.out.println("Error with input");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
        }
        return this;
    }

    private boolean checkReservoir() {
        // validation flag
        boolean valid = true;
        for (int i = 0; i < Reservoir.length; i++)
            for (int j = 0; j < Reservoir[i].length; j++)
            //loop through array, if perimeter, check if it's b, otherwise, call checkInside
                valid &= (i != Reservoir.length - 1 && j != 0 && j != Reservoir.length - 1) ? checkInside(i, j) : Reservoir[i][j] == 'B';
        return valid;
    }

    private boolean checkInside(int y, int x) {
        // find out what's below ~
        switch (Reservoir[y++][x]) {
            case 'B':
                return Reservoir[y][x] == 'B';
            case 'W':
                return y == Reservoir.length - 1 ? Reservoir[y][x] == 'B' : Reservoir[y][x] == 'W' || Reservoir[y][x] == 'B';
            case 'A':
                return y == Reservoir.length - 1 ? Reservoir[y][x] == 'B' : Reservoir[y - 1][x + 1] != 'W' && Reservoir[y - 1][x - 1] != 'W';
            default:
                return false;
        }
    }
}
