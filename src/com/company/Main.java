package com.company;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Operation op = new Operation(scanner);

        int n = scanner.nextInt();

        Point[] points = new Point[n];

        for (int i = 0; i < n; i++)
            points[i] = op.read();

        points = op.sort(points);
        Point[] convex_hull = new Point[1];
        convex_hull[0] = points[0];

        
        for (int i = 0; i < convex_hull.length; i++)
            System.out.println(convex_hull[i].x + " " + convex_hull[i].y);



        




    }
}