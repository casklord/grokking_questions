class ListNode {
  int value = 0;
  ListNode next;

  ListNode(int value) {
    this.value = value;
  }
}

class LinkedListCycleLength {

  public static int findCycleLength(ListNode head) {
      ListNode fast = head; ListNode slow = head;
      while(fast != null && fast.next != null){
          fast = fast.next.next;
          slow = slow.next;
          if (fast == slow){
              return calculateLength(slow);

          }
      }
      return 0;
  }
    public static int findCycleLengthSolution(ListNode head) {
      ListNode slow = head;
      ListNode fast = head;
      while (fast != null && fast.next != null) {
        fast = fast.next.next;
        slow = slow.next;
        if (slow == fast) // found the cycle
          return calculateLength(slow);
      }
      return 0;
    }


  private static int calculateLength(ListNode slow) {
    ListNode current = slow.next;
    int length = 1;

    while(current != slow){
        current = current.next;
        length ++;
    }
    return length;

  }

  private static int calculateLengthSolution(ListNode slow) {
    ListNode current = slow;
    int cycleLength = 0;
    do {
      current = current.next;
      cycleLength++;
    } while (current != slow);
    return cycleLength;
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);
    head.next.next.next.next.next = new ListNode(6);
    head.next.next.next.next.next.next = head.next.next;
    System.out.println("LinkedList cycle length: " 
                          + LinkedListCycleLength.findCycleLength(head));

    head.next.next.next.next.next.next = head.next.next.next;
    System.out.println("LinkedList cycle length: " 
                          + LinkedListCycleLength.findCycleLength(head));
  }
}

class LinkedListCycleStart {

    public static ListNode findCycleStart(ListNode head) {
      int cycleLength = 0;
      // find the LinkedList cycle
      ListNode slow = head;
      ListNode fast = head;
      while (fast != null && fast.next != null) {
        fast = fast.next.next;
        slow = slow.next;
        if (slow == fast) { // found the cycle
          cycleLength = calculateCycleLength(slow);
          break;
        }
      }
  
      return findStart(head, cycleLength);
    }
  
    private static int calculateCycleLength(ListNode slow) {
      ListNode current = slow;
      int cycleLength = 0;
      do {
        current = current.next;
        cycleLength++;
      } while (current != slow);
      
      return cycleLength;
    }
  
    private static ListNode findStart(ListNode head, int cycleLength){
        ListNode p1 = head;
        ListNode p2 = head;
        for (int i = 0; i < cycleLength; i ++){
            p2 = p2.next;
        }

        while (p1 != p2){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    
    private static ListNode findStartSolution(ListNode head, int cycleLength) {
      ListNode pointer1 = head, pointer2 = head;
      // move pointer2 ahead 'cycleLength' nodes
      while (cycleLength > 0) {
        pointer2 = pointer2.next;
        cycleLength--;
      }
  
      // increment both pointers until they meet at the start of the cycle
      while (pointer1 != pointer2) {
        pointer1 = pointer1.next;
        pointer2 = pointer2.next;
      }
  
      return pointer1;
    }
  
    public static void main(String[] args) {
      ListNode head = new ListNode(1);
      head.next = new ListNode(2);
      head.next.next = new ListNode(3);
      head.next.next.next = new ListNode(4);
      head.next.next.next.next = new ListNode(5);
      head.next.next.next.next.next = new ListNode(6);
  
      head.next.next.next.next.next.next = head.next.next;
      System.out.println("LinkedList cycle start: " + 
                              LinkedListCycleStart.findCycleStart(head).value);
  
      head.next.next.next.next.next.next = head.next.next.next;
      System.out.println("LinkedList cycle start: " + 
                              LinkedListCycleStart.findCycleStart(head).value);
  
      head.next.next.next.next.next.next = head;
      System.out.println("LinkedList cycle start: " + 
                              LinkedListCycleStart.findCycleStart(head).value);
    }
  }

class HappyNumber {


    public static boolean find(int num) {

        int fast = findSquareSum(num);
        int slow = num; 
        while(fast != slow) {
            fast = findSquareSum(findSquareSum(fast));
            slow = findSquareSum(slow);
            if (fast == slow){
                if (fast == 1) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;

    }


    public static boolean findSolution(int num) {
        int slow = num, fast = num;
        do {
        slow = findSquareSum(slow); // move one step
        fast = findSquareSum(findSquareSum(fast)); // move two steps
        } while (slow != fast); // found the cycle

        return slow == 1; // see if the cycle is stuck on the number '1'
    }


    private static int findSquareSum(int num){

        int total = 0;
        int digit;
        while (num != 0){
            digit = (num % 10);
            total +=  digit * digit;
            num = num/10;
        }
        return total;
    }

    private static int findSquareSumSolution(int num) {
        int sum = 0, digit;
        while (num > 0) {
        digit = num % 10;
        sum += digit * digit;
        num /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(HappyNumber.find(23));
        System.out.println(HappyNumber.find(12));
    }
}

class MiddleOfLinkedList {

    public static ListNode findMiddle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next!= null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static ListNode findMiddleSolution(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println("Middle Node: " + MiddleOfLinkedList.findMiddle(head).value);

        head.next.next.next.next.next = new ListNode(6);
        System.out.println("Middle Node: " + MiddleOfLinkedList.findMiddle(head).value);

        head.next.next.next.next.next.next = new ListNode(7);
        System.out.println("Middle Node: " + MiddleOfLinkedList.findMiddle(head).value);
    }
}










