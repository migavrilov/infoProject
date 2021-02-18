package problem;

import javax.media.opengl.GL2;

public class Vector2 {
    public float x, y;
    Vector2 (float x0, float y0) {
        x = x0;
        y = y0;
    }

    public static void renderLine (GL2 gl, Vector2 a, Vector2 b, float width) {
        gl.glLineWidth(width);
        gl.glPointSize(5);
        gl.glBegin(GL2.GL_LINES);
        gl.glColor3d(0.5,0.5,0.5);
        gl.glVertex2d(a.x, a.y);
        gl.glColor3d(0.5,0.5,0.5);
        gl.glVertex2d(b.x, b.y);
        gl.glEnd();
    }

}