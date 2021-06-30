/*
class ListNode {
    int val;
    ListNode next, random;
    ListNode(int x) {
        val = x;
        next = random = null;
    }
}
*/
   ListNode cloneList(ListNode headNode){

           if(headNode==null)
           return null;

           ListNode oldList=headNode;
           /**
            * creating new LL for deep copy
            */
           ListNode newList=new ListNode(-1);
           ListNode newListHead=newList;
           /**
            * auxMap will store address of prevNode to newNode, which will be used for assiging random pointers
            */
           HashMap<ListNode, ListNode> auxMap=new HashMap<>();

        while(oldList!=null)
        {
/**
 * first loop, just create new linked with next pointer, don't worry about random pointers,
 * and store address of oldNode to newNode in hashMap
 */
        newList.next=new ListNode(oldList.val);
        newList=newList.next;
        auxMap.put(oldList,newList);

        oldList=oldList.next;

        }

        ListNode oldPointerToCopyRandomPointer=headNode;
        ListNode newPointerToCopyRandomPointer=newListHead.next;
        /**
         * now simply iterate over old list and get address of old to new random pointer
         * and assign that new random pointer address to new linked list random pointer
         */
        while(oldPointerToCopyRandomPointer!=null)
        {

        ListNode currentOldRandomPointer=oldPointerToCopyRandomPointer.random;
        /**
         * get new random pointer for old pointer addrees and assign it to new list random pointer
         */
        ListNode newAddressOfRandomPointer=auxMap.get(currentOldRandomPointer);

        newPointerToCopyRandomPointer.random=newAddressOfRandomPointer;
        /**
         * move old and new list one step ahead
         */
        oldPointerToCopyRandomPointer=oldPointerToCopyRandomPointer.next;
        newPointerToCopyRandomPointer=newPointerToCopyRandomPointer.next;

        }
/**
 * return new ll
 */

        return newListHead.next;
        }