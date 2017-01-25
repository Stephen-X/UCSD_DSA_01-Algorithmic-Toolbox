import java.util.*;

class EditDistance {
  // Recurrent Formula for computing edit distance:
  //  D(i, j) = min{D(i, j-1)+1, D(i-1, j)+1, D(i-1, j-1)+1, D(i-1, j-1)}
  //  i, j: pointer of string s, t (1...string.length-1)
  public static int EditDistance(String s, String t) {
    
    int[][] ed = new int[s.length()+1][t.length()+1];
    // initialize the first row / column of ed[][]
    for (int i = 0; i <= s.length(); i++) ed[i][0] = i;
    for (int j = 1; j <= t.length(); j++) ed[0][j] = j;

    int insertion, deletion, mismatch, match;

    for (int i = 1; i <= s.length(); i++)
      for (int j = 1; j <= t.length(); j++) {
        insertion = ed[i][j-1] + 1;
        deletion  = ed[i-1][j] + 1;
        if (s.charAt(i-1) == t.charAt(j-1)) {  // it is a match
          match    = ed[i-1][j-1];
          ed[i][j] = min(insertion, deletion, match);
        } else {  // it is a mismatch
          mismatch = ed[i-1][j-1] + 1;
          ed[i][j] = min(insertion, deletion, mismatch);
        }
      }

    return ed[s.length()][t.length()];
  }

  // return the minimum value among 3 or 2 values
  public static int min(int a, int b, int c) {
    int temp = min(a, b);
    if (temp < c) return temp;
    else return c;
  }

  public static int min(int a, int b) {
    if (a < b) return a;
    else return b;
  }

  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    String s = scan.next();
    String t = scan.next();

    System.out.println(EditDistance(s, t));
  }

}
