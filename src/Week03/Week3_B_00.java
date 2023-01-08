package Week3;

import java.util.Scanner;

public class Week3_B_00 {
    private static long sum = 0;

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
        int numElemtns = right - left + 1;

        while(left <= leftEnd && mid <= right){
            if(a[left] - a[mid] <= 0)
                b[tmpPos++] = a[left++];
            else {
                b[tmpPos++] = a[mid++];
                sum += leftEnd - left + 1;
            }
        }

        while(left <= leftEnd){
            b[tmpPos++] = a[left++];
        }

        while(mid <= right){
            b[tmpPos++] = a[mid++];
        }

        for(int i = 0; i < numElemtns; i++, right--){
            a[right] = b[right];
        }
    }

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int cases = input.nextInt();
        for(int i = 0; i < cases; i++){
            int n = input.nextInt();
            int[] a = new int[n];
            for(int j = 0; j < n; j++){
                a[j] = input.nextInt();
            }
            checkSwap(a);
        }
    }

    private static void checkSwap(int[] a){
        sum = 0;
        int[] b = new int[a.length];
        mergeSort(a,b,0,a.length - 1);
        System.out.println(sum);
    }

}
