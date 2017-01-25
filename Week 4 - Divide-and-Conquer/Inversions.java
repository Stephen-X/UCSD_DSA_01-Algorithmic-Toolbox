import java.util.*;

public class Inversions {

    private static long getNumberOfInversions(int[] a) {
        int[] aux = new int[a.length];  // aux[] is a temporary array for calculation purpose
        return getNumberOfInversions(a, aux, 0, a.length);
    }

    private static long getNumberOfInversions(int[] a, int[] aux, int left, int right) {
        if (right <= left + 1) return 0;  // a[] only has <= 1 element
        long numberOfInversions = 0;
        int ave = (left + right) / 2;
        numberOfInversions += getNumberOfInversions(a, aux, left, ave);
        numberOfInversions += getNumberOfInversions(a, aux, ave, right);
        numberOfInversions += merge(a, aux, left, ave, right);
        return numberOfInversions;
    }

    private static long merge(int[] a, int[] aux, int left, int ave, int right) {
        long numberOfInversions = 0;
        for (int k = left; k < right; k++) {
            aux[k] = a[k];
        } // initialize aux[] with a[]'s value

        int i = left, j = ave; // i, j: pointer for left / right side of aux[]; k: pointer of a[]
        for (int k = left; k < right; k++) {
            if      (j >= right) a[k] = aux[i++];  // the left side of a[] has remaining elements
            else if (i >= ave)   a[k] = aux[j++];  // ... or the right side has remaining elements
            else if (aux[i] > aux[j]) {
                numberOfInversions += ave - i;  // aux[i...m-1] are all >= a[j]; (m - i) inversions in total
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }

        return numberOfInversions;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        System.out.println(getNumberOfInversions(a));
        // a[] is now sorted in ascending order
        
        /*
        // print sorted a[]
        System.out.println("\na[] = ");
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        */
    }
}

