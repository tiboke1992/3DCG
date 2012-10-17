package renderer.raytracer;

import geomobj.GeomObj;

import java.util.ArrayList;

import renderer.Intersection;
import renderer.Ray;
import scene.Scene;
import util.Colour;

public class RayTracer {

	private Scene scene;

	public RayTracer(Scene scene) {
		this.scene = scene;
	}

	public Colour shade(Ray ray) {
		Colour c = new Colour(255,0,0);
		Intersection i = this.getBestIntersection(ray);
		if(i.getNumHits() == 0){
			c = scene.getBackGround();
		}else{
			c = new Colour(255,0,0);
		}
		return c;
	}

	private Intersection getBestIntersection(Ray ray) {
		ArrayList<GeomObj> objects = scene.getObjects();
		Intersection best = new Intersection();
		float max = Float.MAX_VALUE;
		for (GeomObj object : objects) {
			Intersection i = object.intersection(ray);
			if(i.getNumHits() != 0){
				if(i.getBestHitTime()<max){
					max = i.getBestHitTime();
					best = i;
				}
			}
			
		}
		return best;
	}

}
