/**
Given a set of items, each with weight and benefit, determine the items to include in the knapsack 
so that the total weight is less than or equal to a given weight limit and the total benefit is maximized. 
For example, if we are given following four items with their corresponding weights and benefits which items 
should we include in the knapsack to maximize the benefit. The weight limit for this knapsack is 10?
 
Item    1	2	3	4
Weight	2	2	4	5
Benefit	3	7	2	9
 
Weight=10, Profit=19, Items included: 1,2,4.


Time Complexity is O(weight*N), N=no of items
**/

class Knapsack {
    public static void main(String[] args) throws Exception {
        int val[] = {10, 40, 30, 50};
        int wt[] = {5, 4, 6, 3};
        int W = 10;
        System.out.println(knapsack(val, wt, W));
   }
 
    public static int knapsack(int val[], int wt[], int W) {
         int N = wt.length;
        //Items are in rows and weight at in columns +1 on each side
        int[][] dp = new int[N + 1][W + 1];
 
        //What if the knapsack's capacity is 0 – Set all columns at row 0 to be 0
 
        for (int col = 0; col <= W; col++) {
            dp[0][col] = 0;
        }
        //What if there are no items at home   
        //Fill the first row with 0
        for (int row = 0; row <= N; row++) {
            dp[row][0] = 0;
        }
 
        for (int item=1;item<=N;item++){
                for (int weight=1;weight<=W;weight++){
                //Is the current items weight less than or equal to running weight
                if (wt[item-1] <= weight){
                    dp[item][weight] = Math.max ( dp[item-1][weight],
                                                  val[item-1] + dp[item-1][weight-wt[item-1]]);
                }
                else {
                //If the current item's weight is more than the running weight, just carry forward the value
without the current item
                    dp[item][weight]=dp[item-1][weight];
                }
            }
        }
        return dp[N][W];
    }
}
