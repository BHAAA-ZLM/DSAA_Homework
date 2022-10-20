package Week3;

import java.util.Scanner;

public class Week3_A_00 {

    private static void mergeSort(int[] a, int[] b, int left, int right){
        if(left < right){
            int mid = left + (right - left) / 2;
            mergeSort(a,b,left,mid);
            mergeSort(a,b,mid + 1,right);
            merge(a,b,left,mid+1,right);
        }
    }

    private static void merge(int[] a, int[] b, int left, int mid, int right){
        int leftEnd = mid - 1;
        int tmpPos = left;
        int numElements = right - left + 1;

        while(left <= leftEnd && mid <= right){
            if(a[left] - a[mid] >= 0)
                b[tmpPos++] = a[left++];
            else
                b[tmpPos++] = a[mid++];
        }

        while(left <= leftEnd){
            b[tmpPos++] = a[left++];
        }

        while(mid <= right){
            b[tmpPos++] = a[mid++];
        }

        for(int i = 0; i < numElements; i++, right--){
            a[right] = b[right];
        }
    }

    private static void bubbleSort(int[] a){
        for(int i = 0; i < a.length - 1; i++){
            for(int j = 1; j < a.length; j++){
                if(a[j] < a[j-1]){
                    int n = a[j-1];
                    a[j-1] = a[j];
                    a[j] = n;
                }
            }
        }
    }

    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int length = input.nextInt();
        int[] a = new int[length];
        int[] b = new int[length];
        for(int i = 0; i < length; i++){
            a[i] = input.nextInt();
        }
        long sum = 0;

        mergeSort(a,b,0,length - 1);
        for(int i : a){
            System.out.println(i);
        }
        for(int i = 0; i < length / 2; i ++){
            sum += (long)a[i] * (long)a[length - i - 1];
        }
        System.out.println(sum);
    }

}
