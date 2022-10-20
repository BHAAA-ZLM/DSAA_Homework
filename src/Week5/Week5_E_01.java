package Week5;

import java.io.*;
import java.util.StringTokenizer;

public class Week5_E_01 {
    public static final QReader in = new QReader();
    public static final QWriter out = new QWriter();

    public static void main(String[] args) {
        int n = in.nextInt();
        int m = in.nextInt();
        BlockList block = new BlockList(n);

        int sqrtN = (int)Math.floor(Math.sqrt(n)) + 1;

        //the two 2D arrays for insertion step
        int[][] positions = new int[283][283];
        int[][] buckets = new int[283][80000];

        for(int i = 0; i < n; i++){
            int x = in.nextInt();
            Node newNode = new Node(x);
            BlockList.insert(block, newNode);
            insertToArray(positions,buckets,i,x);
        }//O(n)
        for(int i = 0; i < m; i++){
            String operation = in.next();

            //The step of insertion
            if(operation.equals("I")){
                insertNew(block, in.nextInt(), in.nextInt(),positions,buckets);//O(sqrt(n))
            }

            //The step of swapping
            if(operation.equals("M")){
                exchange(block,in.nextInt(),in.nextInt());//O(sqrt(n))
            }

            //The step of finding the Kth smallest
            if(operation.equals("Q")){
                int l = in.nextInt();
                int r = in.nextInt();
                int k = in.nextInt();
                out.println(findKSmallest(block,l,r,k));
            }
        }
        out.close();
    }

    //for inserting the n elements into their appropriate position in the two arrays
    public static void insertToArray(int[][] positions, int[][] buckets, int i, int x){
        int sqrtN = positions.length;
        int guideIndex = i/sqrtN;
        int intervalIndex = x/283;
        if (guideIndex == 0) {
            positions[guideIndex][intervalIndex]++;
            buckets[guideIndex][x]++;
        } else {
            if (positions[guideIndex][intervalIndex] == 0) {
                positions[guideIndex][intervalIndex] += positions[guideIndex - 1][intervalIndex];
            }
            positions[guideIndex][intervalIndex]++;
            if (buckets[guideIndex][x] == 0) {
                buckets[guideIndex][x] += buckets[guideIndex - 1][x];
            }
            buckets[guideIndex][x]++;
        }
    }

    //for operation "Q", finding the kth smallest number in interval [l,r]
    public static int findKSmallest(BlockList block, int l, int r, int k){
        int dif = r - l;
        int unchangedif = dif;
        Guide lGuide = block.headGuide.next;

        //find the left guide
        while(l > lGuide.count){
            l -= lGuide.count;
            lGuide = lGuide.next;
        }//O(sqrt(n))

        int left = lGuide.count - l;
        Node lNode = lGuide.element;
        //find the left node
        for(int i = 0; i < l; i++){
            lNode = lNode.next;
        }//O(sqrt(n))

        //find the right guide and node
        Guide rGuide = lGuide;
        Node rNode;
        //if the guide of left and right are different
        if(dif > left){
            rGuide = rGuide.next;
            dif -= left;
            while(dif > rGuide.count){
                dif -= rGuide.count;
                rGuide = rGuide.next;
            }
            rNode = rGuide.element;
            for(int i = 0; i < dif; i++){
                rNode = rNode.next;
            }
        }
        //if the guide of left and right are not different
        else{

            rNode = lNode;
            for(int i = 0; i < dif; i++){
                rNode = rNode.next;
            }
        }//O(n)

        int arrayGuideLength = 283;
        int arrayGuideArrayLength = 80000/arrayGuideLength + 1;

        //adding the values to a new array
        ArrGuide[] arrayGuides = new ArrGuide[arrayGuideLength];

        Guide tmpGuide = lGuide;
        Node tmpNode = lNode;
        while(tmpGuide == lGuide){
            while(tmpNode != null && tmpNode != rNode.next){

                //add the value to the arrGuide array
                int index = tmpNode.val/arrayGuideArrayLength;
                if(arrayGuides[index] == null){
                    ArrGuide newArrGuide = new ArrGuide(unchangedif + 1);
                    arrayGuides[index] = newArrGuide;
                    ArrGuide.add(newArrGuide,tmpNode.val);
                }else{
                    ArrGuide.add(arrayGuides[index],tmpNode.val);
                }

                tmpNode = tmpNode.next;
            }
            tmpGuide = tmpGuide.next;
        }
        while(tmpGuide != null && tmpGuide.last!=rGuide){
            tmpNode = tmpGuide.element.next;
            if(rNode == null) {
                out.println("rNode is null, breaking");
                break;
            }
            while(tmpNode != null && tmpNode != rNode.next){

                //add the value to the arrGuide array
                int index = tmpNode.val/arrayGuideArrayLength;
                if(arrayGuides[index] == null){
                    ArrGuide newArrGuide = new ArrGuide(unchangedif + 1);
                    arrayGuides[index] = newArrGuide;
                    ArrGuide.add(newArrGuide,tmpNode.val);
                }else{
                    ArrGuide.add(arrayGuides[index],tmpNode.val);
                }

                tmpNode = tmpNode.next;
            }
            tmpGuide = tmpGuide.next;
        }

        //find the location of the kth value
        int arrGuideIndex = 0;
        while(arrayGuides[arrGuideIndex] == null){
            arrGuideIndex++;
        }
        while(k > arrayGuides[arrGuideIndex].count){
            k -= arrayGuides[arrGuideIndex].count;
            arrayGuides[arrGuideIndex] = new ArrGuide(0);
            arrGuideIndex++;
        }

        //sort the right array in arrayGuides
        mergeSort(arrayGuides[arrGuideIndex].array, 0,
                arrayGuides[arrGuideIndex].array.length - 1);

        return arrayGuides[arrGuideIndex].array[k + arrayGuides[arrGuideIndex].zeroCount-1];
    }


