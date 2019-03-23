import java.util.Arrays;


public class Solution {

public int minSteps(boolean[][] grid, int[] start, int[] end) {
		
		if(grid[start[0]][start[1]] || grid[end[0]][end[1]] )
			return 0;
		int[][] minSteps  = new int[grid.length][grid[0].length]; 
		for(int i = 0 ; i< grid.length; i++)
			Arrays.fill(minSteps[i], Integer.MAX_VALUE);

		boolean[][] visited = new boolean[grid.length][grid[0].length]; 
		dfs(grid, start, end, minSteps, visited, -1);
		
		return minSteps[end[0]][end[1]];
	}
	private void dfs(boolean[][] grid, int[] start, int[] end, int[][] minSteps, boolean[][] visited, int steps) {
		if(grid[start[0]][start[1]] || visited[start[0]][start[1]])
			return;
		
		steps++;
		if(start[0] == end[0] && start[1] == end[1]) {
			minSteps[start[0]][start[1]] = Math.min(minSteps[start[0]][start[1]], steps);
			return;
		}
		
		minSteps[start[0]][start[1]] = Math.min(minSteps[start[0]][start[1]], steps);
		visited[start[0]][start[1]] = true;
		
		/**for(int i = 0 ; i< minSteps.length; i++) {
			for(int j= 0 ; j< minSteps[0].length; j++) {
				System.out.print(minSteps[i][j]+" ");
			}
			System.out.println();
		}**/
		
		int[][] dir = new int[][] {{1,0}, {-1,0},{0,1}, {0,-1}};
		for(int[] d : dir) {
			int r = start[0]+d[0];
			int c = start[1]+d[1];
			if(r<0 || r>=grid.length || c<0 || c>=grid[0].length || grid[r][c] || visited[r][c])
				continue;
			int[] next = new int[] {r,c};
			dfs(grid, next, end, minSteps, visited, steps);
		}
		visited[start[0]][start[1]] = false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution s = new Solution(); 
	    boolean[][] grid = new boolean[][] {{false, false,  false,  false, false},
        {true,  true,   true,  false, false},
        {false, false,  false,  false, true},
        {false, true,  true,  true, false},
        {false, false,  false,  false, false}};

	    int[] start = new int[] {3,4};
	    int[] end = new int[] {0,0};
      System.out.println("Min steps to reach end: " + s.minSteps(grid, start, end)); //15
	}

}
