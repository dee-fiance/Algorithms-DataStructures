/**
in-order:   4 2 5  (1)  6 7 3 8 
post-order: 4 5 2  6 7 8 3  (1) 

Time: O(n)
**/
public class ConstructBST2 {
  public TreeNode buildTree(int[] inorder, int[] postorder) { 
    int inStart = 0; 
    int inEnd = inorder.length - 1; 
    int postStart = 0; 
    int postEnd = postorder.length - 1; 

    return buildTree(inorder, inStart, inEnd, postorder, postStart, postEnd); 
  } 
  private int findIndex(int[] inOrder, int val) { 

      //find parent element index from inorder 
      int index=0; 
      for(int i=0; i<inorder.length; i++){ 
          if(val == inorder[i]){ 
              index=i; 
              break; 
          } 
      } 
    return index; 
  } 
  private TreeNode buildTree(int[] inorder, int inStart, int inEnd, 
    int[] postorder, int postStart, int postEnd) { 
    if (inStart > inEnd || postStart > postEnd) 
    return null; 

    int rootValue = postorder[postEnd]; 
    TreeNode root = new TreeNode(rootValue); 

    int index = findIndex(inOrder, val);  

    root.left = buildTree(inorder, inStart, index - 1, postorder, postStart, postStart + index - inStart - 1); 
    root.right = buildTree(inorder, index + 1, inEnd, postorder, postStart + index - inStart, postEnd - 1); 

    return root; 
  } 
}
