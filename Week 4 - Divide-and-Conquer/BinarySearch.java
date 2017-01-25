import java.io.*;
import java.util.*;

public class BinarySearch {

    static int binarySearch(int[] a, int low, int high, int x) {
        if (low > high) return -1;  // x not found
        int mid = low + (high - low) / 2;
        if (x == a[mid]) return mid;
        else if (x < a[mid]) return binarySearch(a, low, mid - 1, x);
        else return binarySearch(a, mid + 1, high, x);
    }

    static int linearSearch(int[] a, int x) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) return i;
        }
        return -1;
    }

    static void testGen() {
        Random r = new Random();

        while (true) {
            // 1 <= n, k <= 10^5
            int n = r.nextInt((int)Math.pow(10, 2)) + 1;
            int k = r.nextInt((int)Math.pow(10, 2)) + 1;

            int[] a = new int[n];
            int[] b = new int[k];

            // 1 <= ai, bj <= 10^9
            for (int i = 1; i < n; i++) {
                a[i] = r.nextInt((int)Math.pow(10, 5)) + 1;
            }  // this may contain some duplicates
            Arrays.sort(a);
            System.out.println("a[" + n + "] = " + Arrays.toString(a));
            for (int j = 0; j < k; j++) {
                b[j] = r.nextInt((int)Math.pow(10, 5)) + 1;
            }
            System.out.println("b[" + k + "] = " + Arrays.toString(b));

            for (int i = 0; i < k; i++) {
                if (binarySearch(a, 0, a.length-1, b[i]) != linearSearch(a, b[i])) {
                    System.out.println("ERROR @ i = " + i);
                    System.exit(-1);
                }
            }
            System.out.println("PASS\n");
        }
    }

    public static void main(String[] args) {
        ///*
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
          b[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            System.out.print(binarySearch(a, 0, a.length-1, b[i]) + " ");
        }
        //*/
       
        //testGen();
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
