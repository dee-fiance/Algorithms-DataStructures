import java.util.Arrays;

public class Solution {

	public boolean regexMatch(String str, String pattern) {
		if(str == null || pattern == null || str.length() == 0 || pattern.length() == 0)
			return false;
		boolean[][] dp = new boolean[str.length()+1][pattern.length()+1];
		dp[0][0] = true;
		
		for(int i=1; i<= str.length(); i++)
			Arrays.fill(dp[i], false);
		
		for(int j=1;j<= pattern.length(); j++) {
			if(pattern.charAt(j-1) == '*')
				dp[0][j] = dp[0][j-2];
		}
		
		for(int i=1; i<= str.length(); i++) {
			for(int j=1;j<= pattern.length(); j++) {
				if(str.charAt(i-1) == pattern.charAt(j-1) || pattern.charAt(j-1) == '.')
					dp[i][j] = dp[i-1][j-1];
				else if(pattern.charAt(j-1) == '*') {
					dp[i][j] = dp[i][j-2];
					if(str.charAt(i-1) == pattern.charAt(j-2) || pattern.charAt(j-2) == '.')
						dp[i][j] = dp[i][j] || dp[i-1][j];
				} else dp[i][j] = false;
			}
		}
		return dp[str.length()][pattern.length()];
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution s = new Solution(); 
	    String str = "xaabyc";
	    String pattern = "xa*b.c";
	        System.out.println("Match: " + s.regexMatch(str, pattern));
	}

}

