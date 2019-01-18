/**
in-order:   4 2 5 (1) 6 7 3 8 
pre-order: (1) 2 4 5  3 7 6 8 

Time: O(n)
**/
public class ConstructBST1 {
  public TreeNode buildTree(int[] preorder, int[] inorder) { 
      int preStart = 0; 
      int preEnd = preorder.length-1; 
      int inStart = 0; 
      int inEnd = inorder.length-1; 

      return construct(preorder, preStart, preEnd, inorder, inStart, inEnd); 
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
  private TreeNode construct(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd){ 
      if(preStart>preEnd||inStart>inEnd){ 
          return null; 
      } 

      int val = preorder[preStart]; 
      TreeNode p = new TreeNode(val); 

      int index = findIndex(inOrder, val);     


      p.left = construct(preorder, preStart+1, preStart+(index-inStart), inorder, inStart, index-1); 
      p.right= construct(preorder, preStart+(index-inStart)+1, preEnd, inorder, index+1 , inEnd); 

      return p; 
  } 
}
