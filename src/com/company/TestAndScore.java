package com.company;

import java.util.Arrays;

public class TestAndScore {
    private boolean swapType;
    private boolean genomeType;
    private int[][] results;
    private Genome[] testSet;
    private Genome[] heuristic;
    private RandomSolution[] randomSol;
    private int[] scoreHeuristic;
    public static final int NUMBER = 10;

    public TestAndScore(){
        Genome[] testSet;
        Genome[] heuristic;
        RandomSolution[] randomSol;
        int[] scoreHeuristic;
        int[][] results;

    }
    public void runTest(boolean type_swap){
        swapType = type_swap;
        // results is de tabel met resultaten
        results = new int[NUMBER][3];
        scoreHeuristic = new int[NUMBER];

        // de testset wordt uitgevoerd op random genomen met het heuristische
        // algoritme, dus de booleans staan allebei op false
        Genome gen = new Genome(false, false);
        testSet = gen.makeTestSet(NUMBER, swapType);

        runHeuristic(testSet);
        runRandomSol(testSet);

        printTest();
    }

    public void printTest(){
        for(int i = 0; i < NUMBER; i++){
            int no = i+1;
            System.out.println("Genome no. "+ no);
            System.out.println(testSet[i]);
            Genome genHeuristic = heuristic[i];
            System.out.println("Heuristic steps needed: " + genHeuristic.getCountSwaps());
            System.out.println("Crossed distance: "+ genHeuristic.getCountSwaps());
            System.out.println("Heuristic percentile: " + getPercentile(i));

        }
        // tabel printen: S = aantal swaps, MG = moved Genes, PC = percentile
        System.out.println("S \t MG \t PC");
        for (int j = 0; j < NUMBER; j++){
            System.out.println(results[j][0]+"\t"+results[j][1]+"\t"+
            results[j][2]);
        }
    }
    private void runHeuristic(Genome[] testSet) {
        this.heuristic = new Genome[NUMBER];

        for (int i = 0; i < NUMBER; i++) {
            Astar trial = new Astar(testSet[i], swapType);
            trial.findSolution();

            // pak de uiteindelijke oplossing en sla die op
            Genome finalgen = trial.getFinalGen();
            heuristic[i] = finalgen;
            results[i][0] = finalgen.getCountSwaps();
            results[i][1] = finalgen.getcountDistance();
            scoreHeuristic[i] = (int)finalgen.getScore();
            //System.out.println(i);
        }
    }

    private void runRandomSol(Genome[] testSet) {
        randomSol = new RandomSolution[NUMBER];

        for (int i = 0; i < NUMBER; i++){
            RandomSolution trial = new RandomSolution(testSet[i]);
            trial.findSolutions();
            randomSol[i] = trial;
        }
    }

    private double getPercentile(int index){
        int[] ranScores = randomSol[index].getRandomScores();
        int prepercentile = 0;
        Arrays.sort(ranScores);

        // Een loop door de gesorteerde random oplossingen, wanneer er een
        // oplossing is die beter is dan die gevonden door het heuristische
        // algoritme, wordt deze waarde opgeslagen
        for (int i = 0; i < ranScores.length; i++){
            if(ranScores[i]>scoreHeuristic[index] ){
                prepercentile = i;
            }
        }
        prepercentile = ranScores.length - prepercentile;
        double percentile = 100 - (prepercentile/ranScores.length)*100 ;
        results[index][2]= (int) percentile;
        return percentile;
    }

}