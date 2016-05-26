package com.company;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Typ 1 voor het toepassen van algoritmes op het gegeven genoom");
        System.out.println("Typ 2 voor het toepassen van algoritmes op een random genoom");
        System.out.print("Geef keuze: ");
        int index_random = scanner.nextInt();

        System.out.println("Typ 1 voor het uitvoeren van het eerste algoritme");
        System.out.println("Typ 2 voor het uitvoeren van Iterative Depth First zonder heuristiek");
        System.out.println("Typ 3 voor het uitvoeren van Iterative Depth First met heuristiek");
        System.out.println("Typ 4 voor het uitvoeren van Astar");
        System.out.println("Typ 5 voor het uitvoeren van het heuristische algoritme");
        System.out.println("Typ 6 voor het uitvoeren van het heuristische algoritme op een testset van 100 genomen");
        System.out.print("Geef keuze: ");
        int index_algorithm = scanner.nextInt();

        Genome gen;
        if (index_random == 2) {
            if (index_algorithm == 4) {
                gen = new Genome(false, true);
            } else {
                gen = new Genome(false, false);
            }
        } else {
            if (index_algorithm == 4) {
                gen = new Genome(true, true);
            } else {
                gen = new Genome(true, false);
            }
        }

        int index_swap;
        boolean swapType = true;

        if (index_algorithm == 1) {
            SimpleAlgorithm trial = new SimpleAlgorithm(gen);
            trial.findSolution();
        } else if (index_algorithm == 2) {
            Iterative trial = new Iterative(gen);
            trial.findSolution(false);
        } else if (index_algorithm == 3) {
            Iterative trial = new Iterative(gen);
            trial.findSolution(true);
        } else if (index_algorithm == 4 || index_algorithm == 5 || index_algorithm == 6) {
            System.out.println("Typ 1 voor optimaliseren op aantal omkeringen");
            System.out.println("Typ 2 voor optimaliseren op aantal verplaatste allelen");
            System.out.print("Geef keuze: ");
            index_swap = scanner.nextInt();
            if (index_swap == 2) {
                swapType = false;
            }
        }
        if (index_algorithm == 4 || index_algorithm == 5) {
            Astar trial = new Astar(gen, swapType);
            trial.findSolution();
        } else if (index_algorithm == 6) {
            TestAndScore trial = new TestAndScore();
            trial.runTest(swapType);
        }
    }
}