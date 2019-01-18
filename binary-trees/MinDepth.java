/**
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node. 
Time: O(n) 

Using level order traversal, we need not traverse the entire tree. If we find a leaf node at a level, we stop. 
**/
public class MinDepth {
  public int minDepth (TreeNode root) { 
      if(root == null) 
          return 0; 

      LinkedList<TreeNode> current = new LinkedList<TreeNode>(); 
      LinkedList<TreeNode> next = new LinkedList<TreeNode>(); 
      current.add(root); 

      int count = 0; 
      while(!current.isEmpty()){ 
          TreeNode node = current.poll(); 
          if(node.left == null && node.right==null) break; 

          if(node.left != null) 
              next.add(node.left); 
          if(node.right != null) 
              next.add(node.right); 

          if(current.isEmpty()){ 
              current = next; 
              next = new LinkedList<TreeNode>(); 

              count++; 
          } 
      } 
      return (count+1); 
  }
}
