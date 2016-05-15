package com.company;

import java.util.Arrays;

/**
 * Created by Sanne on 11-5-2016.
 */
public class TestAndScore {
    private int[][] results;
    private Genome[] testSet;
    private Genome[] heuristic;
    private RandomSolution[] rSol;
    private int[] scoreHeuristic;
    public static final int NUMBER = 100;

    public TestAndScore(){
        Genome[] testSet;
        Genome[] heuristic;
        RandomSolution[] rSol;
        int[] scoreHeuristic;
        int[][] results;

    }

    /**
     * Dit is de functie die wordt aangeroepen vanuit de main. Hij gaat eerst testen en dan alles uitprinten.
     * Ik denk trouwens, dat nu we die results[][] hebben, dat de scoreHeuristic eruit kan, maar weet ik niet
     * zeker.
     */
    public void printTest(){
        testIt();
        for(int i = 0; i < NUMBER; i++){
            int no = i+1;
            System.out.println("Genome no. "+ no);
            System.out.println(testSet[i]);
            Genome genHeuristic = heuristic[i];
            System.out.println("Heuristic steps needed: " + genHeuristic.countSwaps);
            System.out.println("Crossed distance: "+ genHeuristic.countDistance);
            System.out.println("Heuristic percentile: " + getPercentile(i));

        }
        // tabel printen
        for(int j = 0; j < 3; j++){
            System.out.println("Nummer: " + j);
            for(int k = 0; k<NUMBER; k++){
                System.out.println(results[k][j]);
            }

        }
    }

    /** Deze functie maakt eerst een testset van 100 willekeurige genomen, gaat deze dan heuristisch oplossen.
     * (Zoals jij wil, pas t evt. aan in GenomeComparator). Daarna gaat hij t random oplossen.
     *
     */
    public void testIt(){
        // results is de tabel met resultaten
        results = new int[NUMBER][3];
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
            results[i][0] = finalgen.countSwaps;
            scoreHeuristic[i] = finalgen.countDistance;
            results[i][1] = finalgen.countDistance;
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
        //for(int i = 0; i < 25; i++ ){
            //System.out.print(ranScores[i]+" ");
        //}
        results[index][2]= (int) percentile;
        return 100 - percentile;
    }


}
