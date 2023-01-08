package Week5;

//Java Fast IO

public class Week5_A_00 {
    public static final QReader in = new QReader();
    public static final QWriter out = new QWriter();
    public static int counter = 0;

    public static void main(String[] args) {
        int n = in.nextInt();
        int m = in.nextInt();
        LinkedList nList = new LinkedList();
        LinkedList mList = new LinkedList();
        for(int i = 0; i < n; i++){
            Element tmp = new Element(in.nextLong(),in.nextInt());
            if(tmp.coefficient==0) {
                continue;
            }
            LinkedList.insert(nList, tmp);
        }
        for(int i = 0; i < m; i++){
            Element tmp = new Element(in.nextLong(),in.nextInt());
            if(tmp.coefficient==0) {
                continue;
            }
            LinkedList.insert(mList, tmp);
        }
        LinkedList finalList = compare(nList, mList);
        out.println(LinkedList.length(finalList));
        LinkedList.printList(finalList);
        out.close();
    }

    public static LinkedList compare(LinkedList A, LinkedList B){
        LinkedList list = new LinkedList();
        Node a = A.head;
        Node b = B.head;
        while(a != null && b != null) {
            if (a.value.exponent == b.value.exponent) {
                if(a.value.coefficient + b.value.coefficient == 0){
                    a = a.next;
                    b = b.next;
                    continue;
                }
                LinkedList.insert(list, new Element(a.value.coefficient + b.value.coefficient, a.value.exponent));
                counter++;
                a = a.next;
                b = b.next;
            } else if (a.value.exponent < b.value.exponent){
                LinkedList.insert(list, a.value);
                counter++;
                a = a.next;
            } else {
                LinkedList.insert(list, b.value);
                counter++;
                b = b.next;
            }
        }
        if(a == null){
//            LinkedList.insertNode(list, b);
            while(b != null){
                LinkedList.insert(list, b.value);
                counter++;
                b = b.next;
            }
        }
        if(b == null){
//            LinkedList.insertNode(list, a);
            while(a != null){
                LinkedList.insert(list, a.value);
                counter++;
                a = a.next;
            }
        }
        return list;
    }

    static class Element{
        long coefficient;
        int exponent;

        Element(long x, int y){
            coefficient = x;
            exponent = y;
        }
    }

    static class Node{
        Node next;
        Element value;
        Node(Element x){
            value = x;
            next = null;
        }
    }

    static class LinkedList{
        Node head;
        Node tail;

        public static LinkedList insertNode(LinkedList list, Node x){
            if(list.head == null){
                list.head = x;
            }else {
                Node tmp = list.head;
                while (tmp.next != null) {
                    tmp = tmp.next;
                }
                tmp.next = x;
            }
            return list;
        }
        public static LinkedList insert(LinkedList list, Element x){
            Node newNode = new Node(x);

            if(list.head == null){
                list.head = newNode;
                list.tail = list.head;
            }else{
                list.tail.next = newNode;
                list.tail = newNode;
            }
            return list;
        }

        public static void printList(LinkedList list){
            Node tmp = list.head;
            while(tmp != null){
                out.println(tmp.value.coefficient + " " + tmp.value.exponent);
                tmp = tmp.next;
            }
        }

        public static int length(LinkedList list){
            int counter = 0;
            Node tmp = list.head;
            while(tmp != null){
                counter++;
                tmp = tmp.next;
            }
            return counter;
        }
    }
}


