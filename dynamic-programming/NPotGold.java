/**
Consider a row of n coins of values v1 . . . vn, where n is even. 
We play a game against an opponent by alternating turns. In each turn, a player selects either the first or last coin
from the row, removes it from the row permanently, and receives the value of the coin. Determine the maximum possible 
amount of money we can definitely win if we move first.
Note: The opponent is as clever as the user.

Ci, Ci+1, Ci+2, ….. Cj-2, Cj-1, Cj

Recursive approach,
F(I,j) = Max( Ci + min(F( i+1,j-1) , F(i+2, j) ), Cj + min (F(i+1, j-1) , F(I, j-2)) )

Ex: {3,1,5,6,2,9,3}

Entry of table: (First,second)
	  3	1	5	6	2	9	3
3	(3,0)	(3,1)	(6,3)	(9,6)	(10,7)	(16,10)	(13,16)
1		(1,0)	(5,1)	(7,5)	(7,7)	(16,7)	(16,10)
5			(5,0)	(6,5)	(7,6)	(15,7)	(10,15)
6				(6,0)	(6,2)	(11,6)	(15,5)
2					(2,0)	(9,2)	(5,9)
9						(9,0)	(9,3)
3							(3,0)

Diagonal: Only one coin and first player picks then 0 left for second player
Len of 2, first picks higher value.
When first picks first, what is left is min for second.
#NPotGold

Time: O(n2) 
**/

public class NPotGold {
    static class Pair {
        int first, second;
        int pick=0;
        public String toString(){
	        return first + " " + second + " " + pick; 
        }
}
public Pair[][] findMoves(int pots[]){
	Pair[][] moves = new Pair[pots.length][pots.length]; 
	for(int i=0; i < moves.length; i++){ 
		for(int j=0; j < moves[i].length; j++){ 
		    moves[i][j] = new Pair();
     }
	}
 
	for(int i=0; i < pots.length; i++){ 
		moves[i][i].first = pots[i]; 
		moves[i][i].second = 0;
		//to track the sequence of moves 
		moves[i][i].pick = i; 
	} 

	for(int l = 2; l <= pots.length; l++){ 
		for(int i=0; i <= pots.length - l; i++){ 
			int j = i + l -1;
			if(pots[i] + moves[i+1][j].second > moves[i][j-1].second + pots[j]){ 
				moves[i][j].first = pots[i] + moves[i+1][j].second; 
				moves[i][j].second = moves[i+1][j].first; 
				moves[i][j].pick = i; 
			}. else{
				moves[i][j].first = pots[j] + moves[i][j-1].second; 
				moves[i][j].second = moves[i][j-1].first; 
				moves[i][j].pick =j; 
			} 
		} 
	} 
    return moves;
}
public static void main(String args[]){ 
	NPotGold npg = new NPotGold();
	int pots[] = {3,1,5,6,2,9,3}; 
	Pair[][] moves = npg.findMoves(pots); 
	for(int i=0; i < moves.length; i++){ 
		for(int j=0; j < moves[i].length; j++){ 
			System.out.print(moves[i][j] + " "); 
		} 
        System.out.print("\n");
    }
 } 
}
