import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//Detect Cycle and print in sorted order
// 5 5
// 1 2 2 4 4 5 5 3 3 2
// Output : 2 3 4 5

//5 5
//2 4 2 3 4 3 1 5 5 1
//1 5

public class CycleDirectedGraph {
	
	static int vertices, edges;
	static int[] parent;
	static boolean[] visited,path;
	static int[][] graph;
	static List<Integer> printCycle;
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		vertices = scanner.nextInt();
		edges = scanner.nextInt();
		
		parent = new int[vertices];
		visited = new boolean[vertices]; path = new boolean[vertices];
		graph = new int[vertices][vertices];
		
		Arrays.fill(parent, -1);
		for(int[] row : graph)
			Arrays.fill(row, -1);
		
		for(int i = 0; i < vertices; i++)
		{
			graph[scanner.nextInt() - 1][scanner.nextInt() - 1] = 1;
		}
		
		for(int z = 0; z < vertices; z++)
		{
			if(!visited[z])			
			{
				if(detectCycle(z))
				{
					printCycle(z);
					break;
				}
			}
		}
	}
	
	static boolean detectCycle(int z)
	{
		visited[z] = true;
		path[z] = true;
		
		for(int i = 0; i < graph.length; i++)
		{
			if(graph[z][i] == 1)
			{
				if(!visited[i])
				{
					parent[i] = z;
					if(detectCycle(i))
						return true;
				}
				else if(path[i])
				{
					parent[i] = z;
					return true;
				}
			}
		}
				
		path[z] = false;
		return false;
	}
	
	static void printCycle(int z)
	{
		printCycle = new ArrayList<>();
		int start = 0;
		
		System.out.println(z);
		System.out.println(Arrays.toString(parent));
		if(z == 0)
			start = z+1;
		
		while(start != -1 && !printCycle.contains(start))
		{
			printCycle.add(start);
			start = parent[start];
		}
		
		
		
		for(int k = 0; k < printCycle.size(); k++)
		{
			System.out.print(printCycle.get(k));
		}
		
	}
	
}
