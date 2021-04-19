package problem;

import javax.media.opengl.GL2;

import static problem.Figures.renderQuad;

public class Tetragon {
    public Point a, b, c, d; // each tetragon has 4 points, here I initialize them
    public Tetragon(Point a, Point b, Point c, Point d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public void render(GL2 gl, boolean fill, float width, float[] color) {
        renderQuad (gl, a, b, c, d, fill, width, color);
    }

    public double get_convex_area () { // get area of convex tetragon as 2 areas of triangles
        return get_triangle_area(a, b, c) + get_triangle_area(a, c, d);
    }

    public double get_non_convex_area () {

        /*
            Here I sort points to place forth dot in the triangle that is created with other 3 dots
         */
        Point[] dots = {a, b, c, d};
        double max_area = 0;
        int id1 = 0;
        int id2 = 0;
        int id3 = 0;
        int id4 = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                for (int k = j + 1; k < 4; k++) {
                    double s = get_triangle_area(dots[i], dots[j], dots[k]);
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

        // here i calculate 3 possible areas of not convex tetragon, because in this case area depends on sequence of dots

        //s1 124 + 134
        double s1 = get_triangle_area(dots[id1], dots[id2], dots[id4]) + get_triangle_area(dots[id1], dots[id3], dots[id4]);
        //s2 124 + 234
        double s2 = get_triangle_area(dots[id1], dots[id2], dots[id4]) + get_triangle_area(dots[id2], dots[id3], dots[id4]);
        //s3 134 + 234
        double s3 = get_triangle_area(dots[id1], dots[id3], dots[id4]) + get_triangle_area(dots[id2], dots[id3], dots[id4]);
        // Here I change sequence of dots of tetragon that I use to match maximum area
        if (s1 >= s2 && s1 >= s3) {
            a = dots[id1];
            b = dots[id2];
            c = dots[id4];
            d = dots[id3];
            return s1;
        } else if (s2 >= s3 && s2 >= s1) {
            a = dots[id1];
            b = dots[id2];
            c = dots[id3];
            d = dots[id4];
            return s2;
        } else {
            a = dots[id1];
            b = dots[id3];
            c = dots[id2];
            d = dots[id4];
            return s3;
        }

    }

    public double get_triangle_area (Point a, Point b, Point c) {
        return Math.abs( 0.5* ((a.x - c.x) * (b.y - c.y) - (b.x - c.x) * (a.y - c.y))); // area = half of cross product
    }


    private int min_angle_from_id (double[] angles, int id) { // min angle from array from index = id
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
        Point center = new Point((a.x + b.x + c.x + d.x) / 4, (a.y + b.y + c.y + d.y) / 4); // center that would be between all of dots

        for (int i = 0; i < 4; i++)
            angles[i] = Math.atan2(center.y - dots[i].y, center.x - dots[i].x); // angles between horizon and line from dot to center

        //here i sort dots by angle swapping them
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




    }


    public boolean is_convex () {
        Point[][] dots = {{a, b, c, d}, {a, b, d, c}, {a, c, b, d}}; // all possible tetragons: 1 - normal, 2,3 - sides are intersecting with each other
        boolean[] ress = new boolean[3];
        for (int i = 0; i < 3; i++) {
            // here I get points that represents sides of tetragon as vectors
            Point lab = new Point(dots[i][1].x - dots[i][0].x, dots[i][1].y - dots[i][0].y);
            Point lad = new Point(dots[i][3].x - dots[i][0].x, dots[i][3].y - dots[i][0].y);
            Point lbc = new Point(dots[i][2].x - dots[i][1].x, dots[i][2].y - dots[i][1].y);
            Point lbd = new Point(dots[i][3].x - dots[i][1].x, dots[i][3].y - dots[i][1].y);
            Point lca = new Point(dots[i][0].x - dots[i][2].x, dots[i][0].y - dots[i][2].y);
            Point lcd = new Point(dots[i][3].x - dots[i][2].x, dots[i][3].y - dots[i][2].y);

            //cross product
            double l1 = lab.x * lbc.y - lab.y * lbc.x;
            double l2 = lbc.x * lcd.y - lbc.y * lcd.x;
            double l3 = lcd.x * (-lad.y) - lcd.y * (-lad.x);
            double l4 = (-lad.x) * (lab.y) - (-lad.y) * lab.x;

            if ((l1 <= 0 && l2 <= 0 && l3 <= 0 && l4 <= 0) || (l1 >= 0 && l2 >= 0 && l3 >= 0 && l4 >= 0)) // if so, it means that we turn the same way each time
                ress[i] = true;
            else
                ress[i] = false;
        }

        // in case of convex we will turn the same way only once - when sides of tetragon are not intersecting with each other
        // in case of not convex we will never turn the same way
        if (ress[0] || ress[1] || ress[2]) {
            //System.out.println("convex");
            return true;
        } else {
            //System.out.println("not convex");
            return false;
        }


    }


}
