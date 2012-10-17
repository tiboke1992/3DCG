package geomobj;

import java.util.ArrayList;

import renderer.HitInfo;
import renderer.Intersection;
import renderer.Ray;
import util.Point;
import util.Vector;

public class Sphere extends Shape {

	@Override
	public Intersection intersection(Ray ray) {
		Intersection i = new Intersection();
		// a bereken
		Vector tempdir = new Vector(ray.dir);
		float tempA = tempdir.dot(tempdir);

		// b berekenen
		Vector tempStart = new Vector(ray.start);
		Vector tempDi = new Vector(ray.dir);
		float tempB = tempStart.dot(tempDi);
		tempB = tempB * 2;

		// c berekenen;
		Vector tempS = new Vector(ray.start);
		Float tempC = tempS.dot(tempS);
		tempC = tempC - 1;

		// discriminant
		float discri = tempB * tempB - (4 * tempA * tempC);

		float x = 0;
		float y = 0;
		float t1 = -1;
		float t2 = -1;
		float best;
		ArrayList<Float> lijst = new ArrayList<Float>();
		if (discri < 0) {
		} else if (discri == 0) {
			t1 = -tempB / (2 * tempA);
		} else if (discri > 0) {
			t1 = (float) ((-tempB + Math.sqrt(discri)) / (2 * tempA));
			t2 = (float) ((-tempB - Math.sqrt(discri)) / (2 * tempA));
		}

		if (t2 > 0) {
				Point hitPoint = ray.getPoint(t2);
				HitInfo hitBest = new HitInfo(t2, this, hitPoint, new Vector(
						hitPoint.x, hitPoint.y, hitPoint.z), true);
				Point hitPoint2 = ray.getPoint(t1);
				HitInfo hitworst = new HitInfo(t1, this, hitPoint2, new Vector(
						hitPoint2.x, hitPoint2.y, hitPoint2.z), false);
				i.add(hitBest);
				i.add(hitworst);
		}else{
			if(t1 > 0){
				Point hitPoint2 = ray.getPoint(t1);
				HitInfo hitworst = new HitInfo(t1, this, hitPoint2, new Vector(
						hitPoint2.x, hitPoint2.y, hitPoint2.z), false);
				i.add(hitworst);
			}
		}

		return i;
	}

}
