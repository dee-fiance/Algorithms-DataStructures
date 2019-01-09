/**
Given an array of non-negative integers, you are initially positioned at the first index of the array. 
Each element in the array represents your maximum jump length at that position. 
Your goal is to reach the last index in the minimum number of jumps. 
For example, given array A = [2,3,1,1,4], the minimum number of jumps to reach the last index is 2. 
(Jump 1 step from index 0 to 1, then 3 steps to the last index.) 
[2,5,2,2,4,1,1] : 2
Maintain a map to record the min steps to reach an index. 
At an index, update the min steps of i+data[i] and i+1. As you have only 2 ways of taking a step at i. 
Time: O(n)
**/

class Solution { 
    public int jump(int[] nums) { 
        if(nums.length==0) return 0; 
        
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>(); 
        map.put(0,0); 
        for(int i =1;i<nums.length;i++) 
           map.put(i, Integer.MAX_VALUE); 
        
        for(int i=0; i< nums.length-1; i++) { 
            int index1 = i+nums[i]; 
            if(index1 < nums.length) { 
                int val1 = Math.min(map.get(index1), map.get(i)+1); 
                map.put(index1, val1); 
            } 
    
            int index2 = i+1; 
            if(index2 < nums.length) {
              int val2 = Math.min(map.get(index2), map.get(i)+1); 
              map.put(index2, val2); 
            }
        } 
        System.out.println("Steps: "+map.get(nums.length-1)); 
        return map.get(nums.length-1); 
    } 
} 
