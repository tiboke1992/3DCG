package renderer;

import java.util.Properties;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

import util.Point;
import util.Quaternion;
import util.Vector;

public class Camera {
	public static final double ROTATION = 0.5;
	public Point eye;
	public Point look;
	public Vector up;

	public Vector u, v, n;
	
	public float distance;
	public float width;
	public float height;

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
		
		distance = Float.parseFloat(prop.getProperty("camera.worldwindow.distance"));
		width = Float.parseFloat(prop.getProperty("camera.worldwindow.width"));
		height = Float.parseFloat(prop.getProperty("camera.worldwindow.height"));

	}

	public void setPositionAndOrientationOpenGL(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		GLU glu = new GLU();
		glu.gluLookAt(eye.x, eye.y, eye.z, look.x, look.y, look.z, v.x, v.y,
				v.z);
	}

	public void forward() {
		eye.add(n.getReverse());
	}

	public void backward() {
		eye.add(n);
	}

	public void up() {
		rotateUpOrDown(-ROTATION);
	}

	public void down() {
		rotateUpOrDown(ROTATION);
	}
	
	public void left(){
		rotateLeftOrRight(ROTATION);
	}
	
	public void right(){
		rotateLeftOrRight(-ROTATION);
	}

	private void rotateUpOrDown(double angle) {
		Quaternion q = new Quaternion(eye);
		Quaternion rotate = new Quaternion(angle, u);
		Quaternion theEye = rotate.mult(q).mult(rotate.conjugate());
		this.eye = new Point(theEye.b, theEye.c, theEye.d);
		this.n = this.eye.subtract(this.look);
		this.n.normalize();
		this.v = n.getCross(this.u);
	}
	
	private void rotateLeftOrRight(double angle){
		Quaternion q = new Quaternion(this.eye);
		Quaternion rotation = new Quaternion(angle, this.v);
		Quaternion theEye = rotation.mult(q).mult(rotation.conjugate());
		this.eye = new Point(theEye.b,theEye.c,theEye.d);
		this.n = this.eye.subtract(this.look);
		this.n.normalize();
		this.u = this.v.getCross(this.n);
	}
}
