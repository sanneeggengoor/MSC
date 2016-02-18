package com.company;

public class Main {

    private static int[] genome;
    public static void main(String[] args) {
        genome = createGenome();
        invert(4,8);
    }

    private static int[] createGenome(){
        int[] genomeCreate = {
                23, 1, 2, 11, 24, 22, 19, 6, 10, 7, 25, 20,
                5, 8, 18, 12, 13, 14, 15, 16, 17,
                21, 3, 4, 9
        };
        return genomeCreate;
    }

    private static void invert(int a, int b){
        int[] inverseGen= new int[25];
        for(int i = a; i<=b; i++){
            int x = b - i + a;
            inverseGen[i-1] = genome[x-1];
        }
        for(int i = a; i <= b; i++){
            genome[i-1]=inverseGen[i-1];
        }
    }

    private static void checkFrontAndBack() {
        for(int i = 0; i < 12; i++){
            checkFront(i+1);
            checkBack(25-i);
        }

    }
}
