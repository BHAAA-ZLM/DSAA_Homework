package Week3;

public class Week3_B_BubbleSort {
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

    public static void main(String[] args) {
        int[] a = {4,1,5,3,4,5,6,2};
        bubbleSort(a);
        for(int n: a){
            System.out.println(n);
        }
    }
}
