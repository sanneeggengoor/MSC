package com.company;

/**
 * Created by Sanne on 18-2-2016.
 */
public class Genome {
    public int[] genome;

    public Genome() {
        genome = createGenome();
    }

    private int[] createGenome(){
        int[] genomeCreate = {
                23, 1, 2, 11, 24, 22, 19, 6, 10, 7, 25, 20,
                5, 8, 18, 12, 13, 14, 15, 16, 17,
                21, 3, 4, 9
        };
        return genomeCreate;
    }

    public void invert(int a, int b){
        int[] inverseGen= new int[25];
        for(int i = a; i<=b; i++){
            int x = b - i + a;
            inverseGen[i-1] = genome[x-1];
        }
        for(int i = a; i <= b; i++){
            genome[i-1]=inverseGen[i-1];
        }
    }

    public String toString() {
        String rep = "";
        for (int i = 0; i < genome.length; i++) {
            rep += genome[i] + ", ";
        }
        return rep;
    }
}
