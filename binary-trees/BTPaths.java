/**
Given a binary tree, return all root-to-leaf paths.

Time: O(n)
**/

public List<String> btPaths(TreeNode root) {
	List<String> result = new LinkedList<String>();
	StringBuffer sb = new StringBuffer();
	btPathsHelper(root,result,sb);
	return result;
}

private void btPathsHelper(TreeNode root, List<String> result, StringBuffer sb) {
	if(root == null) return;
	
	sb.append(root.val);

	if(root.left == null && root.right == null) {
		result.add(new String(sb));
		sb.deleteCharAt(sb.length()-1);
		return;
	}
	btPathsHelper(root.left,result,sb);
	btPathsHelper(root.right,result,sb);
	sb.deleteCharAt(sb.length()-1); 
}
