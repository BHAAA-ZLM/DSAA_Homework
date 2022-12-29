package Week13;

import java.util.Scanner;

public class Week13_D_00 {
    public static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int n = in.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = in.nextInt();
        }

        mergeSort(a,0,n-1);

        if(checkGraph(a)){
            System.out.println("YES");
            if(checkSimple(a)){
                System.out.println("YES");
                if(checkTree(a)){
                    System.out.println("YES");
                }else{
                    System.out.println("NO");
                }
            }else{
                System.out.println("NO");
                System.out.println("NO");
            }
        }else{
            System.out.println("NO");
            System.out.println("NO");
            System.out.println("NO");
        }
    }

    public static boolean checkGraph(int[] a){
        long count = 0;
        for(int n : a){
            count += n;
        }
        return count % 2L == 0;
    }

    public static boolean checkSimple(int[] a){
        long[] suffixSum = new long[a.length];
        suffixSum[a.length - 1] = a[a.length - 1];
        for(int i = a.length - 2; i >= 0; i--){
            suffixSum[i] = suffixSum[i + 1] + a[i];
        }
        long need = 0;
        int k = a.length - 1;
        for(int i = 0; i < a.length; i++){
            need += a[i];

            //find k
            while(k > i && a[k] < i + 1){
                k--;
            }
            long supply = (long)i * (long)(i + 1);
            if(Math.max(i,k) < a.length - 1) {
                supply += suffixSum[Math.max(i, k) + 1];
            }
            if (k > i) {
                supply += (long)(k - i) * (long)(i + 1);
            }

            if(supply < need) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkTree(int[] a){
        long sum = 0;
        for (int j : a) {
            if(j == 0){
                return false;
            }
            sum += j;
        }
        if(sum == 0 && a.length == 1){
            return true;
        }
        return sum > 0 && sum == 2L * a.length - 2;
    }

    public static void mergeSort(int[] arr,int l, int r){
        if(l < r){
            int mid = l + (r - l)/2;
            mergeSort(arr, l, mid);
            mergeSort(arr, mid + 1, r);
            merge(arr,l,mid,r);
        }
    }
    public static void merge(int[] arr, int l, int mid, int r){
        int n1 = mid - l + 1;
        int n2 = r - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for(int i = 0; i < n1; i++){
            L[i] = arr[l + i];
        }
        for(int i = 0; i < n2; i++) {
            R[i] = arr[mid + 1 + i];
        }

        int i = 0, j = 0;
        int k = l;
        while(i < n1 && j < n2){
            if(L[i] >= R[j]){
                arr[k] = L[i];
                i++;
            }else{
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
}
