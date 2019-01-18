/** Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum. 
For example: 
Given the below binary tree and sum = 22, 

              5 
             / \ 
            4   8 
           /   / \ 
          11  13  4 
         /  \    / \ 
        7    2  5   1 
return 
[ 
   [5,4,11,2], 
   [5,8,4,5] 
] 
Time: O(n), Space: O(logN) 
**/
 
public class PathSum2 { 

  public ArrayList<ArrayList<Integer>> getPathSums2 (int sum) { 
    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>(); 
    ArrayList<Integer> currRes = new ArrayList<>(); 
    getPathSums(root, result, currRes, sum); 
    return result; 
  } 
  
  private void getPathSums(TreeNode root, ArrayList<ArrayList<Integer>> paths, ArrayList<Integer> currRes, int sum) { 
    if(root == null) return; 

    currRes.add(root.val); 
    if(root.left == null && root.right == null) { 
      if(sum - root.val == 0)  
        paths.add(new ArrayList<Integer>(currRes)); 
    } else { 
          getPathSums(root.left, paths, currRes, sum - root.val); 
          getPathSums(root.right, paths, currRes, sum - root.val); 
    } 
    currRes.remove(currRes.size()-1); 
    return; 
  }
}
 
