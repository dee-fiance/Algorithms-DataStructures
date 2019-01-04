/***
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right,
but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.
The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders 
of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

Example 1
Input 1: a maze represented by a 2D array
0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0
Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)
Output: true
Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
**/

Solution:
import java.util.*;

public class BallInAMaze1 {
 
public static class Point {
	int x;
	int y;
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	private boolean isEqual(Point p) {
		if(this.x == p.x && this.y == p.y)
			return true;
		else return false;
	}
}

public boolean hasPath(int[][] maze, Point start, Point dest) {
	Point[] dir = new Point[] {new Point(0,1), 
						new Point(0,-1),
						new Point(1,0),
						new Point(-1,0) };
	boolean[][] visited = new boolean[maze.length][maze[0].length];
	LinkedList<Point> queue = new LinkedList<Point>();
	queue.add(start);
	visited[start.x][start.y] = true;
	
	while(!queue.isEmpty()) {
		Point c = queue.poll();
		if(c.isEqual(dest)) 
			return true;
		for(Point p : dir) {
			int dx = c.x; int dy = c.y;
			while(dx >= 0 && dx < maze.length && dy >=0 && dy<maze[0].length && maze[dx][dy]==0) {
				dx += p.x;
				dy += p.y;
			}
			dx -= p.x;
			dy -= p.y;
			if(!visited[dx][dy]) {
				queue.add(new Point(dx,dy));
				visited[dx][dy] = true;
			}
		}
	}
	return false;
}

public static void main(String[] args) {
	  BallInAMaze1 s = new BallInAMaze1(); 
	  int[][] maze = new int[][] { { 0, 0, 1, 0, 0},
							  { 0, 0, 0, 0, 0},
							  { 0, 0, 0, 1, 0},
							  { 1, 1, 0, 1, 1},
							  { 0, 1, 0, 0, 0} };
	  Point start = new Point(0,4);
	  Point destination = new Point(4,4); // true
	  //Point destination = new Point(4,0); // false
	  System.out.println("Has path: "+ s.hasPath(maze, start, destination));
  
	}
} 
