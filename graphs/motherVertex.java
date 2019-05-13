// A mother vertex in a graph G = (V,E) is a vertex v such that all other vertices in G can be reached by a path from v. 

import java.util.*; 

public class Solution { 
  public int findMotherVertex(List<String> graph) { 
    //Naive way would be to do a DFS on every vertex and check if its DFS visited all other vertices 
    //This would take O(V*(V+E)) time 
    //Using Kosaraju's strongly connected component algo : Time: O(V+E) 
    //The idea is the vertex from which all other vertices can be visited would finish last if DFS is done on the graph 
    //because there would be no other vertex left to visit after DFS on this vertex 

    //create adj list 
    List<List<Integer>> adjList = new LinkedList<>(); 
    for(int i=0; i< graph.size(); i++) { 
      String g = graph.get(i); 
      String[] tokens = g.split(" "); 
      List<Integer> list = new LinkedList<>(); 
      for(String t : tokens) { 
        if(t != "null") 
          list.add(Integer.parseInt(t)); 
      } 
      adjList.add(i, new LinkedList<>(list)); 
    } 
    // Find vertex which finishes last in DFS 
    boolean[] visited = new boolean[graph.size()]; 
    int lastVisited = -1; 
    Arrays.fill(visited, false); 
    for(int v=0 ;v < graph.size(); v++) { 
      if(!visited[v]) { 
        dfs(v, visited, adjList); 
        lastVisited = v; 
      } 
    } 
    // Check if all vertices are visited for DFS of lastVisited 
    Arrays.fill(visited, false); 
    dfs(lastVisited, visited, adjList); 
    for(int v=0 ;v < graph.size(); v++) { 
      if(!visited[v])  
        return -1; 
    } 
    return lastVisited; 
  } 
  private void dfs(int v, boolean[] visited, List<List<Integer>> adjList) { 
    visited[v] = true; 
    List<Integer> adj = adjList.get(v); 
    for(int a : adj) { 
      if(!visited[a]) 
        dfs(a, visited, adjList); 
    } 
  } 
  public static void main(String[] args) throws Exception{ 
    // TODO Auto-generated method stub 
    Solution s = new Solution(); 
    List<String> graph = new LinkedList<>(Arrays.asList( 
    "1 2","3","null","1","6","2 6","0 4")); 
    System.out.println("Mother vertex: "+ s.findMotherVertex(graph)); 
  } 
} 
