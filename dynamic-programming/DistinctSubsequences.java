/**
#hard
Given a string S and a string T, count the number of distinct subsequences of T in S.
A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
Here is an example:
S = "rabbbit", T = "rabbit"
Return 3.

Approach:
Find a good example to work with and try to solve the example while deducing observations on patterns you are following while solving it.

A good example is:
Input: S = "babgbag", T = "bag"
Output: 5

When you encounter a "g" you need to know how many subsequences are present ending with the previous character "a" so that you can derive the count for subsequences ending with "g".
Similarly, to know the count of subsequences ending with "a", you need data for the previous character "b".

How do you maintain this data?

So, construct a map with S and T.

T/S	b	a	b	g	b	a .   g
b	  1	1	2	2	3	3 .   3
a	  0	1	1	1	1	3+1=4 4
g	  0	0	0	1	1	1 .   4+1=5

if(T.charAt(row) == S.charAt(col))
	dp[row][col] = (row == 0 && col == 0)? 1 
                : ( row==0 ? dp[row][col-1]+1 : dp[row-1][col-1]+dp[row][col-1] );
else
	dp[row][col] = ( row>col ? 0 : dp[row][col-1] );

Time/Space: O(n^2)
**/
