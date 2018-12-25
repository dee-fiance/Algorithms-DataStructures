import java.util.*;

public class DijkstraShortestPathPQ {
	Graph g;
  //This is a node for priority queue.
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
					if( (e.getVertex1() == this && e.getVertex2() == n) || 
          (e.getVertex2() == this && e.getVertex1() == n)) {
						return e.getWeight();
					}
				}
			}
			return 0;
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
	}//Edge
	
	public List<Character> getShortestPath(Character start, Character finish) {
        final Map<Vertex, Integer> distances = new HashMap<Vertex, Integer>();
        final Map<Vertex, Vertex> parent = new HashMap<Vertex, Vertex>();
        PriorityQueue<Node> nodes = new PriorityQueue<Node>((a,b) -> a.dist-b.dist);
        LinkedList<Character> path = new LinkedList<Character>();
        
        for(Vertex v : g.getAllVertices()) {
        		if(v.getId() == start) {
        			distances.put(v, 0);
        			parent.put(v, null);
        			nodes.offer(new Node(v,0));
        		} else {
        			distances.put(v, Integer.MAX_VALUE);
        			parent.put(v, null);
        			nodes.offer(new Node(v, Integer.MAX_VALUE));
        		}
        }
               
        while(!nodes.isEmpty()) {
        		Node minNode = nodes.poll();
        		if(minNode.v.getId() == finish) {
        			Vertex c = minNode.v;
        			while(c != null) {
        				path.addFirst(c.getId());
        				c = parent.get(c);
        			}
        			break;
        		}
        		if(distances.get(minNode.v) == Integer.MAX_VALUE)
        			continue;
        		for(Vertex n : minNode.v.getAdjacentVertices()) {
        			
        			int newDist = distances.get(minNode.v) + minNode.v.getEdgeWeight(n);
        			if(newDist < distances.get(n)) {
        				distances.put(n, newDist);
        				parent.put(n, minNode.v);
        			}
        			
        			for(Node node : nodes) {
        				if(node.v.getId() == n.getId()) {
        					nodes.remove(node);
        					nodes.offer(new Node(n,newDist));
        					break;
        				}
        			}
        		}
        }
        
        for(Vertex v : distances.keySet())
			System.out.print("\n"+v.getId() +" --> "+distances.get(v));

        return path;
	}
	public void printGraph() {
		for(Character c : g.getVertices().keySet()) {
			System.out.print("\n"+c +" --> ");
			Vertex n = g.getVertices().get(c);
			for(Vertex v : n.getAdjacentVertices())
				System.out.print(v.getId()+ " ");
		}
	}
	public static void main(String[] args) { 
	    DijkstraShortestPathPQ i = new DijkstraShortestPathPQ();
	    Graph g = i.new Graph();
	    g.addEdge('A', 'B', 7);
	    g.addEdge('A', 'C', 8);
	    g.addEdge('B', 'F', 2);
	    g.addEdge('C', 'F', 6);
	    g.addEdge('C', 'G', 4);
	    g.addEdge('F', 'G', 9);
	    g.addEdge('F', 'D', 8);
	    g.addEdge('F', 'H', 3);
	    g.addEdge('H', 'E', 1);
	    i.g = g;
	    i.printGraph();
	    List<Character> path = i.getShortestPath('A','H');
	    System.out.println("\nShortest Path: "+path);
	}
}
