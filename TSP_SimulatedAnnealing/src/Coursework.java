import java.io.File;
import java.io.FileNotFoundException;

public class Coursework {
	
	public static void main(String[] args) throws FileNotFoundException {
		long startTime = System.nanoTime();
		File file = new File("C://Users//joe-m//Desktop//TSP_Files//FinalTest4.txt");
		FileReader fr = new FileReader();
		int vertexCount;
		Vertex[] vertices;
		PathFinder pf = new PathFinder();
		double temperature = 100000;
		double coolingRate = 0.999;
		
		vertexCount = fr.getVertexCount(file);
		vertices = new Vertex[vertexCount];
		fr.getVerticesFromFile(file, vertices);
		pf.findPath(vertices, temperature, coolingRate);
		long endTime = System.nanoTime();
		long timeTaken = endTime - startTime;
		double seconds = (double)timeTaken / 1000000000;
		System.out.println(seconds);
	}
	
}
