/*** 
This is all pair shortest path. 

Algorithm: 
Create a |V| x |V| matrix, M, that will describe the distances between vertices 
For each cell (i, j) in M: 
    if i == j: 
        M[i][j] = 0 
    if (i, j) is an edge in E: 
        M[i][j] = weight(i, j) 
    else: 
        M[i][j] = infinity 
for k from 1 to |V|: 
    for i from 1 to |V|: 
        for j from 1 to |V|: 
            if M[i][j] > M[i][k] + M[k][j]: 
                M[i][j] = M[i][k] + M[k][j] 
Time: O(V^3) 
Space: O(V^2) 
**/ 

public class FloydWarshall extends RuntimeException { 
  Graph g; 
public int[][] getShortestPaths() {  
    int vertices = g.getAllVertices().size();  
    int[][] distances = new int[vertices][vertices]; 
    for(int[] dist : distances) 
      Arrays.fill(dist, Integer.MAX_VALUE); 
  
    Map<Character, Integer> vertexIndex = new HashMap<Character, Integer>(); 
    int index = 0 ; 
    System.out.println("\nVertex and Index"); 
    for(Vertex v : g.getAllVertices()) { 
      vertexIndex.put(v.getId(), index); 
      System.out.println(v.getId()+ "->"+ index); 
      index++; 
    } 
  
    for(Vertex v : g.getAllVertices()) {  
       int i = vertexIndex.get(v.getId()); 
       for(Vertex n : v.getAdjacentVertices()) { 
          int j = vertexIndex.get(n.getId()); 
          int d = v.getEdgeWeight(n); 
          distances[i][j] = d; 
          distances[j][i] = d; 
       } 
    } 
    System.out.println("\nDistance matrix"); 
    for(int i =0 ;i<distances.length; i++) { 
      for(int j=0;j< distances[i].length; j++) { 
        if(i==j) distances[i][j] = 0; 
        System.out.print(" "+distances[i][j]); 
      } 
      System.out.println(); 
    } 
    for(int k =0 ; k<vertices; k++) { 
      for(int i =0 ; i<vertices; i++) { 
        for(int j=0; j<vertices; j++) { 
          if(distances[i][k]+distances[k][j] < distances[i][j]) 
            distances[i][j] = distances[i][k]+distances[k][j]; 
        } 
      } 
    } 
    return distances;  
  
  }  
  public void printGraph() { 
    System.out.print("Edge connectivity"); 
    for(Character c : g.getVertices().keySet()) {  
      System.out.print("\n"+c +" --> ");  
      Vertex n = g.getVertices().get(c);  
      for(Vertex v : n.getAdjacentVertices())  
        System.out.print(v.getId()+ " ");  
    } 
    System.out.println(); 
  }  
  
  public static void main(String[] args) { 
    FloydWarshall f = new FloydWarshall();  
    Graph g = f.new Graph(); 
    g.addEdge('A', 'B', 3); 
    g.addEdge('A', 'C', 6); 
    g.addEdge('A', 'D', 10); 
    g.addEdge('B', 'D', 4); 
    g.addEdge('C', 'D', 2); 
    g.addEdge('C', 'E', 6); 
    g.addEdge('D', 'E', 3); 
    f.g = g; 
    f.printGraph(); 
    int[][] distances = f.getShortestPaths(); 
    System.out.println("\nShortest path matrix"); 
    for(int i =0 ;i<distances.length; i++) { 
      for(int j=0;j< distances[i].length; j++) { 
        System.out.print(" "+distances[i][j]); 
      } 
      System.out.println(); 
    } 
  } 
}
/ *** 
OUTPUT 
Edge connectivity 
D --> A B C E 
E --> C D 
A --> B C D 
B --> A D 
C --> A D E 
  
Vertex and Index 
D->0 
E->1 
A->2 
B->3 
C->4 
  
**/ 
