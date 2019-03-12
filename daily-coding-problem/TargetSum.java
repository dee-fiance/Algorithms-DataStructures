/** #easy 
This problem was recently asked by Google. 
Given a list of numbers and a number k, return whether any two numbers from the list add up to k. 
For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17. 
Bonus: Can you do this in one pass? 
**/
 
import java.util.*; 
 
public class TargetSum { 
  public boolean targetSum(int[] arr, int target) { 
    if(target ==0 || arr == null || arr.length==0) 
      return false; 
    Set<Integer> data = new HashSet<>(); 
    for(int n : arr) { 
      if(n > target) 
        continue; 
      if(data.contains(n)) 
        return true; 
      else data.add(target-n); 
    } 
    return false; 
  } 
  public static void main(String[] args) { 
        HelloWorld h = new HelloWorld();  
        int[] arr  = {10,15,3,7}; 
        int target = 17; 
        System.out.println("Target sum exists: "+h.targetSum(arr, target)); 
    } 
}  
