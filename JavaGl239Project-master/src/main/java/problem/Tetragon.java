package problem;

public class Tetragon {
    public Point a, b, c, d;
    public Tetragon(Point a, Point b, Point c, Point d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public double get_convex_area () {
        return get_triangle_area(a, b, c) + get_triangle_area(a, c, d);
    }

    public double get_non_convex_area () {
        Point[] dots = {a, b, c, d};
        int id1 = 0;
        int id2 = 1;
        int id3 = 2;
        int id4 = 3;
        double s = get_triangle_area(dots[id1], dots[id2], dots[id3]);
        double max_area = s;

        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                for (int k = j + 1; k < 4; k++) {
                    s = get_triangle_area(dots[i], dots[j], dots[k]);
                    if (s > max_area) {
                        max_area = s;
                        id1 = i;
                        id2 = j;
                        id3 = k;
                        id4 = 6 - id1 - id2 - id3;
                    }
                }
            }
        }
        //s1 124 + 134
        double s1 = get_triangle_area(dots[id1], dots[id2], dots[id4]) + get_triangle_area(dots[id1], dots[id3], dots[id4]);
        //s2 124 + 234
        double s2 = get_triangle_area(dots[id1], dots[id2], dots[id4]) + get_triangle_area(dots[id2], dots[id3], dots[id4]);
        //s3 134 + 234
        double s3 = get_triangle_area(dots[id1], dots[id3], dots[id4]) + get_triangle_area(dots[id2], dots[id3], dots[id4]);

        if (s1 >= s2 && s2 >= s3) {
            a = dots[id1];
            b = dots[id2];
            c = dots[id4];
            d = dots[id3];
        } else if (s2 >= s3 && s3 >= s1) {
            a = dots[id1];
            b = dots[id2];
            c = dots[id3];
            d = dots[id4];
        } else {
            a = dots[id1];
            b = dots[id3];
            c = dots[id2];
            d = dots[id4];
        }

        return Math.max(s1, Math.max(s2, s3));
    }

    public double get_triangle_area (Point a, Point b, Point c) {
        return Math.abs( 0.5* ((a.x - c.x) * (b.y - c.y) - (b.x - c.x) * (a.y - c.y)));
    }


    private int min_angle_from_id (double[] angles, int id) {
        double min_angle = angles[id];
        int min_id = id;
        for (int i = id; i < angles.length; i++) {
            if (angles[i] < min_angle) {
                min_angle = angles[i];
                min_id = i;
            }
        }
        return min_id;
    }
    public void sort_points () {
        Point[] dots = {a, b, c, d};
        double[] angles = new double[4];
        Point center = new Point((a.x + b.x + c.x + d.x) / 4, (a.y + b.y + c.y + d.y) / 4);

        for (int i = 0; i < 4; i++)
            angles[i] = Math.atan2(center.y - dots[i].y, center.x - dots[i].x);

        for (int i = 0; i < 4; i++) {
            System.out.println(dots[i].x + " - " + dots[i].y);
        }
        System.out.println();
        for (int i = 0; i < 4; i++) {
            int min_id = min_angle_from_id(angles, i);
            double angle = angles[min_id];
            angles[min_id] = angles[i];
            angles[i] = angle;
            Point dot = new Point(dots[min_id].x, dots[min_id].y);
            dots[min_id] = new Point(dots[i].x, dots[i].y);
            dots[i] = dot;
        }
        a = dots[0];
        b = dots[1];
        c = dots[2];
        d = dots[3];

        for (int i = 0; i < 4; i++) {
            System.out.println(dots[i].x + " - " + dots[i].y);
        }


    }


    public boolean is_convex () {
        Point[][] dots = {{a, b, c, d}, {a, b, d, c}, {a, c, d, b}, {b, c, d, a}};
        boolean[] ress = new boolean[4];
        for (int i = 0; i < 4; i++) {
            Point lab = new Point(dots[i][1].x - dots[i][0].x, dots[i][1].y - dots[i][0].y);
            Point lad = new Point(dots[i][3].x - dots[i][0].x, dots[i][3].y - dots[i][0].y);
            Point lbc = new Point(dots[i][2].x - dots[i][1].x, dots[i][2].y - dots[i][1].y);
            Point lbd = new Point(dots[i][3].x - dots[i][1].x, dots[i][3].y - dots[i][1].y);
            Point lca = new Point(dots[i][0].x - dots[i][2].x, dots[i][0].y - dots[i][2].y);
            Point lcd = new Point(dots[i][3].x - dots[i][2].x, dots[i][3].y - dots[i][2].y);

            double labd = lab.x * lad.y - lab.y * lad.x;
            double lbcd = lbc.x * lbd.y - lbc.y * lbd.x;
            double lcad = lca.x * lcd.y - lca.y * lcd.x;

            if ((labd <= 0 && lbcd <= 0 && lcad <= 0) || (labd >= 0 && lbcd >= 0 && lcad >= 0))
                ress[i] = true;
            else
                ress[i] = false;
        }

        if (ress[0] || ress[1] || ress[2] || ress[3])
            return false;
        else
            return true;


    }


}
