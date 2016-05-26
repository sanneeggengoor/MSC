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

        Scanner scanner = new Scanner(System.in);
        System.out.println("Typ 1 voor het toepassen van algoritmes op het gegeven genoom");
        System.out.println("Typ 2 voor het toepassen van algoritmes op een random genoom");
        System.out.print("Geef keuze: ");
        int index2 = scanner.nextInt();

        boolean genomeType = true;
        if (index2 == 2) {
            genomeType = false;
        }

        System.out.println("Typ 1 voor het uitvoeren van het eerste algoritme");
        System.out.println("Typ 2 voor het uitvoeren van Iterative Depth First zonder heuristiek");
        System.out.println("Typ 3 voor het uitvoeren van Iterative Depth First met heuristiek");
        System.out.println("Typ 4 voor het uitvoeren van Astar");
        System.out.println("Typ 5 voor het uitvoeren van het heuristische algoritme");
        System.out.print("Geef keuze: ");
        int index3 = scanner.nextInt();

        Genome gen = new Genome(genomeType);
        if (index3 == 1) {
            SimpleAlgorithm trial = new SimpleAlgorithm(gen);
            trial.findSolution();
        }
        if (index3 == 2) {
            Iterative trial = new Iterative(gen);
            trial.findSolution(false);
        }
        if (index3 == 3) {
            Iterative trial = new Iterative(gen);
            trial.findSolution(true);
        }
        boolean swapType = true;
        if (index3 == 4 || index3 == 5) {
            System.out.println("Typ 1 voor optimaliseren op aantal omkeringen");
            System.out.println("Typ 2 voor optimaliseren op aantal verplaatste allelen");
            System.out.print("Geef keuze: ");
            int index = scanner.nextInt();

            if (index == 2) {
                swapType = false;
            }
        }
        if (index3 == 4) {
            Astar trial = new Astar(gen, swapType);
            trial.findSolution();
        }
        if (index3 == 5) {
            Astar trial = new Astar(gen, swapType);
            trial.findSolution();
        }
        }
        TestAndScore trial = new TestAndScore();
        trial.runTest(swapType);

        System.out.println("Typ 1 voor optimaliseren op aantal omkeringen");
        System.out.println("Typ 2 voor optimaliseren op aantal verplaatste allelen");
        System.out.print("Geef keuze: ");
        int index = scanner.nextInt();

        boolean swapType = true;
        if (index == 2) {
            swapType = false;
        }

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