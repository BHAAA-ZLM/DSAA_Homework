package Week12;

import java.util.ArrayList;
import java.util.Scanner;

public class Week12_C_Brute {
    public static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int n = input.nextInt();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < n; i++){
            list.add(input.nextInt());
        }
        while(list.size() > 1){
            int min = 0;
            for(int i = 1; i < list.size(); i++){
                if(list.get(min) > list.get(i)){
                    min = i;
                }
            }
            int left = list.get(min);
            int right = list.get(min);
            if(min - 1 >= 0) {
                left = list.get(min - 1);
            }
            if(min + 1 < list.size()) {
                right = list.get(min + 1);
            }
            int mergeLeft = (left ^ list.get(min)) + 1;
            int mergeRight = (right ^ list.get(min)) + 1;

        }

    }
}
