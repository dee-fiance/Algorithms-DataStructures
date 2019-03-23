/**
Steps = {1,3,5}
Dp[0]=1; dp[1]=1;
Formula is dp[I-1] + dp[I-3] + dp[I-5]

Dp[2] = dp[i-1]; as 2 >= 1 in {1,3,5}
Dp[3] = dp[i-1] +dp[i-3]; as 3 >= 1,3 in  {1,3,5}
Dp[4] = dp[i-1] +dp[i-3]; as 4 >= 1,3 in  {1,3,5}
**/

class climbStairs { 
    public int climbStairs(int n, int[] steps) { 
        if(n==0) 
            return 0; 
        int[] dp = new int[n+1]; 
        dp[0] = 1; 
        dp[1] = 1; 
        int index=2;
        while(index < steps[steps.length-1]) {
          for( int i=0; i< steps.length; i++) 
            dp[index] += (index >= steps[i]) ? dp[index-steps[i]] : 0;
          index++;
        }
         
        for(int i=5; i <= n;i++) { 
            dp[i] = dp[i-1]+dp[i-3]+dp[i-5];  
        } 
        return dp[n]; 
    } 
    public static void main(String[] args) {
      climbStairs c = new climbStairs();
      int[] steps = new int[]{1,3,5};
      System.out.println("No of ways for 5 stairs: "+ c.climbStairs(5, steps));
      System.out.println("No of ways for 7 stairs: "+ c.climbStairs(7, steps));
      System.out.println("No of ways for 8 stairs: "+ c.climbStairs(8, steps));
      System.out.println("No of ways for 9 stairs: "+ c.climbStairs(9, steps));
      System.out.println("No of ways for 13 stairs: "+ c.climbStairs(13, steps));
    }
} 
