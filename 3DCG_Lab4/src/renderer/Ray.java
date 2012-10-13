package renderer;

import util.Point;
import util.Vector;

public class Ray {

	public Point start;
	public Vector dir;

	public Ray(Point start) {
		this.start = new Point(start);
		this.dir = new Vector();
	}

	public Ray(Point start, Vector dir) {
		this.start = new Point(start);
		this.dir = new Vector(dir);
	}

	public Point getPoint(float t) {
		// p = start + t*dir
		// tdir
		Vector tdir = new Vector(t * this.dir.x, t * this.dir.y, t * this.dir.z);
		// start + tdir
		Point p = new Point(this.start.x + tdir.x, this.start.y + tdir.y,
				this.start.z + tdir.z);
		return p;
	}

}
