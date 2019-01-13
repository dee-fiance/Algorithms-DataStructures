/**
Implement regular expression matching with support for '.' and '*'.
'.' Matches any single character. '*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).
Some examples: 
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true

			x 	a 	* 	b 	. 	c 
		0 	1 	2 	3 	4 	5 	6 
	0 	T 	F 	F 	F 	F 	F 	F 
x 	1 	F 	T 	F 	T 	F 	F 	F 
a 	2 	F 	F 	T 	T 	F 	F 	F 
a 	3 	F 	F 	F 	T 	F 	F 	F 
b 	4 	F 	F 	F 	F 	T 	F 	F 
y 	5 	F 	F 	F 	F 	F 	T 	F 
c 	6 	F 	F 	F 	F 	F 	F 	T 

Time: O(m*n)
**/

public class RegexMatching {
	public boolean matchRegex(char[] text, char[] pattern) { 
		boolean T[][] = new boolean[text.length + 1][pattern.length + 1];
		 
		T[0][0] = true;
		for (int i = 1; i < T.length; i++) {
			 for (int j = 1; j < T[0].length; j++) {
				if (pattern[j - 1] == '.' || pattern[j - 1] == text[i - 1]) {
					T[i][j] = T[i-1][j-1];
				} else if (pattern[j - 1] == '*')  {
					T[i][j] = T[i][j - 2];
					if (pattern[j-2] == '.' || pattern[j - 2] == text[i - 1]) {
						T[i][j] = T[i][j] | T[i - 1][j];
					}
				} else T[i][j] = false;
			}
		}
	}
	public static void main(String args[]){
		RegexMatching rm = new RegexMatching(); 
		System.out.println(rm.matchRegex("aa".toCharArray(), "a*".toCharArray())); 
	} 
}
