package problem;

import javax.media.opengl.GL2;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Класс задачи
 */
public class Problem {
    /**
     * текст задачи
     */
    public static final String PROBLEM_TEXT = "ПОСТАНОВКА ЗАДАЧИ:\n" +
            "На плоскости задано множество точек.\n" +
            "Найти из них такие 4 точки, что построенный\n" +
            "по ним 4-хугольник не является самопересекающимся и имеет при этом\n" +
            "максимальную площадь.\n";

    /**
     * заголовок окна
     */
    public static final String PROBLEM_CAPTION = "Итоговый проект ученика 10-2 Гаврилова Михаила";

    /**
     * путь к файлу
     */
    private static final String FILE_NAME = "points.txt";

    /**
     * список точек
     */
    private final ArrayList<Point> points;

    private Tetragon tetragon = null;
    private double max_area = 0;
    private boolean ready = false;

    /**
     * Конструктор класса задачи
     */
    public Problem() {
        points = new ArrayList<>();
    }

    /**
     * Добавить точку
     *
     * @param x      координата X точки
     * @param y      координата Y точки
     */
    public void addPoint(double x, double y) {
        Point point = new Point(x, y);
        points.add(point);
    }

    /**
     * Решить задачу
     */
    public void solve() {
        if (points.size() < 4) // not going to work if there is less than 4 dots <=> no tetragons
            return;

        for (int i = 0; i < points.size(); i++) { // first point
            for (int j = i + 1; j < points.size(); j++) { // second point
                for (int k = j + 1; k < points.size(); k++) { // third point
                    for (int z = k + 1; z < points.size(); z++) { // forth point
                        Tetragon tet = new Tetragon (points.get(i), points.get(j), points.get(k), points.get(z)); // creating object of tetragon with 4 points
                        double area = 0; // set area to 0
                        if (tet.is_convex()) { // check if tetragon is convex
                            tet.sort_points(); // sort points clockwise
                            area = tet.get_convex_area(); // get area of convex tetragon
                        } else {
                            area = tet.get_non_convex_area(); // get area of not convex tetragon
                        }

                        if (max_area < area) { // if max area is smaller that new area
                            max_area = area; // change max area to its new value
                            tetragon = new Tetragon(tet.a, tet.b, tet.c, tet.d); // create corresponding tetragon
                        }
                    }
                }
            }
        }
        ready = true; // set ready to true makes render() draw the answer (tetragon with max area)

    }

    /**
     * Загрузить задачу из файла
     */
    public void loadFromFile() {
        points.clear();
        try {
            File file = new File(FILE_NAME);
            Scanner sc = new Scanner(file);
            // пока в файле есть непрочитанные строки
            while (sc.hasNextLine()) {
                double x = sc.nextDouble();
                double y = sc.nextDouble();
                sc.nextLine();
                Point point = new Point(x, y);
                points.add(point);
            }
        } catch (Exception ex) {
            System.out.println("Ошибка чтения из файла: " + ex);
        }
    }

    /**
     * Сохранить задачу в файл
     */
    public void saveToFile() {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME));
            for (Point point : points) {
                out.println(Double.toString(point.x).replace(".", ",") + " " + Double.toString(point.y).replace(".", ","));
            }
            out.close();
        } catch (IOException ex) {
            System.out.println("Ошибка записи в файл: " + ex);
        }
    }

    /**
     * Добавить заданное число случайных точек
     *
     * @param n кол-во точек
     */
    public void addRandomPoints(int n) {
        for (int i = 0; i < n; i++) {
            Point p = Point.getRandomPoint();
            points.add(p);
        }
    }

    /**
     * Очистить задачу
     */
    public void clear() {
        ready = false;
        tetragon = null;
        max_area = 0;
        points.clear();
    }

    /**
     * Нарисовать задачу
     *
     * @param gl переменная OpenGL для рисования
     */
    public void render(GL2 gl) {
        int size = 10;
        for (Point point:points){
            float[] color = {0, 0.5f, 1};
            point.render(gl, size, color);
        }
        if (ready) {
            float[] color = {1, 0, 0};
            tetragon.render(gl, false, 3f, color);
            tetragon.a.render(gl, size, color);
            tetragon.b.render(gl, size, color);
            tetragon.c.render(gl, size, color);
            tetragon.d.render(gl, size, color);
        }

    }
}
