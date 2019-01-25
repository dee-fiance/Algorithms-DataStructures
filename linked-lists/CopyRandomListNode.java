/**
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null. 
Return a deep copy of the list. 
Time: O(n) 
**/
public class CopyRandomListNode {
  class RandomListNode { 
    int val; 
    RandomListNode next; 
    RandomListNode random;  
    RandomListNode (int x) { 
      val = x; 
      next = null; 
      random = null; 
    } 
  } 

  public RandomListNode copyRandomList(RandomListNode head) { 

    if (head == null) 
      return null; 

    RandomListNode cur = head; 

    // copy every node and insert to list 
    while (cur != null) { 
      RandomListNode copy = new RandomListNode(cur.label); 
      copy.next = cur.next; 
      cur.next = copy; 
      cur = copy.next; 
    } 

    // copy random pointer for each new node 
    cur = head; 
    while (cur != null) { 
      if (cur.random != null) 
        cur.next.random = cur.random.next; 
      cur = cur.next.next; 
    } 

    // break list to two 
    cur  = head; 
    RandomListNode newHead = head.next; 

    while (cur  != null) { 
      RandomListNode t = cur.next; 
      cur.next = t.next; 
      if (t.next != null) 
        t.next = t.next.next; 
      cur = cur.next; 
    } 

    return newHead; 
  }
}
