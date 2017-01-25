import java.util.Scanner;

public class Fib {
  private static long calc_fib(int n) {
    if (n <= 1)
      return n;

    return calc_fib(n - 1) + calc_fib(n - 2);
  }

  // improved version
  private static long calcFibFast(int n) {
    long[] fib = new long[n + 1];
    fib[0] = 0;
    if (n >= 1) {
      fib[1] = 1;
    }
    for (int i = 2; i <= n; i++) {
      fib[i] = fib[i - 1] + fib[i - 2];
    }
    return fib[n];
  }

  // for testing
  private static boolean testAl() {
    for (int i = 0; i <= 45; i++) {
      System.out.print("i = " + i + ": ");
      if (calc_fib(i) == calcFibFast(i)) {
        System.out.println("OK");
      } else {
        System.out.println("ERROR");
        return false;
      }
    }
    return true;
  }

  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();

    //System.out.println(calc_fib(n));
    System.out.println(calcFibFast(n));
    
    //System.out.println(testAl());  // for testing
  }
}
