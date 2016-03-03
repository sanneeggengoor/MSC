package com.company;

public class Genome {
    public int[] genome;

    public Genome() {
        genome = createGenome();
        int count = 0;
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

    public boolean equals(Object other){
        for(int i = 0; i < 25; i++){
            if(genome[i]){
                return true;
            }
        }

    }

    public int hashCode() {
        int counter = 0;
        for(int i = 0; i<25;i++){
            counter = counter + genome[i]*(i+1);
        }
        counter = counter % 5000;
        return counter;
    }
    public String toString() {
        String rep = "";
        for (int i = 0; i < genome.length; i++) {
            rep += genome[i] + ", ";
        }
        return rep;
    }
}
