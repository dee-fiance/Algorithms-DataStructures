Given a n*n matrix where all numbers are distinct, find the maximum length path (starting from any cell) 
such that all cells along the path are in increasing order with a difference of 1.
We can move in 4 directions from a given cell (i, j), i.e., we can move to (i+1, j) or (i, j+1) or (i-1, j) or (i, j-1) 
with the condition that the adjacent cells have a difference of 1.
Example:
Input:  mat[][] = {{1, 2, 9}
                   {5, 3, 8}
                   {4, 6, 7}}
Output: 4
The longest path is 6-7-8-9. 

Time: O(n2) as dp[i][j] is computed only once.
**/

public int findLongestPathMatrix( int[][] matrix) {
	if(matrix.length == 0 || matrix[0].length == 0 ) return 0;
	int m = matrix.length; int n = matrix[0].length;
	int[][] dp = new int[m][n];
	for(int i=0;i<n;i++) 
	      for(int j=0;j<n;j++) 
	           dp[i][j] = -1; 
	
	int result=0;
	for (int i=0;i<m;i++) {
		for(int j=0; j<n ;j++) {
			if(dp[i][j] == -1) {
				result = findLongestPath(I,j,matrix,dp);
			}
			result = Math.max(result, dp[i][j]);
		}
	}
	return result;
}

private int findLongestPath(int I, int j, int[][] m, int[][] dp) {
	if(i<0 || i>m.length || j<0 || j>m[0].length) return 0;
	
	if(dp[i][j] !=-1) return dp[i][j];
	
	if( m[i+1][j] == m[i][j]+1) 
		dp[i][j] = findLongestPath(i+1,j,m,dp) + 1;
	else If( m[i-1][j] == m[i][j]+1)
		dp[i][j] = findLongestPath(i-1,j,m,dp) + 1;
	else If( m[i][j+1] == m[i][j]+1) 
		dp[i][j] = findLongestPath(i,j+1,m,dp) + 1;
	else If( m[i][j-1] == m[i][j]+1)
		dp[i][j] = findLongestPath(i,j-1,m,dp) + 1;
	else dp[i][j] = 1;
	
	return dp[i][j];
}
