/***
Given several boxes with different colors represented by different positive numbers. 
You may experience several rounds to remove boxes until there is no box left. Each time you can choose some continuous 
boxes with the same color (composed of k boxes, k >= 1), remove them and get k*k points.
Find the maximum points you can get.
Example 1:
Input:
[1, 3, 2, 2, 2, 3, 4, 3, 1]
Output:
23
Explanation:
[1, 3, 2, 2, 2, 3, 4, 3, 1] 
----> [1, 3, 3, 4, 3, 1] (3*3=9 points) 
----> [1, 3, 3, 3, 1] (1*1=1 points) 
----> [1, 1] (3*3=9 points) 
----> [] (2*2=4 points)
Note: The number of boxes n would not exceed 100.

Input:
[1, 3, 2, 2, 2, 3, 4, 3, 1, 3]
Output:
28
***/

/***
Time: O(n^4) .  Space: O(n^3)

Solution:
Source: https://leetcode.com/problems/remove-boxes/discuss/101310/Java-top-down-and-bottom-up-DP-solutions
https://leetcode.com/problems/remove-boxes/discuss/101325/Java-DP-%2B-Memorization-60ms

=> T(i, j, k) which denotes the maximum points possible by removing the boxes of subarray boxes[i, j] with k boxes 
attached to its left of the same color as boxes[i]
=> Our original problem now becomes T(0, n - 1, 0), since there is no boxes attached to the left of the input array 
at the beginning.
a. T(i, i - 1, k) = 0: no boxes so no points, and this is true for any k (you can interpret it as nowhere to attach the boxes).
b. T(i, i, k) = (k + 1) * (k + 1): only one box left in the subarray but we've already got k boxes of the same color 
attached to its left, so the total number of boxes of the same color is (k + 1) and the maximum point is (k + 1) * (k + 1).

=> The recurrence relation is as follows and the maximum points will be the larger of the two cases:
a. If we remove boxes[i] first, we get (k + 1) * (k + 1) + T(i + 1, j, 0) points, where for the first term, 
instead of 1 we again get (k + 1) * (k + 1) points for removing boxes[i] due to the attached boxes to its left; 
and for the second term there will be no attached boxes so we have the 0 in this term.
b. If we decide to attach boxes[i] to some other box of the same color, say boxes[m], then from our analyses above, 
the total points will be T(i + 1, m - 1, 0) + T(m, j, k + 1), where for the first term, since there is no attached boxes 
for subarray boxes[i + 1, m - 1], we have k = 0 for this part; while for the second term, the total number of attached boxes 
for subarray boxes[m, j] will increase by 1 because apart from the original k boxes, we have to account for boxes[i] now, 
so we have k + 1 for this term. But we are not done yet. What if there are multiple boxes of the same color as boxes[i] 
within subarray boxes[i + 1, j]? We have to try each of them and choose the one that yields the maximum points. 
Therefore the final answer for this case will be: max(T(i + 1, m - 1, 0) + T(m, j, k + 1)) 
where i < m <= j && boxes[i] == boxes[m].
***/

public class removeBoxes {
    public int removeBoxes(int[] boxes) {
        if (boxes == null || boxes.length == 0) {
            return 0;
        }
 
        int size = boxes.length;
        int[][][] dp = new int[size][size][size];
 
        return get(dp, boxes, 0, size-1, 0);
    }
    
    
     private int get(int[][][] dp, int[] boxes, int i, int j, int k) {
        if (i > j) {
            return 0;
        } else if (i == j) {
            return (k+1) * (k+1);
        } else if (dp[i][j][k] != 0) {
            return dp[i][j][k];
        } else {
            int points = get(dp, boxes, i + 1, j, 0) + ((k+1) * (k+1));
 
            for (int m = i + 1; m <= j; m++) {
                if (boxes[i] == boxes[m]) {
                    points = Math.max(points, get(dp, boxes, i + 1, m - 1, 0) + get(dp, boxes, m, j, k + 1));
                }
            }
 
            dp[i][j][k] = points;
            return points;
        }
     }
     
     public static void main(String[] args) { 
	     removeBoxes r = new removeBoxes();
	     int[] boxes = new int[] {1, 3, 2, 2, 2, 3, 4, 3, 1, 3}; //28
	     System.out.println("Max points: "+ r.removeBoxes(boxes));
	   }
 
}
