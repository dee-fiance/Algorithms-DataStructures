/**
#Google
There are N children standing in a line. Each child is assigned a rating value. 
You are giving candies to these children subjected to the following requirements:
1. Each child must have at least one candy.
2. Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

Example:
Input = [2,4,2,6,1,7,8,9,2,1]
Candies(left -> right) = [1,2,1,2,1,2,3,4,1,1] => based on ascending pairs
Candies(right->left) = [ 1,2,1,2,1,1,1,3,2,1] => based on descending pairs
Result = 1+2+1+2+1+2+3+4+2+1= 19 (add max of left, right)

Time: O(n)
https://www.hackerrank.com/challenges/candies/problem
**/
public class Candy {
  public int candy(int[] ratings) {
        if(ratings.length == null || ratings.length == 0)
            return 0;
        //result
        int left[] = new int[ratings.length];
        int right[] = new int[ratings.length];

        left[0]=1; 
        for(int i=1; i < ratings.length ; i++) {
            //scan from left to right and update for ascending pairs only.
            if(ratings[i] > ratings[i-1]) {
                left[i] = left[i-1] + 1;
            else left[i] = 1;
        }
        int right[ratings.length - 1] = left[ratings.length - 1];
        Int result = Math.max(left[ratings.length - 1], right[ratings.length - 1]);
        for(int i = ratings.length - 2; i >=0 ; i--) {
            //scan right to left for descending pairs
            if(ratings[i] > ratings[i+1])
                right[i] = right[i+1]+1;
            else right[i] = 1;
            result += Math.max(left[i], right[i]);
        }
  }
}
