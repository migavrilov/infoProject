package problem;

import javax.media.opengl.GL2;

public class Figures {
    public static void renderPoint(GL2 gl, Point a, float size, float[] color) {
        gl.glPointSize(size);
        gl.glBegin(GL2.GL_POINTS);
        gl.glColor3d(color[0], color[1], color[2]);
        gl.glVertex2d(a.x, a.y);
        gl.glEnd();
    }

    public static void renderLine(GL2 gl, Point a, Point b, float width, float[] color) {
        gl.glLineWidth(width);
        gl.glBegin(GL2.GL_LINES);
        gl.glColor3d(color[0], color[1], color[2]);
        gl.glVertex2d(a.x, a.y);
        gl.glVertex2d(b.x, b.y);
        gl.glEnd();
    }

    public static void renderTriangle(GL2 gl, Point a, Point b, Point c, float width, boolean fill, float[] color) {
        if (fill) {
            gl.glBegin(GL2.GL_TRIANGLES);
            gl.glColor3d(color[0], color[1], color[2]);
            gl.glVertex2d(a.x, a.y);
            gl.glVertex2d(b.x, b.y);
            gl.glVertex2d(c.x, c.y);
            gl.glEnd();
        } else {
            renderLine(gl, a, b, width, color);
            renderLine(gl, b, c, width, color);
            renderLine(gl, c, a, width, color);
        }

    }

    public static void renderQuad(GL2 gl, Point a, Point b, Point c, Point d, boolean fill, float width, float[] color) {
        if (fill) {
            renderTriangle(gl, a, b, c, width, fill, color);
            renderTriangle(gl, b, c, d, width, fill, color);
            renderTriangle(gl, a, c, d, width, fill, color);
            renderTriangle(gl, a, b, d, width, fill, color);
        } else {
            renderLine(gl, a, b, width, color);
            renderLine(gl, b, c, width, color);
            renderLine(gl, c, d, width, color);
            renderLine(gl, d, a, width, color);
        }

        gl.glEnd();
    }

    public static void renderCircle(GL2 gl, Point a, float r, boolean fill, float width, float[] color) {
        float d = 0.00001f;
        float x = -r;
        while (x <= r) {
            float y1 = (float) Math.sqrt(r * r - x * x);
            float y2 = -y1;
            Point b = new Point(x + a.x, y1 + a.y);
            Point c = new Point(x + a.x, y2 + a.y);
            if (fill) {
                renderLine(gl, a, b, width, color);
                renderLine(gl, a, c, width, color);
            } else {
                renderPoint(gl, b, width, color);
                renderPoint(gl, c, width, color);
            }
            x += d;
        }

    }
}