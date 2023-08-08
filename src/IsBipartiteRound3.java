import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//Input:
//3
//2 1
//1 2
//7 8
//1 2 2 3 1 6 2 4 4 6 2 7 7 5 5 3
//7 9
//1 2 2 3 1 6 2 4 4 6 2 7 7 5 5 3 2 5
//4 3
//1 2 2 3 3 4
//
//Output
//1 2
//4 1 3 4 7
//-1
//2 2 4


public class IsBipartiteRound3 {

	static int vertices,edges;
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);

		vertices = scanner.nextInt();
		edges = scanner.nextInt();
		
		Graph g = new Graph(vertices);
		for(int i = 0; i < edges; i++)
		{
			g.addEdges(scanner.nextInt() - 1, scanner.nextInt() - 1);
		}
		
		if(g.isBipartite())
		{
			g.printCycle();
		}
		else
			System.out.println("Not Bipertite");
		

	}
	
	static class Graph
	{
		List<Integer>[] edgeList;
		int vertices;
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
			}
					
			return true;
		}
		
		void printCycle()
		{
			System.out.println(Arrays.toString(colour));
		}
		
	}

}
