/**
Detect if a subset from a given set of N non-negative integers sums up to a given value S.
Example:
Input: Set: {1, 3, 9, 2}, S = 5 
Output: true 
Input:  set[] = {3, 34, 4, 12, 5, 2}, sum = 9
Output:  True  //There is a subset (4, 5) with sum 9.

Solution:
The recursive solution may try all subsets of given set in worst case. Therefore time complexity of the above solution is exponential. The problem is in-fact NP-Complete (There is no known polynomial time solution for this problem).
We can solve the problem in Pseudo-polynomial time using Dynamic programming. 

Time: O(sum*N)

	0	1	2	3	4	5	6	7	8	9
0	T	F	F	F	F	F	F	F	F	F
3	T	F	F	T	F	F	F	F	F	F
34	T	F	F	T	F	F	F	F	F	F
4	T	F	F	T	T	F	F	T	F	F
12	T	F	F	T	T	F	F	T	F	F
5	T	F	F	T	T	T	F	T	T	T
2	T	F	T	T	T	T	T	T	F	T
**/

public boolean SubsetSum() {
  boolean table[][] = new boolean [input.length][sum+1];
  int input[] = {3,34,4,12,5,2}

  for(int i=0; i < input.length ; i++) 
    table[i][0] = true;
  for(int j=1; j<=sum; j++)
    table[0][j] = false;

  for(int i=1; I <= input.length ; i++)  {
    for(int j=1; j<=sum; j++) {
      if(j - input[i] >=0) {
        table[i][j] = table[i-1][j] || table[i-1][j - input[i] ];
        //excluding current element can u find the sum || including current element can u find sum-current.
      else table[i][j] = tabl1[i-1][j];
    }
  }
  return table[input.length][sum];
}
