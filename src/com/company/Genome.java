package com.company;

import java.util.Random;

public class Genome {
    public int[] genome;
    private int countSwaps;
    private int countDistance;
    private int movedGenes;
    private double scoreSwap;
    private double scoreDistance;
    private Genome previous;

    private static Random rgen = new Random();

    public Genome() {
        genome = createGenome();
        //genome = createRandomGenome();
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
        // Ik doe wel setSeed() maar toch komen er elke keer andere genomen uit? Hoe kan dat??, als dit wel lukt
        // hebben we namelijk gepaarde data, en dan kunnen we geloof ik makkelijker een test doen.
        rgen.setSeed(2);
        for (int i = 0; i<number; i++){
            testSet[i] = new Genome();
        }
        return testSet;
    }

    public Genome invert(int a, int b){
        int[] inverseGen = new int[25];
        Genome child = new Genome(this);
        for(int i = a; i<=b; i++){
            int x = b - i + a;
            inverseGen[i-1] = child.genome[x-1];
        }
        System.arraycopy(inverseGen, a - 1, child.genome, a - 1, b + 1 - a);
        child.previous = this;
        int distance = calculateDistance(a,b);
        child.countSwaps = this.countSwaps + 1;
        child.countDistance = this.countDistance + distance;
        child.movedGenes = this.movedGenes + Math.abs(a - b);
        child.scoreSwap = child.aStarscoreSwaps();
        child.scoreDistance = child.aStarscoreDistance();

        return child;
    }

    // This function calculates the distance that is added to the total distance when inverting
    // from low to high (Kan mooier nog hoor, maar hij werkt iig)
    // WAAROM???? Je kan gewoon tellen hoeveel genen je swapt
    private int calculateDistance(int low,int high){
        int distance = high - low;
        int dis = high - low;
        int finaldistance = distance;
        while(distance >1){
            distance = distance - 2;
            finaldistance += distance;
        }
        if(distance ==1){
           finaldistance++;
        }
        while(distance < dis){
            distance = distance + 2;
            finaldistance += distance;
            //System.out.println("Loop? "+ high + low);
        }
        //System.out.println("Geen loop in dit");
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


    public double aStarscoreSwaps(){
        double estimate = 0;
        for (int i = 1; i < 25; i++ ){
            if(forbiddenAfter(i)){
                estimate++;
            }
        }
        return estimate/2 + this.countSwaps;
    }

    public double aStarscoreDistance(){
        double estimate = 0;
        int[] gen = this.genome;
        for (int i = 1; i < 25; i++ ){
            if(forbiddenAfter(i)){
                estimate++;
            }
        }
        return estimate + this.countDistance;
    }

    /**
     * Deze functie geeft in ieder geval een schatting terug die werkt. Hij berekent eerst het aantal swaps dat
     * minimaal nog gedaan moet worden (schatting/2) en doet dat keer de afstand van een gemiddelde swap (lengte 12,5)
     * dus ik doe maar van 1 tot 14 (=lengte 13). En dan voegt hij de afstand die nu al is afgelegd er aantoe.
     *
     */
    public double ScoreDistance(){
        double estimate = 0;
        int[] gen = this.genome;
        for (int i = 1; i < 25; i++ ){
            if(forbiddenAfter(i)){
                estimate++;
            }
        }
        int distance = calculateDistance(1,14);
        return distance*estimate/2 + this.countDistance;
    }

    public void printPath(){
        if (previous != null) {

            previous.printPath();

            System.out.println("step: " + Integer.toString(this.countSwaps) + "score in swaps: "+
                    Double.toString(this.scoreSwap) + "score in distance: " +Double.toString(this.scoreDistance));
            System.out.println(this);

        }
    }

    public boolean IsSolution() {
        for (int i = 0; i < 25; i++) {
            if (this.genome[i] != i + 1) {
                return false;
            }
        }
        //System.out.println("Solution found" + this);
        return true;
    }

    public int getCountSwaps() {
        return countSwaps;
    }

    public double getscoreSwap() {
        return scoreSwap;
    }

    public int getcountDistance() {
        return countDistance;
    }

    public double getscoreDistance() {
        return scoreDistance;
    }
}
