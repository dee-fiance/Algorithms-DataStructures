/**
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
For example,
Given:
s1 = "aabcc",
s2 = "dbbca",
When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.

Solution:
O(n*m)
s3 = "aadbbcbcac"
	0	d	b	b	c 	a
0	T	F	F	F	F	F
a 	T	F	F	F	F	F
a 	T	T	T	T	T	F
b 	F	T	T	F	T	F
c	F	F	T	T	T	T
c 	F	F	F	T	F	T
**/

public class InterleavingString {
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
	    InterleavingString i = new InterleavingString();
	    System.out.println("Is interleaving: "+ i.isInterleaving("aabcc", "dbbca", "aadbbcbcac"));
	    System.out.println("Is interleaving: "+ i.isInterleaving("aabcc", "dbbca", "aadbbbaccc"));
	  }
}
