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

public class A2 implements IRunner {
    // class responsible for coordinate point
    public static class Point {
        private int x, y;

        public Point(int pointX, int pointY) {
            x = pointX;
            y = pointY;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public boolean shareVerticalLine(Point point) {
            return this.y == point.getY();
        }

        public boolean shareHorizontalLine(Point point) {
            return this.x == point.getX();
        }
    }
    
    /**
     * Gets the question ID
     * @return Question ID
     */
    public String getQuestionID() {
        return "A2";
    }

    public IRunner Run(String args) {
        // start disposable scanner reading file input
        try (Scanner scanner = new Scanner(new FileReader(args))) {
            // read first number and do nothing. start token on next int
            scanner.nextInt();
            // loop to continue while we have an integer
            while (scanner.hasNextInt()) {
                // validation variables
                boolean shareVertical = true,
                        shareHorizontal = true;
                // Point variables
                Point currentPoint,
                        nextPoint = null;
                // amount of points to parse
                int points = scanner.nextInt();
                // loop through points
                for (int i = 0; i < (points - 1); i++) {
                    // if they don't share a horizontal or vertical line, then finish reading input and break
                    if (!shareHorizontal && !shareVertical) {
                        for(int x = 0; x < points - i; x++) scanner.nextLine();
                        break;
                    }
                    // first condition if nextPoint is null, then we never set our currentPoint
                    if (nextPoint == null) currentPoint = new Point(scanner.nextInt(), scanner.nextInt());
                    else {
                        //the nextPoint would be our current point, read line of input
                        currentPoint = nextPoint;
                        scanner.nextLine();
                    }
                    // set new point
                    nextPoint = new Point(scanner.nextInt(), scanner.nextInt());
                    //if they share a vertical or horizontal, validate that it's still the case with new points
                    if (shareVertical) shareVertical &= currentPoint.shareVerticalLine(nextPoint);
                    if (shareHorizontal) shareHorizontal &= currentPoint.shareHorizontalLine(nextPoint);

                }
                // print yes or no 
                System.out.println(shareHorizontal || shareVertical ? "YES" : "NO");

            }
            // catch exceptions
        } catch (InputMismatchException e) {
            System.out.println("Error with input");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
        }
        return this;
    }
}
