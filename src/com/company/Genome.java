package com.company;

import java.util.Random;


/**
 * Created by Sanne on 18-2-2016.
 */
public class Genome {
    public int[] genome;
    int count;
    int movedGenes;
    double score;
    Genome previous;

    private Random rgen = new Random();


    public Genome() {
        genome = createRandomGenome();
        count = 0;
        movedGenes = 0;
        score = 24;
    }

    public Genome(Genome genome) {
        this.count = genome.count;
        this.genome = new int[25];
        System.arraycopy(genome.genome, 0, this.genome, 0, 25);
        this.previous = genome;
        this.score = genome.score;
    }

    private int[] createGenome(){

        /*
        int[] genomeCreate = {
                3, 2, 1, 4, 6, 5, 8, 7, 9, 10, 11, 12, 13, 14,
                20, 19, 18, 17, 16, 15, 21, 22, 23, 24, 25
        }; */

        return new int[]{
                23, 1, 2, 11, 24, 22, 19, 6, 10, 7, 25, 20,
                5, 8, 18, 12, 13, 14, 15, 16, 17,
                21, 3, 4, 9
        };
    }

    private int[] createRandomGenome(){
        int[] rGenome = new int[25];
        for(int i = 1; i<26; i++){
            int random = rgen.nextInt(25);
            while (rGenome[random] != 0) {
                random++;
                random = random % 25;
            }
            rGenome[random] = i;

        }
        return rGenome;
    }

    public Genome[] makeTestSet() {
        Genome[] testSet = new Genome[100];
        for (int i = 0; i<100; i++){
            testSet[i] = new Genome();
        }
        return testSet;
    }

    public Genome invert(int a, int b){
        int[] inverseGen= new int[25];
        Genome child = new Genome(this);
        for(int i = a; i<=b; i++){
            int x = b - i + a;
            inverseGen[i-1] = child.genome[x-1];
        }
        System.arraycopy(inverseGen, a - 1, child.genome, a - 1, b + 1 - a);
        //child.previous = this;
        child.count = this.count + 1;
        child.movedGenes = this.movedGenes + Math.abs(a - b);
        child.score = child.aStarscore();
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
        for (int aGenome : genome) {
            rep += aGenome + ", ";
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
        return schatting + this.movedGenes;
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
