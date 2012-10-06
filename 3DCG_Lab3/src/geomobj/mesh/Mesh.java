package geomobj.mesh;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

import util.Point;
import util.Vector;

public class Mesh {

	private ArrayList<Point> verts; // vertex list
	private ArrayList<Vector> norms; // normal list
	private ArrayList<Face> faces; // face list

	public Mesh(String fileName) {
		readFile(fileName);
	}

	private void readFile(String fileName) {
		File file = new File(fileName);
		try {
			Scanner scanner = new Scanner(file);
			scanner.useLocale(Locale.US);
			// De 3 waarden inlezen
			int numberVertex = scanner.nextInt();
			int numberNormal = scanner.nextInt();
			int numberFaces = scanner.nextInt();
			// lijsten initialiseren
			verts = new ArrayList<Point>(numberVertex);
			norms = new ArrayList<Vector>(numberNormal);
			faces = new ArrayList<Face>(numberFaces);
			// invullen van de lijsten
			readVertexes(scanner, numberVertex);
			readNormals(scanner, numberNormal);
			readFaces(scanner, numberFaces);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void readVertexes(Scanner scanner, int number) {
		for (int i = 0; i < number; i++) {
			float x = scanner.nextFloat();
			float y = scanner.nextFloat();
			float z = scanner.nextFloat();

			verts.add(new Point(x, y, z));
		}
	}

	private void readNormals(Scanner scanner, int number) {
		for(int i = 0 ; i < number ; i++){
			float x = scanner.nextFloat();
			float y = scanner.nextFloat();
			float z = scanner.nextFloat();
			
			norms.add(new Vector(x,y,z));
		}
	}

	private void readFaces(Scanner scanner, int number) {
		for(int i = 0 ; i < number ; i++){
			//eerst het aantal lezen
			int numberVerts = scanner.nextInt();
			ArrayList<Integer> verts = new ArrayList<Integer>(numberVerts);
			ArrayList<Integer> norms = new ArrayList<Integer>(numberVerts);
			
			for(int j = 0 ; j < numberVerts ; j++){
				verts.add(scanner.nextInt());
			}
			
			for(int j = 0 ; j < numberVerts ; j ++){
				norms.add(scanner.nextInt());
			}
			
			Face face = new Face(verts,norms);
			faces.add(face);
		}
	}

	public void draw(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		for (Face face : faces) {
			gl.glBegin(GL2.GL_POLYGON);
			for (int i = 0; i < face.vertIndices.size(); i++) {
				Vector normal = norms.get(face.normIndices.get(i));
				Point vertex = verts.get(face.vertIndices.get(i));
				gl.glNormal3f(normal.x, normal.y, normal.z);
				gl.glVertex3f(vertex.x, vertex.y, vertex.z);
			}
			gl.glEnd();
		}
	}

}
