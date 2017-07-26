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
public class A1 implements IRunner {
    // enum used for return types
    private enum Primes {
        NOT,
        DEFAULT,
        DOUBLE,
        TRIPLE
    }
    /**
     * Stack of integers to store split numbers
     */
    private Stack<Integer> stack;

    /**
     * Gets the question ID
     * @return Question ID
     */
    public String getQuestionID() {
        return "A1";
    }

    /**
     * Runs program A1
     * @param args file input location 
     */
    public IRunner Run(String args) {
        // start disposable scanner reading file input
        try (Scanner scanner = new Scanner(new FileReader(args))) {
            // read first number and do nothing. start token on next int
            scanner.nextInt();
            // loop to continue while we have an integer
            while (scanner.hasNextInt()) {
                // scan an integer and send it through getPrimeType method
                switch (getPrimeType(scanner.nextInt())) {
                    case NOT: System.out.println("not prime");
                        break;
                    case DEFAULT: System.out.println("prime");
                        break;
                    case DOUBLE: System.out.println("double prime");
                        break;
                    case TRIPLE: System.out.println("triple prime");
                        break;
                }
            }
            // catch exceptions
        } catch (InputMismatchException e) {
            System.out.println("Error with input");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
        }
        return this;
    }


    private Primes getPrimeType(int n) {
        // ternary operator to call different methods if conditions pass.
        return isPrime(n) ? ((isDoublePrime(n)) ? ((isTriplePrime()) ? Primes.TRIPLE : Primes.DOUBLE) : Primes.DEFAULT) : Primes.NOT;
    }

    private boolean isTriplePrime() {
        // use initial stack ref as it's the last method using it
        int sum = 0;
        // get sum of stack
        while (stack.size() > 0) sum += stack.pop();
        // check if sum of digits is prime
        return isPrime(sum);
    }

    private boolean isDoublePrime(int n) {
        // creats a stack instance of our digits and stores in stack variable
        stack = convertNumberToStack(n);
        // clone in case we have to use stack for isTriplePrime method
        Stack<Integer> st = (Stack<Integer>) stack.clone();
        // set validation variable
        Boolean areDigitsPrime = true;
        // loop through stack checking if individual digits are prime
        while (st.size() > 0) areDigitsPrime &= isPrime(st.pop());
        return areDigitsPrime;
    }

    private boolean isPrime(int n) {
        // simple prime algorithm implementation
        if (n <= 1) return false;
        else if (n <= 3) return true;
        else if (n % 2 == 0 || n % 3 == 0) return false;
        int i = 5;
        while (i * i < n) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
            i = i + 6;
        }
        return true;
    }

    private Stack<Integer> convertNumberToStack(int n) {
        Stack<Integer> st = new Stack<>();
        // pushes digits to stack
        while (n > 0) {
            st.push(n % 10);
            n /= 10;
        }
        // returns stack
        return st;
    }
}