/**
The thief has found himself a new place for his thievery again. 
There is only one entrance to this area, called the "root." 
Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that 
"all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses 
were broken into on the same night.
Determine the maximum amount of money the thief can rob tonight without alerting the police.
Example 1:
     3
    / \
   2   3
    \   \ 
     3   1
Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:
     3
    / \
   4   5
  / \   \ 
 1   3   1
Maximum amount of money the thief can rob = 4 + 5 = 9.

Solution:
Use level order traversal to find sum for each level and then take sums of odd and even indices to find out the max.
Time: O(n)

**/

public class HouseRobber3 {
  public int houseRobber3 (TreeNode root) {
    ArrayList<Integer> queue = levelOrder(root);
    for(int i=0;i<queue.length;i++){
      If(i%2==0) even+= queue.get(i);
      Else odd+=queue.get(i);
    } 
    return Math.max(odd,even);
  }

  public ArrayList<Integer> levelOrder(TreeNode root) {
      ArrayList<Integer> al = new ArrayList<Integer>();
      int nodeValues = 0;

      if(root == null)
          return al;

      LinkedList<TreeNode> current = new LinkedList<TreeNode>();
      LinkedList<TreeNode> next = new LinkedList<TreeNode>();
      current.add(root);

      while(!current.isEmpty()){
          TreeNode node = current.remove();

          if(node.left != null)
              next.add(node.left);
          if(node.right != null)
              next.add(node.right);

          nodeValues += node.val;
          if(current.isEmpty()){
              current = next;
              next = new LinkedList<TreeNode>();
              al.add(nodeValues);
              nodeValues = 0;
          }

      }
      return al;
  }
}
