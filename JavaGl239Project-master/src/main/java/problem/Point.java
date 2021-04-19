package problem;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import java.util.Random;
import static problem.Figures.renderPoint;

/**
 * Класс точки
 */
public class Point {
    /**
     * x - координата точки
     */
    double x;
    /**
     * y - координата точки
     */
    double y;

    /**
     * Конструктор точки
     *
     * @param x         координата
     * @param y         координата y
     */
    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Получить случайную точку
     *
     * @return случайная точка
     */
    static Point getRandomPoint() {
        Random r = new Random();
        double nx = Math.round((r.nextDouble() * 2 - 1)*100)/100.0;
        double ny = Math.round((r.nextDouble() * 2 - 1)*100)/100.0;
        return new Point(nx, ny);
    }

    /**
     * Рисование точки
     *
     * @param gl переменная OpenGl для рисования
     */
    void render(GL2 gl, float size, float[] color) {
        renderPoint(gl, this, size, color);
    }

    /**
     * Получить строковое представление точки
     *
     * @return строковое представление точки
     */
    @Override
    public String toString() {
        return "Точка с координатами: {" + x + "," + y + "}";
    }
}