    //merge sort
    public static void mergeSort(int[] arr, int l, int r){
        if(l < r){
            int mid = l + (r - l)/2;
            mergeSort(arr, l, mid);
            mergeSort(arr, mid+1, r);
            merge(arr,l,mid,r);
        }
    }
    public static void merge(int[] arr, int l, int mid, int r){
        int n1 = mid - l + 1;
        int n2 = r - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for(int i = 0; i < n1; i++){
            L[i] = arr[i + l];
        }
        for(int i = 0; i < n2; i++){
            R[i] = arr[mid + 1 + i];
        }

        int i = 0, j = 0;
        int k = l;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    //for operation "M", exchanging the node at given position
    public static BlockList exchange(BlockList block, int a, int b){
        Node newNode = new Node(b);
        Node changeNode = findNode(block,a);
        changeNode.last.next = newNode;
        if(changeNode.next != null) changeNode.next.last = newNode;
        newNode.last = changeNode.last;
        newNode.next = changeNode.next;
        changeNode.last = null;
        changeNode.next = null;
        return block;
    }

    //for operation "I", inserting a new node to a given position
    public static BlockList insertNew(BlockList block, int a, int x, int[][] positions, int[][] buckets){
        Node b = new Node(x);
        Guide tmpGuide = block.headGuide.next;
        int arrGuideCount = 0;
        int intervalIndex = x/283;

        //find the ath guide
        while(a > tmpGuide.count){
            a -= tmpGuide.count;
            tmpGuide = tmpGuide.next;
            arrGuideCount++;
        }

        Node tmpNode = tmpGuide.element;
        //find the ath node
        for(int i = 0; i < a; i++){
            tmpNode = tmpNode.next;
        }

        //insert the node before the ath node, adjust guide count
        insertBeforeNode(tmpNode,b);
        tmpGuide.count++;

        //adjust two arrays
        for(int i = arrGuideCount; i < positions.length; i++){
            positions[i][intervalIndex]++;
            buckets[i][x]++;
        }

        //If one guide is too long, break it into two parts
        if(tmpGuide.count > tmpGuide.size * 2){


            int guideCount = tmpGuide.count;
            Guide newGuide = new Guide(block.guideSize);
            newGuide.next = tmpGuide.next;
            newGuide.last = tmpGuide;
            if(tmpGuide.next != null) tmpGuide.next.last = newGuide;
            tmpGuide.next = newGuide;
            Node breakNode = tmpGuide.element.next;
            for(int i = 0; i < guideCount/2; i++){
                breakNode = breakNode.next;
            }
            breakNode.last.next = null;
            breakNode.last = newGuide.element;
            newGuide.element.next = breakNode;
            tmpGuide.count = guideCount/2;
            newGuide.count = guideCount - guideCount/2;

            //operate the two array to fit demands
            changeArraySplite(positions,buckets,arrGuideCount,tmpGuide,newGuide);
        }

        return block;
    }

    public static void changeArraySplite(int[][] positions,int[][] buckets, int arrGuideCount,Guide tmpGuide, Guide newGuide){
        //operate the two array to fit demands
        int[][] newPositions = new int[positions.length + 1][283];
        int[][] newBuckets = new int[buckets.length + 1][80000];
        int originPos = 0;
        for(int i = 0; i < newPositions.length;i++){
            if(i != arrGuideCount){
                newPositions[i] = positions[originPos++];
            }else{
                originPos++;
                Node insertToArrayNode = tmpGuide.element.next;
                while(insertToArrayNode!=null){
                    int val = insertToArrayNode.val;
                    newPositions[i][val/283]++;
                    newBuckets[i][val]++;
                    insertToArrayNode = insertToArrayNode.next;
                }
                i++;
                insertToArrayNode = newGuide.element.next;
                while(insertToArrayNode!=null){
                    int val = insertToArrayNode.val;
                    newPositions[i][val/283]++;
                    newBuckets[i][val]++;
                    insertToArrayNode = insertToArrayNode.next;
                }
            }
        }
    }

    public static void insertBeforeNode(Node c, Node b){
        b.next = c;
        b.last = c.last;
        c.last.next = b;
        c.last = b;
    }

    //find the node for given index in blockList
    public static Node findNode(BlockList block, int a) {
        Guide tmpGuide = block.headGuide.next;
        while(a > tmpGuide.count){
            a -= tmpGuide.count;
            tmpGuide = tmpGuide.next;
        }
        Node tmpNode = tmpGuide.element;
        for(int i = 0; i < a; i++){
            tmpNode = tmpNode.next;
        }
        return tmpNode;
    }

    static class Node{
        Node next;
        Node last;
        int val;
        Node(int val){
            this.val = val;
        }
    }

    static class Guide{
        Guide next;
        Guide last;
        int size;
        int count = 0;
        Node element = new Node(-1);
        Node tmpElement;

        Guide(int size){
            this.size = size;
        }
    }

    static class BlockList{
        Guide headGuide = new Guide(-1);
        Guide tmpGuide = headGuide;
        int guideSize;

        BlockList(int n){
            guideSize = (int)Math.floor(Math.sqrt(n));
        }

        public static BlockList insert(BlockList block, Node node){

            //Insert guide when there are no guides
            if(block.headGuide.next == null){
                Guide newGuide = new Guide(block.guideSize);
                block.headGuide.next = newGuide;
                newGuide.last = block.headGuide;
                block.tmpGuide = newGuide;
            }

            //Insert elements into the tmpGuide
            if(block.tmpGuide.count < block.guideSize){
                if(block.tmpGuide.element.next == null){
                    block.tmpGuide.element.next = node;
                    node.last = block.tmpGuide.element;
                    block.tmpGuide.tmpElement = node;
                    block.tmpGuide.count++;
                }else{
                    block.tmpGuide.tmpElement.next = node;
                    node.last = block.tmpGuide.tmpElement;
                    block.tmpGuide.tmpElement = node;
                    block.tmpGuide.count++;
                }
            }

            //When tmpGuide is full, create new guide and set tmpGuide to the new Guide
            if(block.tmpGuide.count >= block.guideSize){
                Guide newGuide = new Guide(block.guideSize);
                block.tmpGuide.next = newGuide;
                newGuide.last = block.tmpGuide;
                block.tmpGuide = newGuide;
            }
            return block;
        }
    }

    static class ArrGuide{
        int count = 0;
        int[] array;
        int tmpPos = 0;
        int zeroCount;

        ArrGuide(int n){
            array = new int[n];
            zeroCount = n;
        }

        public static ArrGuide add(ArrGuide arrGuide, int a){
            arrGuide.array[arrGuide.tmpPos++] = a;
            arrGuide.count++;
            arrGuide.zeroCount--;
            return arrGuide;
        }
    }
}
