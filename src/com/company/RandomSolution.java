package com.company;

/**
 * Created by Maaike on 11-5-2016.
 */
import java.util.Random;

public class RandomSolution {

    private Random rnd = new Random();
    Genome gen;
    Genome newgen;
    boolean solutionFound;
    int number_swaps;
    int[] randomScores;

    public RandomSolution(Genome gen) {
        this.gen = gen;
        randomScores = new int[100];
    }

    public int[] findSolutions() {
        for(int i = 0; i<100; i++) {
            number_swaps = 0;
            newgen = gen;
            while (!newgen.IsSolution() && number_swaps < 1000000) {
                newgen = randomInvert();
                number_swaps++;
            }
            System.out.println(number_swaps);
            randomScores[i] = number_swaps;
        }
        return randomScores;
    }

    private Genome randomInvert() {
        int start_invert = rnd.nextInt(24) + 1;
        int end_invert = rnd.nextInt(25 - start_invert) + start_invert + 1;
        return newgen.invert(start_invert, end_invert);
    }

}
