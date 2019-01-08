/**
Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
For example, given
s = "leetcode",
dict = ["leet", "code"].
 Return true because "leetcode" can be segmented as "leet code".

Algorithm/Insights
 
The given problem can be solved by using Dynamic Programming as described below:
1. Create a temporary boolean array validWords[] defined as: 
validWords[i] 
            = true, if input substring from 0 to i forms valid words string
            = false, otherwise
 2. For i = 0 to input.length, 
   a. If input substring from 0...i is present in the dictionary, then set validWords[i] = true
   b. If validWords[i] == true, from j = i+1 to n-1, check if substring from i+1 to j, for all values of j (= i+1 to n-1), is present in the dictionary and set validWords[j] to true if found in the dictionary.
 
3. When we reach the end of the string, if validWords[n-1] is true, then return true else return false.
 
Time complexity: O(n^2)
Space : O(n)
**/

import java.util.*;
 public class WordBreak1 {
   Set<String> dictionary;
   WordBreak1 () {
    dictionary = new HashSet<String>();
    dictionary.add("leet");
    dictionary.add("code");
   }

  boolean hasValidWords (String checkString) {
    if(checkString.isEmpty())
      return false;
    boolean[] isValid = new boolean[checkString.length()];

    for(int i = 0 ; i < checkString.length(); i++) {
      if(dictionary.contains(checkString.substring(0, i+1)))
        isValid[i] = true;

      if(isValid[i] == true) {
        for(int j = i+1 ; j < checkString.length(); j++) {
          if(dictionary.contains(checkString.substring(i+1, j+1)))
            isValid[j] = true;
          if( (j == checkString.length() - 1) && (isValid[checkString.length() - 1 ] == true))
            return true;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    String checkWord = "leetcode";
    WordBreak1 dp1 = new WordBreak1();
    System.out.println(checkWord + " consists of valid words from dictionary "
    + dp1.hasValidWords(checkWord));
  }
}
