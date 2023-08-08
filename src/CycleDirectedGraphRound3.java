import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//Detect Cycle and print in sorted order
// 5 5
// 1 2 2 4 4 5 5 3 3 2
// Output : 2 3 4 5

//5 5
//2 4 2 3 4 3 1 5 5 1
//1 5


public class CycleDirectedGraphRound3 {
	
	static boolean[] path, visited;
	static int[][] graph;
	static int vertices, edges;
	static int[] parent;
	static List<Integer> printVertices;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		vertices = scanner.nextInt();
		edges = scanner.nextInt();
		
		graph = new int[vertices][vertices];
		visited = new boolean[vertices];
		path = new boolean[vertices];
		
		for(int[] row : graph)
			Arrays.fill(row, -1);
		
		for(int i = 0 ; i < edges; i++)
		{
			graph[scanner.nextInt() - 1][scanner.nextInt() - 1] = 1;
		}
		
		parent = new int[vertices];
		Arrays.fill(parent, -1);
		
		boolean noCycleExisted = true;
		
		for(int i = 0; i < vertices; i++)
		{
			if(!visited[i])
			{
				if(cycleDetected(i))
				{
					printCycle();
					noCycleExisted = false;
					break;
				}
			}
		}
		
		if(noCycleExisted)
			System.out.println("No Cycle");
		
	}
	
	static boolean cycleDetected(int x)
	{
		visited[x] = true; path[x] = true;
		
		for(int i = 0 ; i < graph.length; i++)
		{
			if(graph[x][i] != -1)
			{
				if(!visited[i])
				{
					parent[i] = x;
					if(cycleDetected(i))
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
		printVertices = new ArrayList<>();
	       
        for(int i = 0 ; i < parent.length; i++)
        {
            if(parent[i] != -1)
            {
            	printVertices.add(i+1);
            }
        }
        
        for(int i = 0 ; i < printVertices.size(); i++)
        {
        	System.out.print(printVertices.get(i) + " ");
        }
	}
	
}
