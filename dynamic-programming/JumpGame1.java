/**
Given an array of non-negative integers, you are initially positioned at the first index of the array. 
Each element in the array represents your maximum jump length at that position. Determine if you are able to reach the last index. 
For example: A = [2,3,1,1,4], return true. A = [3,2,1,0,4], return false. 

Sample data: [2,3,1,1,4] 
Let's calculate the max index you can reach at a particular index. 
[2,4,4,4,4] 
U can jump from 2 to 3 (max is 2 indices, min can be less) 

Time: O(n)
**/

public class JumpGame1 {
  public int jumpgame(int[] data) {
    int max=0; 
    for(int i =0 ; i < data.length; i++) { 
      if(data[i]==0 && max <= i) // cannot go further and dint even reach to this index 
        return false; 
    
      max = Math.max(max, i+data[i]); 
      if(max == data.length-1) 
        return true; 
    } 
    return false;
  }
}
