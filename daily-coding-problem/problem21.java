class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        if(intervals == null)
            return 0;
        
        Arrays.sort(intervals, (a,b) -> { 
            if(a.start != b.start)
                return a.start-b.start;
            else return a.end-b.end; } );
        
        int maxRooms = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(Interval i : intervals) {
            while(!pq.isEmpty() && i.start >= pq.peek())
                pq.poll();
            
            pq.offer(i.end);
            maxRooms = Math.max(maxRooms, pq.size());
        }
        return maxRooms;
    }
}
