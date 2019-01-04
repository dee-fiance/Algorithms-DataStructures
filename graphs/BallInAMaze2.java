/***
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right,
but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the 
destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) 
to the destination (included). If the ball cannot stop at the destination, return -1.
The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the 
borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

Example 1
Input 1: a maze represented by a 2D array
0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0
Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)
Output: 12
Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
             The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.

Solution:
Time complexity : O(m * n * max(m,n)). 
Complete traversal of maze will be done in the worst case. 
Here, m and n refers to the number of rows and columns of the maze. 
Further, for every current node chosen, we can travel upto a maximum depth of max(m,n) in any direction.
Space complexity : O(mn). queue size can grow upto m*n in the worst case.
***/

import java.util.*;

public class BallInAMaze2 {
	  
	public static class Point {
		int x;
		int y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public int shortestPath(int[][] maze, Point start, Point dest) {
		Point[] dir = new Point[] {new Point(0,1), 
				new Point(0,-1),
				new Point(1,0),
				new Point(-1,0) };
		LinkedList<Point> queue = new LinkedList<Point>();
		
		int[][] distance = new int[maze.length][maze[0].length];
		for(int[] row : distance) 
			Arrays.fill(row, Integer.MAX_VALUE);
		
		queue.add(start);
		distance[start.x][start.y] = 0;

		while(!queue.isEmpty()) {
			Point c = queue.poll();
			
			for(Point p : dir) {
				int dx = c.x; int dy = c.y;
				int length=0;
				while(dx >= 0 && dx < maze.length && dy >=0 && dy<maze[0].length && maze[dx][dy]==0) {
					dx += p.x;
					dy += p.y;
					length++;
				}
				dx -= p.x;
				dy -= p.y;
				length--;
				
				if(distance[c.x][c.y] + length < distance[dx][dy]) {
					distance[dx][dy] = distance[c.x][c.y] + length;
					queue.add(new Point(dx,dy));
				}
			}
		}
		for(int[] d : distance) {
			for(int dd : d) {
				System.out.print(dd+" ");
			}
			System.out.println();
		}
		return (distance[dest.x][dest.y] == Integer.MAX_VALUE) ?
				-1 : distance[dest.x][dest.y];
		
	}
	public static void main(String[] args) {
        BallInAMaze2 h = new BallInAMaze2(); 
        int[][] maze = new int[][] {	{ 0, 0, 1, 0, 0},
                                      { 0, 0, 0, 0, 0},
                                      { 0, 0, 0, 1, 0},
                                      { 1, 1, 0, 1, 1},
                                      { 0, 1, 0, 0, 0}	};
        Point start = new Point(0,4);
        //Point destination = new Point(4,4); // 12
        //Point destination = new Point(4,0); // -1
        Point destination = new Point(4,2); // 10
        System.out.println("Has path: "+ h.shortestPath(maze, start, destination));
	}
} 
