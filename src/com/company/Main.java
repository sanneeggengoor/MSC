package com.company;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Genome gen = new Genome();
        Genome[] testSet = gen.makeTestSet();
        TestAndScore trial =  new TestAndScore();
        trial.printTest();
        RandomSolution trial = new RandomSolution(gen);
        System.out.println(trial.findSolutions());
        //Genome[] testSet = gen.makeTestSet();

        /**
        Stack genomeStack = new Stack();
        Genome gen = new Genome();
        AstarDeep trial = new AstarDeep(gen);
        //Astar trial = new Astar(gen);
        //Iterative trial = new Iterative(gen);
        trial.findSolution();
         */

    }
/**
        genome = createGenome();
        checkFrontAndBack();
        Main main = new Main();
        main.createGenome();
        for(int i = 0; i<25;i++){
            System.out.println(genome[i]);
        }

    private static void checkFrontAndBack() {
        for(int i = 0; i < 12; i++){
            checkFront(i+1);
            checkBack(25-i);
        }
    }

    private static void checkFront(int m) {
        for (int i = 0; i < 25; i++) {
            if (genome[i] == m) {
                Genome.invert(m, i + 1);
                break;
            }
        }
    }

    private static void checkBack(int num){
        for (int i = 0; i<25; i++){
            if(genome[i]==num){
                Genome.invert(i+1,num);
                break;
            }
        }
    }
 */
}