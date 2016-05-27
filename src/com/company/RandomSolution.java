package com.company;

import java.util.Random;

public class RandomSolution {

    private Random rnd = new Random();
    private Genome gen;
    private Genome newgen;
    private int[] randomScores;
    // Dit is een constante voor hoeveel random genomen we willen maken, staat nu op 10, want dat duurt niet lang
    private final static int RANDOMTESTSIZE = 1000;


    public RandomSolution(Genome gen) {
        this.gen = gen;
        randomScores = new int[RANDOMTESTSIZE];
    }

    public int[] findSolutions() {
        for(int i = 0; i<RANDOMTESTSIZE; i++) {
            int number_swaps = 0;
            newgen = gen;
            while(!newgen.IsSolution()) {
                newgen = gen;
                while (!newgen.IsSolution() && number_swaps < 1000) {
                    newgen = randomInvert();
                    number_swaps++;
                }
            }
            //System.out.println(number_swaps);
            randomScores[i] = number_swaps;
        }
        return randomScores;
    }

    private Genome randomInvert() {
        int start_invert = rnd.nextInt(24) + 1;
            while(!newgen.forbiddenBefore(start_invert)){
                start_invert = rnd.nextInt(24) + 1;
            }
        int end_invert = rnd.nextInt(25 - start_invert) + start_invert + 1;
            while(!newgen.forbiddenAfter(end_invert)){
                end_invert = rnd.nextInt(25 - start_invert) + start_invert + 1;
            }
        return newgen.invert(start_invert, end_invert);
    }

    public int[] getRandomScores() {
        return randomScores;
    }

}
