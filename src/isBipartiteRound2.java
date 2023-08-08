import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class isBipartiteRound2 {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int vertices, edges;
		Scanner scanner = new Scanner(System.in);
		
		vertices = scanner.nextInt();
		edges = scanner.nextInt();
		
		Graph g = new Graph(vertices);
		
		for(int i = 0; i < edges; i++)
		{
			g.addEdges(scanner.nextInt() - 1, scanner.nextInt() - 1);
		}
		
		if(g.isBipartite())
			g.printCycle();
		else
			System.out.println(-1);
	}
	
	
	static class Graph
	{
		int vertices; 
		List<Integer>[] edgeList;
		int[] colour;
		
		Graph(int vertices)
		{
			this.vertices = vertices;
			edgeList = new ArrayList[vertices];
			colour = new int[vertices];
			
			for(int i = 0; i < vertices; i++)
			{
				edgeList[i] = new ArrayList<>();
				colour[i] = -1;				
			}			
		}
		
		void addEdges(int x, int y)
		{
			edgeList[x].add(y);
			edgeList[y].add(x);
		}
		
		boolean isBipartite()
		{
			LinkedList<Integer> queue = new LinkedList<>();
			
			for(int i = 0; i < vertices; i++)
			{
				if(colour[i] == -1)
				{
					colour[i] = 1;
					queue.push(i);
				}
				
				while(!queue.isEmpty())
				{
					int v = queue.pop();
					
					for(int z : edgeList[v])
					{
						if(colour[z] == -1)
						{
							colour[z] = 1 - colour[v];
							queue.push(z);
						}
						else
						{
							if(colour[z] == colour[v])
								return false;
						}
						
					}					
				}
			}
			return true;
		}
		
		void printCycle()
		{
			System.out.println(Arrays.toString(colour));
		}
		
	}

}
