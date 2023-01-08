package Week5;

public class Test {
    public static void main(String[] args) {
        int[] a = new int[10];
        for(int i = 0; i < 10; i++){
            System.out.print(a[i] + " ");
        }
        System.out.println();
        changeElement(a);
        for(int i = 0; i < 10; i++){
            System.out.print(a[i] + " ");
        }
    }

    public static void changeElement(int[] a){
        a = new int[a.length + 1];
        for(int i = 0; i < a.length; i++){
            a[i] = i;
        }
    }

}
