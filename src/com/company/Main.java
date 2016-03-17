package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Stack genomeStack = new Stack();
        Genome gen = new Genome();
        Genome other = new Genome();
        Genome gen2 = other.invert(4,8);
        Genome gen3 = gen2.invert(4,8);

        System.out.print(gen.hashCode());
        System.out.println(gen3.hashCode());
        Iterative trial = new Iterative(gen);
        trial.findSolution();

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