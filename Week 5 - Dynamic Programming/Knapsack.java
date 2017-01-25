import java.util.*;

public class Knapsack {
    // optimal(w, i) = max{optimal(w - w[i], i - 1) + w[i], optimal(w, i - 1)}
    static int optimalWeight(int W, int[] w) {
        int[] optimal = new int[W+1];
        int weightTemp;
        for (int i = 1; i <= w.length; i++)
            for (int j = W; j >= 1; j--) {
                if (w[i-1] <= j) {
                    weightTemp = optimal[j - w[i-1]] + w[i-1];
                    if (weightTemp > optimal[j])
                        optimal[j] = weightTemp;
                }
            }
        return optimal[W];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}

