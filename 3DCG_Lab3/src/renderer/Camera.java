package renderer;

import java.util.Properties;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

import util.Point;
import util.Vector;

public class Camera {

	public Point eye;
	public Point look;
	public Vector up;

	public Vector u, v, n;

	public Camera(Properties prop) {
		eye = new Point(Float.parseFloat(prop.getProperty("camera.eye.x")),
				Float.parseFloat(prop.getProperty("camera.eye.y")),
				Float.parseFloat(prop.getProperty("camera.eye.z")));
		look = new Point(Float.parseFloat(prop.getProperty("camera.look.x")),
				Float.parseFloat(prop.getProperty("camera.look.y")),
				Float.parseFloat(prop.getProperty("camera.look.z")));
		up = new Vector(Float.parseFloat(prop.getProperty("camera.up.x")),
				Float.parseFloat(prop.getProperty("camera.up.y")),
				Float.parseFloat(prop.getProperty("camera.up.z")));
		n = new Vector(eye.x - look.x, eye.y - look.y, eye.z - look.z);
		n.normalize();
		u = up.getCross(n);
		u.normalize();
		v = n.getCross(u);

	}

	public void setPositionAndOrientationOpenGL(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		GLU glu = new GLU();
		glu.gluLookAt(eye.x, eye.y, eye.z, look.x, look.y, look.z, v.x, v.y,
				v.z);
	}
	
	public void forward(){
		eye.add(n.getReverse());
	}
	
	public void backward(){
		eye.add(n);
	}
}
