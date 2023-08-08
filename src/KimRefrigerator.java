import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class KimRefrigerator {

    static int vertices;
    static int[][] graph;
    static int[][] dp;
    static int INF = 1000000000;
    
    static class Point
    {   
        int x, y;
        Point(int x, int y)
        {
            this.x = x; this.y = y;
        }
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        Scanner scanner = new Scanner(System.in);
        // int T = scanner.nextInt();
            
        for(int test = 1; test <= 1 ; test++)
        {
            vertices = scanner.nextInt();
            Point[] points = new Point[vertices+2];
            
            for(int i = 0; i < vertices + 2; i++)
            {
                points[i] = new Point(scanner.nextInt(),scanner.nextInt());
            }
            
            graph = new int[vertices+2][vertices+2];
            
//            for(int[] row : graph)
//            	Arrays.fill(row, -1);

            for(int i=0 ; i < graph.length; i++)
            {
            	for(int j=0; j < graph.length; j++)
            	{
            		graph[i][j] = Math.abs(points[i].x - points[j].x) + Math.abs(points[i].y - points[j].y);
            	}
            }
            
            dp = new int[vertices+2][1 << vertices+2];
            for(int[] row : dp)
            	Arrays.fill(row, -1);
            
            System.out.println(calculateDistance(0,1));
            
            
        }
        scanner.close();
    }
    
    static int calculateDistance(int current, int visited)
    {
    	if(visited == (1 << vertices + 2) - 1)
    	{
    		if(graph[current][0] != 0)
    			return graph[current][1];
    		else
    			return INF;
    	}
    	
    	if(dp[current][visited] != -1)
    		return dp[current][visited];
    	
    	dp[current][visited] = INF;
    	
    	for(int i = 0; i < vertices+2; i++)
    	{
    		if((visited & (1 << i)) != 0 || graph[current][i] == 0)
    		{
    			continue;
    		}
    		dp[current][visited] = Math.min(dp[current][visited], (calculateDistance(i,(visited | (1 << i)))) + graph[current][i]);

    	}
    	
    	return dp[current][visited];
    }
    
}