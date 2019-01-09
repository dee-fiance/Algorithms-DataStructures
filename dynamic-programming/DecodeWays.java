/**
Let 1 represent ‘A’, 2 represents ‘B’, etc. Given a digit sequence, count the number of possible decodings of the 
given digit sequence.
Examples:
Input: digits[] = "121" 
Output: 3
// The possible decodings are "ABA", "AU", "LA" 
Input: digits[] = "1234"
Output: 3
// The possible decodings are "ABCD", "LCD", "AWD" 
An empty digit sequence is considered to have one decoding. 
It may be assumed that the input contains valid digits from 0 to 9 and there are no leading 0’s, 
no extra trailing 0’s and no two or more consecutive 0’s. 

Time/Space Complexity is O(n)
**/
public class DecodeWays {
  public static int countDecodingDp(String message) { 
    int msgLen = message.length(); 
    int[] dp= new int[msgLen + 1];
    dp[0] =1;
    dp[1] =1;
    for(int i=2; i<= msgLen; i++) { 
      if(message.charAt(i - 1 ) > '0') 
        dp[i] = dp[i-1]; 
	                
      if((message.charAt(i - 2) < '2') || ( message.charAt(i - 2) == '2' && message.charAt(i - 1) < '7' ) )
        dp[i] +=  dp[i-2]; //dp[i-1] is already added
    }
    return dp[msgLen];
  }
}
