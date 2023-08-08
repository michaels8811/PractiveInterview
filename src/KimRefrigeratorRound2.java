import java.util.Arrays;
import java.util.Scanner;

//5
//0 0 100 100 70 40 30 10 10 5 90 70 50 20

public class KimRefrigeratorRound2 {

	static class Point
	{
		int x, y;
		Point(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}
	
	static int[][] dp, graph;
	static int vertices;
	static Point[] points;
	static int INF = 1000000000;
	
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		vertices = scanner.nextInt();
		
		points = new Point[vertices + 2];
		for(int i = 0 ; i < vertices + 2 ; i++)
		{
			points[i] = new Point(scanner.nextInt(), scanner.nextInt());
		}
		
		graph = new int[vertices + 2][vertices + 2];
		for(int i = 0; i < graph.length; i++)
		{
			for(int j = 0; j < graph.length; j++)
			{
				graph[i][j] = Math.abs(points[i].x - points[j].x) + Math.abs(points[i].y - points[j].y);
			}
		}
		
		dp = new int[vertices + 2][1 << vertices + 2];
		for(int[] row : dp)
			Arrays.fill(row, -1);
		
		System.out.println(minDistance(0,1));
		
	}

	static int minDistance(int current, int visited)
	{
		if(visited == ((1 << vertices + 2)-1))
		{
			if(graph[current][0] != 0)
				return graph[current][1];
			else
				return INF;
		}
		
		
		if(dp[current][visited] != -1)
			return dp[current][visited];
		
		
		dp[current][visited] = INF;
		
		for(int i = 0 ; i < vertices+2; i++)
		{
			if((visited & (1 << i)) != 0 && graph[current][i] == 0)
				continue;
			dp[current][visited] = Math.min(dp[current][visited], graph[current][i] + minDistance(i,(visited | 1 << (i))));
		}
		
		
		return dp[current][visited];
	}
}
