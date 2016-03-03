package com.company;

public class Main {

    private static int[] genome;

    public static void main(String[] args) {
        Stacks.makeStack();
        Genome g = new Genome();
        System.out.println(g);
        g.invert(1,7);
        System.out.println(g);
        Genome g2 = new Genome();
        System.out.println(g);
        System.out.println(g2);



        genome = createGenome();
        checkFrontAndBack();
        Main main = new Main();
        main.createGenome();
        for(int i = 0; i<25;i++){
            System.out.println(genome[i]);
        }
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

    private static void checkFront(int m) {
        for (int i = 0; i < 25; i++) {
            if (genome[i] == m) {
                invert(m, i + 1);
                break;
            }
        }
    }

    private static void checkBack(int num){
        for (int i = 0; i<25; i++){
            if(genome[i]==num){
                invert(i+1,num);
                break;
            }
        }
    }
}