import java.util.*;
import java.io.*;

public class MaxPairwiseProduct {
    static long getMaxPairwiseProduct(int[] numbers) {
        long result = 0;
        int n = numbers.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if ((long) numbers[i] * numbers[j] > result) {
                    result = (long) numbers[i] * numbers[j];
                }
            }
        }
        return result;
    }

    // a faster solution
    static long getMaxPairwiseProductFast(int[] numbers) {
        int n = numbers.length;
        int max_index1 = 0;
        for (int i = 1; i < n; i++) {
            if (numbers[i] > numbers[max_index1])
                max_index1 = i;
        }
        int max_index2;
        if (max_index1 == 0) max_index2 = 1;
        else max_index2 = 0;
        for (int i = 1; i < n; i++) {
            if ((numbers[i] > numbers[max_index2]) && (i != max_index1))
                max_index2 = i;
        }
        //System.out.println("Fast - index: " + max_index1 + " " 
        //                   + max_index2); // debug info
        return (long) numbers[max_index1] * numbers[max_index2];
        
    }

    // stress test data generator
    static void stressTest() {
        Random r = new Random();
        int n = r.nextInt(1000) + 2;
        System.out.println("n = " + n);
        int[] numbers = new int[n];
        while (true) {
            for (int i = 0; i < n; i++) {
                numbers[i] = r.nextInt(100000);
                System.out.print(numbers[i] + " ");
            }
            System.out.println();
            long result1 = getMaxPairwiseProduct(numbers);
            long result2 = getMaxPairwiseProductFast(numbers);
            if (result1 == result2) 
                System.out.println("OK");
            else {
                System.out.println("ERROR - different results: "
                                   + result1 + " , " + result2);
                break;
            }
        }
    }

    public static void main(String[] args) {
        //stressTest();  // for debugging
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        System.out.println(getMaxPairwiseProductFast(numbers));
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}