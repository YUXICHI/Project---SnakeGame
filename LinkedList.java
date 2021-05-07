package LinkedList;

public class LinkedList {
    private LinkedListNode firstNode = null;
    private LinkedListNode lastNode = null;

    public boolean isEmpty() {
        return firstNode == null;
    }

    public int count() {
        int n = 0;

        LinkedListNode node = firstNode;
        while (node != null) {
            n++;
            node = node.next;
        }

        return n;
    }

    public LinkedListNode begin() {
        return firstNode;
    }

    public LinkedListNode end() {
        return lastNode;
    }

    public void pushBack(Object data) {
        LinkedListNode newNode = new LinkedListNode(data);

        if (firstNode == null) {
            firstNode = newNode;
        } else {
            lastNode.next = newNode;
            newNode.prev = lastNode;
        }
        lastNode = newNode;
    }


    public void pushFront(Object data) {
        LinkedListNode newNode = new LinkedListNode(data);

        if (firstNode == null) {
            firstNode = newNode;
            lastNode = newNode;
        } else {
            newNode.next = firstNode;
            firstNode.prev = newNode;
            firstNode = newNode;
        }
    }

    public void popBack() {
        if (firstNode == null) {
            return;
        }

        if (firstNode == lastNode) {
            firstNode = null;
            lastNode = null;
        } else {
            lastNode = lastNode.prev;
            lastNode.next=null;
        }
    }

    public void popFront() {
        if (firstNode == null) {
            return;
        }

        if (firstNode == lastNode) {
            firstNode = null;
            lastNode = null;
        } else {
            firstNode = firstNode.next;
            firstNode.prev = null;
        }
    }

    public Object front() {
        if (firstNode != null) {
            return firstNode.data;
        } else {
            return null;
        }
    }

    public Object back() {
        if (lastNode != null) {
            return lastNode.data;
        } else {
            return null;
        }
    }

    public void clear() {
        firstNode = null;
        lastNode = null;
    }

    public void print() {
        System.out.println("=====start======");

        LinkedListNode node = firstNode;
        while (node != null) {
            System.out.println(node.data);
            node = node.next;
        }

        System.out.println("=======end======");
    }

    public void print_backToFront() {
        System.out.println("=======start======");

        LinkedListNode node = lastNode;
        while (node != null) {
            System.out.println(node.data);
            node = node.prev;
        }

        System.out.println("=======end======");
    }
}
