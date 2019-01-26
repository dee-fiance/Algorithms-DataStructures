/**
The key to solve the problem is defining a fake head. Then compare the first elements from each list. 
Add the smaller one to the merged list. Finally, when one of them is empty, simply append it to the merged list, 
since it is already sorted. 
**/ 
public class MergeTwoSortedLists {
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) { 
      ListNode newhead = new ListNode(0); 
      ListNode current = newhead; 

      while(l1!=null || l2!=null){ 
          if(l1!=null && l2!=null){ 
              if(l1.val < l2.val){ 
                  current.next = l1; 
                  l1=l1.next; 
              }else{ 
                  current.next=l2; 
                  l2=l2.next; 
              } 
              current = current.next; 
          }else if(l1==null){ 
              current.next = l2; 
              break; 
          }else if(l2==null){ 
              current.next = l1; 
              break; 
          } 
      } 

      return newhead.next; 
  } 
}
