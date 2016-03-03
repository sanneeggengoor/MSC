package com.company;

/**
 * Created by Sanne on 18-2-2016.
 */
public class Genome {
    public int[] genome;
    int count;

    public Genome() {
        genome = createGenome();
        count = 0;
    }

    public Genome(Genome genome) {
        genome.count = this.count;
        this.genome = new int[25];
        for(int i = 0; i < 25; i++) {
            this.genome[i] = genome.genome[i];
        }
    }

    private int[] createGenome(){
        int[] genomeCreate = {
                23, 1, 2, 11, 24, 22, 19, 6, 10, 7, 25, 20,
                5, 8, 18, 12, 13, 14, 15, 16, 17,
                21, 3, 4, 9
        };
        return genomeCreate;
    }

    public Genome invert(int a, int b){
        int[] inverseGen= new int[25];
        Genome child = new Genome(this);
        for(int i = a; i<=b; i++){
            int x = b - i + a;
            inverseGen[i-1] = child.genome[x-1];
        }
        for(int i = a; i <= b; i++){
            child.genome[i-1]=inverseGen[i-1];
        }
        return child;
    }

    public boolean compare(Object other){
        String gen1 = other.toString();
        String gen2 = this.toString();
        if (gen1.equals(gen2)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int counter = 0;
        for(int i = 0; i<25;i++){
            counter += genome[i]*(i+1);
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
