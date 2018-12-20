/**
There is a new alien language which uses the latin alphabet. 
However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
Example 1:
Given the following words in dictionary,
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".
Example 2:
Given the following words in dictionary,
[
  "z",
  "x"
]
The correct order is: "zx".
Example 3:
Given the following words in dictionary,
[
  "z",
  "x",
  "z"
]
The order is invalid, so return ""

Solution:
Construct a directed graph.
Print the topological sort output.
Using HashMap and ArrayList.
***/

import java.util.*;

public class AlienDictionary {
  public String alienOrder(String[] words) { 
    Map<Character,HashSet<Character>> graph = new HashMap<Character,HashSet<Character>>();  
    Map<Character,Integer> inDegree = new HashMap<Character,Integer>();  
    
    // Build graph and inDegree 
    for(String w : words) {  
      for(int i =0 ; i< w.length()-1; i++) { 
        if(w.charAt(i) == w.charAt(i+1))
          continue;
        
        //Initialization of inDegree
        if(!inDegree.containsKey(w.charAt(i)))
            inDegree.put(w.charAt(i), 0);
         
        HashSet<Character> list;
        //if ab is processed then ignore ba
        if(graph.containsKey(w.charAt(i+1))) {
          list = graph.get(w.charAt(i+1));  
        if(list.contains(w.charAt(i))) 
          continue;
        }
                 
        if(!graph.containsKey(w.charAt(i))) 
          list = new HashSet<Character>();
        else
          list = graph.get(w.charAt(i));  
             
        if(!list.contains(w.charAt(i+1))) {
            list.add(w.charAt(i+1));
            graph.put(w.charAt(i), list);
            if(inDegree.containsKey(w.charAt(i+1)))  
              inDegree.put(w.charAt(i+1), inDegree.get(w.charAt(i+1))+1);  
            else 
              inDegree.put(w.charAt(i+1),1);
          }
      }
    }
 
    Queue<Character> queue = new PriorityQueue<Character>();  
    for(Character key : inDegree.keySet()) {  
      if(inDegree.get(key) == 0)  
        queue.offer(key);  
    } 
    StringBuilder result = new StringBuilder(); 
    while(!queue.isEmpty()){ 
      char c = queue.remove(); 
      result.append(c); 
      if(!graph.containsKey(c))
        continue;
      HashSet<Character> adj = graph.get(c);
      Iterator<Character> itr = adj.iterator();
      while(itr.hasNext()){
        char ele = itr.next(); 
        int ind = inDegree.get(ele); 
        if(ind == 1)  
          queue.offer(ele);  
        inDegree.put(ele, inDegree.get(ele)-1);  
      }  
    }  
    return result.toString();  
  }

  public static void main(String[] args) { 
    AlienDictionary i = new AlienDictionary();
   
    String[] words = new String[]{"wrt","wrf","er","ett","rftt"};
    String[] words_1 = new String[]{"baa", "abcd", "abca", "cab", "cad"};
   
    System.out.println(i.alienOrder(words));
  }
}
