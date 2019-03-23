class Solution {
    public int minCostII(int[][] costs) {
        if(costs == null || costs.length == 0 || costs[0].length == 0)
            return 0;
        
        int min = 0;
        int secMin = 0;
        int minIndex = -1;
        for(int i = 0; i< costs.length ; i++) {
            int curMin = Integer.MAX_VALUE;
            int curSecMin = Integer.MAX_VALUE;
            int curMinIndex = -1;
            
            for(int j = 0; j < costs[0].length; j++) {
                int curCost = (j == minIndex) ?
                    secMin + costs[i][j] : 
                    min + costs[i][j];
                
                if(curCost < curMin) {
                    curSecMin = curMin;
                    curMin = curCost;
                    curMinIndex = j;
                } else if(curCost < curSecMin) {
                    curSecMin = curCost;
                }
            }
            min = curMin;
            secMin = curSecMin;
            minIndex = curMinIndex;
        }
        return min;
    }
}
