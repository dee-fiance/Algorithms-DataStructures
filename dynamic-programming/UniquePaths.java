/**
A robot is located at the top-left corner of a m x n grid. 
It can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid.

Solution:
Input: m = 7, n = 3
Output: 28

1	  1 	1 	1 	1 	1 	1
1 	2 	3 	4 	5 	6 	7
1 	3 	6 	10  15  21	28

Time: O(m*n)
**/

public int uniquePaths(int m, int n) {
   int[][] paths = new int[n][m];
  for(int i = 0; i< m; i++)
    paths[0][i] = 1;
  for(int j = 1; j< n ; j++) 
    paths[j][0] = 1;
	
  for(int i = 1; i< m; i++) {
    for(int j = 1; j< n ; j++) 
      paths[i][j] = paths[i][j-1] + paths[i-1][j];
	
  return paths[n-1][m-1];
}
