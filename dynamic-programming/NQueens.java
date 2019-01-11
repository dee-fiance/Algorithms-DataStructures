/**
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.
Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' 
both indicate a queen and an empty space respectively.
Example:
Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],
["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
Time: O(n^2)

**/

public class NQueens { 
  private class NQueenPos {
      int row;
      int col; 
      NQueenPos(int r, int c) { row=r; col=c;}
  }
  //FIND one Solution only
  public NQueenPos[] solve(int n) { 
      NQueenPos[] Positions = new NQueenPos[n];
      boolean hasSolution = solveUtil(n, Positions,0);
      if(hasSolution) 
                return Positions;
      else return new NQueenPos[0]; 
  }

  private boolean solveUtil (int n, NQueenPos[] pos, int row) { 
    If(n==row) return true;

    For(int col=0;col<n;col++) {
      Boolean isSafe = true;
      //for each queen placed before the current row
      For(int queen = 0 ; queen < row; queen++) {
        If(pos[queen].row + pos[queen].col == row+col ||
        pos[queen].row - pos[queen].col == row-col ||
        pos[queen].col == col) {
          isSafe =false;
          break;
        }
      }
      //row and col are safe to place the queen.
      if(isSafe) 
        pos[row] = new NQueenPos(row,col);
        if(solveUtil(n,pos,row+1)) return true;
      }
    }
    return false;
  }
}
//FIND all possible solutions
  public List<List<String>> solve(int n) {
	NQueenPos[] Positions = new NQueenPos[n]; 
	List<List<String>> result = new ArrayList<>(); 
	solveUtil(n, Positions,0, result);
	return result; 
   }
  private List<List<String>> solveUtil(int n, NQueenPos[] pos, int row, List<List<String>> result) {
	if(row==n) {
		List<String> temp = new List<String>();
		for(int i=0; i<n ;i++) {
			StringBuffer sb = new StringBuffer();
			for(int j=0; j<n ;j++) {
				if(pos[i].col == j)
					sb.append("Q");
				else sb.append(".");
			}
			temp.add(sb.toString());
		}
		result.add(temp);
	}
	for(int col=0;col<n;col++) {
		boolean isSafe = true;
		//for each queen placed before the current row
		for(int queen = 0 ; queen < row; queen++) {
			if(pos[queen].row + pos[queen].col == row+col ||
			pos[queen].row - pos[queen].col == row-col ||
			pos[queen].col == col) {
				isSafe =false;
				break;
			}
		}
		//row and col are safe to place the queen.
		if(isSafe) 
			pos[row] = new NQueenPos(row,col);
			solveUtil(n,pos,row+1);
		}
	}
	return;
}
