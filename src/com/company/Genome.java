package com.company;

import java.util.Random;

public class Genome {
    public int[] genome;
    private int countSwaps;
    private int countDistance;
    private double scoreSwap;
    private double scoreDistance;
    private Genome previous;
    private boolean swapType;
    private boolean genomeType;
    private boolean scoreType;

    private static Random rgen = new Random();

    public Genome(boolean type_genome, boolean type_astar) {
        genomeType = type_genome;
        scoreType = type_astar;
        if(genomeType) {
            genome = createGenome();
        } else {
            genome = createRandomGenome();
        }
        countSwaps = 0;
        countDistance = 0;
        scoreSwap = 24;
        scoreDistance = 0;
    }

    // waarom wordt dit nooit gebruikt? Hebben we het dan wel nodig?
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

    public Genome[] makeTestSet(int number, boolean type) {
        swapType = type;
        Genome[] testSet = new Genome[number];
        rgen.setSeed(2);

        for(int j = 0; j<33; j++){
            for(int k = 0; k<25; k++){
                int hoi = rgen.nextInt();
            }
        }
        for (int i = 0; i<number; i++){
            testSet[i] = new Genome(genomeType, scoreType);
        }
        return testSet;
    }

    public Genome invert(int a, int b){
        int[] inverseGen = new int[25];
        Genome child = new Genome(this.genomeType, this.scoreType);
        for(int i = a; i<=b; i++){
            int x = b - i + a;
            inverseGen[i-1] = child.genome[x-1];
        }
        System.arraycopy(inverseGen, a - 1, child.genome, a - 1, b + 1 - a);
        child.previous = this;
        child.countSwaps = this.countSwaps + 1;
        child.countDistance = this.countDistance + Math.abs(a - b) + 1;
        child.scoreSwap = child.aStarscoreSwaps(scoreType);
        child.scoreDistance = child.aStarscoreDistance(scoreType);

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
        return false;
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

    public double aStarscoreSwaps(boolean astar){
        double estimate = 0;
        for (int i = 1; i < 25; i++ ){
            if(forbiddenAfter(i)){
                estimate++;
            }
        }
        if (astar) {
            return estimate/2 + this.countSwaps;
        }
        return estimate/1.5 + this.countSwaps;
    }

    public double aStarscoreDistance(boolean astar){
        double estimate = 0;
        int[] gen = this.genome;
        for (int i = 1; i < 25; i++ ){
            if(forbiddenAfter(i)){
                estimate++;
            }
        }
        if (astar) {
            return estimate + this.countDistance;
        }
        return estimate*10 + this.countDistance;
    }

    public void printPath(){
        if (previous != null) {

            previous.printPath();

            System.out.println("step: " + Integer.toString(this.countSwaps) + "score in swaps: "+
                    Double.toString(this.scoreSwap) + "score in distance: " +Double.toString(this.scoreDistance));
            System.out.println(this);

        }
    }

    public void changeType(boolean type_swap){
        swapType = type_swap;
    }

    public boolean IsSolution() {
        for (int i = 0; i < 25; i++) {
            if (this.genome[i] != i + 1) {
                return false;
            }
        }
        return true;
    }


    public int getCountSwaps() {
        return countSwaps;
    }

    public double getScore(){
        if (swapType){
            return scoreSwap;
        } else {
            return scoreDistance;
        }
    }

    public int getcountDistance() {
        return countDistance;
    }
}
