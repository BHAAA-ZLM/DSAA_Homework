package Week3;

import java.util.Scanner;

public class Week3_C_00 {

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
            else
                b[tmpPos++] = a[mid++];
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

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int n = input.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = input.nextInt();
        }
        check(a);
    }

    private static void check(int[] a){
        int[] b = new int[a.length];
        mergeSort(a,b,0,a.length-1);
        int smallLimit = a.length / 3;
        int large = a.length / 3, small = 0;
        System.out.println(a[smallLimit]);
        while(small < smallLimit){
            System.out.print(a[small++] + " " + a[large++] + " " + a[large++] + " ");
        }
        while(large < a.length){
            System.out.print(a[large++] + " ");
        }
    }
}
