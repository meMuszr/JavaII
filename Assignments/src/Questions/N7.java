package Questions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Mustafa Abdul-Kader
 * CMPSC-203-01
 * Dr Mehta
 * ${Date}
 */
public class N7 implements IRunner {
    public String getQuestionID() {
        return "N7";
    }

    private final Pattern pattern = Pattern.compile("\\b[a-zA-Z]{4}\\b");

    public IRunner Run(String args) {
        try (Scanner scanner = new Scanner(new FileReader(args))) {
            int trials = scanner.nextInt();
            scanner.nextLine();
            while (trials > 0) {
                System.out.println(pattern.matcher(scanner.nextLine().replaceAll("[\',.]", "")).find() ? "upset" : "happy");
                trials--;
            }
        } catch (InputMismatchException e) {
            System.out.println("Error with input");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
        }
        return this;
    }
}
