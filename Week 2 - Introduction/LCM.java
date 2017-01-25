import java.util.*;

public class LCM {
  private static long lcm(int a, int b) {
    return ((long)a * b) / gcd(a, b);
  }  // a * b = gcd(a, b) * lcm(a, b)

  // implementing euclidean algorithm to get GCD
  private static int gcd(int a, int b) {
    if (b == 0) return a;
    else return gcd(b, a % b);
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    System.out.println(lcm(a, b));
  }
}
