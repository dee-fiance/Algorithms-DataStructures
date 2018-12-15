/***
* Trie is an efficient information reTrieval data structure.
* M => length of word(max) 
* N => No of words 
* Insert, Search and delete => O(mn) 
***/

public class Trie {  
  private class TrieNode { 
    Map<Character, TrieNode> children;  
    boolean isWord; 
    TrieNode() {  
      children = new HashMap<Character, TrieNode>();  
      isWord = false;  
    }  
  } 
  
  TrieNode root;  

  public Trie() {  
    root = new TrieNode();  
  }  

  public void insert(String word) {  
    insertRecursive(root, word, 0);  
  }  

  private void insertRecursive(TrieNode node, String word, int i) {  
    if(node == null) return; 
    if(word.length() == i) {  
      node.isWord = true; 
      return;  
    }  
    char ch = word.charAt(i); 
    TrieNode current;  
    if(node.children.containsKey(ch)) {  
      current = node.children.get(ch);  
    } else {  
      current = new TrieNode();   
      node.children.put(ch, current);  
    }  
    insertRecursive(current, word, i+1);  
  } 

  public boolean search(String word) { 
    return searchRec(root, word, 0); 
  } 

  private boolean searchRec(TrieNode node, String word, int index) { 
    if(node == null) 
      return false; 
    if(word.length() == index) 
      return node.isWord; 
    char ch = word.charAt(index); 
    TrieNode current; 
    if(node.children.containsKey(ch)) { 
      current = node.children.get(ch); 
    } else return false; 
    return searchRec(current, word, index+1); 
  } 

  /** Returns if there is any word in the trie that starts with the given prefix. */  
  public boolean searchPrefix(String prefix) {  
    return searchPrefix(root, prefix, 0); }  
  } 

  private boolean searchPrefix(TrieNode node, String prefix, int i) {  
    if(node == null) 
      return false;  
    if(prefix.length() == i)  
      return true; 
    char ch = prefix.charAt(i); 
    TrieNode current; 
    if(node.children.containsKey(ch)) {  
      current = node.children.get(ch);  
    } else {  
       return false; 
    } 
    return searchPrefix(current, prefix, i+1);  
  } 

  public void deleteWord (String word) {  
    deleteWord(root, word, 0);  
  } 

  // returning false indicates that there are other words with this prefix and we cannot delete these chars as they form another word. 
  // True would indicate that this is the only word with this prefix and we can delete the chars formning this word. 
  private boolean deleteWord(TrieNode node, String word, int i) {  
    if(word.length() == i) {  
      if(node.isWord) {  
        node.isWord = false; 
        return (node.children.size() == 0); 
     }  
    else return false; 
  } 

    char ch = word.charAt(i); 
    TrieNode current;  
    if(node.children.containsKey(ch)) {  
      current = node.children.get(ch);  
    } else {  
      return false;  
    }  
    boolean isDeleted = deleteWord(current, word, i+1);  
    if(isDeleted) {  
      node.children.remove(ch); 
      return (node.children.size() == 0);  
    }  
    return false;  
  }  

  public void printTrie(TrieNode node) { 
    if(node == null) 
      return; 
    if(node.children == null) 
      return; 
    else System.out.print("isWord: "+node.isWord+" "); 

    for(char ch: node.children.keySet()) { 
      System.out.print(ch+" "); 
      TrieNode n = node.children.get(ch); 
      printTrie(n); 
    } 
  } 

  public static void main(String[] args) {  
    Trie obj = new Trie();  
    obj.insert("abcd");  
    obj.insert("abgl");  
    obj.insert("cdf");  
    obj.insert("abcde");  
    obj.insert("lmn");  
    obj.printTrie();  
    System.out.println(obj.search("lmn"));  
    System.out.println(obj.search("ab"));  
    System.out.println(obj.search("cdf"));  
    System.out.println(obj.search("ghi"));  
    System.out.println(obj.searchPrefix("ab")); 
    System.out.println(obj.searchPrefix("lo"));  
    obj.deleteWord("abc");  
    obj.printTrie();  
    obj.deleteWord("abcd");  
    obj.printTrie();  
  }  
} 
