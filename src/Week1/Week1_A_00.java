package Week1;

import java.util.Scanner;

public class Week1_A_00 {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int times = input.nextInt();

        for(int i = 0; i < times; i++){
            int a = input.nextInt();
            int b = input.nextInt();
            int c = input.nextInt();

            box(a, b, c);
        }
    }

    public static void box(int a, int b, int c){

        int col_num = a * 2 + b * 2 + 1;
        int row_num = c * 2 + b * 2 + 1;

//        System.out.println(col_num);
//        System.out.println(row_num);

        String[][] output = new String[col_num][row_num];

        //front
        for(int i = 0; i <= 2 * a; i++){
            for(int j = 0; j <= 2 * c; j++) {
                //front
                if (i % 2 == 0 && j % 2 == 0) {
                    output[i][j] = "+";
                } else if (i % 2 == 0 && j % 2 == 1) {
                    output[i][j] = "|";
                } else if (i % 2 == 1 && j % 2 == 0) {
                    output[i][j] = "-";
                } else if (i % 2 == 1 && j % 2 == 1) {
                    output[i][j] = ".";
                }
            }
        }

        //top
        for(int i = 2 * a + 2 * b; i >= 0; i--){
            for(int j = 2 * c + 2 * b; j > 2 * c; j--){
                if(i >= j - 2 * c && i - 2 * a <= j - 2 * c){
                    if(i % 2 == 0){
                        if((j - 2 * c) % 2 == 0){
                            output[i][j] = "+";
                        }else{
                            output[i][j] = ".";
                        }
                    }else{
                        if((j - 2 * c) % 2 == 0){
                            output[i][j] = "-";
                        }else{
                            output[i][j] = "/";
                        }
                    }
                }else if(i - 2 * a < j - 2 * c + 1){
                    output[i][j] =".";
                }
            }
        }

        //side
        for(int i = 2 * a + 2 * b; i > 2 * a; i--){
            for(int j = 2 * c + 2 * b; j >= 0; j--){

                if(j - 2 * c < i - 2 * a) {
                    if (j >= i - 2 * a) {
                        if(j % 2 == 0){
                            if(i % 2 == 0){
                                output[i][j] = "+";
                            }else{
                                output[i][j] = ".";
                            }
                        }else{
                            if(i % 2 == 0){
                                output[i][j] = "|";
                            }else{
                                output[i][j] = "/";
                            }
                        }
                    }else{
                        output[i][j] = ".";
                    }
                }
            }
        }

        for(int i = 0; i < row_num; i++){
            for(int j = 0; j < col_num; j++) {
                System.out.print(output[j][row_num - i - 1]);
            }
            System.out.println();
        }

    }
}


