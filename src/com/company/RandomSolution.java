package com.company;

/**
 * Created by Maaike on 11-5-2016.
 */
import java.util.Random;

public class RandomSolution {

    private Random rnd = new Random();
    Genome gen;
    boolean solutionFound;

    public RandomSolution(Genome gen) {
        this.gen = gen;
        solutionFound = false;
    }

    public void findSolution() {
        while(!solutionFound) {
            randomInvert();
            if
        }
    }

    private void randomInvert() {
        int start_invert = rnd.nextInt(25);
        int end_invert = rnd.nextInt(25 - start_invert) + start_invert + 1;
        gen = gen.invert(start_invert,end_invert);


    }

}
