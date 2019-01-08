/**
Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.

Loop through the array, each time remember the max and min value for the previous product, the most important thing is to update the max and min value: we have to compare among max * A[i], min * A[i] as well as A[i], since this is product, a negative * negative could be positive.
Time : O(n)
**/

class MaxProductSubarray {
  public int maxProduct (int[] num) {
    if(num.length == 0 || num == null)
      return 0;
 
    int max = num[0];
    int min = num[0];
    int result = num[0];
    for(int i=1; i< num.length; i++) {
      int store = max;
      max = Math.max( Math.max(num[i]* max, num[i]*min), num[i] );
      min = Math.min( Math.min(num[i]* store, num[i]*min), num[i] );
      if(max > result)
        result = max;
    }
    return result;
 
  }
  public static void main (String args[]) {
     MaxProductSubarray m = new MaxProductSubarray();
     int[] input = new int[]{6,-3,-10,0,2};
     System.out.println("Max product is: " +m.maxProduct(input));
  }
}
