public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)
            return null;
        ListNode curA = headA;
        ListNode curB = headB;
        while(curA != curB) {
            if(curA.next == null && curB.next == null)
                break;
            curA = (curA.next == null)? headB : curA.next;
            curB = (curB.next == null)? headA : curB.next;
        }
        if(curA == curB)
            return curA;
        else return null;
    }
}
