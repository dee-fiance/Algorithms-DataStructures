public class BinaryTreeTraversals { 
  TreeNode root; 
  BinaryTree() { 
    root = null; 
  } 
  public class TreeNode { 
    int val; 
    TreeNode left; 
    TreeNode right; 
    TreeNode(int v) { 
      val=v; 
      left=right=null; 
    } 
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
    BinaryTree bt =new BinaryTree(); 
    bt.constructTree(); 
    bt.preOrder(); 
    bt.postOrder(); 
    bt.inOrder(); 
    bt.levelOrder();
    bt.verticalOrder();
  } 
  
  //Time: O(n), Space: O(logN)
  public void preOrder() {
    preOrderRec(root);
    preOrderIterative(root);
  }
  private void preOrderRec (TreeNode root) { 
    if(root == null) return; 
    System.out.println(root.val+ " "); 
    preOrderRec(root.left); 
    preOrderRec(root.right); 
  } 

  private void preOrderIterative(TreeNode root) { 
    if(root == null) return; 
    Stack<TreeNode> stack = new Stack<TreeNode>(); 
    LinkedList<Integer> result = new LinkedList<Integer>(); 

    stack.push(root); 
    while(!stack.isEmpty()) { 
      TreeNode c = stack.pop(); 
      result.add(c.val); 

      if(c.right != null) stack.push(c.right); 
      if(c.left != null) stack.push(c.left); 
    } 
  }
  
  //Time: O(n), Space: O(logN) 
  public void postOrder() {
    postOrderRec(root);
    postOrderIterative(root);
  }
  private void postOrderRec (TreeNode root) { 
    if(root == null) return; 
    postOrderRec(root.left); 
    postOrderRec(root.right); 
    System.out.println(root.val+ " "); 
  } 

  private void postOrderIterative(TreeNode root) { 
    if(root == null) return; 
    Stack<TreeNode> stack = new Stack<TreeNode>(); 
    LinkedList<Integer> result = new LinkedList<Integer>(); 

    stack.push(root); 
    while(!stack.isEmpty()) { 
      TreeNode c = stack.pop(); 
      result.addFirst(c.val); 

      if(c.left != null) stack.push(c.left); 
      if(c.right != null) stack.push(c.right); 
    } 
  }
  
  //Time: O(n), Space: O(logN) 
  public void inOrder() {
    inOrderRec(root);
    inOrderIterative(root);
  }
  public void inOrderRec (TreeNode root) { 
    if(root == null) return; 
    inOrderRec(root.left); 
    System.out.println(root.val+ " "); 
    inOrderRec(root.right); 
  } 

  private void inOrderIterative(TreeNode root) { 
    if(root == null) return; 
    Stack<TreeNode> stack = new Stack<TreeNode>(); 
    LinkedList<Integer> result = new LinkedList<Integer>(); 
    TreeNode p = root; 
        while(p!=null){ 
            stack.push(p); 
            p = p.left; 
        } 

        while(!stack.isEmpty()){ 
            TreeNode t = stack.pop(); 
            result.add(t.val); 

            if(t.right!=null){ 
                t= t.right; 
                while(t!=null){ 
                    stack.push(t); 
                    t=t.left; 
                } 
            } 
        } 
        return result; 
  } 

  //Level order: 

  public ArrayList<ArrayList<Integer>> levelOrder() { 
    ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>(); 
    ArrayList<Integer> nodeValues = new ArrayList<Integer>(); 
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
  
        nodeValues.add(node.val); 
        if(current.isEmpty()){ 
            current = next; 
            next = new LinkedList<TreeNode>(); 
            al.add(nodeValues); 
            nodeValues = new ArrayList(); 
        } 
  
    } 
    return al; 
  } 
  //Vertical order: 
  /**   Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column). 
  For each node, its left child's degree is -1 and is right child's degree is +1. 
  We can do a level order traversal and save the degree information. **/
  public List<List<Integer>> verticalOrder() { 
    List<List<Integer>> result = new ArrayList<List<Integer>>(); 
    if(root==null) 
        return result; 
  
    // level and list     
    HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>(); 
  
    LinkedList<TreeNode> queue = new LinkedList<TreeNode>(); 
    LinkedList<Integer> level = new LinkedList<Integer>(); 
  
    queue.offer(root); 
    level.offer(0); 
  
    int minLevel=0; 
    int maxLevel=0; 
  
    while(!queue.isEmpty()){ 
        TreeNode p = queue.poll(); 
        int l = level.poll(); 
  
        //track min and max levels 
        minLevel=Math.min(minLevel, l); 
        maxLevel=Math.max(maxLevel, l); 
  
        if(map.containsKey(l)){ 
            map.get(l).add(p.val); 
        }else{ 
            ArrayList<Integer> list = new ArrayList<Integer>(); 
            list.add(p.val); 
            map.put(l, list); 
        } 
  
        if(p.left!=null){ 
            queue.offer(p.left); 
            level.offer(l-1); 
        } 
  
        if(p.right!=null){ 
            queue.offer(p.right); 
            level.offer(l+1); 
        } 
    } 
    for(int i=minLevel; i<=maxLevel; i++){ 
        if(map.containsKey(i)){ 
            result.add(map.get(i)); 
        } 
    } 
    return result; 
  } 
} 
 
