import java.util.*;

public class PointsAndSegments {

    private static int[] fastCountSegments(int[][] segments, int[] points) {
        int s = segments.length, p = points.length;

        // record segments
        int[][] segmentSet = new int[2 * s][2];
        for (int i = 0; i < s; i++) {
            segmentSet[i][0] = 0;  // "0" indicates segment front
            segmentSet[i][1] = segments[i][0];  // segment front's value
            segmentSet[i+s][0] = 1;  // "1" indicates segment end
            segmentSet[i+s][1] = segments[i][1];  // segment end's value
        }
        // sort segmentSet[][1] in ascending order
        Arrays.sort(segmentSet, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[1], b[1]);
            }
        });

        // record points
        int[][] pointSet = new int[p][2];
        for (int i = 0; i < p; i++) {
            pointSet[i][0] = i + 2;
            // (point's original index) + 2 (to distinguish from segments after congregation)
            pointSet[i][1] = points[i];  // individual point's value
        }
        // sort pointSet[][1] in ascending order
        Arrays.sort(pointSet, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[1], b[1]);
            }
        });

        // combine sorted segments and points into one array
        int[][] congregated = new int[2*s+p][2];
        int a = 0, b = 0;  // a, b: pointer for segmentSet/pointSet array
        while (a < segmentSet.length && b < pointSet.length) {
            if (segmentSet[a][1] < pointSet[b][1] || (segmentSet[a][1] == pointSet[b][1]
             && segmentSet[a][0] == 0))
                congregated[a+b] = segmentSet[a++];
            else congregated[a+b] = pointSet[b++];
        }  // order of points with same value: segment front -- individual point -- segment end
        if (a == segmentSet.length) {  // only pointSet[b..p] is left
            for (int i = b; i < pointSet.length; i++)
                congregated[a+i] = pointSet[i];
        } else {  // only segmentSet[a..2*s] is left
            for (int i = a; i < segmentSet.length; i++)
                congregated[i+b] = segmentSet[i];
        }

        /*
        System.out.println();
        for (int i = 0; i < congregated.length; i++) {
            printArray(congregated[i]);
        }  // for debugging
        */

        // counting segments for points
        int[] cnt = new int[p];
        int segmentsCont = 0;  // number of segments current point has
        for (int i = 0; i < congregated.length; i++) {
            if (congregated[i][0] == 0) segmentsCont++;  // this is a segment front
            else if (congregated[i][0] == 1) segmentsCont--;  // this is a segment end
            else cnt[congregated[i][0] - 2] = segmentsCont;  // total number of segments that contain the point
        }
        return cnt;
    }

    private static int[] naiveCountSegments(int[][] segments, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < segments.length; j++) {
                if (segments[j][0] <= points[i] && points[i] <= segments[j][1]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }


    // generate test cases
    private static void testGen() {
        Random r = new Random();
        int test = 0;
        while (true) {
            // 1 <= s, p <= 50000
            int s = r.nextInt(50000) + 1, p = r.nextInt(50000) + 1;
            int[][] segments = new int[s][2];
            int[] points = new int[p];
            for (int i = 0; i < s; i++) {
                // -10^8 <= a[i] <= b[i] <= 10^8
                segments[i][0] = (-1+r.nextInt(2))*(r.nextInt((int)Math.pow(10, 8)+1));
                segments[i][1] = segments[i][0]+r.nextInt((int)Math.pow(10, 5)-segments[i][0]+1);
            }
            for (int j = 0; j < p; j++) {
                // -10^8 <= x[j] <= 10^8
                points[j] = (-1+r.nextInt(2))*(r.nextInt((int)Math.pow(10, 5)+1));
            }

            System.out.print("Test " + test++ + ": ");
            int[] fastResult = fastCountSegments(segments, points);
            //System.out.println("COMPLETED");  // ends here for time test
            ///*
            int[] naiveResult = naiveCountSegments(segments, points);
            if (Arrays.equals(fastResult, naiveResult)) {
                System.out.println("PASSED");
            } else {
                System.out.println("FAILED: s = " + s + ", p = " + p);
                printArray(fastResult);
                printArray(naiveResult);
                System.out.println("----The following are test cases------");
                printArray(segments);
                printArray(points);
                return;
            }
            //*/
        }
    }

    // print array to standard output
    private static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    private static void printArray(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i][0] + "(S) " + a[i][1] +"(E) ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        ///*
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[][] segments = new int[n][2];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            segments[i][0] = scanner.nextInt();  // segment front
            segments[i][1] = scanner.nextInt();  // segment end
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        int[] cnt = fastCountSegments(segments, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
        //*/

        //testGen();
    }
}
