/**
#medium
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each
operation is counted as 1 step.)
You have the following 3 operations permitted on a word: 
a) Insert a character
b) Delete a character
c) Replace a character 

Time and Space: O(m*n)
**/

class EditDistance {
public:
int minDistance(String word1, String word2) { 
	int m = word1.length(), n = word2.length();       
	int[][] dp = new int[m+1][n+1];
	If(word1.charAt(0) == word2.charAt(0))
		dp[0][0] = 0; 
	Else dp[0][0] = 1;
        
	for (int i = 1; i < m; i++)
       dp[i][0] = dp[i-1][0]+1;
           for (int j = 1; j < n; j++)
                 dp[0][j] = dp[0][j-1]+1;

	for (int i = 1; i <= m; i++) {
		for (int j = 1; j <= n; j++) { 

			if (word1.charAt(i ) == word2.charAt(j )) 
				dp[i][j] = dp[i - 1][j - 1]; 
			else 
				dp[i][j] = 1 + min(dp[i - 1][j - 1] , min(dp[i][j - 1], dp[i - 1][j]));
		}
	}
	
     return dp[m][n];
    }
}
/** OUTPUT
i/j	s	a	t	u	r	d	a	y
s	  0	1	2	3	4	5	6	7
u	  1	1	2	2	3	4	5	6
n	  2	2	2	3	3	4	5	6
d	  3	3	3	3	4	3	4	5
a	  4	3	4	4	4	4	3	4
y	  5	4	4	5	5	5	4	3
**/
