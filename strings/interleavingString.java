/***
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
For example,
Given:
s1 = "aabcc",
s2 = "dbbca",
When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.
***/

import java.util.*;

public class interleavingString {
  public boolean isInterleaving(String s1, String s2, String s3) {
    int m = s1.length();
    int n = s2.length();
    int l = s3.length();
    boolean dp[][] = new boolean[m+1][n+1];
    
    if(m+n != l)
      return false;
      
    for(int i = 0; i <= m; i++) {
      for(int j = 0; j <= n ; j++) {
        if(i ==0 && j ==0) {
          dp[i][j] = true;
          continue;
         }
          
        int k = i+j-1;

        if(i==0)
          dp[i][j] = (s2.charAt(j-1) == s3.charAt(k) ? dp[i][j-1] : false); 
        
        else if(j==0)
          dp[i][j] = (s1.charAt(i-1) == s3.charAt(k) ? dp[i-1][j] : false); 
          
        else
          dp[i][j] = (s1.charAt(i-1) == s3.charAt(k) ? dp[i-1][j] : false) ||
                     (s2.charAt(j-1) == s3.charAt(k) ? dp[i][j-1] : false) ;
                     
      } //for
    } //for
    return dp[m][n];
  }
  public static void main(String[] args) { 
    interleavingString i = new interleavingString();
    System.out.println("Is interleaving: "+ i.isInterleaving("aabcc", "dbbca", "aadbbcbcac"));
    System.out.println("Is interleaving: "+ i.isInterleaving("aabcc", "dbbca", "aadbbbaccc"));
  }
}
