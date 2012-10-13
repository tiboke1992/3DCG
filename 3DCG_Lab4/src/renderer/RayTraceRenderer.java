package renderer;

import java.util.Properties;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

import renderer.raytracer.RayTracer;
import scene.Scene;
import util.Colour;
import util.Vector;

public class RayTraceRenderer extends Renderer {
	
	private RayTracer rayTracer;	
	private int nRows, nCols;	

	public RayTraceRenderer(Scene scene, Camera camera, Properties prop) {
		super(scene, camera);	
		nRows = Integer.parseInt(prop.getProperty("canvas.height"));
	    nCols = Integer.parseInt(prop.getProperty("canvas.width"));	    
	    rayTracer = new RayTracer(scene);    
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		
	}
	
	
	
	@Override
	public void render(GLAutoDrawable drawable) {
				
		GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);		
		GLU glu = new GLU();
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluOrtho2D(0, nCols, 0, nRows);		
		gl.glMatrixMode(GL2.GL_MODELVIEW);
	    gl.glLoadIdentity();   	   
		gl.glDisable(GL2.GL_LIGHTING);
		
		// to do: implement
		//v1
		Vector v1 = camera.u;
		v1.mult(-(camera.width/2));
		Vector temp2 = camera.v;
		temp2.mult(camera.height/2);
		v1.add(temp2);
		
		//v2
		
		//V3
		Vector v3 = camera.n;
		v3.mult(-camera.distance);
		v3.add(v1);
		
		Ray ray = new Ray(camera.eye);
		Colour col = new Colour();
		
		for(int r = 0 ; r < this.nRows ; r++){
			//hier zit ik 
		}
				
		gl.glFlush();
	}
	
}
