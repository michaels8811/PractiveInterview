import java.util.Arrays;
import java.util.Scanner;

//Detect Cycle and print in sorted order
//5 5
//1 2 2 4 4 5 5 3 3 2
//Output : 2 3 4 5

//5 5
//2 4 2 3 4 3 1 5 5 1
//1 5

public class CycleDirectedGraphRound2 {

	static boolean[] visited, path;
	static int[] parent;
	static int vertices, edges;
	static int[][] graph;
	
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		
		vertices = scanner.nextInt();
		edges = scanner.nextInt();
		visited = new boolean[vertices];
		path = new boolean[vertices];
		graph = new int[vertices][vertices];
		parent = new int[vertices];
		
		Arrays.fill(parent, -1);		
		
		for(int[] row : graph)
			Arrays.fill(row, 0);

		for(int i = 0; i < edges; i++)
		{
			graph[scanner.nextInt() - 1 ][scanner.nextInt() - 1 ] = 1;
		}
		
		boolean checkCycle = false;
		for(int i = 0; i < vertices; i++)
		{
			if(!visited[i])
			{
				if(haveCycle(i))
				{
					printCycle();
					checkCycle = true;
					break;
				}
			}
		}
		
		if(!checkCycle)
			System.out.println("Dont have Cycle");
	}

	static boolean haveCycle(int x)
	{
		visited[x] = true;
		path[x] = true;
		
		for(int i = 0; i < graph.length; i++)
		{
			if(graph[x][i] != 0)
			{
				if(!visited[i])
				{
					parent[i] = x;
					if(haveCycle(i))
						return true;
				}
				else
				{
					if(path[i])
					{
						parent[i] = x;
						return true;
					}						
				}
			}
		}
		
		
		path[x] = false;
		return false;
	}
	
	static void printCycle()
	{
		System.out.println("cycle: "+Arrays.toString(parent));
	}
	
}
