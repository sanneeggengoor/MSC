package com.company;

/**
 * Created by Sanne on 11-5-2016.
 */
public class TestAndScore {
    private Genome[] testSet;
    private Genome[] heuristic;
    private RandomSolution[] rSol;

    public TestAndScore(){
        Genome[] testSet;
        Genome[] heuristic;
        RandomSolution[] rSol;

    }

    public void testIt(){
        Genome gen = new Genome();
        testSet = gen.makeTestSet();
        makeHeuristic(testSet);
    }

    private void makeHeuristic(Genome[] testSet) {
        this.heuristic = new Genome[100];
        for (int i = 0; i < 100; i++) {
            Astar trial = new Astar(testSet[i]);
            trial.findSolution();
            Genome finalgen = trial.getFinalGen();
            heuristic[i] = finalgen;
            System.out.println(i);
        }
    }

    public void printTest(){
        testIt();
        for(int i = 0; i < 100; i++){
            int no = i+1;
            System.out.println("Genome no. "+ no);
            System.out.println(testSet[i]);
            Genome genHeuristic = heuristic[i];
            int scoreHeuristic = genHeuristic.count;
            System.out.println("Heuristic steps needed: " + scoreHeuristic);
        }
    }
}
