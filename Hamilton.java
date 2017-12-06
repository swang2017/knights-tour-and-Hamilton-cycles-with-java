import java.util.*;

////////////////////////////////////////////////////////////////
class HamiltonApp
{
  public static void main(String[] args)
  {
    Graph theGraph = new Graph();
    theGraph.addVertex('A');    // 0  (start for dfs)
    theGraph.addVertex('B');    // 1
    theGraph.addVertex('C');    // 2
    theGraph.addVertex('D');    // 3
    theGraph.addVertex('E');    // 4
    
    theGraph.addEdge(0, 1, 5);     // AB
    theGraph.addEdge(1, 2, 4);     // BC
    theGraph.addEdge(0, 3, 3);     // AD
    theGraph.addEdge(3, 4, 8);     // DE
    theGraph.addEdge(2, 4, 7);     // CE
    theGraph.addEdge(0, 4, 2);     // AE
    
    System.out.println();
      System.out.println("Show the adjacency matrix: \n");
    theGraph.displayAdjMat();
     
    System.out.println("Possible Cycles: ");   
   for(int i=0; i<5; i++)                                            // print out cycles starting with each vertex
   {theGraph.dfs(i);             // depth-first search
     System.out.println();}
     
  }  // end main()
}  // end class HamiltonApp

////////////////////////////////////////////////////////////////
class Vertex
{
  public char label;        // label (e.g. 'A')
  public boolean wasVisited;
// ------------------------------------------------------------
  public Vertex(char lab)   // constructor
  {
    label = lab;
    wasVisited = false;
  }
// ------------------------------------------------------------
}  // end class Vertex
////////////////////////////////////////////////////////////////
class Graph
{
  private final int MAX_VERTS = 20;
  private Vertex vertexList[]; // list of vertices
  private int adjMat[][];      // adjacency matrix
  private int nVerts;          // current number of vertices
  private Stack theStack=new Stack(); // use stack for DFS 
  private int temp;                  // Helper for print out the last vertex
  //private int solutionMat[];
// ------------------------------------------------------------
  public Graph()               // constructor
  {
    
    vertexList = new Vertex[MAX_VERTS];
    // adjacency matrix
    adjMat = new int[MAX_VERTS][MAX_VERTS];
    nVerts = 0;
    for(int y=0; y<MAX_VERTS; y++)      // set adjacency
      for(int x=0; x<MAX_VERTS; x++)   //    matrix to 0
      adjMat[x][y] = 0;
    theStack = new Stack();
  }  // end constructor
// ------------------------------------------------------------
  public void addVertex(char lab)
  {
    vertexList[nVerts++] = new Vertex(lab);
  }
// ------------------------------------------------------------
  public void addEdge(int start, int end, int weight)
  {
    adjMat[start][end] = weight;
    adjMat[end][start] = weight;
  }
// ------------------------------------------------------------
  public void displayAdjMat()                  //  function to display ajacency matrix
  {
    for (int i=0; i<nVerts; i++)
    {for (int j=0; j<nVerts; j++)
      {System.out.print(adjMat[i][j]+"\t");
       }
         System.out.print("\n\n");
         }
      
      }


// ------------------------------------------------------------
   public void displayVertex(int v)
   {
     System.out.print(vertexList[v].label+"   ");
   }
// ------------------------------------------------------------
   public void dfs(int startVertex)  // depth-first search
   {                                 // begin at vertex 0
     
    /* int[][] solutionMat = new int[24][5];       // I tried to use a matrix to store all the vertices met during DFS.
     for(int i=0; i<24; i++)
     {for (int j=0; j<5; j++)
       {solutionMat[i][j]=0;
     System.out.print(solutionMat[i][j]+"\t");
     }
     System.out.println();
    
     } */
     Queue<Integer> visitQue=new LinkedList<Integer>();  // queue used for getting the last vertex
     
     vertexList[startVertex].wasVisited = true;  // mark it
     
     visitQue.add(startVertex);
     displayVertex(startVertex);                 // display it
     theStack.push(startVertex);                 // push it
     
     while( !theStack.isEmpty() && visitQue.size()!=nVerts )      // until stack empty,
     {
       // get an unvisited vertex adjacent to stack top
       
       int v = getAdjUnvisitedVertex( (int)theStack.peek());
        //visitQue.add(v);
       if(v == -1)                    // if no such vertex,
         theStack.pop();
       else                           // if it exists,
       {
         vertexList[v].wasVisited = true;  // mark it
        displayVertex(v);                 // display it
        visitQue.add(v);
         theStack.push(v);                 // push it
      
       }
       temp=v;
     }  //end while
        
     if (adjMat[startVertex][temp]!=0)
     displayVertex(startVertex);
     //System.out.println(visitQue.toString());
     // stack is empty, so we're done
     for(int j=0; j<nVerts; j++)          // reset flags
       vertexList[j].wasVisited = false;
   }  // end dfs
// ------------------------------------------------------------
   // returns an unvisited vertex adj to v
   public int getAdjUnvisitedVertex(int v)
   {
     for(int j=0; j<nVerts; j++)
       if(adjMat[v][j]!=0 && vertexList[j].wasVisited==false)
       return j;
      return -1;
   }  // end getAdjUnvisitedVertex()
// ------------------------------------------------------------
   }  // end class Graph
////////////////////////////////////////////////////////////////


