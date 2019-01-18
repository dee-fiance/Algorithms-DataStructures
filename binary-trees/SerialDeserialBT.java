/** Used preOrder: Time :O(n) **/

public class SerialDeserialBT {
 
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb=new StringBuilder();
        dfs(root,sb);
        return sb.toString();
    }
    private void dfs(TreeNode x, StringBuilder sb) {
        if (x==null) {
            sb.append("null ");
            return;
        }
        sb.append(String.valueOf(x.val));
        sb.append(' ');
        dfs(x.left,sb);
        dfs(x.right,sb);
    }
 
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] node=data.split(" ");
        return dfs(node,0);
    }
    private TreeNode dfs(String[] node, int i) {
        if (node[i].equals("null")) {
            return null;
        }
        TreeNode x=new TreeNode(Integer.valueOf(node[i]));
        i++;
        x.left=dfs(node,i);
        i++;
        x.right=dfs(node,i);
        return x;
    }
}
