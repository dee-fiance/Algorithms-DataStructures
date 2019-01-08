/**
#difficult
Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.
Input: "aab"
Output: [  ["aa","b"],  ["a","a","b"] ]
Input: "banana"
Output: [ ["b","a","nan","a"] , ["b","ana","n","a"] ,["b","anana"] ,["b","a","n","ana"] ,["b","a","n","a","n","a"] ]
Reference: http://www.lifeincode.net/programming/leetcode-palindrome-partitioning-i-and-iijava/

Solution:

	1. Build metadata to figure out the substrings that are palindrome.
	palindrome[i][j] indicates if substring(i, j+1) is palindromic or not.
	Example: b-a-n-a-n-a
		B	A	N	A	N	A
	B	T	F	F	F	F	F
	A		T	F	T	F	T
	N			T	F	T	F
	A				T	F	T
	N					T	F
	A						T
	
	2. Using this metadata, we should be able to find list of all possible palindromes as well as the minimum cut required to get palindromic substrings.
	To get all the possible palindromes, we have to maintain an array pointing to a list of strings, where palindrome[i] stores the palindromic substrings in s.substring(i, n).
	i=5 => p[5] = {"a"}
	i=4 => p[4] = {"n","a"}
	i=3 => p[3] = {"a","n","a"}, {"ana"}
	i=2 => p[2] = (j=2) {"n","a","n","a"}, {"n", "ana"} (j=4) {"nan","a"}
	i=1 => p[1] = (j=1) {"a","n","a","n","a"}, {"a","n", "ana"}, {"a", "nan","a"}, (j=3) {"ana","n","a"}, 
	(j=5) {"anana"}
	i=0 => p[0] = only j=0 is true so fetch from p[1] and add "b" to all the elements of the list.
	{"b","a","n","a","n","a"}, {"b","a","n", "ana"}, {"b","a", "nan","a"}, {"b","ana","n","a"}, {"b","anana"}

Time: Assuming the total number of strings is k, then the complexity is O(n^2+k).
**/
public class PalindromePartition1 {
  public List<List<String>> palindromePartition(String s) {
    int n = s.length();
    boolean[][] palindrome = new boolean[n][n];
    buildMetaData(s, n, palindrome);
    List<List<String>> result = new List<List<String>>();

    partition(s, n, palindrome, result);
    System.out.println(result);
    return result;
  }

  private void buildMetaData(String s, int n, boolean[][] palindrome) {
    for(int i = 0; i<n; i++)
      palindrome[i][i] = true;
    for(int i = 0; i<n-1; i++) {
      if(s.charAt(i) == s.charAt(i+1))
        palindrome[i][i+1] = true;
      else palindrome[i][i+1] = false;
    }
    for(int len = 3; len <= n; len++) {
      for(int i = 0; i< n-len-1 ; i++) {
        if(s.charAt(i) == s.charAt(i+len-1) && palindrome[i][i+len-2])
          palindrome[i][i+len-1] = true;
        else palindrome[i][i+len-1] = false;
      }
    }
  }

  private List<List<String>> partition(String s, int n, boolean[][] palindrome, List<List<String>> result) {
    List<List<String>>[] p = new List<List<String>>() [n+1];
    p[n] = (List)(new LinkedList<List<String>>());
    List<String> emptyList = new LinkedList<String>();
    p[n].add(emptyList); 
    // very important to put an emptylist at p[n]. 
    // If p[i][n-1] is palindromic then we need an empty list at p[n] to say that there are no substrings to consider 
    // after substring(i,n).
    for(int i = n-1; i>=0; i--) {
      p[i] = (List)(new LinkedList<List<String>>());
      for(int j = i; j< n ; j++) {
        if (isPalindrome[i][j]) { // only when you find the substring(i,j) to be palindromic.
          List<String> lists = p[j+1]; // fetch the list from the next char
          String subs = s.substring(i, j+1); // construct the palindromic substring here
          for ( List<String> li : lists) {
            List<String> newList = new LinkedList<>();
            newList.add(subs); // add palindromic substring first
            newList.addAll(li); // then rest of the elements from the list to form a newlist
            p[i].add(newList); 
          } //for
        } //if
      }
    }
    return p[0];
  }
}
