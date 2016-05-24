package com.company;

/**
 * Met deze klasse kan er voor één genoom een bepaald aantal random oplossingen gezocht worden.
 */
import java.util.Random;

public class RandomSolution {

    private Random rnd = new Random();
    Genome gen;
    Genome newgen;
    int[] randomScores;
    private int TESTSIZE = 10;


    public RandomSolution(Genome gen) {
        this.gen = gen;
        randomScores = new int[TESTSIZE];
    }

    /** Deze methode wordt aangeroepen om TESTSIZE aantal random genomen te maken.
     * Eerst wordt
     * @return
     */
    public int[] findSolutions() {
        for(int i = 0; i<TESTSIZE; i++) {
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

}
