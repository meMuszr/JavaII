package Questions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

/**
 * Mustafa Abdul-Kader
 * CMPSC-203-01
 * Dr Mehta
 * Wrong output
 */
public class A8 implements IRunner {
    public String getQuestionID() {
        return "A8";
    }

    public IRunner Run(String args) {
        try (Scanner scanner = new Scanner(new FileReader(args))) {
            int trials = scanner.nextInt();
            while (trials != 0) {
                int stackSize = scanner.nextInt(),
                        maxNum = scanner.nextInt();
                Stack<Integer> numbers = new Stack<>();
                for (int j = 0; j < stackSize; j++) numbers.push(scanner.nextInt());
                int maxGcd = numbers.pop();
                while (numbers.size() > 0) {
                    int a = maxGcd;
                    int b = numbers.pop();
                    maxGcd = (a < b) ? GCD(b, a) : GCD(a, b);
                }
                boolean included = false;
                for (; maxNum > 0; maxNum--)
                    if (maxNum % maxGcd == 0) {
                        included = true;
                        break;
                    }
                System.out.println(included ? maxNum : 0);
                trials--;
            }
        } catch (InputMismatchException e) {
            System.out.println("Error with input");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
        }

        return this;
    }

    private int GCD(int a, int b) {
        return b == 0 ? a : GCD(b, a % b);
    }
}
