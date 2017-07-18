package questions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Mustafa Abdul-Kader
 * CMPSC-203-01
 * Dr Mehta
 */
public class N6 implements IRunner {
    public String getQuestionID() {
        return "N6";
    }
    private int maxScore = Integer.MIN_VALUE,
                rowLength,
                colLength;
    private int[][] matrix;
    public IRunner Run(String args) {
        try (Scanner scanner = new Scanner(new FileReader(args))) {
            int width = rowLength = scanner.nextInt(),
                height = colLength = scanner.nextInt(),
                samples = scanner.nextInt();
            matrix = new int[height][width];
            while(samples > 0) {
                width = scanner.nextInt();
                height=scanner.nextInt();
                matrix[height][width] = scanner.nextInt();
                samples--;
            }
            samples = scanner.nextInt();
            while(samples > 0) {
                width = scanner.nextInt();
                height = scanner.nextInt();
                matrix[height][width] = -100;
                samples--;
            }
            FindOptimal(0,0,rowLength,colLength,-(rowLength+colLength-2));
            System.out.println(maxScore);
        } catch (InputMismatchException e) {
            System.out.println("Error with input");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
        }

        return this;
    }
    void Run(int row,int col){


    }
    void FindOptimal(int r,int c,int row,int col,int value)
    {
        if(r==row-1 && c==col-1)
        {
            int total = value + matrix[c][r];
            if(maxScore < total) maxScore = total;
            return;
        }
        value=value+ matrix[c][r];
        if(c<(col-1))FindOptimal( r,c+1,row, col,value);
        if(r<(row-1) )FindOptimal(r+1,c,row, col,value);
    }
}
