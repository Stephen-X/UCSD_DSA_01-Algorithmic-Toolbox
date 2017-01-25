import java.util.*;

public class FractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double[][] valuePerWeight = new double[values.length][2];
        for (int i = 0; i < values.length; i++) {
            valuePerWeight[i][0] = i;
            valuePerWeight[i][1] = (double) values[i] / weights[i];
        }

        // sorting valuePerWeight[][1] in ascending order
        Arrays.sort(valuePerWeight, new Comparator<double[]>() {
            @Override
            public int compare(double[] a, double[] b) {
                return Double.compare(a[1], b[1]);
            }
        });

        double totalValue = 0;
        int minWeight, j;
        /* minWeight: minimum value between individual item weight and knapsack capacity
                   j: item index indicated by valuePerWeight[][0]
        */
        for (int i = values.length - 1; i >= 0; i--) {
            if (capacity == 0) break;  // knapsack is full
            j = (int) valuePerWeight[i][0];
            minWeight = Math.min(capacity, weights[j]);  // weight to be filled next
            totalValue += valuePerWeight[i][1] * minWeight;
            capacity -= minWeight;
        }
        return totalValue;

    }

    // generating test cases
    private static void testGen() {
        Random r = new Random();
        while (true) {
            int n = r.nextInt(1000) + 1;  // 1 <= n <= 10^3
            int capacity = r.nextInt(2*(int)Math.pow(10, 6) + 1);  // 0 <= W <= 2*10^6
            int[] values = new int[n];
            int[] weights = new int[n];
            for (int i = 0; i < n; i++) {
                values[i] = r.nextInt(2*(int)Math.pow(10, 6) + 1);   // 0 <= V(i) <= 2*10^6
                weights[i] = r.nextInt(2*(int)Math.pow(10, 6)) + 1;  // 0 < W(i) <= 2*10^6
            }
            System.out.println(getOptimalValue(capacity, values, weights));
        }
    }

    public static void main(String args[]) {
        ///*
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();  // number of items
        int capacity = scanner.nextInt();  // capacity of the knapsack
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
        //*/
       
       //testGen();
    }
} 
