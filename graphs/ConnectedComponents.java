/**
1) Initialize all vertices as not visited.
2) Do following for every vertex 'v'.
       (a) If 'v' is not visited before, call dfs(v, <list>)
       (b) Print <list>
dfs(v, <list>)
1) Mark 'v' as visited.
2) Add 'v' to <list>
3) Do following for every adjacent 'u' of 'v'.
     If 'u' is not visited, then recursively call dfs(u, <list>)

Time: O(E+V)
Space: O(V)
**/

private void dfs(Vertex v, LinkedList<Character> temp) {
  v.visited = true;
  temp.add(v.getId());
  for(Vertex n : v.getAdjacentVertices()) {
    if(!n.visited)
      dfs(n,temp);
  }
}

public void connectedComponents() {
  LinkedList<LinkedList<Character>> cc = new LinkedList<LinkedList<Character>>();
  LinkedList<Character> temp;
  for(Vertex v : g.getAllVertices()) {
	  temp = new LinkedList<Character>();
	  if(!v.visited) {
		  dfs(v,temp);
		  cc.add(new LinkedList<Character>(temp));
	  }
  }

  for(LinkedList<Character> l : cc) {
	  System.out.println(l);
  }
}

public static void main(String[] args) {
     ConnectedComponents f = new ConnectedComponents (); 
     Graph g = f.new Graph();
     g.addEdge('A', 'B', 3);
     g.addEdge('A', 'D', 1);
     g.addEdge('B', 'C', 1);
     g.addEdge('B', 'D', 3);
   
     g.addEdge('E', 'F', 5);
     g.addEdge('E', 'G', 4);

     f.g = g;
     f.connectedComponents();
}
