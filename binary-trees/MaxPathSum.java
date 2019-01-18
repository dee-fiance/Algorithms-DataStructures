/**
Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along 
the parent-child connections. The path must contain at least one node and does not need to go through the root.
Example :

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Time: O(n)
**/
public class MaxPathSum {
  int max=0;
  
  public int findMaxPathSum(TreeNode root) {
    int maxSum = findMaxPathSumHelper(root);
    return Math.max(max,  maxSum);
  }
	
  private int findMaxPathSumHelper (TreeNode root) {
    if(root == null) return 0;
    if(root.left == null && root.right == null)
      return root.val;
    int left = findMaxPathSumHelper(root.left);
    int right = findMaxPathSumHelper(root.right);
		
    max = Math.max(max, root.val+left+right);
		
    return Math.max(root.val+left, root.val+right) ;
   }
}
