package questions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author      Mustafa Abdul-Kader <abdulkader.m01@mymail.sxu.edu>
 * @version     1.0
 * @since       1.0
 * https://github.com/meMuszr
 */
public class A8 implements IRunner {

    /**
     * Gets the question ID
     * @return Question ID
     */    
    public String getQuestionID() {
        return "A8";
    }

    public IRunner Run(String args) {
        // start disposable scanner reading file input
        try (Scanner scanner = new Scanner(new FileReader(args))) {
            // get trials
            int trials = scanner.nextInt();
            // loop through trials
            while (trials != 0) {
                // get stack size and maxNumber
                int stackSize = scanner.nextInt(),
                        maxNum = scanner.nextInt();
                Stack<Integer> numbers = new Stack<>();
                // populate stack
                for (int j = 0; j < stackSize; j++) numbers.push(scanner.nextInt());
                // set maxGcD to first number
                int maxGcd = numbers.pop();
                // loop while we have a stack
                while (numbers.size() > 0) {
                    // set a and b
                    int a = maxGcd,
                        b = numbers.pop();
                        //set maxGCD based on GCD method
                    maxGcd = (a < b) ? GCD(b, a) : GCD(a, b);
                }
                // loop maxNumber to 0 and find highest value that meets gcd requirement.
                for(; maxNum > 0; maxNum--) if (maxNum % maxGcd == 0) break;
                // print that number
                System.out.println(maxNum);
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
    // Euclidean algorithm implementation
    private int GCD(int a, int b) {
        return b == 0 ? a : GCD(b, a % b);
    }
}
