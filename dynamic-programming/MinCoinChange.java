/**
You are given coins of different denominations and a total amount of money amount. 
Write a function to compute the fewest number of coins that you need to make up that amount. 
If that amount of money cannot be made up by any combination of the coins, return -1. 

Solution: 
Input: coins[] = {25, 10, 5}, V = 30 
Output: Minimum 2 coins required 
We can use one coin of 25 cents and one of 5 cents  

Input: coins[] = {9, 6, 5, 1}, V = 11 
Output: Minimum 2 coins required 
We can use one coin of 6 cents and 1 coin of 5 cents 

dp[i] => stores the min coins required to make amount i. 
  0(amount) 1        2        3   4   5        6        7       8       9       10      11 
  0         dp[0]+1  dp[1]+1  3   4   dp[0]+1  dp[0]+1  dp[1]+1 dp[2]+1 dp[0]+1 dp[1]+1 dp[5]+1
            0+1=1    1+1=2            1        1        2       3       1       2       2
i = 1 -> amount 
For each coin less than i 
dp[i] = min (dp[i], dp[ i - coins[j] ] + 1) 

So, to find out min coins for amount 10, when we see coin=9, we check min coins to make amount 10-9=1 which is 1 
and add 1 coin to this which makes it 2. 
To find min coins for amount 11,  
For coin=9 => we check min coins to make amount 11-9=2 which is dp[2]+1 = 3  
For coin=6 => 11-6=5 which is dp[5]+1 = 2 
For coin=5 => 11-5=6 which is dp[6]+1 = 2 
For coin=1 => 11-1=10 which is dp[10]+1 = 3 
Min is 2. 

Time: O(n2) 
**/

public class MinCoinChange {
  public int coinChange(int[] coins, int amount) { 
      if(amount==0) return 0; 

      int[] dp = new int [amount+1]; 
      dp[0]=0; // do not need any coin to get 0 amount 
      for(int i=1;i<=amount; i++) 
          dp[i]= Integer.MAX_VALUE; 

      for(int i=1; i <= amount; i++){ 
          for(int j=0; j < coins.length; j++){ 
              if( coins[j] <= i) 
                      dp[i] = Math.min(dp[i], dp[ i - coins[j] ]  + 1); 
          } 
      } 

      if(dp[amount] >= Integer.MAX_VALUE) 
          return -1; 

      return dp[amount]; 
  } 
}
