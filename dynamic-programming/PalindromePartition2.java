/**
#difficult
Given a string s, partition s such that every substring of the partition is a palindrome. Return the minimum cuts needed 
for a palindrome partitioning of s. For example, given s = "aab", return 1 since the palindrome partitioning ["aa","b"] 
could be produced using 1 cut.
Reference: http://www.lifeincode.net/programming/leetcode-palindrome-partitioning-i-and-iijava/
Solution:

Same as PalindromePartition1 problem, just that we need to maintain the minimum cut instead of constructing all 
the possible palindromic substrings.
**/

public class PalindromePartition2 {
  public int minimumCut(String s) {
    int n = s.length();
    boolean[][] palindrome = new boolean[n][n];
    buildMetaData(s, n, palindrome);
    int minCut = minCut(s, n, palindrome);
    return minCut;
  }

  private void buildMetaData(String s, int n, boolean[][] palindrome) {
    for(int i = 0; i<n; i++)
      palindrome[i][i] = true;
    for(int i = 0; i<n-1; i++) {
      if(s.charAt(i) == s.charAt(i+1))
        palindrome[i][i+1] = true;
      else palindrome[i][i+1] = false;
    }
    for(int len = 3; len <= n; len++) {
      for(int i = 0; i< n-len-1 ; i++) {
        if(s.charAt(i) == s.charAt(i+len-1) && palindrome[i][i+len-2])
          palindrome[i][i+len-1] = true;
        else palindrome[i][i+len-1] = false;
      }
    }
  }
/***
We can use a 1-D array D[n] to save the minimum cut. For example, D[i] saves the number of minimum cut of substring(i, n). 
We can start from i = n – 1, and move i from right to left. 
When we want to get the D[i] for a new i, we can check every possible substrings from i to n, 
which means we can use another point j, in which j is between i and n. If substring(i, j) is a palindrome, 
then we can update D[i] = min(D[i], 1 + D[j + 1]).

Time: O(n2)
Space: O(n2)

**/

  private int minCut(String s, int n, boolean[][] palindrome) {
    int[] minCut = new int[n+1];
    for(int i = n; i>=0; i--)
      minCut[i] = n-i-1;

    i       0	1	2	3	4	5	6
    minCut  5	4	3	2	1	0	-1

    for(int i = n-1; i>=0; i--)
      for(int j = i; j < n ; j++) {
        if (isPalindrome[i][j]) 
          minCut[ i ] = Math.min (minCut[ i ], 1 + minCut[ j+1 ] );
      }
    }
    return minCut[0];
  }
}
