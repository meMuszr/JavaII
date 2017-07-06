package Questions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class A1 implements IRunner {
    private enum Primes {
        NOT,
        DEFAULT,
        DOUBLE,
        TRIPLE
    }

    private int currentValue;
    private Stack<Integer> stack;

    public String getQuestionID() {
        return "A1";
    }

    public IRunner Run(String args) {
        try (Scanner scanner = new Scanner(new FileReader(args))) {
            scanner.nextInt();
            while (scanner.hasNextInt()) {
                currentValue = scanner.nextInt();
                switch (getPrimeType(currentValue)) {
                    case NOT:
                        System.out.println("not prime");
                        break;
                    case DEFAULT:
                        System.out.println("prime");
                        break;
                    case DOUBLE:
                        System.out.println("double prime");
                        break;
                    case TRIPLE:
                        System.out.println("triple prime");
                        break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error with input");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
        }
        return this;
    }

    private Primes getPrimeType(int n) {
        return isPrime(n) ? ((isDoublePrime(n)) ? ((isTriplePrime()) ? Primes.TRIPLE : Primes.DOUBLE) : Primes.DEFAULT) : Primes.NOT;
    }

    private boolean isTriplePrime() {
        //use initial stack ref as it's the last method using it
        int sum = 0;
        while (stack.size() > 0) sum += stack.pop();
        return isPrime(sum);
    }

    private boolean isDoublePrime(int n) {
        stack = convertNumberToStack(n);
        //clone in case we have to use stack for isTriplePrime method
        Stack<Integer> st = (Stack<Integer>) stack.clone();
        Boolean areDigitsPrime = true;
        while (st.size() > 0) areDigitsPrime &= isPrime(st.pop());
        return areDigitsPrime;
    }

    private boolean isPrime(int n) {
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
        int i = 0;
        while (n > 0) {
            st.push(n % 10);
            n /= 10;
        }
        return st;
    }
}