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
        this.count = genome.count;
        this.genome = new int[25];
        for(int i = 0; i < 25; i++) {
            this.genome[i] = genome.genome[i];
        }
    }

    private int[] createGenome(){
        /*
        int[] genomeCreate = {
                23, 1, 2, 11, 24, 22, 19, 6, 10, 7, 25, 20,
                5, 8, 18, 12, 13, 14, 15, 16, 17,
                21, 3, 4, 9
        };*/

        int[] genomeCreate = {
                1, 2, 4, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
                20, 19, 18, 16, 17, 15, 21, 22, 23, 24, 25
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

    public boolean equals(Object other) {
        if (other instanceof Genome) {
            Genome othergenome = (Genome) other;
            for (int i = 0; i < othergenome.genome.length; i++) {
                if (othergenome.genome[i] != this.genome[i]) {
                    return false;
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    public int hashCode() {
        int counter = 0;
        for(int i = 0; i<25;i++){
            counter += genome[i]*(10^i);
        }
        counter = counter % 1299827;
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
