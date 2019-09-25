/* 
https://algorithms.tutorialhorizon.com/kruskals-algorithm-minimum-spanning-tree-mst-complete-java-implementation/ 

Time: O(E logV) 
*/
 
import java.util.*; 

public class KrushkalMST { 
  Graph g; 
  private class Edge { 
    int src; 
    int dst; 
    int weight; 
    Edge(int s, int d, int w) { 
      src = s; 
      dst = d; 
      weight = w; 
    } 
    public String toString() { 
      return src+ " -> " + dst + " ("+ weight +")"; 
    } 
  } 
  private class Graph { 
    private int vertices; 
    private List<Edge> allEdges; 
      Graph(int v) { 
      vertices = v; 
      allEdges = new ArrayList<>(); 
    } 
    public void addEdge(int s, int d,int w) { 
      Edge e = new Edge(s,d,w); 
      allEdges.add(e); 
    } 
  } 
  private class DisjointSet { 
    private class Node { 
      int rank; 
      Node parent; 
      long data; 
      Node(int rank, long data) { 
        this.rank = rank; 
        this.data = data; 
      } 
    } 
    Map<Long, Node> map; 
      DisjointSet() { map = new HashMap<>(); } 
      public void makeSet(long d) { 
      Node n = new Node(0, d); 
      n.parent = n; 
      map.put(d,n); 
    } 
    public void union(long d1, long d2) { 
      Node n1 = map.get(d1); 
      Node n2 = map.get(d2); 
      Node p1 = findSet(n1); 
      Node p2 = findSet(n2); 
      if(p1 == p2) 
      return; 
      if(p1.rank >= p2.rank) { 
        p1.rank = (p1.rank == p2.rank)? p1.rank+1 : p1.rank; 
        p2.parent = p1; 
      } else { 
        p1.parent = p2; 
      } 
    } 
    public Node findSet(Node n) { 
      Node parent = n.parent; 
      if(parent == n) 
        return parent; 
      n.parent = findSet(n.parent); // path compression 
      return n.parent; 
    } 
    public long findSet(long d) { 
      return findSet(map.get(d)).data; 
    } 
   } 

    public List<Edge> kruskalsMST(int source) { 
      DisjointSet ds = new DisjointSet(); 
      for(int i=0; i< g.vertices; i++) 
        ds.makeSet(i); 
      ArrayList<Edge> mst = new ArrayList<>(); 
      PriorityQueue<Edge> pq = new PriorityQueue<Edge>((a,b) -> a.weight-b.weight); 
      for (int i = 0; i < g.allEdges.size() ; i++) { 
        pq.add(g.allEdges.get(i)); 
      } 
      int nodes = 0; 
      while(nodes != g.vertices-1) { 
        Edge e = pq.poll(); 
        System.out.println(e); 
        if(ds.findSet(e.src) != ds.findSet(e.dst)) { 
          nodes++; 
          ds.union(e.src, e.dst); 
          mst.add(e); 
        } 
      } 
    return mst; 
  } 

  public static void main(String[] args) throws Exception{ 
    // TODO Auto-generated method stub 
    Solution s = new Solution(); 
    Graph g = s.new Graph(6); 
    g.addEdge(0,1, 4); 
    g.addEdge(0,2, 3); 
    g.addEdge(1,2, 1); 
    g.addEdge(1,3, 2); 
    g.addEdge(2,3, 4); 
    g.addEdge(3,4, 2); 
    g.addEdge(4,5, 6); 

    s.g = g; 

    System.out.println(s.kruskalsMST(0)); 
    /*  1 -> 2 (1) 
    1 -> 3 (2) 
    3 -> 4 (2) 
    0 -> 2 (3) 
    0 -> 1 (4) 
    2 -> 3 (4) 
    4 -> 5 (6) 
    [1 -> 2 (1), 1 -> 3 (2), 3 -> 4 (2), 0 -> 2 (3), 4 -> 5 (6)] 
    */ 
  } 

} 
