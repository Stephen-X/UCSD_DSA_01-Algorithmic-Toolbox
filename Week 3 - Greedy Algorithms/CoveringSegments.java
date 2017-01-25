import java.util.*;

public class CoveringSegments {

    private static int[] optimalPoints(int[][] segments) {
        // sorting segments[][1] (ending points of segments) in ascending order
        Arrays.sort(segments, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[1], b[1]);
            }
        });

        int[] points = new int[segments.length+1];
        // use points[0] to store the actual length of points[]
        points[0] = 1;
        points[1] = segments[0][1];  // marking the minimum right endpoint
        for (int i = 1; i < segments.length; i++) {
            if (segments[i][0] > points[points[0]]) {
                // the existing points isn't covering this segment
                points[++points[0]] = segments[i][1];
            }
        }
        return points;
    }

    // generating test cases
    private static void testGen() {
        Random r = new Random();
        while (true) {
            int n = r.nextInt(100) + 1;  // 1 <= n <= 100
            int[][] segments = new int[n][2];
            for (int i = 0; i < n; i++) {
                segments[i][0] = r.nextInt((int)Math.pow(10, 9)+1);  // 0 <= a(i) <= 10^9
                segments[i][1] = segments[i][0] + r.nextInt((int)Math.pow(10, 9)-segments[i][0]+1);
                // a(i) <= b(i) <= 10^9
            }
            int[] points = optimalPoints(segments);
            System.out.print(points[0] + ": ");
            for (int i = 1; i <= points[0]; i++) {
                System.out.print(points[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ///*
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();  // number of segments
        int[][] segments = new int[n][2];
        for (int i = 0; i < n; i++) {
            segments[i][0] = scanner.nextInt();  // starting point of a segment
            segments[i][1] = scanner.nextInt();  // ending point of a segment
        }
        int[] points = optimalPoints(segments);
        System.out.println(points[0]);  // printing number of points
        for (int i = 1; i <= points[0]; i++) {
            System.out.print(points[i] + " ");
        }
        //*/
       
        //testGen();
    }

}
