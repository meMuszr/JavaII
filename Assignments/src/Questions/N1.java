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
public class N1 implements IRunner {
    public String getQuestionID() {
        return "N1";
    }

    public IRunner Run(String args) {
        try (Scanner scanner = new Scanner(new FileReader(args))) {
            scanner.nextInt();
            while (scanner.hasNextInt()) {
                int currentValue = scanner.nextInt();
                int counter = 0;
                for(int i = 1; i <= Math.sqrt(currentValue); i++)
                    if(currentValue%i == 0) counter++;
                System.out.println(counter);
            }
        } catch (InputMismatchException e) {
            System.out.println("Error with input");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
        }

        return this;
    }
}
