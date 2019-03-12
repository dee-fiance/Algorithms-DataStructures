/**
* #Twitter #medium 
* Implement an autocomplete system. That is, given a query string s and a set of all possible query strings, return all strings in the set that have s as a prefix. 
* For example, given the query string de and the set of strings [dog, deer, deal], return [deer, deal]. 
* Hint: Try preprocessing the dictionary into a more efficient data structure to speed up queries. 
* Build a TRIE from the list of query strings first and then query every time in O(1) time. 
**/

import java.util.*; 
import java.util.LinkedList; 
public class AutoComplete { 
    private class TrieNode { 
        Map<Character, TrieNode> children; 
        boolean isWord; 
        TrieNode() { 
            children = new HashMap<Character, TrieNode>(); 
            isWord = false; 
        } 
    } 
    TrieNode root; 

    public AutoComplete() { 
        root = new TrieNode(); 
    } 
    /* Inserts a word into the trie. */ 
    public void insert(String word) { 
        char[] w = word.toCharArray(); 
        insert(root, w, 0); 
    } 
    private void insert(TrieNode root, char[] word, int index) { 
        if(root == null) 
            return; 
        if(word.length == index) { 
            root.isWord = true; 
            return; 
        } 
        TrieNode current = null; 
        if(root.children.containsKey(word[index])) { 
            current = root.children.get(word[index]); 
        } else { 
            current = new TrieNode(); 
            root.children.put(word[index], current); 
        } 
        insert(current, word, index+1); 
    } 
    /* Returns list of words that start with the given prefix. */ 
    public LinkedList<String> startsWith(String prefix) { 
        char[] w = prefix.toCharArray(); 
        TrieNode start = startsWith(root, w, 0); 
        LinkedList<String> result = new LinkedList<String>(); 
        StringBuilder sb = new StringBuilder(); 
        constructWords(start, prefix, result, sb); 
        return result; 
    } 
    private TrieNode startsWith(TrieNode root, char[] prefix, int index) { 
        if(root == null) 
            return null; 
        if(prefix.length == index) { 
            return root; 
        } 
        TrieNode current = null; 
        if(root.children.containsKey(prefix[index])) { 
            current = root.children.get(prefix[index]); 
        } else return null; 
         
        return startsWith(current, prefix, index+1); 
    }  
    private void constructWords(TrieNode start, String prefix, LinkedList<String> result, StringBuilder sb) { 
      if(start == null) 
        return; 
      if(start.isWord) { 
        result.add(prefix+sb.toString()); 
        return; 
      } 
      for(Character ch : start.children.keySet()) { 
        sb.append(ch); 
        TrieNode current = null; 
        current = start.children.get(ch); 
        constructWords(current, prefix, result, sb); 
        sb.deleteCharAt(sb.length()-1); 
      } 
    } 
  
    public static void main(String[] args) { 
      AutoComplete a = new AutoComplete(); 
      String[] listOfWords = new String[]{ "dearing", "deal", "demand", "apple", "banana", "determination", "deceptive", "application", "april"}; 
      for(String s : listOfWords) 
        a.insert(s); 
  
      System.out.println("Matching words with prefix \"de\" are: " + a.startsWith("de")); 
      System.out.println("Matching words with prefix \"dea\" are: " + a.startsWith("dea")); 
      System.out.println("Matching words with prefix \"app\" are: " + a.startsWith("app")); 
    } 
} 
