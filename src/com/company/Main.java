package com.company;


/**
 * Hee even een berichtje! Als je wil optimaliseren naar swaps moet je in the GenomeComparator
 * scoreDistance veranderen in scoreSwaps :) Verder print hij na het runnen een tabelletje uit.
 * Deze is te lezen als: eerste kolom: aantal swaps, tweede kolom: afstand, derde kolom: percentiel.
 *
 */
public class Main {

    public static void main(String[] args) {
        boolean swapType = true;
        Genome gen = new Genome();
        TestAndScore trial = new TestAndScore();
        trial.printTest(swapType);


        //Genome[] testSet = gen.makeTestSet(100);
        //TestAndScore trial =  new TestAndScore();
        //trial.printTest();
        //RandomSolution trial = new RandomSolution(gen);
        //System.out.println(trial.findSolutions());

        //Genome[] testSet = gen.makeTestSet();

    }
}