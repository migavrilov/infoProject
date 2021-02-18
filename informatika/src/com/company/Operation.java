package com.company;

import java.util.*;

public class Operation {

    public Scanner scanner;

    public Operation(Scanner scanner) {
        this.scanner = scanner;
    }

    public Operation() {
    }

    public float get_k(Point a, Point b) {
        return (a.y - b.y) / (a.x - b.x);
    }

    public Point[] add(Point[] arr, Point a) {
        Point[] new_arr = new Point[arr.length + 1];
        for (int i = 0; i < arr.length; i++)
            new_arr[i] = arr[i];

        new_arr[arr.length] = a;
        return new_arr;
    }

    public Point read() {
        float x = scanner.nextFloat();
        float y = scanner.nextFloat();

        return new Point(x, y);

    }


    public Point[] sort(Point[] points) {
        List<Point> new_points = Arrays.asList(points);
        new_points.sort((a, b) -> {
            int xc = Float.compare(a.x, b.x);
            if (xc != 0)
                return xc;
            else return -Float.compare(a.y, b.y);
        });
        return new_points.toArray(new Point[points.length]);
    }

    static public float sq(float x) {
        return x * x;
    }

    public static float tri_area(Point[] points) {
        float s1 = (float) Math.sqrt(Operation.sq(points[0].x - points[1].x) + Operation.sq(points[0].y - points[1].y));
        float s2 = (float) Math.sqrt(Operation.sq(points[1].x - points[2].x) + Operation.sq(points[1].y - points[2].y));
        float s3 = (float) Math.sqrt(Operation.sq(points[0].x - points[2].x) + Operation.sq(points[0].y - points[2].y));

        float p = (s1 + s2 + s3) / 2;
        float res = (float) Math.sqrt((p - s1) * (p - s2) * (p - s3) * p);
        return res;
    }

    public float square_area (Point[] points) {
        Point[] tri1 = {points[0], points[1], points[2]};
        Point[] tri2 = {points[0], points[2], points[3]};
        float s1 = Operation.tri_area (tri1);
        float s2 = Operation.tri_area (tri2);

        return s1 + s2;
    }
}
