/**
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the 
sum of all numbers along its path.

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.

Time: O(n2) Space: O(n2) 
**/

public class MinPathSum {
    int minPathSum( int[][] grid ) {
        int m = grid.size();
        int n = grid[0].size();
        int[][] sum = new int[m][n];
        sum[0][0]=0;

        for (int i = 1; i < m; i++)
          sum[i][0] = sum[i - 1][0] + grid[i][0];
        for (int j = 1; j < n; j++)
          sum[0][j] = sum[0][j - 1] + grid[0][j];
        for (int i = 1; i < m; i++)
          for (int j = 1; j < n; j++)
            sum[i][j]  = min(sum[i - 1][j], sum[i][j - 1]) + grid[i][j];
        
        return sum[m - 1][n - 1];
   }
} 

// Optimization in space. As we need only the previous row to compute the next row.
// Time: O(n2) Space: O(n)

public class MinPathSum {
    int minPathSum(int [][] grid ) {
        int m = grid.size();
        int n = grid[0].size();
        int[] cur = new int[m];
        cur[0]=grid[0][0];
        for (int i = 1; i < m; i++)
          cur[i] = cur[i - 1] + grid[i][0];
        for (int j = 1; j < n; j++) {
          cur[0] += grid[j][0];
          for (int i = 1; i < m; i++) {
            cur[i] = min(cur[i-1], cur[i] ) + grid[i][j];
          }
        }
        return cur[m - 1];
     }
}
