/**
#difficult
Given two strings S1 and S2. Find the longest common subsequence between S1 and S2. 
Example: Input- S1 = "ACBEA" S2 = "ADCA" Output- "ACA" 

Time and Space is O(n^2)

MATCH TABLE
        A 	D 	C 	A 
A 	1 	1 	1 	1 
C 	1 	1 	2 	2 
B 	1 	1 	2 	2 
E 	1 	1 	2 	2 
A 	1 	1 	2 	3 

POINTER TABLE
 	  A .     D 	C     A 
A 	s1/s2 	s1 	s1 .  s1 
C 	s2 .    s2 	s1/s2 s1 
B 	s2 .    s2 	s2 .  s2 
E 	s2 .    s2 	s2    s2 
A 	s1/s2 	s1 	s2    s1/s2 
**/

String longestCommonSubsequence (String s1, String s2) { 
	int len1 = s1.length();
	int len2 = s2.length(); 
	
	String[][] pointer_table = new String[len1][len2]; 
	int[][] match_table = new int[len1][len2]; 
	
	for(int i = 0; i< len1; i++) { 
		for(int j=0 ;j < len2; j++) { 
			if( s1.charAt(i) == s2.charAt(j)) { 
				if(i > 0 && j > 0) 
					match_table[i][j] = match_table[i-1][j-1] + 1; 
				else 
					match_table[i][j] = 1; 
				
				pointer_table[i][j] = "pick_s1_or_s2"; 
			} else { 
				if(i==0 && j > 0) {
					match_table[i][j] = match_table[i][j-1]; 
					pointer_table[i][j] = "pick_s1"; 
				}
				else if(j==0 && i > 0) { 
					match_table[i][j] = match_table[i-1][j]; 
					pointer_table[i][j] = "pick_s2"; 
				} 
				elseif(i>0 &&j>0) {
					if(match_table[i][j-1] > match_table[i-1][j]) { 
						match_table[i][j] = match_table[i][j-1]; 
						pointer_table[i][j] = "pick_s1"; 
					} else {
						match_table[i][j] = match_table[i-1][j]; 
						pointer_table[i][j] = "pick_s2"; 
					}
				} 
				else pointer_table[i][j] = "pick_none"; 
			} 
		} 
	}
	StringBuffer result = new StringBuffer(); 
	int counteri = len1 -1;
	int counterj = len2 -1; 
	
	while (counteri >= 0 && counterj >=0 ) { 
		switch(pointer_table[counteri][counterj]) {
			case "pick_s1_or_s2" : 
				result.append(s1.charAt(counteri)); 
				counteri--;
				counterj--;
				break; 
			case "pick_s1" : 
				counterj--; 
				break;
			case "pick_s2" : 
				counteri--; 
				break; 
			default : 
				counteri--; 
				counterj--; 
				break; 
		} 
	 } 
	return result.reverse().toString();
} 
