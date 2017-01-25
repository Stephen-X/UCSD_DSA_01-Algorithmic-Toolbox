import java.util.*;

public class DotProduct {
    // greedy safe move: SUM(max(a[])* min(b[]))
    private static long minDotProduct(int[] a, int[] b) {
        long result = 0;
        int n = a.length;  // a, b have the same length
        int[] a2 = a;
        int[] b2 = b;
        Arrays.sort(a2);
        Arrays.sort(b2);

        for (int i = 0; i < n; i++) {
            result += (long) a[n-i-1] * b[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        System.out.println(minDotProduct(a, b));
    }
}

