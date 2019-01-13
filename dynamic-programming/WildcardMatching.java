/**
Implement wildcard pattern matching with support for '?' and '*'. 
'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence). 
The matching should cover the entire input string (not partial). 

Some examples: 
isMatch("aa","a") → false 
isMatch("aa","aa") → true 
isMatch("aaa","aa") → false 
isMatch("aa", "*") → true 
isMatch("aa", "a*") → true 
isMatch("ab", "?*") → true 
isMatch("aab", "c*a*b") → false 

		pattern	x 	? 	y 	* 	z 
		0 	1 	2 	3 	4 	5 
str	0 	T 	F 	F 	F 	F 	F 
x 	1 	F 	T 	F 	F 	F 	F 
b 	2 	F 	F 	T 	F 	F 	F 
y 	3 	F 	F 	F 	T 	T 	F 
l 	4 	F 	F 	F 	F 	T 	F 
m 	5 	F 	F 	F 	F 	T 	F 
z 	6 	F 	F 	F 	F 	T 	T

Time: O(m*n)
**/

public boolean WildcardMatching(char[] str, char[] pattern) {

  boolean[][] dp = new boolean[str.length()+1][pattern.length()+1]; // fill with false
  if(pattern[0] == '*')
    dp[0][1] = true;
  dp[0][0] = true;

  for (int i = 1; i < dp.length; i++) {
      for (int j = 1; j < dp[0].length; j++) {
        if (pattern[j-1] == '?' || str[i-1] == pattern[j-1]) {
          dp[i][j] = dp[i-1][j-1];
        } else if (pattern[j-1] == '*'){
          dp[i][j] = dp[i-1][j] || dp[i][j-1];
        } else dp[i][j] = false;
     } 
  } 
  return T[str.length()][pattern.length()];
}

