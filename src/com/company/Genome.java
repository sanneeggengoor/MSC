package com.company;

import java.util.Random;


/**
 * Created by Sanne on 18-2-2016.
 */
public class Genome {
    public int[] genome;
    int countSwaps;
    int countDistance;
    int movedGenes;
    double scoreSwap;
    double scoreDistance;
    Genome previous;

    private Random rgen = new Random();


    public Genome() {
        genome = createRandomGenome();
        countSwaps = 0;
        countDistance = 0;
        movedGenes = 0;
        scoreSwap = 24;
        scoreDistance = 0;
    }

    public Genome(Genome genome) {
        this.countSwaps = genome.countSwaps;
        this.countDistance = genome.countDistance;
        this.genome = new int[25];
        System.arraycopy(genome.genome, 0, this.genome, 0, 25);
        this.previous = genome;
        this.scoreSwap = genome.scoreSwap;
        this.scoreDistance = genome.scoreDistance;
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

    public Genome[] makeTestSet(int number) {
        Genome[] testSet = new Genome[number];
        for (int i = 0; i<number; i++){
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
        int distance = calculateDistance(a,b);
        child.countSwaps = this.countSwaps + 1;
        child.countDistance = this.countDistance + distance;
        child.movedGenes = this.movedGenes + Math.abs(a - b);
        child.scoreSwap = child.aStarscoreSwaps();
        child.scoreDistance = child.aStarscoreDistance();

        return child;
    }

    private int calculateDistance(int low,int high){
        int distance = high - low;
        int finaldistance = 0;
        for(int i = 0; i < distance/2; i++){
            finaldistance = finaldistance + 1 + i;
        }
        return finaldistance;
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


    public double aStarscoreSwaps(){
        double schatting = 0;
        for (int i = 1; i < 25; i++ ){
            if(forbiddenAfter(i)){
                schatting++;
            }
        }
        schatting = schatting/1;
        return schatting + this.countSwaps;
       //return schatting + this.movedGenes;
    }

    public double aStarscoreDistance(){
        double schatting = 0;
        int[] gen = this.genome;
        for(int i = 0; i<25; i++){
            int verschil = gen[i] - i + 1;
            if (verschil < 0) {
                verschil = verschil *-1;
            }
            schatting = schatting+verschil;
        }
        return schatting*20;
    }


    public void printPath(){
        if (previous != null) {

            previous.printPath();

            System.out.println("step: " + Integer.toString(this.countSwaps) + "score in swaps: "+
                    Double.toString(this.scoreSwap) + "score in distance: " +Double.toString(this.scoreDistance));
            System.out.println(this);

        }
    }

    public boolean checkSolution() {
        if (IsSolution()) {
            //System.out.println("Solution found" + this);

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
