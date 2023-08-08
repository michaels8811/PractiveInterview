import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

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
// THIS IS SOOOO EASY

public class IsBipartite {

	public static void main(String[] args) {
		
		
		Scanner scanner = new Scanner(System.in);
		
		for(int k = 1 ; k<3 ; k++)
		{
			int vertices, edges;
			
			vertices = scanner.nextInt();
			edges = scanner.nextInt();
			
			Graph g = new Graph(vertices);
			
			for(int i = 0 ; i < edges ; i++)
			{
				g.addEdges(scanner.nextInt() -1 , scanner.nextInt() -1 );
			}
			
			if(g.isBipertite())
			{
				g.printList();
			}
			else
				System.out.println(-1);
		}

	}

	
	static class Graph
	{
		List<Integer>[] edgeList;
		int[] colour;
		int vertices;		
		
		Graph(int vertices)
		{
			edgeList = new ArrayList[vertices];
			colour = new int[vertices];
			this.vertices = vertices;
			
			for(int i=0; i<vertices; i++)
			{
				edgeList[i] = new ArrayList<Integer>();
				colour[i] = -1;
			}
		}
		
		void addEdges(int vertexA, int vertexB)
		{
			edgeList[vertexA].add(vertexB);
			edgeList[vertexB].add(vertexA);			
		}
		
		boolean isBipertite()
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
		
		void printList()
		{
			List<Integer> printList = new ArrayList<>();
			
			
			for(int i = 0; i < colour.length; i++)
			{
				if(colour[i] == 1)
					printList.add(i+1);		
			}
			
			System.out.print(printList.size()+" ");
			for(int i = 0; i < printList.size(); i++)
			{
				System.out.print(printList.get(i)+" ")	;
			}
			System.out.println(Arrays.toString(colour));
		}
		
		
	}
	
}
