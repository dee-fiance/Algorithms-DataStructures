/** 
A minimum spanning tree (MST) or minimum weight spanning tree is a subset of the edges of a connected, edge-weighted undirected graph that connects all the vertices together, without any cycles and with the minimum possible total edge weight. 
Time: O(ElogV)
Space: O(V+E)
**/
import java.util.*;

public class PrimsMST {
  Graph g;
  private class Node { 
    Vertex v; 
    int dist; 
    Node(Vertex v, int d) { 
      this.v = v; 
      this.dist = d; 
    } 
  } 
 
  public class Graph { 
    private List<Edge> allEdges; 
    private Map<Character, Vertex> allVertices; 
    Graph() { 
      allVertices = new HashMap<Character, Vertex>(); 
      allEdges = new ArrayList<Edge>(); 
    } 
    public List<Edge> getAllEdges() { 
      return allEdges; 
    } 
    public Collection<Vertex> getAllVertices() { 
      return allVertices.values(); 
    } 
    public Map<Character, Vertex> getVertices() { 
      return allVertices; 
    } 
    public Vertex getVertex(Character c) { 
      if(allVertices.containsKey(c)) 
        return allVertices.get(c); 
      else return null; 
    } 
    public void addEdge(Character v1, Character v2, int weight) { 
      Vertex one = allVertices.containsKey(v1) ? allVertices.get(v1) : new Vertex(v1); 
      Vertex two = allVertices.containsKey(v2) ? allVertices.get(v2) : new Vertex(v2); 
      Edge e = new Edge(one,two,weight); 
      allEdges.add(e); 
      one.addAdjacentVertex(two); 
      one.addEdge(e); 
      allVertices.put(v1, one); 
      two.addAdjacentVertex(one); 
      two.addEdge(e); 
      allVertices.put(v2, two); 
    } 
  } 
  private class Vertex { 
    private Character id; 
    private ArrayList<Edge> edges; 
    private ArrayList<Vertex> adjacentVertices; 
    Vertex(Character id) { 
      this.id = id; 
      edges = new ArrayList<Edge>(); 
      adjacentVertices = new ArrayList<Vertex>(); 
    } 
    public Character getId() { 
      return id; 
    } 
    public ArrayList<Vertex> getAdjacentVertices() { 
      return adjacentVertices; 
    } 
    public void addAdjacentVertex(Vertex v) { 
      this.adjacentVertices.add(v); 
    } 
    public int getEdgeWeight(Vertex n) { 
      if(adjacentVertices.contains(n)) { 
        for(Edge e : edges) { 
          if( (e.getVertex1() == this && e.getVertex2() == n) 
          || (e.getVertex2() == this && e.getVertex1() == n)) { 
            return e.getWeight(); 
          } 
        } 
      } 
      return 0; 
    }
    public Edge getEdge(Vertex n) { 
      if(adjacentVertices.contains(n)) { 
        for(Edge e : edges) { 
          if( (e.getVertex1() == this && e.getVertex2() == n) 
          || (e.getVertex2() == this && e.getVertex1() == n)) { 
            return e; 
          } 
        } 
      } 
      return null; 
    }
    public void addEdge(Edge e) { 
      this.edges.add(e); 
    } 
  }//Vertex 
 
  private class Edge { 
    private Vertex v1; 
    private Vertex v2; 
    private int weight; 
    Edge(Vertex v1, Vertex v2, int weight) { 
      this.v1 = v1; 
      this.v2 = v2; 
      this.weight = weight; 
    } 
    Vertex getVertex1() { 
      return v1; 
    } 
    Vertex getVertex2() { 
      return v2; 
    } 
    int getWeight() { 
      return weight; 
    }
    public String toString() {
      return this.getVertex1().getId()+ " -> " + this.getVertex2().getId();
    }
  }//Edge
 
  public List<Edge> primsMST(Character start) {
    PriorityQueue<Node> nodes = new PriorityQueue<Node>((a,b) -> a.dist-b.dist);
    Map<Vertex, Edge> vertexToEdge = new HashMap<Vertex, Edge>();
    LinkedList<Edge> mst = new LinkedList<Edge>(); 
 
    for(Vertex v : g.getAllVertices()) {
      if(v.getId() == start)
        nodes.offer(new Node(v,0));
      else nodes.offer(new Node(v, Integer.MAX_VALUE));
    }
 
    while(!nodes.isEmpty()) { 
        Node minNode = nodes.poll();
        Edge spanningEdge = vertexToEdge.get(minNode.v);
        if(spanningEdge != null)
          mst.add(spanningEdge);
 
        for(Vertex n : minNode.v.getAdjacentVertices()) {
          for(Node node : nodes) { 
            if(node.v.getId() == n.getId()) {
              if(node.dist >  minNode.v.getEdgeWeight(n)) {
                nodes.remove(node); 
                nodes.offer(new Node(n,minNode.v.getEdgeWeight(n)));
                vertexToEdge.put(n,minNode.v.getEdge(n));
                break; 
              }
            }
          }
        }
    }
    return mst;
  }
  public void printMst(List<Edge> mst) {
    for(Edge e : mst) 
    System.out.print(e.toString()+"  ");
  }

  public static void main(String[] args) {
    PrimsMST f = new PrimsMST(); 
    Graph g = f.new Graph();
    g.addEdge('A', 'B', 3);
    g.addEdge('A', 'D', 1);
    g.addEdge('B', 'C', 1);
    g.addEdge('B', 'D', 3);
    g.addEdge('C', 'D', 1);
    g.addEdge('C', 'E', 5);
    g.addEdge('C', 'F', 4);
    g.addEdge('E', 'F', 2);
    f.g = g;
    List<Edge> mst = f.primsMST('A');
    f.printMst(mst);
  }
} 
