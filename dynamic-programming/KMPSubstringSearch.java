//KMP = Knuth-Morris-Pratt
//Time: O(m+n)

public class KMPSubstringSearch {
	// Time complexity is O(pattern length)
	int[] buildPartialMatchTableUsingKMP (String pattern) { 
		int[] lps = new int[pattern.length()]; lps[0] = 0;
		int j = 0;
		for(int i = 1; i < pattern.length(); ) { 
			if(pattern.charAt(i) == pattern.charAt(j)) { 
				lps[i] = j + 1; 
				i++; 
				j++; 
			} else { 
				if(j == 0) i++; 
			     else j = lps[j-1];
			}
		} 
		return lps;
	} 
			
	boolean findSubstring (String string, String substring) { int[] lps = buildPartialMatchTableUsingKMP(substring); 
		int i = 0; int j = 0;
		while(i < string.length() && j < substring.length()) { 
			if(string.charAt(i) == substring.charAt(j)) { 
				i++; 
				j++; 
			} else { 
			     if(j==0) i++;
			     else j = lps[j-1];
		  }
		}
		if( j == substring.length()) 
			 return true;
		else return false;
	} 
	
	public static void main(String[] args) {
		//Substring search
		KMPSubstringSearch kmp1 = new KMPSubstringSearch();
		text = "abxabcabcaby";
		String substring = "abcaby"; 
		System.out.println("Substring " + substring + " found in "+text + " : " + kmp1.findSubstring(text, substring)); 
		substring = "abcabz"; 
		System.out.println("Substring " + substring + " found in "+text + " : " + kmp1.findSubstring(text, substring)); 
	}
 }
