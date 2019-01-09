/**
Say you have an array for which the ith element is the price of a given stock on day i. 
If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), 
design an algorithm to find the maximum profit. 

Prices = [10,15,2,7,18,55,30,46] , Profit = 53
**/ 

public int maxProfit(int[] prices) {
  int profit = 0;
  for(int i=1; i<n; i++) { 
    for(int j=0; j<i; j++) { 
      if( prices[j] < prices[i] && profit < prices[j] - prices[i] ) 
        profit = prices[j] - prices[i]; 
    } 
  } 
//Time = O(n2) 

public int maxProfit(int[] prices) {
  int min = prices[0]; 
  int profit = 0; 
  for(int i=1; i<n; i++) { 
    profit = max(profit, prices[i]-min); 
    min = min(min, prices[i]); 
  } 
  return profit; 
}
//Time = O(n)
