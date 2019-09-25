import java.util.*; 

public class DisjointSet { 
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
      map.put(d,  n); 
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

    public static void main(String[] args) { 
        DisjointSet ds = new DisjointSet(); 
        ds.makeSet(1); 
        ds.makeSet(2); 
        ds.makeSet(3); 
        ds.makeSet(4); 
        ds.makeSet(5); 
        ds.makeSet(6); 
        ds.makeSet(7); 

        ds.union(1,2); 
        ds.union(2,3); 
        ds.union(4,5);  
        ds.union(6,7); 
        ds.union(5,6); 
        ds.union(3,7); 

        System.out.println(ds.findSet(1)); 
        System.out.println(ds.findSet(2)); 
        System.out.println(ds.findSet(3)); 
        System.out.println(ds.findSet(4)); 
        System.out.println(ds.findSet(5)); 
        System.out.println(ds.findSet(6)); 
        System.out.println(ds.findSet(7)); 
    }  
} 
