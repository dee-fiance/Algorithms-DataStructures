/**      1 
        / \ 
       2   5 
      / \   \ 
     3   4   6 

The flattened tree should look like: 

   1 
    \ 
     2 
      \ 
       3 
        \ 
         4 
          \ 
           5 
            \ 
             6 

Do this in-place. 
Time: O(n) 
**/
 

public class FlattenBT { 
  public class TreeNode { 
    int val; 
    TreeNode left; 
    TreeNode right; 
    TreeNode(int v) { 
      val=v; 
      left=right=null; 
    } 
  } 

  TreeNode root; 
  BinaryTree() { root = null; } 

  public void flatten () { 
    if(root == null) return null; 
    Stack<TreeNode> s = new Stack<TreeNode>(); 
    s.push(root); 

    while(!s.isEmpty()) { 
      TreeNode c = s.pop(); 
      if(c.right!=null) s.push(c.right); 
      if(c.left!=null) s.push(c.left); 
      if(c.left!=null) 
        c.left = null; 
      if (!s.isEmpty()) 
        c.right = s.peek(); 
    } 
  } 
  public void preOrder() { 
    preOrder(root); 
  } 
  private void preOrder(TreeNode root) { 
    if(root == null) return; 
    System.out.print(root.val+ " "); 
    preOrder(root.left); 
    preOrder(root.right); 
  } 

  public void constructTree() { 
    root = new TreeNode(1); 
    root.left = new TreeNode(2); 
    root.left.left = new TreeNode(3); 
    root.left.left.right = new TreeNode(4); 
    root.left.right = new TreeNode(5); 
    root.left.right.left = new TreeNode(6); 
    root.right = new TreeNode(7); 
    root.right.left = new TreeNode(8); 
    root.right.right = new TreeNode(9); 
  } 
  public static void main(String[] args) { 
    FlattenBT bt =new FlattenBT(); 
    bt.constructTree(); 
    bt.preOrder(); 
    bt.flatten(); 
    System.out.println(); 
    bt.preOrder(); 
  } 
}
