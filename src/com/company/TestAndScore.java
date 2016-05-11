package com.company;

import java.util.Arrays;

/**
 * Created by Sanne on 11-5-2016.
 */
public class TestAndScore {
    private Genome[] testSet;
    private Genome[] heuristic;
    private RandomSolution[] rSol;
    private int[] scoreHeuristic;
    public static final int NUMBER = 1;

    public TestAndScore(){
        Genome[] testSet;
        Genome[] heuristic;
        RandomSolution[] rSol;
        int[] scoreHeuristic;

    }

    public void testIt(){
        scoreHeuristic = new int[NUMBER];
        Genome gen = new Genome();
        testSet = gen.makeTestSet(NUMBER);
        makeHeuristic(testSet);
        scoreHeuristic = new int[NUMBER];
        makeRandomSol(testSet);
    }

    private void makeHeuristic(Genome[] testSet) {
        this.heuristic = new Genome[NUMBER];
        for (int i = 0; i < NUMBER; i++) {
            Astar trial = new Astar(testSet[i]);
            trial.findSolution();
            Genome finalgen = trial.getFinalGen();
            heuristic[i] = finalgen;
            scoreHeuristic[i] = finalgen.countSwaps;
            //System.out.println(i);
        }
    }

    private void makeRandomSol(Genome[] testSet) {
        this.rSol = new RandomSolution[NUMBER];
        for (int i = 0; i < NUMBER; i++){
            RandomSolution trial = new RandomSolution(testSet[i]);
            trial.findSolutions();
            rSol[i] = trial;
        }

    }

    private double getPercentile(int index){
        int[] ranScores = rSol[index].randomScores;
        int prepercentile = 0;
        Arrays.sort(ranScores);
        for (int i = 0; i < ranScores.length; i++){
            if(ranScores[i]>scoreHeuristic[index] ){
                prepercentile = i;
            }
        }
        prepercentile = ranScores.length - prepercentile;
        double percentile = (prepercentile/ranScores.length)*100 ;
        System.out.println();
        for(int i = 0; i < 25; i++ ){
            System.out.print(ranScores[i]+" ");
        }
        return percentile;
    }

    public void printTest(){
        testIt();
        for(int i = 0; i < NUMBER; i++){
            int no = i+1;
            System.out.println("Genome no. "+ no);
            System.out.println(testSet[i]);
            Genome genHeuristic = heuristic[i];
            System.out.println("Heuristic steps needed: " + genHeuristic.countSwaps);
            System.out.println("Heuristic percentile: " + getPercentile(i));

        }
    }
}
