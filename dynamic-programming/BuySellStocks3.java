/**
//HARD
Say you have an array for which the ith element is the price of a given stock on day i.Design an algorithm to find the maximum profit. 
You may complete at most k transactions. 
Note: 
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again). 
Solution: 
There are two options here: transacting at ith day or not transacting on ith day. 
And we need to consider the option that yields max profit for us. 
We need to build a table to maintain this information, 
Example, k=3, sample data= [2 5 7 1 4 3 1 3] 
k/prices                    2 5 7 1 4 3 1 3
0 (cannot transact if k=0)  0 0 0 0 0 0 0 0
1                           0 3 5 5 5 5 5 5
2                           0 3 5 5 8 8 8 8
3                           0 3 5 5 8 8 8 10
T[3][7] =
Max(8, max(T[2][0]+ (3-2), T[2][1]+ (3-5, T[2][2]+3-7), T[2][3]+(3-1), T[2][4]+(3-4), T[2][5]+(3-3), T[2][6]+(3-1), T[2][7]+(3-3)) 
= max(8, max(1,-,-,5+2,7,8,8+2,8) = 10 

Not transacting is simple = T[i][j] = T[i][j-1] 
Transacting on jth day = You sell the stock on jth day (kth transaction) 
You need to finish k-1 transactions before you buy stock for selling it on jth day. 
You need to find max profit out of 1. Bought stock on mth day and sold on jth day and finishing k-1 transactions on mth day. 
T[i][j] = Max( prices[j]-prices[m] + T[i-1][m]), m= 0->j 

Hence, the formula that we arrive at: 
maxVal = max(prices[j]-prices[m] + T[i-1][m]), m=0->j 
T[i][j] = Max( T[i][j-1], maxval) 
**/

public int maxProfit(int k, int[] prices) { 
  if(k==0 || prices.length() == 0) return –1; 
  int T[][] = new int[k+1][prices.length]; 
  for(int i=0;i < prices.length ; i++) 
    T[0][i] = 0; // when no transactions are permitted 
  for(int j=0;j <=k ; j++) 
    T[j][0] = 0; // only one price is available 

  for(int i=0; i <= k ; i++) { 
    for(int j=0;j < prices.length ; j++) { 
      int maxval = 0; 
      for(int m=0; m < j ; m++) { 
        maxval = Math.max(maxval, prices[j]-prices[m] + T[i-1][m]); 
      } 
      T[i][j] = Math.max(T[i][j-1], maxval); 
    }
  }
  return T[k][prices.length-1]; 
} 

//Time complexity is O(k * number of days ^ 2) 
