import java.util.Scanner;

public class Change {
    private static int getChange(int n) {
        int ten = n / 10;
        int five = n % 10;
        int one = five % 5;
        five /= 5;
        return ten + five + one;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(getChange(n));

        /*
        // for testing
        for (int i = 1; i <= Math.pow(10, 3); i++) {
            System.out.println("i = " + i + ": " + getChange(i));
        }
        */

    }
}
