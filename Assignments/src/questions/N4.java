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
public class N4 implements IRunner {
    /**
     * Gets the question ID
     * @return Question ID
     */   
    public String getQuestionID() {
        return "N4";
    }
    public IRunner Run(String args) {
        // start disposable scanner reading file input
        try (Scanner scanner = new Scanner(new FileReader(args))) {
            // set number of trials
            int trials = scanner.nextInt();
            // loop trials
            while (trials > 0) {
                // set students in class and how many friends ben has
            	int students = scanner.nextInt(),
                    bensFriendCount = scanner.nextInt();
                // set array for students and friends
            	boolean[] studentArray = new boolean[students],
                          friendArray = new boolean[students];
                            // loop through students array, if 0 set to true (they took notes), else false (they were asleep)
                for(int i = 0; i < students; i++) studentArray[i] = scanner.nextInt() == 0;
                            // loop through bens friends array set to true where bens friend is placed
                for(int i = 0; i < bensFriendCount; i++) friendArray[scanner.nextInt() - 1] = true;
                // flag for whether or not they can get notes
                boolean canGetNotes = false;
                // loop through both arrays, if they're awake (true) and are a friend (true) then he can get notes!
            	for(int i=0; i < students; i++) 
            		if(friendArray[i] && studentArray[i]) {
            			canGetNotes = true;
            			break;
                }
                // print output
            	System.out.println(canGetNotes ? "YES" : "NO");
            	
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
}
