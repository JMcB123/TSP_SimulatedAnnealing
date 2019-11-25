import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
		//Receives a file path and Vertex array as arguments and fills the Vertex array with
		//Vertex objects which represent the data from the cities in the file.
		public void getVerticesFromFile(File file, Vertex[] vertices) throws FileNotFoundException {
			Scanner sc = new Scanner(file);
			Vertex currentPoint;
			//ID number of the city currently being read by the scanner
			int currentId;
			//X coordinate of the city currently being read by the scanner
			int currentX;
			//Y coordinate of the city currently being read by the scanner
			int currentY;
			//count the iterations of the while loop
			int counter = 0;
			
			//loop while there are still lines (cities) in the file that haven't been parsed
			while (sc.hasNextLine()) {
				//Update the ID, X coordinate and Y coordinate of the current city
				currentId = Integer.parseInt(sc.next());
				currentX = Integer.parseInt(sc.next());
				currentY = Integer.parseInt(sc.next());
				
				//Create a Vertex object with the current city's data
				currentPoint = new Vertex(currentId, currentX, currentY);
				//Add the Vertex to the vertices array
				vertices[counter] = currentPoint;
				//increment the loop counter
				counter++;
			}
			//close the scanner stream
			sc.close();
		}
	
	public int getVertexCount(File file) throws FileNotFoundException {
		//Fill the Scanner object with the TSP data file
		Scanner sc = new Scanner(file);
		//Initialise vertexCount
		int vertexCount = 0;
		
		//loop while there are still lines (cities) in the file that haven't been parsed
		while (sc.hasNextLine()) {
			//increment vertexCount
			vertexCount++;
			//go to the next line (city) in the file
			sc.nextLine();
		}
		
		//file has been parsed so close the scanner
		sc.close();
		//return the number of vertices in the file
		return vertexCount;
	}
	
}
