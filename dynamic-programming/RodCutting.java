/**
Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n. 
Determine the maximum value obtainable by cutting up the rod and selling the pieces. 
For example, if length of the rod is 8 and the values of different pieces are given as following, 
then the maximum obtainable value is 22 (by cutting in two pieces of lengths 2 and 6)
length   | 1   2   3   4   5   6   7   8  
--------------------------------------------
price    | 1   5   8   9  10  17  17  20
 
Whereas, in the below example, the maximum profit would be 24 (by cutting rod into 8 pieces of length 1)
length   | 1   2   3   4   5   6   7   8  
--------------------------------------------
price    | 3   5   8   9  10  17  17  20

Time Complexity is O(n^2)
**/
class RodCutting {
    /* Returns the best obtainable price for a rod of length n and price[] as prices of different pieces */
    public int cutRod(int price[],int n) {
        int dp[] = new int[n+1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
 
        // Build the table val[] in bottom up manner and return the last entry from the table
        for (int i = 1; i<=n; i++) {
            for (int j = i; j > 0; j--)
                       dp[i][j] = Math.max(dp[i][j], price[j] + dp[i-j]);
        }
         return dp[n];
    }
 
    /* Driver program to test above functions */
    public static void main(String args[])
    {
        int arr[] = new int[] {1, 5, 8, 9, 10, 17, 17, 20};
        int size = arr.length;
        RodCutting r = new RodCutting();
        System.out.println("Maximum Obtainable Value is " + r.cutRod(arr, size));
    }
}
