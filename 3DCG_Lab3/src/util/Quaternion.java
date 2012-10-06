package util;

public class Quaternion {
	private float a, b, c, d;

	public Quaternion(float a, float b, float c, float d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	public Quaternion(Point point) {
		this.a = 0;
		this.b = point.x;
		this.c = point.y;
		this.d = point.z;
	}

	public Quaternion(float angle, Vector r) {
		Math.toDegrees(angle);
		
	}
}
