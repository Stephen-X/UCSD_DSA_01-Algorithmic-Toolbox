/*
   Key to the problem: must take as many consecutive integers starting from 1
     as possible.
 */

import java.util.*;

public class DifferentSummands {
    private static List<Integer> optimalSummands(int n) {
        List<Integer> summands = new ArrayList<Integer>();
        if (n == 2) {
            summands.add(2);
            return summands;
        }
        summands.add(1);
        int sum = 1, index = 0;  // adding 1 to the sequence
        while (sum < n) {
            if (n - sum <= 2 * (summands.get(index) + 1)) {
            // the remaining number is already in the sequence (n-(sum+newNum)<=newNum)
                summands.add(n - sum);
                sum = n;
            } else {
                summands.add(summands.get(index) + 1);
                sum += summands.get(++index);
            }
        }
        return summands;
    }

    // generating test cases
    private static void testGen() {
        for (int i = 1; i <= (int)Math.pow(10, 9); i++) {
            List<Integer> summands = optimalSummands(i);
            System.out.print(i + ": " + summands.size() + " - ");
            for (Integer summand : summands) {
                System.out.print(summand + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ///*
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
        //*/

        //testGen();
    }
}

