/**
#hard
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. You may assume the dictionary does not contain duplicate words.
Return all such possible sentences.
For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].
A solution is ["cats and dog", "cat sand dog"].

Solution:
	1. Scan the the string from the tail
	2. Build possible solution for the current index based on DP results
	3. Return the solution when index==0
Time: O(n)
**/

public class WordBreak2 {
    public List<String> wordBreak(String s, Set<String> dict) {
        Map<Integer, List<String>> validMap = new HashMap<Integer, List<String>>();
 
        // initialize the valid values
        List<String> l = new ArrayList<String>();
        l.add("");
        validMap.put(s.length(), l);
 
        // generate solutions from the end
        for(int i = s.length() - 1; i >= 0; i--) {
            List<String> values = new ArrayList<String>();
            for(int j = i + 1; j <= s.length(); j++) {
                if (dict.contains(s.substring(i, j))) {
                    for(String word : validMap.get(j)) {
                        values.add(s.substring(i, j) + (word.isEmpty() ? "" : " ") + word);
                    }
                }
            }
            validMap.put(i, values);
        }
        return validMap.get(0);
    }
    public static void main(String[] args) {
      Set<String> dict = new HashSet<String>();
      dict.add("cat");
      dict.add("cats");
      dict.add("sand");
      dict.add("and");
      dict.add("dog");
      WordBreak2 w = new WordBreak2();
      List<String> result = w.wordBreak("catsanddog", dict);
      System.out.println(result);
    }
 }
 
/** OUTPUT
0	  "cat sand dog", "cats and dog"
1	
2	
3	  "sand dog"
4	  "and dog"
5	
6	
7	  "dog"
8	
9	
10  " "
**/
