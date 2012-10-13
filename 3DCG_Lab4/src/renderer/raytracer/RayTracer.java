package renderer.raytracer;

import renderer.Intersection;
import renderer.Ray;
import scene.Scene;
import util.Colour;

public class RayTracer {
	
	private Scene scene;
	
	public RayTracer(Scene scene){
		this.scene = scene;
	}
	
	public Colour shade(Ray ray){
		// to do: implement		
		return null;
	}
	
	private Intersection getBestIntersection(Ray ray) {		
		// to do: implement	
		return null;
	}

}
