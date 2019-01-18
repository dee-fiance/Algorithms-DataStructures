/**
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL

Time: O(n), Space: O(logN)
**/

public void populateNextPointers() {
  if(root == null) return;
    
  Queue<TreeNode> queue = new LinkedList<TreeNode>();
  queue.add(root);
  
  while(!queue.isEmpty()) {
    TreeNode c = queue.remove();
    if(c.left != null) 
       c.left.next = ( c.right == null) ? 
                      ((c.next.left == null) ? c.next.right : c.next.left)
                      : c.right;
    if(c.right!= null) 
      c.right.next = (c.next.left == null) ? c.next.right : c.next.left;

    if(c.left!=null) queue.add(c.left);
    if(c.right!=null) queue.add(c.right);
  }
  return;
}
