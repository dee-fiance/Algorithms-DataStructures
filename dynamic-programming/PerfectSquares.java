/**
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n. 
For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9. 
 
Solution: 
N=8 => min(dp[7]+dp[1], dp[6]+dp[2], dp[5]+dp[3], dp[4]+dp[4]) 
**/

public static int numSquares(int n) { 
    int[] dp = new int[n+1]; 
    Arrays.fill(dp, Integer.MAX_VALUE); 
    dp[0]=0; dp[1]=1; dp[2]=2; dp[3]=3; 

    for(int i=4; i<=n; i++){ 
        for(int j=1; j<=i/2; j++){ 
            if(i==j*j) 
                dp[i]=1; 
            else  
                dp[i]=Math.min(dp[i], dp[j] + dp[i-j]); 
        } 
    } 
  
    return dp[n]; 
} 
// Time: O(n2)
