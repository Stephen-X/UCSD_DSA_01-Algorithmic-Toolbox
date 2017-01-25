import java.util.*;
import java.io.*;

public class MajorityElement {
    // Boyer–Moore Majority Vote Algorithm
    // Hint for explanation: majority element appears more than n/2 times.
    // https://en.wikipedia.org/wiki/Boyer–Moore_majority_vote_algorithm
    // http://www.cs.utexas.edu/~moore/best-ideas/mjrty/index.html
    private static int getMajorityElement(int[] a) {
        /*
           candidate : the candidate majority element
               count : counter for the candidate element
         */
        int candidate = 0, count = 0;
        for (int i = 0; i < a.length; i++) {
            if (count == 0) {
                candidate = a[i];
                count++;
            } else if (candidate == a[i]) {
                count++;
            } else {
                count--;
            }
        }

        // validate whether candidate is indeed the majority element
        //  or there's no majority element in the array
        count = 0;
        for (int i = 0; i < a.length; i++) {
            if (candidate == a[i]) {
                count++;
            }
        }
        if (count > a.length / 2) return candidate;
        else return -1;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        if (getMajorityElement(a) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
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

