import java.util.*;

public class FibonacciLastDigit {
    private static int getFibonacciLastDigit(int n) {
        int[] fib = new int[n + 1];
        fib[0] = 0;
        if (n >= 1) {
          fib[1] = 1;
        }
        for (int i = 2; i <= n; i++) {
          fib[i] = (fib[i - 1] + fib[i - 2]) % 10;
        }
        return fib[n];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = getFibonacciLastDigit(n);
        System.out.println(c);
    }
}

