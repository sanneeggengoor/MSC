package com.company;


import java.util.Scanner;

/**
 * Hee even een berichtje! Als je wil optimaliseren naar swaps moet je in the GenomeComparator
 * scoreDistance veranderen in scoreSwaps :) Verder print hij na het runnen een tabelletje uit.
 * Deze is te lezen als: eerste kolom: aantal swaps, tweede kolom: afstand, derde kolom: percentiel.
 *
 */
public class Main {

    public static void main(String[] args) {

        /*
        Genome genome = new Genome();
        Genome[] testSet = genome.makeTestSet(100,true);
        int[][] results = new int[2][100];
        for (int i = 0; i< 100; i++){
            SimpleAlgorithm simp = new SimpleAlgorithm(testSet[i]);
            simp.findSolution();


        }
        */

        Scanner scanner = new Scanner(System.in);
        System.out.println("Typ 1 voor optimaliseren op aantal omkeringen");
        System.out.println("Typ 2 voor optimaliseren op aantal verplaatste allelen");
        System.out.print("Geef keuze: ");
        int index = scanner.nextInt();

        boolean swapType = true;
        if (index == 2) {
            swapType = false;
        }
        TestAndScore trial = new TestAndScore();
        trial.runTest(swapType);
    }


        /*
        Genome gen = new Genome();
        Astar trial = new Astar(gen, true);
        trial.findSolution();

        //Genome[] testSet = gen.makeTestSet(100);
        //TestAndScore trial =  new TestAndScore();
        //trial.printTest();
        //RandomSolution trial = new RandomSolution(gen);
        //System.out.println(trial.findSolutions());

        //Genome[] testSet = gen.makeTestSet();

    }*/
}