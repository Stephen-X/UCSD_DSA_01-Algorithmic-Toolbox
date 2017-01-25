import java.util.Scanner;

public class PlacingParentheses {
    // Recurrent formula for maximizing the value of the expression (exp):
    //  Suppose k = index of the last operator to be executed, then k = {1, 3, 5, ... , 2n-1}
    //  Suppose exp = "d0 op0 d1 op1 ..."
    //  M(i, j) = max{M(i, k) op(k) M(k+1, j),
    //                M(i, k) op(k) m(k+1, j),
    //                m(i, k) op(k) M(k+1, j),
    //                m(i, k) op(k) m(k+1, j)}
    //  m(i, j) = min{M(i, k) op(k) M(k+1, j),
    //                M(i, k) op(k) m(k+1, j),
    //                m(i, k) op(k) M(k+1, j),
    //                m(i, k) op(k) m(k+1, j)}
    // (i <= k <= j-1)
    // (Digits in the expression (d) range only from 0 to 9)
    private static long getMaximValue(String exp) {
        int digitTotal = (exp.length() - 1) / 2 + 1;
        long[][] maximum = new long[digitTotal][digitTotal];
        long[][] minimum = new long[digitTotal][digitTotal];
        // maximum / minimum: max. / min. value for exp(i...j); i <= j and both only point to digits
        //  Note: when i = j, maximum(i, j) = corresponding digit in exp
        char[] op = new char[exp.length() - digitTotal];
        for (int l = 0; l < exp.length()-1; l++){
            maximum[l/2][l/2] = exp.charAt(l) - 48;  // copy digit to maximum(i, j), i = j; '0' = 48
            minimum[l/2][l/2] = exp.charAt(l) - 48;  // copy digit to minimum(i, j), i = j
            op[(++l)/2] = exp.charAt(l);  // the next char must be an operator
        }
        maximum[digitTotal-1][digitTotal-1] = exp.charAt(exp.length()-1) - 48;
        minimum[digitTotal-1][digitTotal-1] = exp.charAt(exp.length()-1) - 48;
        // add the last digit to the two arrays
        
        // now digits and operators are indexed as: d0 op0 d1 op1 ...
        int j; long a, b, c, d, maxim, minim;
        for (int m = 1; m < digitTotal; m++)
            for (int i = 0; i < digitTotal-m; i++) {
                j = i + m;  // m = j - i; data in maximum[][] and minimum[][] is processed diagonally
                maxim = Long.MIN_VALUE;
                minim = Long.MAX_VALUE;
                for (int k = i; k <= j-1; k++) {  // picking operators in exp(i...j); i <= k <= j-1
                    a = eval(maximum[i][k], op[k], maximum[k+1][j]);  // M(i, k) op(k) M(k+1, j)
                    b = eval(maximum[i][k], op[k], minimum[k+1][j]);  // M(i, k) op(k) m(k+1, j)
                    c = eval(minimum[i][k], op[k], maximum[k+1][j]);  // m(i, k) op(k) M(k+1, j)
                    d = eval(minimum[i][k], op[k], minimum[k+1][j]);  // m(i, k) op(k) m(k+1, j)
                    maxim = max(maxim, a, b, c, d);  // picking the maximum outcome for exp(i...j)
                    minim = min(minim, a, b, c, d);  // picking the minimum outcome for exp(i...j)
                }
                maximum[i][j] = maxim;
                minimum[i][j] = minim;
            }

        return maximum[0][digitTotal-1];
    }

    // the basic arithmetic operation of this program only includes '+', '-', '*'
    private static long eval(long a, char op, long b) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }

    private static long max(long a, long b, long c, long d, long e) {
        long abcd = max(max(a, b), max(c, d));
        if (abcd > e) return abcd;
        else return e;
    }

    private static long max(long a, long b) {
        if (a > b) return a;
        else return b;
    }

    private static long min(long a, long b, long c, long d, long e) {
        long abcd = min(min(a, b), min(c, d));
        if (abcd < e) return abcd;
        else return e;
    }

    private static long min(long a, long b) {
        if (a < b) return a;
        else return b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
    }
}

