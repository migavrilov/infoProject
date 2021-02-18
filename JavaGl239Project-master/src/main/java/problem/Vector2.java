package problem;

import javax.media.opengl.GL2;

public class Vector2 {
    public float x, y;
    Vector2 (float x0, float y0) {
        x = x0;
        y = y0;
    }

    public static void renderPoint (GL2 gl, Vector2 a, float size) {
        gl.glPointSize(size);
        gl.glBegin(GL2.GL_POINTS);
        gl.glColor3d(0, 0.5, 1);
        gl.glVertex2d(a.x, a.y);
        gl.glEnd();
    }

    public static void renderLine (GL2 gl, Vector2 a, Vector2 b, float width) {
        gl.glLineWidth(width);
        gl.glBegin(GL2.GL_LINES);
        gl.glColor3d(0,0.7,1);
        gl.glVertex2d(a.x, a.y);
        gl.glVertex2d(b.x, b.y);
        gl.glEnd();
    }

    public static void renderTriangle (GL2 gl, Vector2 a, Vector2 b, Vector2 c, boolean fill, float width) {
        if (fill) {
            gl.glBegin(GL2.GL_TRIANGLES);
            gl.glColor3d(0, 0.7, 1);
            gl.glVertex2d(a.x, a.y);
            gl.glVertex2d(b.x, b.y);
            gl.glVertex2d(c.x, c.y);
            gl.glEnd();
        } else {
            renderLine(gl, a, b, width);
            renderLine(gl, a, c, width);
            renderLine(gl, b, c, width);
        }

    }

}