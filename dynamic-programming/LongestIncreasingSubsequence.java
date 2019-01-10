/**
The Longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence of a given sequence
such that all elements of the subsequence are sorted in increasing order. 
For example, the length of LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60, 80}. 

for each num in nums 
     if(list.size()==0) 
          add num to list 
     else if(num > last element in list) 
          add num to list 
     else  
          replace the element in the list which is the smallest but greater than num 

Time: O(nlogn)
**/
public class LongestIncreasingSubsequence {
  public int lengthOfLIS(int[] nums) { 
      if(nums==null || nums.length==0) 
          return 0; 

      ArrayList<Integer> list = new ArrayList<Integer>();  

      for(int num: nums){ 
          if(list.size()==0 || num>list.get(list.size()-1)){ 
              list.add(num); 
          } else{ 
              int i=0;  
              int j=list.size()-1; 

              while(i<j){ 
                  int mid = (i+j)/2; 
                  if(list.get(mid) < num){ 
                      i=mid+1; 
                  }else{ 
                      j=mid; 
                  } 
              } 
              list.set(j, num); 
          } 
      } 
      return list.size(); 
  }
}
 
