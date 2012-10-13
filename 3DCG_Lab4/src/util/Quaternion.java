package util;

public class Quaternion {
	public float a;
	public float b;
	public float c;
	public float d;

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

	public Quaternion(double angle, Vector r) {
		angle = Math.toRadians(angle);
		double sinus = Math.sin(angle / 2);

		this.a = (float) Math.cos(angle / 2);
		this.b = (float) sinus * r.x;
		this.c = (float) sinus * r.y;
		this.d = (float) sinus * r.z;
	}
	
	public Quaternion mult(Quaternion o){
		float ma = this.a*o.a - this.b * o.b - this.c * o.c - this.d * o.d;
		float mb = this.b*o.a + this.a * o.b + this.d * o.c - this.c * o.d;
		float mc = this.c*o.a - this.d * o.b + this.a * o.c + this.b * o.d;
		float md = this.d*o.a + this.c * o.b - this.b * o.c + this.a * o.d;
		
		return new Quaternion(ma,mb,mc,md);
	}
	
	public Quaternion conjugate(){
		return new Quaternion(this.a,-this.b, -this.c, -this.d);
	}
}
