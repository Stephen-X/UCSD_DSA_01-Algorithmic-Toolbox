import java.util.*;

public class FibonacciHuge {
    private static long getFibonacciHuge(long n, int m) {
        ArrayList<Integer> modList = new ArrayList<Integer>();
        modList.add(0);  // Fib(0) = 0, 0 mod x = 0
        int a = 1, b = 1;
        modList.add(a);
        modList.add(b);  // Fib(1) = Fib(2) = 1, 1 mod x (x > 1) = 1
        while (true) {
            b = a % m + b % m;
            a = b - a % m;  // moving to the next pair of fibonacci numbers
            modList.add(b % m);
            // (a + b) % n = [(a % n) + (b % n)] % n (Wiki: Modulo operation)
            if (modList.get(modList.size() - 2) == 0
               && modList.get(modList.size() - 1) == 1) {
                break;
            }  // seems to work? ;p
        }
        // return (long)modList.size() - 2;  // return Pisano period
        return modList.get((int)(n % (modList.size() - 2)));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        int m = scanner.nextInt();
        System.out.println(getFibonacciHuge(n, m));
        
        /*
        for (int i = 2; i <= 100000; i++) {
            System.out.println(i + ": " + getFibonacciHuge((long)Math.pow(10, 18), i));
        }
        */
    }
}
