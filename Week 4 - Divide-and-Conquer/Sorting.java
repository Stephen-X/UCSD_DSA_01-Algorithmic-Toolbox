import java.io.*;
import java.util.*;

public class Sorting {
    private static Random random = new Random();

    // Bentley-McIlroy 3-way partitioning scheme
    private static void quick3(int[] a, int l, int r) {
        if (l >= r) return;  // a[] only has 1 element
        int i = l, j = r + 1;
        int p = l, q = r + 1;
        // pick the pivot randomly and put it in a[l]
        int m = random.nextInt(r - l + 1) + l;
        swap(a, m, l);
        int x = a[l];

        while (true) {
            // move i from left to find an element that's not less
            while (a[++i] < x)
                if (i == r) break;
            // move j from right to find an element that's not greater
            while (a[--j] > x)
                if (j == l) break;

            // stop if pointers have crossed
            if (i == j && a[i] == x) swap(a, ++p, i);
            if (i >= j) break;

            // exchange the >= x from the left and the <= x from the right
            swap(a, i, j);
            // if left element equal, exchange to left end
            if (a[i] == x) swap(a, ++p, i);
            // if right element equal, exchange to right end
            if (a[j] == x) swap(a, --q, j);
        }

        // swap equals from both ends to center after partition
        i = j + 1;
        for (int k = l; k <= p; k++) swap(a, k, j--);
        for (int k = r; k >= q; k--) swap(a, k, i++);

        quick3(a, l, j);  // sort left part (< x)
        quick3(a, i, r);  // sort right part (> x)
    }

    private static void quick2(int[] a, int l, int r) {
        if (l >= r) return;
        // pick the pivot randomly and put it in a[l]
        int m = random.nextInt(r - l + 1) + l;
        swap(a, m, l);
        int x = a[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] <= x) {
                j++;
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        int t = a[l];
        a[l] = a[j];
        a[j] = t;
        
        quick2(a, l, j - 1);
        quick2(a, j + 1, r);
    }

    //----------------------------------------------------
    // swap a[i] with a[j]
    private static void swap(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // print array to standard output
    private static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
    //----------------------------------------------------

    // test cases generator
    private static void testGen() {
        while (true) {
            // 1 <= n <= 10^5
            int n = random.nextInt((int)Math.pow(10, 5)) + 1;
            int[] a = new int[n];
            int[] b = new int[n];
            for (int i = 0; i < n; i++) {
                // 1 <= a[i] <= 10^9
                a[i] = random.nextInt((int)Math.pow(10, 9)) + 1;
                b[i] = a[i];
            }

            quick3(a, 0, n - 1);
            quick2(b, 0, n - 1);
            System.out.print(" n = " + n + ": ");
            if (Arrays.equals(a, b)) {
                System.out.println("PASSED");
            } else {
                System.out.println("FAILED");
                System.out.print("quick3[] = ");
                printArray(a);
                System.out.print("quick2[] = ");
                printArray(b);
                return;
            }
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
        quick3(a, 0, n - 1);
        printArray(a);
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

