package com.company;

import java.util.*;

public class Operation {

    public Scanner scanner;

    public Operation(Scanner scanner) {
        this.scanner = scanner;
    }

    public Operation() {
    }

    public float get_k (Point a, Point b) {
        return (a.y - b.y) / (a.x - b.x);
    }

    public Point[] add (Point[] arr, Point a) {
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
}
