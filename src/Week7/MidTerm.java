package Week7;

public class MidTerm {

    //this are all the codes for the algorithms before the midterm

    //Binary search. Time complexity: O(n), Space complexity: O(1)
    public static boolean binarySearch(int[] sortedArray, int num){
        int left = 0, right = sortedArray.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(sortedArray[mid] > num){
                right = mid - 1;
            }else if(sortedArray[mid] < num){
                left = mid + 1;
            }else{
                return true;
            }
        }
        return false;
    }

    //Selection sort. Time complexity: O(n^2), space complexity: O(1)
    public static int[] selectionSort(int[] arr){
        for(int i = 0; i < arr.length - 1; i++){
            int k = i;
            for(int j = i + 1; j < arr.length; j++){
                if(arr[k] > arr[j]){
                    k = j;
                }
            }
            int x = arr[k];
            arr[k] = arr[i];
            arr[i] = x;
        }
        return arr;
    }

    //Insertion sort. Time complexity: O(n^2), space complexity: O(1)
    public static int[] insertionSort(int[] arr){
        for(int i = 1; i < arr.length; i++){
            for(int j = i; j >= 1; j--){
                if(arr[j-1] > arr[j]){
                    int x = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = x;
                }else{
                    break;
                }
            }
        }
        return arr;
    }

    //Bubble sort. Time complexity: O(n^2)
    public static int[] bubbleSort(int[] arr){
        for(int i = 0; i < arr.length - 1; i++){
            for(int j = 0; j < arr.length - 1; j++){
                if(arr[j] > arr[j + 1]){
                    int x = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = x;
                }
            }
        }
        return arr;
    }

    //Merge sort. Time complexity: O(nlogn), Space comlexity: O(n)
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
            if(L[i] <= R[j]){
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

    public static int[] getLSP(String str){
        int[] lsp = new int[str.length()];
        lsp[0] = 0;
        int k = 0;
        for(int i = 1; i < str.length(); i++){
            while(k > 0 && str.charAt(i) != str.charAt(k)){
                k = lsp[k - 1];
            }
            if(str.charAt(i) == str.charAt(k)){
                k++;
            }
            lsp[i] = k;
        }
        return lsp;
    }

    public static void main(String[] args) {
        int[] a = {1,4,2,3,6};
        mergeSort(a,0,4);
        for(int b : a) {
            System.out.print(b);
        }
        System.out.println();
        String b = "abbababbaba";
        int[] c = getLSP(b);
        for(int d : c){
            System.out.print(d);
        }
    }


}
