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
public class N5 implements IRunner {
    public String getQuestionID() {
        return "N5";
    }
    public IRunner Run(String args) {
    	try (Scanner scanner = new Scanner(new FileReader(args))) {
            int trials = scanner.nextInt();
            while (trials > 0) {
            	System.out.println(validLegs( scanner.nextInt(),scanner.nextInt(),scanner.nextInt()) ? "YES" : "NO");
                trials--;
            }
        } catch (InputMismatchException e) {
            System.out.println("Error with input");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
        }
        return this;
    }
    private boolean validLegs(int cats, int dogs, int legs) {
    	for(int i = 0; i < dogs; i++) {
    		int catsOnBack = 0;
    		while(cats >= 0) {
    			if(legs == 4 * (cats-- + dogs)) return true;
    			catsOnBack++;
    			if(catsOnBack == 2) break;
    		}
    	}
    	return false;
    }
}
