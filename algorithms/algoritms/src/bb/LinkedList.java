package bb;

public class LinkedList{

    class Node {
        int val;
        Node next;
    }

    public Node getNewNode(int val) {
        Node node = new Node();
        node.val = val;
        node.next = null;
        return node;
    }

    private int addOne(Node head) {
        if (head == null) {
            return 1;
        }
        int sum = head.val + addOne(head.next);
        head.val = sum % 10;
        return sum / 10;
    }

    public Node increment(Node head) {
        int carry = addOne(head);
        if (carry != 0) {
            Node newHead = getNewNode(carry);
            newHead.next = head;
            return newHead;
        }
        return head;
    }
    
    public void printList(Node head) {
        while(head!=null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.print("NULL");
        System.out.println();
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        LinkedList linkedList = new LinkedList();
        Node head = linkedList.getNewNode(4);
        head.next = linkedList.getNewNode(1);
        head.next.next = linkedList.getNewNode(9);
        linkedList.printList(head);
        head = linkedList.increment(head);
        linkedList.printList(head);
    }

}
