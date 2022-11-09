package Week7;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Week7_B_00 {
    public static final Scanner input = new Scanner(System.in);
    public static final int hash = 29;

    public static void main(String[] args) {
        String A = input.next();
        String B = input.next();
        int length = min(A.length(), B.length());

        int oddLength = checkOddLength(length, A, B);
        int evenLength = checkEvenLength(length, A, B);
        System.out.println(max(oddLength, evenLength));
    }

    //check the max odd-length common palindromic string in A and B
    public static int checkOddLength(int length, String A, String B){
        int maxLength;
        if(length % 2 == 1){
            maxLength = length;
        }else{
            maxLength = length - 1;
        }

        int min = 0, max = maxLength;
        int mid = 0;
        while(min < max){
            mid = min + (max - min)/2;
            if(mid % 2 == 0) {
                mid--;
            }
            if(checkLength(mid, A, B)){
                min = mid + 2;
            }else{
                max = mid - 2;
            }
        }
        return mid;
    }

    //check the max even-length common palindromic string in A and B
    public static int checkEvenLength(int length, String A, String B){
        int maxLength;
        if(length % 2 == 1){
            maxLength = length - 1;
        }else{
            maxLength = length;
        }
        int min = 0, max = maxLength;
        int mid = 0;
        while(min < max){
            mid = min + (max - min)/2;
            if(mid % 2 == 1) {
                mid--;
            }
            if(checkLength(mid, A, B)){
                min = mid + 2;
            }else{
                max = mid - 2;
            }
        }
        return mid;
    }

    //check if A and B have the same palindromic sequence in the given length
    public static boolean checkLength(int length, String A, String B){
        int[] matchHashA = findPalindrom(length, A);
        int[] matchHashB = findPalindrom(length, B);

        for(int i : matchHashB){
            if(find(i, matchHashA)){
                return true;
            }
        }
        return false;
    }

    public static boolean find(int i,int[] matchHashA){
        int left = 0, right = matchHashA.length - 1;
        while(left < right){
            int mid = left + (right - left)/2;
            if(matchHashA[mid] < i){
                left = mid + 1;
            }else if(matchHashA[mid] > i){
                right = mid - 1;
            }else{
                return true;
            }
        }
        return false;
    }

    public static int[] findPalindrom(int length, String A){
        Node head = new Node(-1);
        Node tmpNode = head;
        int count = 0;
        int front = 0;
        int back = 0;
        int power = 1;
        for(int i = 0; i < length; i++){
            power = power * hash;
            front = front + (A.charAt(i) - 'a') * power;
            back = back + (A.charAt(length - 1 - i) - 'a') * power;
        }
        for(int i = 0; i < A.length() - length; i++){
            if(front == back){
                count++;
                tmpNode.next = new Node(front);
                tmpNode = tmpNode.next;
            }
            front = (front - (A.charAt(i) - 'a'))/hash + (A.charAt(i + length) - 'a') * power;
            back = (back - (A.charAt(i) - 'a') * power) * hash + (A.charAt(length + i) - 'a') * hash;
        }

        int[] matchHash = new int[count];
        int hashIndex = 0;
        while(head.next != null){
            head = head.next;
            matchHash[hashIndex] = head.hash;
            hashIndex++;
        }
        Arrays.sort(matchHash);
        return matchHash;
    }

    static class Node{
        Node next;
        int hash;
        Node(int hash){
            this.hash = hash;
        }
    }
}

