package com.company;

import java.util.Comparator;

/**
 * Created by Sanne on 18-2-2016.
 */
public class Genome {
    public int[] genome;
    int count;
    int movedGenes;
    double score;
    Genome previous;

    public Genome() {
        genome = createGenome();
        count = 0;
        movedGenes = 0;
        score = 24;
    }

    public Genome(Genome genome) {
        this.count = genome.count;
        this.genome = new int[25];
        for(int i = 0; i < 25; i++) {
            this.genome[i] = genome.genome[i];
        }
        this.previous = genome;
        this.score = aStarscore();
    }

    private int[] createGenome(){

        int[] genomeCreate = {
                23, 1, 2, 11, 24, 22, 19, 6, 10, 7, 25, 20,
                5, 8, 18, 12, 13, 14, 15, 16, 17,
                21, 3, 4, 9
        };

        /*
        int[] genomeCreate = {
                3, 2, 1, 4, 6, 5, 8, 7, 9, 10, 11, 12, 13, 14,
                20, 19, 18, 17, 16, 15, 21, 22, 23, 24, 25
        }; */

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
        //child.previous = this;
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
        counter = counter % 30006863;
        return counter;
    }

    public String toString() {
        String rep = "";
        for (int i = 0; i < genome.length; i++) {
            rep += genome[i] + ", ";
        }
        return rep;
    }

    public boolean forbiddenBefore(int invert){
        int[] row = this.genome;
        int gen = row[invert - 1];
        return !(invert > 1 && (row[invert-2] == gen + 1 | row[invert-2] == gen - 1));
    }

    public boolean forbiddenAfter(int invert){
        int[] row = this.genome;
        int gen = row[invert - 1];
        return !(invert < 24 && (row[invert] == gen + 1 | row[invert] == gen - 1));
    }


    public double aStarscore(){
        double schatting = 0;
        for (int i = 1; i < 25; i++ ){
            if(forbiddenAfter(i)){
                schatting++;
            }
        }
        double score = schatting/2 + this.movedGenes;
        return score;
    }


    public void printPath(){
        if (previous != null) {

            previous.printPath();

            System.out.println("step: " + Integer.toString(this.count) + "score: "+ Double.toString(this.score));
            System.out.println(this);

        }
    }

    public boolean checkSolution() {
        if (IsSolution()) {
            System.out.println("Solution found" + this);

            return true;
        }
        return false;
    }

    public boolean IsSolution() {
        for (int i = 0; i < 25; i++) {
            if (this.genome[i] != i + 1) {
                return false;
            }
        }
        return true;
    }
}
