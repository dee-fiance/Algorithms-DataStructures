/**
Given a number of dollars, "n" , and a list of dollar values for "m"  distinct coins, C = {c0,c1,c2,…., cm-1} , 
find and print the number of different ways you can make change for "n" dollars if each coin is available in an infinite quantity. 
Solution: 
Example: {1,2,3} Amount=4 
Output= {1,1,1,1}, {1,1,2}, {2,2}, {1,3} = 4 
{2,5,3,6} Amount=10 
Output= {2,2,2,2,2}, {2,5,3}, {5,5}, {2,2,6}, {2,2,3,3} = 5 

Reference: https://algorithms.tutorialhorizon.com/dynamic-programming-coin-change-problem/ 
Recursive solution: 

C() --> count() 
                             C({1,2,3}, 5)                      
                           /             \     
                         /                 \                   
             C({1,2,3}, 2)                 C({1,2}, 5) 
            /       \                      /      \          
           /         \                    /         \    
C({1,2,3}, -1)  C({1,2}, 2)        C({1,2}, 3)    C({1}, 5) 
               /    \             /     \           /     \ 
             /       \           /       \         /        \ 
    C({1,2},0)  C({1},2)   C({1,2},1) C({1},3)    C({1}, 4)  C({}, 5) 
                   / \     / \        /\         /     \          
                  /   \   /   \     /   \       /       \   
                .      .  .     .   .     .   C({1}, 3) C({}, 4) 
                                               / \  
                                              /   \    
                                             .      . 
**/

public void coinChange(int n, int[] coins) { 
        int result = coinChangeRec(coins, coins.length-1, n); 
        System.out.println(result); 
} 
private int coinChangeRec(int[] coins, int no_of_coins, int amount) { 
        if(amount == 0) return 1; 
        if(amount < 0) return 0; 
        if(no_of_coins < 0) return 0; 
        return (coinChangeRec(coins, no_of_coins, amount - coins[no_of_coins])  
               + coinChangeRec(coins, no_of_coins-1, amount)); 
} 

/**
We are computing subproblems multiple times in the recursive solution. Need to store the computed results to use it later. 
Time: 2n 

For every coin we have 2 options, either we include it or exclude it so if we think in terms of binary, 
its 0(exclude) or 1(include). so for example if we have 2 coins, options will be 00, 01, 10, 11. so its 2^2. 
for n coins , it will be 2^n. In all these options we will be checking whether that selection has made the change which is required. 
But if we notice that we are solving many sub-problems repeatedly. 
We can do better by applying Dynamic programming. 
DP solution: 

Create a solution matrix. (solution[coins+1][amount+1]). 
Base Cases: 
if amount=0 then just return empty set to make the change, so 1 way to make the change. 
if no coins given, 0 ways to change the amount. 
Rest of the cases: 
For every coin we have an option to include it in solution or exclude it. 
check if the coin value is less than or equal to the amount needed, if yes then we will find ways by including that coin and excluding that coin. 
Include the coin: reduce the amount by coin value and use the sub problem solution (amount-v[i]). 
Exclude the coin: solution for the same amount without considering that coin. 
If coin value is greater than the amount then we can’t consider that coin, so solution will be without considering that coin. 
Equation: 


solution[i][j] = 0 , if i=0  
               = 1 , if j=0 
               = solution[i – 1][j] + solution[i][j – coin[i – 1]] , if(coin[i]<=j) 
               = solution[i – 1][j] , if(coin[i]>j) 

Time: O(nm) 
**/
 

public class WaysToCoinChange { 
  public static int coinChange(int[] coins, int amount) { 
  int[][] solution = new int[coins.length + 1][amount + 1]; 

  // if amount=0 then just return empty set to make the change 
  for (int i = 0; i <= coins.length; i++) { 
    solution[i][0] = 1; 
  } 

  // if no coins given, 0 ways to change the amount 
  for (int i = 1; i <= amount; i++) { 
    solution[0][i] = 0; 
  } 
  // now fill rest of the matrix. 

   for (int i = 1; i <= coins.length; i++) { 
      for (int j = 1; j <= amount; j++) { 
        // check if the coin value is less than the amount needed 
        if (coins[i - 1] <= j) { 
        // reduce the amount by coin value and use the subproblem solution (amount-coins[i]) and add the solution from the top to it 
          solution[i][j] = solution[i - 1][j]	+ solution[i][j - coins[i - 1]]; 
        } else { 
        // just copy the value from the top 
          solution[i][j] = solution[i - 1][j]; 
        } 
      } 
    } 
    return solution[coins.length][amount]; 
  } 

  public static void main(String[] args) { 
    WaysToCoinChange w = new WaysToCoinChange();
    int amount = 5; 
    int[] coins = { 1, 2, 3 }; 
    System.out.println("By Dynamic Programming " + w.coinChange(coins, amount)); 
  } 
} 
