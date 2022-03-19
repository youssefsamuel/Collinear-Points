import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private final ArrayList<LineSegment> segmentsList;

    public BruteCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException();
        }
        Point[] p = Arrays.copyOf(points, points.length);
        Arrays.sort(p);
        if (!check(p))
            throw new IllegalArgumentException();
        int i, j, k, m, n = points.length;
        segmentsList = new ArrayList<LineSegment>();
        for (i = 0; i < n - 3; i++) {
            for (j = i + 1; j < n - 2; j++) {
                for (k = j + 1; k < n - 1; k++) {
                    for (m = k + 1; m < n; m++) {
                        if (p[m].compareTo(p[k]) == 0 || p[m].compareTo(p[j]) == 0 || p[m].compareTo(p[i]) == 0
                                || p[k].compareTo(p[j]) == 0 || p[k].compareTo(p[i]) == 0 || p[i].compareTo(p[j]) == 0)
                            throw new IllegalArgumentException();
                        if ((p[i].slopeTo(p[j]) == p[i].slopeTo(p[k])) && (p[i].slopeTo(p[j]) == p[i].slopeTo(p[m]))) {
                            LineSegment line = new LineSegment(p[i], p[m]);
                            if (!segmentsList.contains(line))
                                segmentsList.add(line);
                        }
                    }
                }
            }
        }
    }

    private static boolean check(Point[] p) {
        for (int i = 1; i < p.length; i++) {
            if (p[i].compareTo(p[i - 1]) == 0)
                return false;
        }
        return true;
    }

    public int numberOfSegments() {
        return segmentsList.size();
    }

    public LineSegment[] segments() {
        LineSegment[] segments = segmentsList.toArray(new LineSegment[segmentsList.size()]);
        return segments;
    }
}
