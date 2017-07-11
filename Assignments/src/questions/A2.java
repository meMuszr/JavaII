package questions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class A2 implements IRunner {
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
    public String getQuestionID() {
        return "A2";
    }

    public IRunner Run(String args) {
        try (Scanner scanner = new Scanner(new FileReader(args))) {
            scanner.nextInt();
            while (scanner.hasNextInt()) {
                boolean shareVertical = true,
                        shareHorizontal = true;
                Point currentPoint,
                        nextPoint = null;
                int points = scanner.nextInt();
                for (int i = 0; i < (points - 1); i++) {
                    if (!shareHorizontal && !shareVertical) {
                        for(int x = 0; x < points - i; x++) scanner.nextLine();
                        break;
                    }
                    if (nextPoint == null) currentPoint = new Point(scanner.nextInt(), scanner.nextInt());
                    else {
                        currentPoint = nextPoint;
                        scanner.nextLine();
                    }
                    nextPoint = new Point(scanner.nextInt(), scanner.nextInt());
                    if (shareVertical) shareVertical &= currentPoint.shareVerticalLine(nextPoint);
                    if (shareHorizontal) shareHorizontal &= currentPoint.shareHorizontalLine(nextPoint);

                }
                if (shareHorizontal || shareVertical) System.out.println("YES");
                else System.out.println("NO");

            }
        } catch (InputMismatchException e) {
            System.out.println("Error with input");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
        }
        return this;
    }
}
