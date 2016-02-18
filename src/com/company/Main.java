package com.company;

public class Main {

    private static int[] genome;
    public static void main(String[] args) {
        genome = createGenome();
        inverse(4,8);
        for(int i = 0; i<25; i++){
            System.out.println(genome[i]);
        }

    }

    private static int[] createGenome(){
        int[] genome = {
                23, 1, 2, 11, 24, 22, 19, 6, 10, 7, 25, 20,
                5, 8, 18, 12, 13, 14, 15, 16, 17,
                21, 3, 4, 9
        };
        return genome;
    }

    private static void invert(int a, int b){
        int[] inverseGen= new int[25];
        for(int i = a; i<=b; i++){

        }
        System.out.println
        for(int i = 0; i < 25; i++){
            if(a<i && i<b){
                genome[i]=inverseGen[i];
            }
        }
    }
}
