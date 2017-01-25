import java.util.*;

public class PrimitiveCalculator {
    private static ArrayList<Integer> optimal_sequence(int n) {
        ArrayList<Integer> minOp = new ArrayList<Integer>();
        // minOp saves the minimum operations needed for each n (index: n - 1)
        minOp.add(0);  // 0 operation is needed if n = 1
        // Recurrent formula: C(n) = min{C(n/2), C(n/3), C(n-1)} + 1,
        //  suppose C(n) is the minimum operators needed for number n.
        int r2, r3;
        for (int i = 2; i <= n; i++) {
            r2 = i % 2; r3 = i % 3;
            if (r2 == 0 && r3 == 0) {
                minOp.add(min(minOp.get(i/2-1), minOp.get(i/3-1), minOp.get(i-2)) + 1);
            } else if (r2 != 0 && r3 != 0) {
                minOp.add(minOp.get(i-2) + 1);
            } else if (r2 == 0) {
                minOp.add(min(minOp.get(i/2-1), minOp.get(i-2)) + 1);
            } else {  // r3 == 0
                minOp.add(min(minOp.get(i/3-1), minOp.get(i-2)) + 1);
            }
        }  // now minOp.get(n-1) stores the minimum operations needed for number n

        // reconstruct the optimal sequence
        ArrayList<Integer> optimal = new ArrayList<Integer>();
        int j = n, k;
        while (j > 0) {
            optimal.add(j);  // an intermediate number in the optimal sequence
            k = minOp.get(j-1) - 1;
            if      (j % 2 == 0 && k == minOp.get(j/2-1)) j /= 2;  // C(j) = C(j/2)+1
            else if (j % 3 == 0 && k == minOp.get(j/3-1)) j /= 3;  // C(j) = C(j/3)+1
            else                                          j--;     // C(j) = C(j-1)+1
        }
        Collections.reverse(optimal);  // the sequence should be in ascending order

        return optimal;
    }

    // find the minimum number of the two / three numbers
    private static int min(int a, int b) {
        if (a < b) return a;
        else return b;
    }

    private static int min(int a, int b, int c) {
        int d = min(a, b);
        if (d < c) return d;
        else return c;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}
