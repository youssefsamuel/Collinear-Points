import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private final ArrayList<LineSegment> segmentsList;

    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException();
        }
        Point[] p = Arrays.copyOf(points, points.length);
        int i, j, n = points.length;
        Arrays.sort(p);
        if (!check(p))
            throw new IllegalArgumentException();
        segmentsList = new ArrayList<LineSegment>();
        Point secondPoint = null;
        Point[] copy = Arrays.copyOf(p, n);
        for (i = 0; i < n; i++) {
            Arrays.sort(copy);
            Arrays.sort(copy, p[i].slopeOrder());
            for (j = 1; j < n - 1; j++) {
                int count = 1, f = 1;
                while (((p[i].slopeTo(copy[j]) == p[i].slopeTo(copy[j + 1])) && p[i].compareTo(copy[j]) != 0)
                        && p[i].compareTo(copy[j + 1]) != 0) {
                    if (count == 1)
                        secondPoint = copy[j];
                    f = j + 1;
                    count++;
                    j++;
                    if (j == n - 1)
                        break;
                }
                if (count >= 3) {
                    LineSegment line = new LineSegment(p[i], copy[f]);
                    if (secondPoint.compareTo(p[i]) > 0)
                        segmentsList.add(line);
                }
            }
        }
    }

    private boolean check(Point[] p) {
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
