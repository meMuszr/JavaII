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
public class N4 implements IRunner {
    public String getQuestionID() {
        return "N4";
    }
    public IRunner Run(String args) {
        try (Scanner scanner = new Scanner(new FileReader(args))) {
            int trials = scanner.nextInt();
            while (trials > 0) {
            	int students = scanner.nextInt(),
            		bensFriendCount = scanner.nextInt();
            	boolean[] studentArray = new boolean[students],
            			friendArray = new boolean[students];
            	for(int i =0; i < students; i++) 
            		studentArray[i] = scanner.nextInt() == 0;
            	for(int i = 0; i < bensFriendCount; i++)
            		friendArray[scanner.nextInt() - 1] = true;
            	boolean canGetNotes = false;
            	for(int i=0; i < students; i++) 
            		if(friendArray[i] == true && studentArray[i] == true) {
            			canGetNotes = true;
            			break;
            	}
            	System.out.println(canGetNotes ? "YES" : "NO");
            	
            	
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
