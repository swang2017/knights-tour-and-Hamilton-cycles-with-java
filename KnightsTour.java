import java.util.*;

public class KnightsTour 
{
  
  private int [][]board;
  private int size;
  private static int count=0; // helper to print out jump sequence and maintain matrix type printing format
  ////////////////////////////////////////////////////
  // Main function , the size and start location can be changed 
  public static void main(String[] args) 
  {
    //change board size here
    KnightsTour knightsTour = new KnightsTour(5);
    // change locaiton index here
    knightsTour.dfs(0);
  }
  
  ////////////////////////////////////////////////////
  public KnightsTour(int size)                           // constructor 
  {
    this.size=size;
    board = new int[size][size];
  }
  ////////////////////////////////////////////////////////  
  
  public void dfs(int locationIndex)
  {
    
    int row, col,top,temp;
    Stack<Integer> stack = new Stack<Integer>();
   //get start location
    row = locationIndex/size;
    col = locationIndex-row*size;
    

    // make a talbe and set the table elements to 0s
    System.out.print("Make a 0 table \n");
    for (int i=0; i<size; i++)
    {
      for (int j=0; j<size; j++)
      {
        board[i][j]=0;
        System.out.print(" "+board[i][j]+"\t");
      }
      System.out.print("\n");
    }
    System.out.print("\n\n\n");
    
    // print out the first location (based on location index)
    System.out.println("visit sequence (from 0 to " + (size*size-1)+", based on location index)"); // to print in matrix format
    count++;
    board[row][col]=count;
    System.out.print(" "+(row*size+col)+",  ");
    
    // push the start location to the stack
    stack.push(locationIndex);
    
    // loop to get the next vertices
    while (!stack.isEmpty()) 
    {
      top = stack.peek();
      
      // if the next vertex is not visited and not out of board
      if ( (temp = getAdjUnvistedVertex(top)) != -1) 
      {
        // calculate current temporary vertex and assign count value to this vertex
        row = temp/size;
        col = temp-row*size;
        count++;
        board[row][col]=count;
        
        // print out the follow location (based on location index)
        System.out.print("  "+(row*size+col)+",  ");
        
        // push the temporary vertex to stack
        stack.push(temp); // temp is knight next location
      } 
      else 
      {
        // if visited take the vertex out of the stack
        stack.pop();
      }
    }
    
    // print out the traversed table with real jump sequence. 
    System.out.print("\n\n\n");
     System.out.print(" jump step numbers on the board (from 1 to "+ size*size +")\n");
    for (int i=0; i<size; i++)
    {
      for (int j=0; j<size; j++)
      {
      
        System.out.print(" "+board[i][j]+"\t");
      }
      System.out.print("\n");
    }
    System.out.print("\n\n\n");
  }
  



///////////////////////////////////////////////////////////////////
  private int getAdjUnvistedVertex(int top) 
  {
    int row, col, rowIndex, leftColIndex, rightColIndex, i, j, index;
   // calculate the coordinate of the top element in the stack
    row = top/size;
    col = top-row*size;
    // for loop to vary among the eight directions a knight can jump on the board
    for (i=-2; i<=2; i++)
    {
      if (i==0)
      {
        continue; 
      }
      
      if (i%2==0) 
      {
        index=1;
      } 
      else 
      {
        index=2;
      }
      
      // calculation for the eight directions
      rowIndex = row+i;
      leftColIndex = col-index;
      rightColIndex= col+index;
      
      // checking whether the next vertex is within board and whether it's visited already or not. 
      // if visited return the location index
      // otherwise return -1
      if ( rowIndex >=0 && rowIndex < size)
      { 
        
        if ( leftColIndex >=0 && leftColIndex < size) 
        { // checking boundary conditions
          if (board[rowIndex][leftColIndex] == 0)
            return (rowIndex*size + leftColIndex); // knight next step loc
        }
        if ( rightColIndex >=0 && rightColIndex < size) 
        { // checking boundary conditions
          if (board[rowIndex][rightColIndex] == 0)
            return (rowIndex*size + rightColIndex); // knight next step loc
        }
      }
      
    }
    return -1;
  }
  ///////////////////////////////////////////////////////////////////
  
}