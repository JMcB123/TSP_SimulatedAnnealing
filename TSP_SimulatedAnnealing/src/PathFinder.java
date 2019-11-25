import java.util.Arrays;
import java.util.Collections;

public class PathFinder {
	public double findPath(Vertex[] vertices, double temperature, double coolingRate) {
		//number of vertices in the problem
		int vertexCount = vertices.length;
		//store the best solution found so far
		Vertex[] bestSolution = new Vertex[vertexCount];
		//store the distance of the bestSolution
		double bestDistance = Double.MAX_VALUE;
		//ordered path of current solution
		 Vertex[] currentSolution = new Vertex[vertexCount];
		//distance of the newly formed solution
		double newDistance = Double.MAX_VALUE;
		//distance of the current solution
		double currentDistance = Double.MAX_VALUE;
		
		//generate a random order of the cities to act as a baseline solution
//		Collections.shuffle(Arrays.asList(vertices));
		
		newDistance = getDistance(vertices, vertexCount);
		
		while (temperature > 1) {
			swapTwo(vertices, vertexCount);
			
			//calculate the new distance
			newDistance = getDistance(vertices, vertexCount);
			
			// if new distance is lower (better) than the current distance, 
			//replace the current distance with the new distance for the next iteration
			if (newDistance < currentDistance) {
				//if the new distance is the current lowest (best) distance, 
				//store the solution and distance in the'best' variables
				if (newDistance < bestDistance) {
					//updating best distance value
					bestDistance = newDistance;
					//empty the bestSolution array
					bestSolution = new Vertex[vertexCount];
					//store the contents of vertices into bestSolution
					//using .addAll to copy vertices without being a reference to it
					System.arraycopy( vertices, 0, bestSolution, 0, vertexCount );
				}
				//updating current distance value
				currentDistance = newDistance;
				//empty the currentSolution array
				currentSolution = new Vertex[vertexCount];
				//store the contents of vertices into bestSolution
				System.arraycopy( vertices, 0, currentSolution, 0, vertexCount );
			}
			
			else {
				if (acceptanceProbability(currentDistance, newDistance, temperature) > Math.random()) {
					//updating current distance value
					currentDistance = newDistance;
					//empty the currentSolution array
					currentSolution = new Vertex[vertexCount];
					//store the contents of vertices into bestSolution
					System.arraycopy( vertices, 0, currentSolution, 0, vertexCount );
				}
			}
			
			//reduce temperature according to the cooling rate
			temperature *= coolingRate;
		}
		
		System.out.println(bestDistance);
		
		for (int i = 0; i < bestSolution.length; i++) {
			System.out.print(bestSolution[i].getId() + " -> ");
		}
		System.out.println();
		return bestDistance;
	}
	
	//Randomly swap two vertices in the solution
	public void swapTwo(Vertex[] vertices, int vertexCount) {
		//generate 2 random numbers to be used as indexes of vertices in the vertices array
		int rndIndex1 = (int)(Math.random() * (vertexCount - 1));
		int rndIndex2 = (int)(Math.random() * (vertexCount - 1));
		Vertex rndVertex1;
		Vertex rndVertex2;
		
		//keep randomising the rndIndex2 if it is the same as rndIndex1 (cant swap a vertex with itself)
		while (rndIndex2 == rndIndex1) {
			rndIndex2 = (int)(Math.random() * (vertexCount - 1));
		}
		
		//store the randomly selected vertices in variables
		rndVertex1 = vertices[rndIndex1];
		rndVertex2 = vertices[rndIndex2];
		//replace the vertices with one another in the vertices array
		vertices[rndIndex1] = rndVertex2;
		vertices[rndIndex2] = rndVertex1;
	}
	
	public double getDistance(Vertex[] vertices, int vertexCount) {
		int pathSize = vertexCount;
		double totalDistance = 0;
		
		for (int i = 0; i < pathSize - 1; i++) {
			double currentDistance = vertices[i].distanceTo(vertices[i + 1]);
			totalDistance += currentDistance;
		}
		//Add distance of the return from final vertex to start vertex
		totalDistance += vertices[pathSize - 1].distanceTo(vertices[0]);
		
		return totalDistance;
	}
	
	//Calculate an acceptance probability
	//higher temperature value means a higher chance of the program settling on a worse solution
	//in order to give a much wider exploration of the  the program is less likely to get stuck
	//in a l
	public double acceptanceProbability(double bestDistance, double totalDistance, double temperature) {
		return Math.exp((bestDistance - totalDistance) / temperature);
	}
}
