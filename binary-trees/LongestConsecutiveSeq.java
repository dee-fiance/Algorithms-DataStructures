
/**
The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
The longest consecutive path need to be from parent to child (cannot be the reverse). 
   1 
    \ 
     3 
    / \ 
   2   4 
        \ 
         5 

Longest consecutive sequence path is 3-4-5, so return 3. 
Time: O(n) 
**/
  public int longestConsecutiveSeq(TreeNode root) { 
    if(root == null) return 0; 
    return helper(root, 0); 
  } 

  private int helper(TreeNode root, int paths) { 
    int l=0; int r=0; 
    if(root.left != null) {
     l = (root.left == (root.val+1)) ) ? 
         helper(root.left, paths+1); 
       : helper(root.left, paths); 
    }

    if(root.right != null) {
      r = (root.right== (root.val+1)) ) ?
          helper(root.right, paths+1); 
        : helper(root.right, paths); 
    }
    return Math.max(l,r); 
  } 
