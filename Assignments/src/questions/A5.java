package questions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * Mustafa Abdul-Kader
 * CMPSC-203-01
 * Dr Mehta
 */
public class A5 implements IRunner {
    private static class Plate implements Comparable<Plate> {
        private int Sum;
        private int Gain;
        private int Loss;

        public Plate(int gain, int loss) {
            Gain = gain;
            Loss -= loss;
            Sum = gain + Math.abs(loss);
        }

        public int getSum() {
            return Sum;
        }

        public int getGain() {
            return Gain;
        }

        public int getLoss() {
            return Loss;
        }

        public int compareTo(Plate o) {
            if (this.Sum == o.getSum()) return 0;
            return this.Sum < o.getSum() ? -1 : 1;
        }
    }

    public String getQuestionID() {
        return "A5";
    }


    public IRunner Run(String args) {
        try (Scanner scanner = new Scanner(new FileReader(args))) {
            int plates = scanner.nextInt(), washed = scanner.nextInt();
            List<Plate> plateArr = new ArrayList<>();
            for (int i = 0; i < plates; i++)
                plateArr.add(new Plate(scanner.nextInt(), scanner.nextInt()));
            Collections.sort(plateArr, Collections.reverseOrder());
            int maxCost = 0;
            for (Plate p : plateArr) {
                maxCost += (washed != 0) ? p.getGain() : p.getLoss();
                if (washed > 0) washed--;
            }
            System.out.println(maxCost);


        } catch (InputMismatchException e) {
            System.out.println("Error with input");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
        }
        return this;
    }
}
