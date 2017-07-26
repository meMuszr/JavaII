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
public class N6 implements IRunner {
    /**
     * Gets the question ID
     * @return Question ID
     */    
    public String getQuestionID() {
        return "N6";
    }
    // initalize max score, row length, and col length
    private int maxScore = Integer.MIN_VALUE,
                rowLength,
                colLength;
    // initalize matrix
    private int[][] matrix;
    public IRunner Run(String args) {
        // start disposable scanner reading file input
        try (Scanner scanner = new Scanner(new FileReader(args))) {
            // set values for width, height and samples (bonuses)
            int width = rowLength = scanner.nextInt(),
                height = colLength = scanner.nextInt(),
                samples = scanner.nextInt();
            // set matrix to height and width
            matrix = new int[height][width];
            // loop through samples and place at matrix location
            while(samples > 0) {
                width = scanner.nextInt();
                height=scanner.nextInt();
                matrix[height][width] = scanner.nextInt();
                samples--;
            }
            // samples for X (store as score [-100])
            // do same as above
            samples = scanner.nextInt();
            while(samples > 0) {
                width = scanner.nextInt();
                height = scanner.nextInt();
                matrix[height][width] = -100;
                samples--;
            }
            // call find Optimal starting at 0, 0 and value for route initally
            // the negative sum of length and height - 2 (each step is -1 value)
            FindOptimal(0,0,rowLength,colLength,-(rowLength+colLength-2));
            // print maxScore
            System.out.println(maxScore);
        } catch (InputMismatchException e) {
            System.out.println("Error with input");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
        }

        return this;
    }
    void FindOptimal(int r,int c,int row,int col,int value)
    {
        // did we get to end corner?
        if(r==row-1 && c==col-1)
        {
            // get value and add to current spot
            int total = value + matrix[c][r];
            // set new maxScore if less than total
            if(maxScore < total) maxScore = total;
            // exit
            return;
        }
        // add value to current spot's worth
        value=value+ matrix[c][r];
        // if we have room in our matrix, try both down and right avenues
        if(c<(col-1))FindOptimal( r,c+1,row, col,value);
        if(r<(row-1) )FindOptimal(r+1,c,row, col,value);
    }
}
