import java.util.*;

public class GCD {
  private static int gcd(int a, int b) {
    int current_gcd = 1;
    for(int d = 2; d <= a && d <= b; ++d) {
      if (a % d == 0 && b % d == 0) {
        if (d > current_gcd) {
          current_gcd = d;
        }
      }
    }

    return current_gcd;
  }

  // implementing euclidean algorithm
  private static int gcdFast(int a, int b) {
    if (b == 0) return a;
    else return gcdFast(b, a % b);
  }

  // for testing
  private static void testAl() {
    Random rand = new Random();
    while (true) {
      // nextInt(max) = 0 ~ max - 1
      /*
      int a = rand.nextInt(2 * (int)Math.pow(10, 9)) + 1;
      System.out.print("a = " + a + "; ");
      int b = rand.nextInt(2 * (int)Math.pow(10, 9)) + 1;
      System.out.print("b = " + b + "; ");
      */
      int a = rand.nextInt(99) + 1;
      System.out.print("a = " + a + "; ");
      int b = rand.nextInt(99) + 1;
      System.out.print("b = " + b + "; ");
      if (gcd(a, b) == gcdFast(a, b)) {
        System.out.println("OK");
      } else {
        System.out.println("ERROR");
        break;
      }
    }
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    //System.out.println(gcd(a, b));
    System.out.println(gcdFast(a, b));
   
    //testAl();  // for testing
  }
}
