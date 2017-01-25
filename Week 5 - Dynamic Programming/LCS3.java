import java.util.*;

public class LCS3 {
    // Recurrent Formula for computing longest common subsequence of 3 sequences:
    // D(i, j, k) = max{D(i, j-1, k), D(i, j-1, k-1), D(i, j, k-1),   // insertions (to 1st sequence)
    //                  D(i-1, j, k), D(i-1, j-1, k), D(i-1, j, k-1), // deletions (to 1st sequence)
    //                  D(i-1, j-1, k-1),                             // mismatch
    //                  D(i-1, j-1, k-1) + 1}                         // match
    // i, j, k: pointer of string a, b, c (1...string.length-1)
    // Initialize all lcs[i][j][0], lcs[i][0][k], lcs[0][j][k] to 0 (no common subsequence
    //  for a sequence of 0 element)
    private static int lcs3(int[] a, int[] b, int[] c) {
        int[][][] lcs = new int[a.length+1][b.length+1][c.length+1];

        int ins1, ins2, ins3, del1, del2, del3, mismatch, match;

        for (int i = 1; i <= a.length; i++)
            for (int j = 1; j <= b.length; j++)
                for (int k = 1; k <= c.length; k++) {
                    ins1 = lcs[i][j-1][k];
                    ins2 = lcs[i][j-1][k-1];
                    ins3 = lcs[i][j][k-1];
                    del1 = lcs[i-1][j][k];
                    del2 = lcs[i-1][j-1][k];
                    del3 = lcs[i-1][j][k-1];

                    if (a[i-1] == b[j-1] && a[i-1] == c[k-1]) {  // it is a match
                        match = lcs[i-1][j-1][k-1] + 1;
                        lcs[i][j][k] = max(ins1, ins2, ins3, del1, del2, del3, match);
                    } else {  // it is a mismatch
                        mismatch = lcs[i-1][j-1][k-1];
                        lcs[i][j][k] = max(ins1, ins2, ins3, del1, del2, del3, mismatch);
                    }
                }

        return lcs[a.length][b.length][c.length];
    }

    // Recurrent Formula for computing longest common subsequence of 2 sequences:
    // D(i, j) = max{D(i, j-1), D(i-1, j), D(i-1, j-1), D(i-1, j-1)+1}
    // i, j: pointer of string s, t (1...string.length-1)
    public static int lcs2(int[] a, int[] b) {
    
      int[][] lcs = new int[a.length+1][b.length+1];
      // initialize the first row / column of lcs[][]
      //for (int i = 0; i <= a.length; i++) lcs[i][0] = 0;
      //for (int j = 1; j <= b.length; j++) lcs[0][j] = 0;

      int insertion, deletion, mismatch, match;

      for (int i = 1; i <= a.length; i++)
        for (int j = 1; j <= b.length; j++) {
          insertion = lcs[i][j-1];
          deletion  = lcs[i-1][j];
          if (a[i-1] == b[j-1]) {  // it is a match
            match    = lcs[i-1][j-1] + 1;
            lcs[i][j] = max(insertion, deletion, match);
          } else {  // it is a mismatch
            mismatch = lcs[i-1][j-1];
            lcs[i][j] = max(insertion, deletion, mismatch);
          }
        }

      return lcs[a.length][b.length];
    }

    // return the minimum value among 6 values
    public static int max(int a, int b, int c, int d, int e, int f, int g) {
        return Math.max(Math.max(max(a, b, c), max(d, e, f)), g);
    }

    // return the minimum value among 3 values
    public static int max(int a, int b, int c) {
      return Math.max(Math.max(a, b), c);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int an = scanner.nextInt();
        int[] a = new int[an];
        for (int i = 0; i < an; i++) {
            a[i] = scanner.nextInt();
        }
        int bn = scanner.nextInt();
        int[] b = new int[bn];
        for (int i = 0; i < bn; i++) {
            b[i] = scanner.nextInt();
        }
        ///*
        int cn = scanner.nextInt();
        int[] c = new int[cn];
        for (int i = 0; i < cn; i++) {
            c[i] = scanner.nextInt();
        }
        System.out.println(lcs3(a, b, c));
        //*/
        
        //System.out.println(lcs2(a, b));
    }
}

