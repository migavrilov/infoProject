package problem;

import javax.media.opengl.GL2;

public class Figures {
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
            renderLine(gl, b, c, width);
            renderLine(gl, c, a, width);
        }

    }

    public static void renderQuad (GL2 gl, Vector2 a, Vector2 b, Vector2 c, Vector2 d, boolean fill, float width) {
        if (fill) {
            renderTriangle(gl, a, b, c, fill, width);
            renderTriangle(gl, b, c, d, fill, width);
            gl.glEnd();
        } else {
            renderLine(gl, a, b, width);
            renderLine(gl, b, c, width);
            renderLine(gl, c, d, width);
            renderLine(gl, d, a, width);
        }
    }

    public static void renderCircle (GL2 gl, Vector2 a, float r, boolean fill, float width) {
        if (!(fill)) {
            float d = 0.000001f;
            float x = -r;
            while (x <= r) {
                float y1 = (float) Math.sqrt(r * r - x * x);
                float y2 = -1 * (float) Math.sqrt(r * r - x * x);
                Vector2 b = new Vector2(x + a.x, y1 + a.y);
                Vector2 c = new Vector2(x + a.x, y2 + a.y);
                renderPoint(gl, b, width);
                renderPoint(gl, c, width);
                x += d;
            }
        } else {
            float d = 0.00001f;
            float x = -r;
            while (x <= r) {
                float y1 = (float) Math.sqrt(r * r - x * x);
                float y2 = -1 * (float) Math.sqrt(r * r - x * x);
                Vector2 b = new Vector2(x + a.x, y1 + a.y);
                Vector2 c = new Vector2(x + a.x, y2 + a.y);
                renderLine(gl, a, b, width);
                renderLine(gl, a, c, width);
                x += d;
            }
        }
    }
}
