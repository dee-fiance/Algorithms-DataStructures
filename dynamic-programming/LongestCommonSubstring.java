/**
#hard
Given two strings S1 and S2. Find the longest common substring between S1 and S2.
Example: S1 = "LCLC" S2 = "CLCL" LCS = "CLC" 

Time and Space is O(n^2)

	  L 	C 	L 	C 
C 	0 	1 	0 	1 
L 	1 	0 	2 	0 
C 	0 	2 	0 	3 
L 	1 	1 	3 	0 
max = 1, result = {C}
max = 1, result = {C, L }
max = 2, result = {CL}
max = 2, result = {CL, LC}
max = 3, result = {CLC}
max = 3, result = {CLC, LCL}
**/

import java.util.*;
public class LongestCommonSubstring {
     ArrayList<String> longestCommonSubstring (String s1, String s2) { 
      int max_len = 0;

      ArrayList<String> common_substring = new ArrayList<String>(); 

      int[][] compute_table = new int[s1.length()][s2.length()]; 

      for(int i=0; i< s1.length() ; i++) {
        for(int j=0; j < s2.length(); j++) {
          if(s1.charAt(i) == s2.charAt(j)) {
              if(i != 0 && j != 0)
                 compute_table[i][j] = compute_table[i-1][j-1] + 1; 
              else 
                 compute_table[i][j] = 1;

            if(max_len < compute_table[i][j]) {
              max_len = compute_table[i][j]; 
              common_substring = new ArrayList<String>(); 
              common_substring.add(s1.substring(i-max_len+1, i+1)); 
            } 
            else if(max_len == compute_table[i][j]) {
              common_substring.add(s1.substring(i-max_len+1, i+1)); 
            } 
          } //if 
          else compute_table[i][j] = 0;
        } //for
      } //for
      return common_substring; 
    }
}
